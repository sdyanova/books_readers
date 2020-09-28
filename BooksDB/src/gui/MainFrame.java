//THE CONTROLLER
package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.prefs.Preferences;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import controller.Controller;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = -2530553255357890124L;
	private Toolbar toolbar;
	private FormPanel formPanel;
	private JFileChooser fileChooser;
	private Controller controller;
	private TablePanel tablePanel;
	private PrefsDialog prefsDialog;
	private Preferences prefs;
	private JSplitPane splitPane;
	private JTabbedPane tabPane;
	private BookPanel bookPanel;
	
	public MainFrame() {
		//setting title of app(mainframe)
		super("Book Publishing Market Overview");
		
		//setting look&feel theme
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
		    System.out.println("can't set look&feel theme");
		} 
		

		setLayout(new BorderLayout());

		toolbar = new Toolbar();
		formPanel = new FormPanel();
		tablePanel = new TablePanel();
		prefsDialog = new PrefsDialog(this);
		tabPane = new JTabbedPane();
		bookPanel = new BookPanel(this);
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, formPanel, tabPane ); //the formPanel on the left and tablePanel on the right
		
		//expandable icons for the form/table panel
		splitPane.setOneTouchExpandable(true);
		
		//addinf tabs
		tabPane.addTab("Readers", tablePanel);
		tabPane.addTab("Book Types", bookPanel);
		
		
		prefs = Preferences.userRoot().node("db");
		
		//receiving info of readers
		controller = new Controller();

		tablePanel.setData(controller.getReaders());
		//deleting a row trough ReaderTableListener interface
		tablePanel.setReaderTableListener(new ReaderTableListener() {
			public void rowDeleted(int row) {
				controller.removeReader(row);
			}
		});
		
		//listener for books in tabPane
		tabPane.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				int tabIndex = tabPane.getSelectedIndex();

				if(tabIndex==1) {
					bookPanel.refresh();
				}
			}
		});
		
		prefsDialog.setPrefsListener(new PrefsListener() {
			@Override //so the info can be stored
			public void preferencesSet(String user, String password, int port) {
				prefs.put("user", user);  //key & value
				prefs.put("password", password);
				prefs.putInt("port", port);
				
				try {
					controller.configure(port, user, password);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(MainFrame.this, "Unable to reconnect");
				}
			}		
		});
		
		//sort of port in order to connect through different accounts
		String user = prefs.get("user", "user1");
		String password = prefs.get("pasword", "Mjppass1983!");
		Integer port = prefs.getInt("port", 3306);
		prefsDialog.setDefaults(user, password, port);
		
		fileChooser = new JFileChooser();
		// if we wish to use multiple fileFilters, we need to add them here
		// we alse can add custom panels for our file choosers - see the swing doc
		fileChooser.addChoosableFileFilter(new ReaderFileFilter());

		setJMenuBar(createMenuBar());

		// making connection between components: the toolbar & textPanel - save
		// through ToolbarListener interface
		toolbar.setToolbarListener(new ToolbarListener() {

			@Override
			public void saveEventOccured() {
				connect();
				
				try {
					controller.save();
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(MainFrame.this, "Can't save changes to database:( ", "Database connection problem", JOptionPane.ERROR_MESSAGE);
				}
			}

			@Override
			public void refreshEventOccured() {
				connect();
				
				try {
					controller.load();
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(MainFrame.this, "Can't load data from database:( ", "Database connection problem", JOptionPane.ERROR_MESSAGE);
				}
				tablePanel.refresh();
			}
		});

		// FormListener  interface-raising the event
		formPanel.setFormListener(new FormListener() {
			public void formEventOccurred(FormEvent e) {
				controller.addReader(e); // pass the event above to the controller
				tablePanel.refresh();
			}
		});
		
		addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e) {
				controller.disconnect();
				dispose();
				
				//run the garbage collector before quitting
				System.gc();
			}
			
		});

		//add(formPanel, BorderLayout.WEST);
		add(toolbar, BorderLayout.PAGE_START);
		add(splitPane, BorderLayout.CENTER);

		setMinimumSize(new Dimension(1000, 500));
		setSize(600, 500);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setVisible(true);
	}
	
	private void connect() {
		try {
			controller.connect();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(MainFrame.this, "Can't connect to database:( ", "Database connection problem", JOptionPane.ERROR_MESSAGE);
		}
	}

	//set up MenuBar
	private JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		
		//fileMenu
		JMenu fileMenu = new JMenu("File");		
		JMenuItem exportDataItem = new JMenuItem("Export Data...");
		JMenuItem importDataItem = new JMenuItem("Import Data...");
		JMenuItem exitItem = new JMenuItem("Exit");
		
		fileMenu.add(exportDataItem);
		fileMenu.add(importDataItem);
		fileMenu.addSeparator();
		fileMenu.add(exitItem);
		
		//windowMenu
		JMenu windowMenu = new JMenu("Window");
		JMenu showMenu = new JMenu("Show");
		JMenuItem prefsItem = new JMenuItem("Preferences...");
		
		JCheckBoxMenuItem showFormItem = new JCheckBoxMenuItem("Reader Form"); 
		showFormItem.setSelected(true);
		
		showMenu.add(showFormItem);
		windowMenu.add(showMenu);
		windowMenu.add(prefsItem);
		
		menuBar.add(fileMenu);
		menuBar.add(windowMenu);
		
		
		//setting up listeners to prefsDialog & showFormItem
		prefsItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				prefsDialog.setVisible(true);
			}
			
		});
		
		showFormItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ev) {
				JCheckBoxMenuItem menuItem =(JCheckBoxMenuItem)ev.getSource();
				//set position of the divider
				if(menuItem.isSelected()) {
					splitPane.setDividerLocation((int)formPanel.getMinimumSize().getWidth());
				}
				formPanel.setVisible(menuItem.isSelected());
			}		
		});
		
		//mnemonics
		fileMenu.setMnemonic(KeyEvent.VK_F);
		exitItem.setMnemonic(KeyEvent.VK_X);
		
		//accelerators
		prefsItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,
				ActionEvent.CTRL_MASK));
		
		exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
				ActionEvent.CTRL_MASK));
		
		//importing data
		importDataItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK));
		
		importDataItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(fileChooser.showOpenDialog(MainFrame.this)==JFileChooser.APPROVE_OPTION) {
					try {
						controller.loadFromFile(fileChooser.getSelectedFile());
						tablePanel.refresh();
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(MainFrame.this, 
								"Could not load data from file.", "Error", 
								JOptionPane.ERROR_MESSAGE);
					}
				}		
			}
		});
		
		 exportDataItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(fileChooser.showSaveDialog(MainFrame.this)==JFileChooser.APPROVE_OPTION) {
					try {
						controller.saveToFile(fileChooser.getSelectedFile());
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(MainFrame.this, 
								"Could not save data to file.", "Error", 
								JOptionPane.ERROR_MESSAGE);
					}
				}			
			}		
		 });
		
		exitItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int action = JOptionPane.showConfirmDialog(MainFrame.this, 
					"Do you really want to exit the app?", 
					"Confirm Exit", JOptionPane.OK_CANCEL_OPTION);
				
				if (action ==JOptionPane.OK_OPTION) {
					
					//returning an array of window listeners
					WindowListener[] listeners =  getWindowListeners();
					
					for (WindowListener listener: listeners) {
						//announcing window event
						listener.windowClosing(new WindowEvent(MainFrame.this, 0));
					}
				}		
			}
		});
		
		return menuBar;
	}
}
