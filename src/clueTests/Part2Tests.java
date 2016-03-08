package clueTests;
import clueGame.Board;

import clueGame.BoardCell;
import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

public class Part2Tests {
	// followed her approach to the tests
	
	
	// set up
	private static Board board;
	@BeforeClass
	public static void setUp() {
		board = new Board("BoardConfig.csv", "legend.txt");
		board.initialize();
	}

	@Test
	public void testAdjacenciesEdges(){
		LinkedList<BoardCell> testList = board.getAdjList(5, 6);
		assertEquals(4, testList.size());		
		//Only walkways test
		
		testList = board.getAdjList(0, 0);
		assertEquals(0, testList.size());
		//inside a room on the corner, has no place to go
		
		testList = board.getAdjList(18, 0);
		assertEquals(1, testList.size());
		
		testList = board.getAdjList(15, 22);
		assertEquals(2, testList.size());
		
		testList = board.getAdjList(22, 6);
		assertEquals(3, testList.size());
		
	}
	
	@Test
	public void testNextToRoomNotDoorways(){
		LinkedList<BoardCell> testList = board.getAdjList(21, 5);
		assertEquals(3, testList.size());		
		//Only walkways test
		
		testList = board.getAdjList(22, 13);
		assertEquals(1, testList.size());
		//only one
	}
	
	@Test
	public void testNextToDoorNeedDirection(){
		LinkedList<BoardCell> testList = board.getAdjList(18, 4);
		assertEquals(3, testList.size());		
		//can go through the door
		
		testList = board.getAdjList(18, 15);
		assertEquals(4, testList.size());
		//accessed to all with door
		
		testList = board.getAdjList(7, 16);
		assertEquals(4, testList.size());
		// can go through door too
		
		testList = board.getAdjList(6, 10);
		assertEquals(3, testList.size());
		//three with door
		
	}
	
	@Test
	public void testDoorWays(){
		LinkedList<BoardCell> testList = board.getAdjList(9, 2);
		assertEquals(1, testList.size());		
		//Can only leave
		
		testList = board.getAdjList(16, 8);
		assertEquals(1, testList.size());
		//can leave out through up only
	}
	
	@Test
	public void testAlongWalkways(){

		board.calcTargets(3, 0, 2);
		Set<BoardCell> targets= board.getTargets();
		assertEquals(2, targets.size());
		assertTrue(targets.contains(board.getCellAt(4, 1)));
		assertTrue(targets.contains(board.getCellAt(3, 2)));

		board.calcTargets(0, 6, 2);
		targets = board.getTargets();
		assertEquals(2, targets.size());
		assertTrue(targets.contains(board.getCellAt(2, 6)));
		assertTrue(targets.contains(board.getCellAt(1, 7)));
		
		board.calcTargets(2, 14, 3);
		targets= board.getTargets();
		assertEquals(6, targets.size());
		assertTrue(targets.contains(board.getCellAt(0, 15)));
		assertTrue(targets.contains(board.getCellAt(1, 14)));
		assertTrue(targets.contains(board.getCellAt(2, 15)));
		assertTrue(targets.contains(board.getCellAt(3, 14)));
		assertTrue(targets.contains(board.getCellAt(4, 15)));
		assertTrue(targets.contains(board.getCellAt(5, 14)));
		
		board.calcTargets(4, 6, 2);
		targets= board.getTargets();
		assertEquals(7, targets.size());
		assertTrue(targets.contains(board.getCellAt(3, 5)));
		assertTrue(targets.contains(board.getCellAt(2, 6)));
		assertTrue(targets.contains(board.getCellAt(3, 7)));
		assertTrue(targets.contains(board.getCellAt(4, 4)));
		assertTrue(targets.contains(board.getCellAt(5, 5)));
		assertTrue(targets.contains(board.getCellAt(6, 6)));
		assertTrue(targets.contains(board.getCellAt(5, 7)));
	}
	
	@Test
	public void testEnterRoom(){
		board.calcTargets(18, 4, 2);
		Set<BoardCell> targets= board.getTargets();
		assertTrue(targets.contains(board.getCellAt(18, 3)));
		assertFalse(targets.contains(board.getCellAt(18, 2)));
		
		board.calcTargets(3, 5, 3);
		targets= board.getTargets();
		assertTrue(targets.contains(board.getCellAt(2, 5)));
		assertFalse(targets.contains(board.getCellAt(2, 3)));
	}
	
	@Test
	public void testLeavingRoom(){
		board.calcTargets(4, 16, 2);
		Set<BoardCell> targets= board.getTargets();
		assertEquals(3, targets.size());
		assertTrue(targets.contains(board.getCellAt(5, 15)));
		assertTrue(targets.contains(board.getCellAt(6, 16)));
		assertTrue(targets.contains(board.getCellAt(5, 17)));
		
		board.calcTargets(17, 18, 1);
		targets= board.getTargets();
		assertEquals(1, targets.size());
		assertTrue(targets.contains(board.getCellAt(16, 18)));
	}
}
