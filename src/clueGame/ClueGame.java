package clueGame;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class ClueGame extends JFrame {
	private static Board board;
	String boardConfigFile = "ClueLayout.csv";
	String roomConfigFile = "ClueLegend.txt";
	String playerConfigFile = "Players.txt";
	String weaponConfigFile = "Weapons.txt";
	
	public ClueGame() {
		setSize(989, 946);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		board = new Board(boardConfigFile, roomConfigFile, playerConfigFile, weaponConfigFile);
		board.initialize();
		add(board, BorderLayout.CENTER);
		setTitle("Clue Board");
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		menuBar.add(createFileMenu());
		board.dealCards();
	}
	
	private JMenu createFileMenu() {
		JMenu menu = new JMenu("File");
		menu.add(createNotesItem());
		menu.add(createFileExitItem());
		return menu;
	}

	private JMenuItem createFileExitItem() {
		JMenuItem item = new JMenuItem("Exit");
		class MenuItemListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		}
		item.addActionListener(new MenuItemListener());
		return item;
	}


	private JMenuItem createNotesItem() {
		JMenuItem item = new JMenuItem("Detective Notes");
		class MenuItemListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				JFrame notes = new JFrame();
				notes.add(new DetectiveNotes(), BorderLayout.CENTER);
				notes.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				notes.setTitle("Detective Notes");
				notes.setSize(500, 500);
				notes.setVisible(true);
			}
		}
		item.addActionListener(new MenuItemListener());
		return item;
	}


public static void main(String[] args) {

		ClueGame game = new ClueGame();
		game.setVisible(true);
		
//		BoardCell cell = board.getCellAt(4, 4);
//		//System.out.println(cell.toString());
//		if(cell.isDoorway())
//			System.out.println("is doorway");
//		else 
//			System.out.println("is NOT doorway");

	}
	
}
