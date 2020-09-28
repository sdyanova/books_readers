package gui;

import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.util.EventObject;

import javax.swing.AbstractCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeCellEditor;
import javax.swing.tree.TreePath;


public class BookTypesTreeCellEditor extends AbstractCellEditor implements TreeCellEditor {

	private static final long serialVersionUID = 1L;
	private BookTypesTreeCellRenderer renderer;
	private JCheckBox checkBox;
	private TypeInfo info;
	
	public BookTypesTreeCellEditor() {
		renderer=new BookTypesTreeCellRenderer();
	}
	
	@Override //returns a component that allows us to edit the node
	public Component getTreeCellEditorComponent(JTree tree, Object value, boolean isSelected, boolean expanded,
			boolean leaf, int row) {
		//if leaf is checked returns a checkbox/leafrenderer
		Component component = renderer.getTreeCellRendererComponent(tree, value, isSelected, expanded, leaf, row, true);
		
		
		//if true, it will get getTreeCellEditorComponent and the leaf will be editable
		if(leaf) {
			
			//cast the value from constructor above
			DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode)value;
			//pass the value of the object
			info = (TypeInfo)treeNode.getUserObject();
			
			checkBox = (JCheckBox)component;
			
			//anonimous class
			ItemListener itemListener = new ItemListener() {
				
				@Override//check if item is ticked
				public void itemStateChanged(ItemEvent arg0) {
					//once stopped, it will say stop using the editor and use the renderer again
					fireEditingStopped();
					checkBox.removeItemListener(this);
				}
			};		
			checkBox.addItemListener(itemListener);	
		}
		
		// TODO Auto-generated method stub
		return component;
	}
	
	@Override //gets the user object value for the tree node - will be called after the fire editor stop
	public Object getCellEditorValue() {
		info.setChecked(checkBox.isSelected());
		return info;
	}

	@Override // the first method that will be callled to check if cell is editable
	public boolean isCellEditable(EventObject event) {
		
		if(!(event instanceof MouseEvent)) 	{
			return false;
		}
		
		MouseEvent mouseEvent = (MouseEvent) event;
		JTree tree = (JTree) event.getSource();
		
		//coordinates passed by the mouseEvent
		TreePath path = tree.getPathForLocation(mouseEvent.getX(), mouseEvent.getY());
		
		if(path==null) {
			return false;
		}
		
		Object lastComponent = path.getLastPathComponent();
		
		if(lastComponent == null) {
			return false;
		}
		DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode)lastComponent;
		
		//if yes,can be edtited
		return treeNode.isLeaf();
	}

}
