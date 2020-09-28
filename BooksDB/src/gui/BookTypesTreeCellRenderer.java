package gui;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JCheckBox;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellRenderer;

public class BookTypesTreeCellRenderer implements TreeCellRenderer {
	//for rendering the leafes
	private JCheckBox leafRenderer;
	//for rendering the non leaf nodes
	private DefaultTreeCellRenderer nonLeafRenderer;
	
	private Color textForeground;
	private Color textBackground;
	private Color selectionForeground;
	private Color selectionBackground;
	
	public BookTypesTreeCellRenderer() {
		leafRenderer = new JCheckBox();
		nonLeafRenderer = new DefaultTreeCellRenderer();
				
		nonLeafRenderer.setLeafIcon(Utils.createIcon("/pics/download.gif"));
		nonLeafRenderer.setOpenIcon(Utils.createIcon("/pics/download.gif"));
		nonLeafRenderer.setClosedIcon(Utils.createIcon("/pics/download.gif"));
		
		//returns color object
		textForeground = UIManager.getColor("Tree.textForeground");
		textBackground = UIManager.getColor("Tree.textBackground");
		selectionForeground = UIManager.getColor("Tree.selectionForeground");
		selectionBackground = UIManager.getColor("Tree.selectionBackground");
	}

	@Override
	public Component getTreeCellRendererComponent(JTree tree,
            Object value,
            boolean selected,
            boolean expanded,
            boolean leaf,
            int row,
            boolean hasFocus) {
		
		//if leaf is checked 
		if(leaf) {
			//cast is needed to get user object from DefaultMutableTreeNode
			DefaultMutableTreeNode node = (DefaultMutableTreeNode)value;
			TypeInfo nodeInfo = (TypeInfo)node.getUserObject();
			
				if(selected) {
					leafRenderer.setForeground(selectionForeground);
					leafRenderer.setBackground(selectionBackground);
				}
				else {
					leafRenderer.setForeground(textForeground);
					leafRenderer.setBackground(textBackground);
				}
			
			//returning the text of each leaf and ckecking if checked
			leafRenderer.setText(nodeInfo.toString());
			leafRenderer.setSelected(nodeInfo.isChecked());
			return leafRenderer;
		}
		else {
			return nonLeafRenderer.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
		}
	}
}
