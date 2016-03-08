package clueTests;
import clueGame.Board;

import clueGame.BadConfigFormatException;
import clueGame.BoardCell;
import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

public class Part1Tests {

	private static Board board;
	@Before
	public void setUp() {
		board = new Board("BoardConfig.csv", "legend.txt");
		board.initialize();
	}
	
	@Test
	public void testRoomsNumber() {
		System.out.println("Test room number");
		HashMap<Character, String> rooms = Board.getRooms();
		System.out.println(rooms.size());
		assertEquals(rooms.size(), 11);
	}
	
	@Test
	public void testBoardDimensions() {
		assertTrue(board.getNumColumns() * board.getNumRows() > 0);
		assertTrue(board.getNumColumns() * board.getNumRows() <= Board.BOARD_SIZE * Board.BOARD_SIZE);
		
	}
	
	@Test
	public void testDoorDirections() {
		int temp = 0;
		//System.out.println("numrows = " + board.getNumRows() + " numcols = " + board.getNumColumns());
		for (int i = 0; i < board.getNumRows(); i++) {
			for (int j = 0; j < board.getNumColumns(); j++) {
				BoardCell test = board.getBoardCell(i,j);
				if (test == null)
					System.out.println("NULL!!!!!");
				System.out.println("i : " + i + " j : " + j);
				if (test.isDoorway()){
					//System.out.println("Doorway i : " + i + " j : " + j);
					temp++;
				}
			}
		}
		assertTrue(board.numDoors > 0);
		assertEquals(board.numDoors, temp);
	}
	
	@Test
	public void testRoomInitial() {
		System.out.println("Test room initial");
		assertTrue(board.getNumRows() > 0);
		for (int i = 0; i < board.getNumRows(); i++) {
			for (int j = 0; j < board.getNumColumns(); j++) {
				if (Board.getRooms().containsKey(board.getBoardCell(i,j).getInitial())){
					continue;
				}
				else fail();
			}
		}
	}
	
	@Test(expected = FileNotFoundException.class)
	public void ZtestFNFException() throws IOException, BadConfigFormatException, FileNotFoundException {
		System.out.println("FNF Test");
		String badBoardConfigFile = "garbage";
		String badRoomConfigFile = "also_garbage";
		Board newBoard = new Board(badBoardConfigFile, badRoomConfigFile);
		newBoard.loadRoomConfig();
		newBoard.loadBoardConfig();

	}
	
	@Test(expected = clueGame.BadConfigFormatException.class)
	public void ZtestBadConfigException1() throws IOException, BadConfigFormatException, FileNotFoundException {
		String badBoardConfigFile = "ClueLayoutBadColumns.csv";
		String badRoomConfigFile = "ClueLegend.txt";
		Board newBoard = new Board(badBoardConfigFile, badRoomConfigFile);
		newBoard.loadRoomConfig();
		newBoard.loadBoardConfig();
	}
	
	@Test(expected = clueGame.BadConfigFormatException.class)
	public void ZtestBadConfigException2() throws IOException, BadConfigFormatException, FileNotFoundException {
		String badBoardConfigFile = "ClueLayoutBadRoom.csv";
		String badRoomConfigFile = "ClueLegend.txt";
		Board newBoard = new Board(badBoardConfigFile, badRoomConfigFile);
		newBoard.loadRoomConfig();
		newBoard.loadBoardConfig();
	}

	@Test(expected = clueGame.BadConfigFormatException.class)
	public void ZtestBadConfigException3() throws IOException, BadConfigFormatException, FileNotFoundException {
		System.out.println("Bad Legend Test");
		Board newBoard = new Board("ClueLayout.csv", "ClueLegendBadFormat.txt");
		newBoard.loadRoomConfig();
		newBoard.loadBoardConfig();
	}
	
}
