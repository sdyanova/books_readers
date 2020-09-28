package gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class FormPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	private JLabel nameLbl;
	private JLabel occupationLbl;
	private JTextField nameField;
	private JCheckBox occupationCheck;
	private JTextField occupationField;
	private JButton okBtn;
	private FormListener formListener;
	private JList ageList;
	private JComboBox eduCombo;
	private JCheckBox citizenCheck;
	private JTextField pinField;
	private JLabel pinLabel;
	
	private JRadioButton maleRadio;
	private JRadioButton femaleRadio;
	private ButtonGroup genderGroup;
	
	public FormPanel() {
		//setting size 
		Dimension dim = getPreferredSize();
		dim.width=250;
		setPreferredSize(dim);
		setMinimumSize(dim);
		
		//creating components
		nameLbl = new JLabel("Name: ");
		occupationLbl = new JLabel("Occupation: ");
		nameField = new JTextField(10);
		occupationCheck = new JCheckBox();
		occupationField = new JTextField(10);
		ageList = new JList();
		eduCombo = new JComboBox();
		citizenCheck = new JCheckBox();
		pinField = new JTextField(10);
		pinLabel = new JLabel("PIN: ");
		okBtn = new JButton("OK");
				
		//Set up mnemonics
		okBtn.setMnemonic(KeyEvent.VK_O);
		
		nameLbl.setDisplayedMnemonic(KeyEvent.VK_N);
		nameLbl.setLabelFor(nameField);
		
		//set up gender
		maleRadio = new JRadioButton("male");
		femaleRadio = new JRadioButton("female");
		maleRadio.setActionCommand("male");
		femaleRadio.setActionCommand("female");
		genderGroup = new ButtonGroup();
		
		maleRadio.setSelected(true);
		
		//adding gender radio buttons
		genderGroup.add(maleRadio);
		genderGroup.add(femaleRadio);
		
		//Set up occupationCheck
		occupationLbl.setEnabled(false);
		occupationField.setEnabled(false);
		
		//connecting the checkbox with textfield
		occupationCheck.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				boolean isTicked = occupationCheck.isSelected();
				occupationLbl.setEnabled(isTicked);
				occupationField.setEnabled(isTicked);	
			}
		});
		
		
		//Set up pin ID
		pinLabel.setEnabled(false);
		pinField.setEnabled(false);
		
		citizenCheck.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				boolean isTicked = citizenCheck.isSelected();
				pinLabel.setEnabled(isTicked);
				pinField.setEnabled(isTicked);
				
			}
		});
		
		//Set up a list box for ageCat
		DefaultListModel ageModel = new DefaultListModel(); 
		ageModel.addElement(new AgeCategory(0, "Under 18"));
		ageModel.addElement(new AgeCategory(1, "18 to 65"));
		ageModel.addElement(new AgeCategory(2, "65 or over"));
		ageList.setModel(ageModel); ///setting the above mode to ageList
		
		ageList.setPreferredSize(new Dimension(110, 70));
		ageList.setBorder(BorderFactory.createEtchedBorder());
		ageList.setSelectedIndex(1);
		
		//Set up a combo box for eduCat
		DefaultComboBoxModel eduModel = new DefaultComboBoxModel(); 
		eduModel.addElement("preschool");
		eduModel.addElement("primary_school");
		eduModel.addElement("secondary_school");
		eduModel.addElement("bachelor_degree");
		eduModel.addElement("master_degree");
		eduModel.addElement("doctoral_degree");
		eduCombo.setModel(eduModel);
		eduCombo.setSelectedIndex(0);
		eduCombo.setEditable(true);
		
		//adding listener to okBtn
		okBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String name = nameField.getText();
				String occupation = occupationField.getText();
				AgeCategory ageCat = (AgeCategory) ageList.getSelectedValue(); // getting the ageCategory object from the list
				String eduLevel = (String)eduCombo.getSelectedItem();
				String pinId = pinField.getText();
				boolean euCitizen = citizenCheck.isSelected();
				
				String gender = genderGroup.getSelection().getActionCommand();
								
				FormEvent ev = new FormEvent(this, name, occupation, 
						ageCat.getId(), eduLevel, pinId, euCitizen,gender);
			
				if(formListener!=null) {
					formListener.formEventOccurred(ev); //calling the formeventoccured method in the mainframe
				}
			}	
		});
		
		//setting borders
		Border innerBorder = BorderFactory.createTitledBorder("New reader");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

		layoutComponents();

	}
	
	//arranging components in the formPanel
	public void layoutComponents() {
		
		setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();
		
		//First row
		
		gc.gridy = 0;
		
		//how much space it takes relative to other cells
		//the important is how this number compares to the weight of the other cells
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;//upper right
		gc.insets = new Insets(0, 0, 0, 5);
		add(nameLbl, gc);
		
		gc.gridx = 1;
		gc.gridy = 0;
		gc.insets = new Insets(0,0,0,0);
		gc.anchor = GridBagConstraints.LINE_START;
		add(nameField, gc);
		
//		//Next row
		
		gc.gridy++;
						
		gc.weightx = 1;
		gc.weighty = 0.1;//not to take all the rest of the space
			
		gc.gridx = 0;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.LINE_END;
		add(new JLabel("Employed: "), gc);
				
		gc.gridx = 1;
		
		gc.insets = new Insets(0,0,0,5);
		gc.anchor = GridBagConstraints.LINE_START;
		add(occupationCheck,gc);	
		
		//next row
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridx = 0;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.LINE_END;
		add(occupationLbl, gc);
		
		gc.gridx = 1;
		gc.gridy = 2;
		gc.insets = new Insets(0,0,0,0);
		gc.anchor = GridBagConstraints.LINE_START;
		add(occupationField, gc);
		
		//Next row
		
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.2;//not to take all the rest of the space
			
		gc.gridx = 0;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		add(new JLabel("Age: "), gc);		
		
		gc.gridx = 1;
		gc.insets = new Insets(0,0,0,0);
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(ageList,gc);
		
		//Next row
		
		gc.gridy++;
				
		gc.weightx = 1;
		gc.weighty = 0.2;//not to take all the rest of the space
			
		gc.gridx = 0;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		add(new JLabel("Education: "), gc);
		
		gc.gridx = 1;
		gc.insets = new Insets(0,0,0,0);
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(eduCombo,gc);
		
		//Next row
		
		gc.gridy++;
						
		gc.weightx = 1;
		gc.weighty = 0.2;//not to take all the rest of the space
			
		gc.gridx = 0;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		add(new JLabel("EU citizen: "), gc);
				
		gc.gridx = 1;
		gc.insets = new Insets(0,0,0,0);
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(citizenCheck,gc);		
		
		//Next row
		
		gc.gridy++;
						
		gc.weightx = 1;
		gc.weighty = 0.2;//not to take all the rest of the space
			
		gc.gridx = 0;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		add(pinLabel, gc);
				
		gc.gridx = 1;
		gc.insets = new Insets(0,0,0,0);
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(pinField,gc);		

		//Next row
		
		gc.gridy++;
						
		gc.weightx = 1;
		gc.weighty = 0.05;//not to take all the rest of the space
			
		gc.gridx = 0;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.LINE_END;
		add(new JLabel("Gender: "), gc);
				
		gc.gridx = 1;
		gc.insets = new Insets(0,0,0,0);
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(maleRadio,gc);		
		
		//Next row
		
		gc.gridy++;
						
		gc.weightx = 1;
		gc.weighty = 0.7;//not to take all the rest of the space
				
		gc.gridx = 1;
		gc.insets = new Insets(0,0,0,0);
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(femaleRadio,gc);				
		
		//Next row
		
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 2;
		
		gc.gridx = 1;
		gc.insets = new Insets(0,0,0,0);
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(okBtn,gc);
	}
	
	
	//setting formListener 
	public void setFormListener(FormListener listener) {
		this.formListener = listener;
	}
}

//ceating AgeCategory class
class AgeCategory {
	private int id;
	private String text;
	
	public AgeCategory (int id, String text) {
		this.id = id;
		this.text=text;
	}
	@Override
	public String toString() {
		return text;
	}
	
	public int getId() {
		return id;
	}
}
