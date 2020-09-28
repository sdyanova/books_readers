package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JToolBar;

public class Toolbar extends JToolBar implements ActionListener{
	private JButton btnSave;
	private JButton btnRefresh;
	
	private ToolbarListener textListener;
	
	public Toolbar() {
		setBorder(BorderFactory.createEtchedBorder());	
		
		btnSave = new JButton();
		btnSave.setIcon(Utils.createIcon("/pics/save.gif"));
		btnSave.setToolTipText("Save");
		
		btnRefresh = new JButton();
		btnRefresh.setIcon(Utils.createIcon("/pics/refresh.gif"));
		btnRefresh.setToolTipText("Refresh");
		
		btnSave.addActionListener(this);
		btnRefresh.addActionListener(this);
		
		add(btnSave);
		add(btnRefresh);
	}
	
	//setting listener to Toolbar
	public void setToolbarListener(ToolbarListener listener) {
			this.textListener=listener;	
	}
	
	//checking which button has been clicked
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton clicked = (JButton) e.getSource();
		
		if (clicked == btnSave) {
			if(textListener!=null) {
				textListener.saveEventOccured();
			}
		}else  if (clicked == btnRefresh){
			if(textListener!=null) {
				textListener.refreshEventOccured();
			}
		}	
	}
}
