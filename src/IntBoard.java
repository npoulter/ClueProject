import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class IntBoard {
	private Map<BoardCell, LinkedList<BoardCell>> adjMtx; 
	private Set<BoardCell> visited;
	private Set<BoardCell> targets;
	private BoardCell[][] grid;
	private int size;
	//constructor
	public IntBoard(int size) {
		super();
		// TODO Auto-generated constructor stub
		this.size = size;
		this.adjMtx = new HashMap<BoardCell, LinkedList<BoardCell>>();
		this.visited = new HashSet<BoardCell>();
		this.targets = new HashSet<BoardCell>();
		this.grid = new BoardCell[size][size];
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++){
				grid[i][j] = new BoardCell(i, j);
			}
		}
		this.calcAdjacencies();
	}
	

	public void calcAdjacencies(){
		// Calculates the adjacency list for each grid cell, stores in a map data structure.
		for(int i = 0; i < size; i++)
			for(int j = 0; j < size; j++){
				LinkedList<BoardCell> adj = new LinkedList<BoardCell>();
				if(i != 0)
					adj.add(grid[i - 1][j]);
				if(i != size - 1)
					adj.add(grid[i + 1][j]);
				if(j != 0)
					adj.add(grid[i][j - 1]);
				if(j != size - 1)
					adj.add(grid[i][j + 1]);
				adjMtx.put(grid[i][j], adj);
			}
		return;
	}
	
	public void setGrid(BoardCell[][] grid) {
		this.grid = grid;
	}

	public void calcTargets(BoardCell startCell, int pathLength){ 
		// Calculates the targets that are pathLength distance from the startCell. 
		// The list of targets will be stored in an instance variable.
		visited = new HashSet<BoardCell>();  //not entirely neccessary but wanted to make it start empty
		targets = new HashSet<BoardCell>();
		
		visited.add(startCell);
		findAllTargets(startCell, pathLength);
		//targets.remove(startCell);
		
		return;
	}
	
	public void findAllTargets(BoardCell startCell, int numSteps){
		LinkedList<BoardCell> newAdj = new LinkedList<BoardCell>(getAdjList(startCell));
		for(int i = 0; i < newAdj.size(); i++){
			if (visited.contains(newAdj.get(i))) continue;
			visited.add(newAdj.get(i));
			if(numSteps == 1) {
				targets.add(newAdj.get(i));
			}
			else{
				findAllTargets(newAdj.get(i), numSteps - 1);
			}
			visited.remove(newAdj.get(i));
		}
	}
	
	public Set<BoardCell> getTargets(){
		return targets;
	}

	public BoardCell getCell(int i, int j) {
		BoardCell test = grid[i][j];
		return test;
	}

	public LinkedList<BoardCell> getAdjList(BoardCell cell) {
		LinkedList<BoardCell> test = new LinkedList<BoardCell>();
		test = adjMtx.get(cell);
		return test;
	}
	
	
	public static void main(String[] args) {

		System.out.println("SADFSDFA");
	}

}
