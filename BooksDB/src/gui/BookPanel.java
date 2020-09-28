package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ExecutionException;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.SwingWorker;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

import controller.BookServer;
import model.Book;


public class BookPanel extends JPanel  implements ProgressDialogListener{
	private static final long serialVersionUID = 1L;
	
	private JTree typesTree;
	private BookTypesTreeCellRenderer treeCellRenderer;
	private BookTypesTreeCellEditor treeCellEditor;
	private ProgressDialog progressDialog;
	
	private Set<Integer> selectedTypes;
	private BookServer bookServer;
	private SwingWorker<List<Book>, Integer> worker; //An abstract class to perform lengthy GUI-interaction tasks in abackground thread. 
	
	private TextPanel textPanel;
	private JList bookList;
	private JSplitPane upperPane;
	private JSplitPane lowerPane;
	private DefaultListModel bookListModel;
	
	
	public BookPanel (JFrame parent){
		
		bookListModel = new DefaultListModel<Book>();
		progressDialog = new ProgressDialog(parent,"Downloading titles from selected book type ...");
		bookServer = new BookServer();
		
		progressDialog.setListener(this);
		
		//adding selected items by default
		selectedTypes = new TreeSet<Integer>();

		selectedTypes.add(50);
		
		treeCellRenderer = new BookTypesTreeCellRenderer();
		treeCellEditor = new BookTypesTreeCellEditor();
	
		typesTree = new JTree(createTree());
		typesTree.setCellRenderer(treeCellRenderer);
		typesTree.setCellEditor(treeCellEditor);
		//making tree editable
		typesTree.setEditable(true);
		
		
		//select 1 node at a time
		typesTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		
		bookServer.setSelectedTypes(selectedTypes);
		
		//looking for the editor to start/stop the fireEditor
		treeCellEditor.addCellEditorListener(new CellEditorListener() {
			@Override
			public void editingStopped(ChangeEvent e) {	
				TypeInfo info = (TypeInfo) treeCellEditor.getCellEditorValue();
					
				int typeId = info.getId();
				
				if(info.isChecked()) {
					selectedTypes.add(typeId);
				}
				else {
					selectedTypes.remove(typeId);
				}
				
				bookServer.setSelectedTypes(selectedTypes);
				
				retrieveBooks();
			}
						
			@Override
			public void editingCanceled(ChangeEvent e) {	
			}
		});
		
		setLayout(new BorderLayout());
		
		textPanel = new TextPanel();
		bookList = new JList(bookListModel);
		bookList.setCellRenderer(new BookListRenderer());
		
		//responding to book list selections
		bookList.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				
				//returning first selected book object
				Book book = (Book) bookList.getSelectedValue();
				
				textPanel.setText("Author: "+book.getAuthor()+"; \n"+ "Excerpt: "+book.getExcerpt());
			}
		});
		
		//booksPanes
		//upperPane contains the lower one
		lowerPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,new JScrollPane(bookList) , textPanel);
		lowerPane.setBorder(BorderFactory.createTitledBorder("Select a title"));
		upperPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, new JScrollPane(typesTree), lowerPane);
		
		textPanel.setMinimumSize(new Dimension(10, 100));
		bookList.setMinimumSize(new Dimension(10, 100));
		
		//equaly devides lower & upperPane when maximized
		upperPane.setResizeWeight(0.5);
		
		lowerPane.setResizeWeight(0.5);
		
		add(upperPane, BorderLayout.CENTER);
	}
	
	//calling retrieveBooks
	public void refresh() {
		retrieveBooks();
	}
	
	private void retrieveBooks(){
		
		progressDialog.setMaximum(bookServer.getBookCount());
		progressDialog.setVisible(true);	

		
		
		//whenever the thread is done, we will get the info
		 worker = new SwingWorker<List<Book>, Integer>(){

			
			@Override
			protected void done() {  //called when the thread finishes
				
				progressDialog.setVisible(false);
				
				if(isCancelled()) return;
				try {
					List<Book> retrievedBooks= get();
					
					//clears all elelments from the list
					bookListModel.removeAllElements();
					
					//setting items in the list
					for(Book book: retrievedBooks) {
						bookListModel.addElement(book);
					}
					
				//	bookList.setSelectedIndex(0);
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}		
			}
			
			
			@Override //updates gui
			protected void process(List<Integer> counts) {
				int retrieved = counts.get(counts.size()-1);
				
				progressDialog.setValue(retrieved);
			}
			
			@Override //so the code can run in a separate thread
			protected List<Book> doInBackground() throws Exception {
				
				List<Book> retrievedBooks = new ArrayList<Book>();
				
				int count=0;
				
				for(Book book: bookServer) {
					
					if(isCancelled()) break;
					System.out.println(book.getTitle());
					retrievedBooks.add(book);
					
					count++;
					
					//process is going to receive regularly whatever type is published(the second argument) - in this case - Integer
					publish(count);
				}
				return retrievedBooks;
			}
		};
			worker.execute();	
	}

	private DefaultMutableTreeNode createTree() {
		//creating tree 
		DefaultMutableTreeNode top = new DefaultMutableTreeNode("Book Preferences");
		
		//adding objects
		DefaultMutableTreeNode branch1 = new DefaultMutableTreeNode("Fiction");
		
		//selectedTypes returns true only if type is selected
		DefaultMutableTreeNode server1 = new DefaultMutableTreeNode(new TypeInfo("Action and adventure", 0, selectedTypes.contains(0)));
		DefaultMutableTreeNode server2 = new DefaultMutableTreeNode(new TypeInfo("Alternate history", 1, selectedTypes.contains(1)));
		DefaultMutableTreeNode server3 = new DefaultMutableTreeNode(new TypeInfo("Anthology", 2, selectedTypes.contains(2)));
		DefaultMutableTreeNode server4 = new DefaultMutableTreeNode(new TypeInfo("Chick lit", 3, selectedTypes.contains(3)));
		DefaultMutableTreeNode server5 = new DefaultMutableTreeNode(new TypeInfo("Children's",4, selectedTypes.contains(4)));
		DefaultMutableTreeNode server6 = new DefaultMutableTreeNode(new TypeInfo("Classic", 5, selectedTypes.contains(5)));
		DefaultMutableTreeNode server7 = new DefaultMutableTreeNode(new TypeInfo("Comic book", 6, selectedTypes.contains(6)));
		DefaultMutableTreeNode server8 = new DefaultMutableTreeNode(new TypeInfo("Coming-of-age", 7, selectedTypes.contains(7)));
		DefaultMutableTreeNode server9 = new DefaultMutableTreeNode(new TypeInfo("Crime", 8, selectedTypes.contains(8)));
		DefaultMutableTreeNode server10 = new DefaultMutableTreeNode(new TypeInfo("Drama", 9, selectedTypes.contains(9)));
		DefaultMutableTreeNode server11 = new DefaultMutableTreeNode(new TypeInfo("Fairytale", 10, selectedTypes.contains(10)));
		DefaultMutableTreeNode server12 = new DefaultMutableTreeNode(new TypeInfo("Fantasy", 11, selectedTypes.contains(11)));
		DefaultMutableTreeNode server13 = new DefaultMutableTreeNode(new TypeInfo("Graphic novel", 12, selectedTypes.contains(12)));
		DefaultMutableTreeNode server14 = new DefaultMutableTreeNode(new TypeInfo("Historical fiction", 13, selectedTypes.contains(13)));
		DefaultMutableTreeNode server15 = new DefaultMutableTreeNode(new TypeInfo("Horror", 14, selectedTypes.contains(14)));
		DefaultMutableTreeNode server16 = new DefaultMutableTreeNode(new TypeInfo("Mystery", 15, selectedTypes.contains(15)));
		DefaultMutableTreeNode server17 = new DefaultMutableTreeNode(new TypeInfo("Paranormal romance", 16, selectedTypes.contains(16)));
		DefaultMutableTreeNode server18 = new DefaultMutableTreeNode(new TypeInfo("Picture book", 17, selectedTypes.contains(17)));
		DefaultMutableTreeNode server19 = new DefaultMutableTreeNode(new TypeInfo("Poetry", 18, selectedTypes.contains(18)));
		DefaultMutableTreeNode server20 = new DefaultMutableTreeNode(new TypeInfo("Political thriller",19, selectedTypes.contains(19)));
		DefaultMutableTreeNode server21 = new DefaultMutableTreeNode(new TypeInfo("Romance", 20, selectedTypes.contains(20)));
		DefaultMutableTreeNode server22 = new DefaultMutableTreeNode(new TypeInfo("Satire", 21, selectedTypes.contains(21)));
		DefaultMutableTreeNode server23 = new DefaultMutableTreeNode(new TypeInfo("Science fiction", 22, selectedTypes.contains(22)));
		DefaultMutableTreeNode server24 = new DefaultMutableTreeNode(new TypeInfo("Short story", 23, selectedTypes.contains(23)));
		DefaultMutableTreeNode server25 = new DefaultMutableTreeNode(new TypeInfo("Suspense", 24, selectedTypes.contains(24)));
		DefaultMutableTreeNode server26 = new DefaultMutableTreeNode(new TypeInfo("Thriller", 25, selectedTypes.contains(25)));
		DefaultMutableTreeNode server27 = new DefaultMutableTreeNode(new TypeInfo("Western", 26, selectedTypes.contains(26)));
		DefaultMutableTreeNode server28 = new DefaultMutableTreeNode(new TypeInfo("Young adult",27, selectedTypes.contains(27)));
		
		//adding leafes
		branch1.add(server1);
		branch1.add(server2);
		branch1.add(server3);
		branch1.add(server4);
		branch1.add(server5);
		branch1.add(server6);
		branch1.add(server7);
		branch1.add(server8);
		branch1.add(server9);
		branch1.add(server10);
		branch1.add(server11);
		branch1.add(server12);
		branch1.add(server13);
		branch1.add(server14);
		branch1.add(server15);
		branch1.add(server16);
		branch1.add(server17);
		branch1.add(server18);
		branch1.add(server19);
		branch1.add(server20);
		branch1.add(server21);
		branch1.add(server22);
		branch1.add(server23);
		branch1.add(server24);
		branch1.add(server25);
		branch1.add(server26);
		branch1.add(server27);
		branch1.add(server28);
		
		DefaultMutableTreeNode branch2 = new DefaultMutableTreeNode("NonFiction");
		
		DefaultMutableTreeNode server50 = new DefaultMutableTreeNode(new TypeInfo("Art/architecture",50, selectedTypes.contains(50)));
		DefaultMutableTreeNode server51 = new DefaultMutableTreeNode(new TypeInfo("Autobiography", 51, selectedTypes.contains(51)));
		DefaultMutableTreeNode server52 = new DefaultMutableTreeNode(new TypeInfo("Biography", 52, selectedTypes.contains(52)));
		DefaultMutableTreeNode server53 = new DefaultMutableTreeNode(new TypeInfo("Business/economics", 53, selectedTypes.contains(53)));
		DefaultMutableTreeNode server54 = new DefaultMutableTreeNode(new TypeInfo("Crafts/hobbies", 54, selectedTypes.contains(54)));
		DefaultMutableTreeNode server55 = new DefaultMutableTreeNode(new TypeInfo("Cookbook", 55, selectedTypes.contains(55)));
		DefaultMutableTreeNode server56 = new DefaultMutableTreeNode(new TypeInfo("Diary", 56, selectedTypes.contains(56)));
		DefaultMutableTreeNode server57 = new DefaultMutableTreeNode(new TypeInfo("Dictionary", 57, selectedTypes.contains(57)));
		DefaultMutableTreeNode server58 = new DefaultMutableTreeNode(new TypeInfo("Encyclopedia",58, selectedTypes.contains(58)));
		DefaultMutableTreeNode server59 = new DefaultMutableTreeNode(new TypeInfo("Guide", 59, selectedTypes.contains(59)));
		DefaultMutableTreeNode server60 = new DefaultMutableTreeNode(new TypeInfo("Health/fitness",60, selectedTypes.contains(60)));
		DefaultMutableTreeNode server61 = new DefaultMutableTreeNode(new TypeInfo("History", 61, selectedTypes.contains(61)));
		DefaultMutableTreeNode server62 = new DefaultMutableTreeNode(new TypeInfo("Humor", 62, selectedTypes.contains(62)));
		DefaultMutableTreeNode server63 = new DefaultMutableTreeNode(new TypeInfo("Journal", 63, selectedTypes.contains(63)));
		DefaultMutableTreeNode server64 = new DefaultMutableTreeNode(new TypeInfo("Math", 64, selectedTypes.contains(64)));
		DefaultMutableTreeNode server65 = new DefaultMutableTreeNode(new TypeInfo("Memoir", 65, selectedTypes.contains(65)));
		DefaultMutableTreeNode server66 = new DefaultMutableTreeNode(new TypeInfo("Philosophy", 66, selectedTypes.contains(66)));
		DefaultMutableTreeNode server67 = new DefaultMutableTreeNode(new TypeInfo("Prayer", 67, selectedTypes.contains(67)));
		DefaultMutableTreeNode server68 = new DefaultMutableTreeNode(new TypeInfo("Religion, spirituality, and new age", 68, selectedTypes.contains(68)));
		DefaultMutableTreeNode server69 = new DefaultMutableTreeNode(new TypeInfo("Textbook", 69, selectedTypes.contains(69)));
		DefaultMutableTreeNode server70 = new DefaultMutableTreeNode(new TypeInfo("True crime", 70, selectedTypes.contains(70)));
		DefaultMutableTreeNode server71 = new DefaultMutableTreeNode(new TypeInfo("Review", 71, selectedTypes.contains(71)));
		DefaultMutableTreeNode server72 = new DefaultMutableTreeNode(new TypeInfo("Science", 72, selectedTypes.contains(72)));
		DefaultMutableTreeNode server73 = new DefaultMutableTreeNode(new TypeInfo("Self help", 73, selectedTypes.contains(73)));
		DefaultMutableTreeNode server74 = new DefaultMutableTreeNode(new TypeInfo("Sports and leisure", 74, selectedTypes.contains(74)));
		DefaultMutableTreeNode server75 = new DefaultMutableTreeNode(new TypeInfo("Travel", 75, selectedTypes.contains(75)));
		DefaultMutableTreeNode server76 = new DefaultMutableTreeNode(new TypeInfo("True crime", 76, selectedTypes.contains(76)));
		
		branch2.add(server50);
		branch2.add(server51);
		branch2.add(server52);
		branch2.add(server53);
		branch2.add(server54);
		branch2.add(server55);
		branch2.add(server56);
		branch2.add(server57);
		branch2.add(server58);
		branch2.add(server59);
		branch2.add(server60);
		branch2.add(server61);
		branch2.add(server62);
		branch2.add(server63);
		branch2.add(server64);
		branch2.add(server65);
		branch2.add(server66);
		branch2.add(server67);
		branch2.add(server68);
		branch2.add(server69);
		branch2.add(server70);
		branch2.add(server71);
		branch2.add(server72);
		branch2.add(server73);
		branch2.add(server74);
		branch2.add(server75);
		branch2.add(server76);
		
		top.add(branch1);
		top.add(branch2);
		
		return top;
		}

	@Override
	public void progressDialogCancelled() {
		if(worker!=null) {
			worker.cancel(true);
		};	
	}
}