package clueGame;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class ControlPanelGUI extends JPanel {
	
	private JTextField guess, result;
	private String name, roll;
	private JTextArea display;
	
	public ControlPanelGUI() {
		
		JPanel panel = createCurrentPlayerPanel();
		add(panel, BorderLayout.NORTH);		
		panel = createRollPanel();
		add(panel, BorderLayout.WEST);	
		panel = createGuessPanel();
		add(panel, BorderLayout.CENTER);
		panel = createResultPanel();
		add(panel, BorderLayout.EAST);
		panel = createButtonPanel();
		add(panel, BorderLayout.SOUTH);		
	}
	
	private JPanel createCurrentPlayerPanel() {
		JPanel panel = new JPanel();
		panel.setLayout( new GridLayout(2, 1));
		name = "CURRENT_PLAYER_NAME";
		display = new JTextArea(1, 15);
		display.setText(name);
		panel.add(display);
		panel.setBorder(new TitledBorder(new EtchedBorder(), "Current Player"));				
		return panel;
	}	
	
	private JPanel createRollPanel() {
		JPanel panel = new JPanel();
		roll = "NUMBER_ROLLED";
		display = new JTextArea(1, 10);
		display.setText(roll);
		panel.add(display);
		panel.setBorder(new TitledBorder(new EtchedBorder(), "Number Rolled"));
		return panel;
	}
	
	private JPanel createResultPanel() {
		JPanel panel = new JPanel();
		result = new JTextField(10);
		panel.add(result);
		panel.setBorder(new TitledBorder(new EtchedBorder(), "Guess Result"));
		return panel;
	}
	
	private JPanel createGuessPanel() {
		JPanel panel = new JPanel();
		guess = new JTextField(50);
		panel.add(guess);
		panel.setBorder(new TitledBorder(new EtchedBorder(), "Guess"));
		return panel;
	}
	
	private JPanel createButtonPanel() {
		JButton doneWithTurn = new JButton("Done With Turn");
		JButton makeAccusation = new JButton("Make An Accusation");
		JPanel panel = new JPanel();
		panel.add(doneWithTurn);
		panel.add(makeAccusation);
		return panel;
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(750, 250);
		ControlPanelGUI gui = new ControlPanelGUI();
		frame.add(gui, BorderLayout.CENTER);
		frame.setVisible(true);
	}

}
