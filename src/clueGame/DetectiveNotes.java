package clueGame;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class DetectiveNotes extends JPanel {
	
	private JCheckBox Person1, Person2, Person3, Person4, Person5, Person6;
	private JCheckBox Room1, Room2, Room3, Room4, Room5, Room6, Room7, Room8, Room9;
	private JCheckBox Weapon1, Weapon2, Weapon3, Weapon4, Weapon5, Weapon6;
	
	public DetectiveNotes(){
		setLayout(new GridLayout(3,2));
		JPanel panel = createPeoplePanel();
		add(panel);
		panel = createPersonGuessPanel();
		add(panel);
		panel = createRoomPanel();
		add(panel);
		panel = createRoomGuessPanel();
		add(panel);
		panel = createWeaponPanel();
		add(panel);
		panel = createWeaponGuessPanel();
		add(panel);
	}
	
	private JPanel createPeoplePanel(){
		JPanel newPanel = new JPanel();
		newPanel.setLayout(new GridLayout(3,2));
		newPanel.setBorder(new TitledBorder(new EtchedBorder(), "Players"));
		Person1 = new JCheckBox("Mr. Robot");
		newPanel.add(Person1);
		Person2 = new JCheckBox("General Ketchup");
		newPanel.add(Person2);
		Person3 = new JCheckBox("Ms. Ostrich");
		newPanel.add(Person3);
		Person4 = new JCheckBox("John Cena");
		newPanel.add(Person4);
		Person5 = new JCheckBox("Bruce Banner");
		newPanel.add(Person5);
		Person6 = new JCheckBox("Gilbert Grape");
		newPanel.add(Person6);
		return newPanel;
	}
	
	private JPanel createPersonGuessPanel(){
		JPanel panel = new JPanel();
		JComboBox<String> dropDownList = new JComboBox<String>();
		dropDownList.addItem("Mr. Robot");
		dropDownList.addItem("General Ketchup");
		dropDownList.addItem("Ms. Ostrich");
		dropDownList.addItem("John Cena");
		dropDownList.addItem("Bruce Banner");
		dropDownList.addItem("Gilbert Grape");
		panel.add(dropDownList);
		panel.setBorder(new TitledBorder(new EtchedBorder(), "Player Guess:"));
		return panel;
	}
	
	private JPanel createRoomPanel(){
		JPanel newPanel = new JPanel();
		newPanel.setLayout(new GridLayout(0,2));
		newPanel.setBorder(new TitledBorder(new EtchedBorder(), "Rooms"));
		Room1 = new JCheckBox("Conservatory");
		newPanel.add(Room1);
		Room2 = new JCheckBox("Kitchen");
		newPanel.add(Room2);
		Room3 = new JCheckBox("Ballroom");
		newPanel.add(Room3);
		Room4 = new JCheckBox("Billiard Room");
		newPanel.add(Room4);
		Room5 = new JCheckBox("Library");
		newPanel.add(Room5);
		Room6 = new JCheckBox("Study");
		newPanel.add(Room6);
		Room7 = new JCheckBox("Dining Room");
		newPanel.add(Room7);
		Room8 = new JCheckBox("Lounge");
		newPanel.add(Room8);
		Room9 = new JCheckBox("Hall");
		newPanel.add(Room9);
		return newPanel;
	}

	private JPanel createRoomGuessPanel(){
		JPanel panel = new JPanel();
		JComboBox<String> dropDownList = new JComboBox<String>();
		dropDownList.addItem("Conservatory");
		dropDownList.addItem("Kitchen");
		dropDownList.addItem("Ballroom");
		dropDownList.addItem("Billiard Room");
		dropDownList.addItem("Library");
		dropDownList.addItem("Study");
		dropDownList.addItem("Dining Room");
		dropDownList.addItem("Lounge");
		dropDownList.addItem("Hall");
		panel.add(dropDownList);
		panel.setBorder(new TitledBorder(new EtchedBorder(), "Room Guess:"));
		return panel;
	}
	
	private JPanel createWeaponPanel(){
		JPanel newPanel = new JPanel();
		newPanel.setLayout(new GridLayout(0,2));
		newPanel.setBorder(new TitledBorder(new EtchedBorder(), "Weapons"));
		Weapon1 = new JCheckBox("Grenade Launcher");
		newPanel.add(Weapon1);
		Weapon2 = new JCheckBox("Machete");
		newPanel.add(Weapon2);
		Weapon3 = new JCheckBox("Guillotine");
		newPanel.add(Weapon3);
		Weapon4 = new JCheckBox("Piano Wire");
		newPanel.add(Weapon4);
		Weapon5 = new JCheckBox("Candlestick");
		newPanel.add(Weapon5);
		Weapon6 = new JCheckBox("Excalibur");
		newPanel.add(Weapon6);
		return newPanel;
	}
	
	private JPanel createWeaponGuessPanel(){
		JPanel panel = new JPanel();
		JComboBox<String> dropDownList = new JComboBox<String>();
		dropDownList.addItem("Grenade Launcher");
		dropDownList.addItem("Machete");
		dropDownList.addItem("Guillotine");
		dropDownList.addItem("Piano Wire");
		dropDownList.addItem("Candlestick");
		dropDownList.addItem("Excalibur");
		panel.add(dropDownList);
		panel.setBorder(new TitledBorder(new EtchedBorder(), "Weapon Guess:"));
		return panel;
	}
	
	public static void main(String[] args) {
		DetectiveNotes thisNote = new DetectiveNotes();
		JFrame newFrame = new JFrame();
		newFrame.add(thisNote, BorderLayout.CENTER);
		newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		newFrame.setTitle("Detective Notes");
		newFrame.setSize(500, 500);
		newFrame.setVisible(true);
	}
	
}
