package clueGame;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class Board {
	private int numRows;
	private int numColumns;
	public static final int BOARD_SIZE = 50;
	private BoardCell[][] board;
	

	private static HashMap<Character, String> rooms;
	private Map<BoardCell, LinkedList<BoardCell>> adjMtx; 
	private Set<BoardCell> targets;
	private Set<BoardCell> visited;
	private String boardConfigFile;
	private String roomConfigFile;
	public int numDoors;
	
	// constructor
	public Board() {
		super();
		board = new BoardCell[BOARD_SIZE][BOARD_SIZE];
		this.adjMtx = new HashMap<BoardCell, LinkedList<BoardCell>>();
		rooms = new HashMap<Character, String>();
		//this.visited = new HashSet<BoardCell>();
		//this.targets = new HashSet<BoardCell>();
		this.boardConfigFile = "ClueLayout.csv";
		this.roomConfigFile = "ClueLegend.txt";
	}	

	// constructor with files
	public Board(String boardConfigFile, String roomConfigFile) {
		super();
		board = new BoardCell[BOARD_SIZE][BOARD_SIZE];
		this.adjMtx = new HashMap<BoardCell, LinkedList<BoardCell>>();
		rooms = new HashMap<Character, String>();
		//this.visited = new HashSet<BoardCell>();
		//this.targets = new HashSet<BoardCell>();
		this.boardConfigFile = boardConfigFile;
		this.roomConfigFile = roomConfigFile;
	}

	// initialize the board
	public void initialize() {
		try {
			loadRoomConfig();
		} catch (FileNotFoundException e) {
			System.out.println(e);
		}
		catch (BadConfigFormatException e) {
			System.out.println(e);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			loadBoardConfig();
		} catch (IOException | BadConfigFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		calcAdjacencies();
	}

	public void loadRoomConfig() throws BadConfigFormatException, IOException{
		// parse file into usable crap - John
		String line = null;
		FileReader fileReader = new FileReader(roomConfigFile);
		// Always wrap FileReader in BufferedReader.
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        
        while((line = bufferedReader.readLine()) != null) {
            //reading one line at a time
        	String [] parts = line.split(", ");
        	if(parts.length != 3) {
        		//System.out.println("Bad Legend");
        		throw new BadConfigFormatException("Bad Config File: Legend does not match.");
        	}
        	//System.out.println(parts[2]);
        	char symbol = parts[0].charAt(0);
        	//System.out.println(symbol + "    " + parts[1]);
        	rooms.put(symbol, parts[1]);
        	//System.out.println(parts[0] + " " + parts[1]);
        	
        }   
        bufferedReader.close();
	}

	@SuppressWarnings("resource")
	public void loadBoardConfig() throws IOException, BadConfigFormatException{
		String line = null;
		int row = 0;
		FileReader fileReader = new FileReader(boardConfigFile);
		// Always wrap FileReader in BufferedReader.
        BufferedReader bReader = new BufferedReader(fileReader);
        line = bReader.readLine();
        String [] parts = line.split(",");
        numColumns = parts.length;
        do {
        	System.out.println(line);
        	parts = line.split(",");
        	if(parts.length != numColumns) {
        		//System.out.println("Bad Columns");
        		throw new BadConfigFormatException("Bad Config File: Columns do not match.");
        	}
        	//System.out.println(numColumns);
        	for(int i = 0; i < numColumns; i++){
        		// going through every column for each row
        		// create the board cell
        		BoardCell current = new BoardCell(row, i);
        		
        		if(parts[i].length() > 1)
        			numDoors++;
        		if(parts[i].length() == 1){
        			char symbol = parts[i].charAt(0);
        			if(!rooms.containsKey(symbol)) {
        				//System.out.println("Bad Rooms");
        				throw new BadConfigFormatException("Bad Config File: Rooms do not match");
        			}
        			current.setInitial(symbol);
        			current.setDd(DoorDirection.NONE);
        		}
        		else{
        			//System.out.println(parts[i]);
        			char symbol = parts[i].charAt(0);
        			current.setInitial(symbol);
        			char door = parts[i].charAt(1);
        			if(door == 'N'){
        				numDoors--;
        				current.setDd(DoorDirection.NONE);
        			}
        			//System.out.println(parts[i]);
        			if(door == 'U')
        				current.setDd(DoorDirection.UP);
        			else if(door == 'R')
        				current.setDd(DoorDirection.RIGHT);
        			else if(door == 'D')
        				current.setDd(DoorDirection.DOWN);
        			else if(door == 'L')
        				current.setDd(DoorDirection.LEFT);
        		}
        		board[row][i] = current;
        		//System.out.println(current.getInitial());
        	}
        	row++;
        }while((line = bReader.readLine()) != null);
        this.numRows = row;
        bReader.close();
	}
	
	// Various getters
	public int getNumRows() {
		return numRows;
	}

	public int getNumColumns() {
		return numColumns;
	}

	public static HashMap<Character, String> getRooms() {
		return rooms;
	}
	
	public BoardCell getBoardCell(int i, int j) {
		return board[i][j];
	}
	
	public BoardCell getCellAt(int i, int j) {
		return board[i][j];
	}
	
	public int getNumDoors() {
		return numDoors;
	}
	
	public Set<BoardCell> getTargets() {
		return targets;
	}

	// Setters
	public void setCellAt(BoardCell cell) {
		int row = cell.getRow();
		int col = cell.getCol();
		board[row][col] = cell; 
	}
	

	// Calculates Adjacency Lists
	public void calcAdjacencies(){
		// Calculates the adjacency list for each grid cell, stores in a map data structure.
		// Calculates the adjacency list for each grid cell, stores in a map data structure.
		for(int i = 0; i < numRows; i++)
			for(int j = 0; j < numColumns; j++){
				BoardCell temp = board[i][j];
				LinkedList<BoardCell> adj = new LinkedList<BoardCell>();
				if  (temp.isDoorway()) {
					switch(temp.getDd()){
					case UP:
						adj.add(board[i-1][j]);
						break;
					case DOWN:
						adj.add(board[i+1][j]);
						break;
					case LEFT:
						adj.add(board[i][j-1]);
						break;
					case RIGHT:
						adj.add(board[i][j+1]);
						break;
					default:
						break;
					}
				}
				else if (temp.isWalkway()) {
					if(i != 0)
						if (board[i - 1][j].isWalkway()){ 
							adj.add(board[i - 1][j]);
						}
						else if (board[i - 1][j].isDoorway() &&  board[i - 1][j].getDd() == DoorDirection.DOWN){
							adj.add(board[i - 1][j]);
						}
					if(i != numRows - 1) {
						if (board[i + 1][j].isWalkway()){ 
							adj.add(board[i + 1][j]);
						}
						else if (board[i + 1][j].isDoorway() &&  board[i + 1][j].getDd() == DoorDirection.UP){
							adj.add(board[i + 1][j]);
						}
					}
					if(j != 0) {
						if (board[i][j - 1].isWalkway()){ 
							adj.add(board[i][j - 1]);
						}
						else if (board[i][j - 1].isDoorway() &&  board[i][j - 1].getDd() == DoorDirection.RIGHT){
							adj.add(board[i][j - 1]);
						}
					}	
					if(j != numColumns - 1) {
						if (board[i][j + 1].isWalkway()){ 
							adj.add(board[i][j + 1]);
						}
						else if (board[i][j + 1].isDoorway() &&  board[i][j + 1].getDd() == DoorDirection.LEFT){
							adj.add(board[i][j + 1]);
						}
					}	
				}
				adjMtx.put(board[i][j], adj);
			}
		return;
	}

	// Gets Adjacency List
	public LinkedList<BoardCell> getAdjList(int i, int j) {
		return adjMtx.get(board[i][j]);
	}


	// Initial portion to calculate targets
	public void calcTargets(int row, int col, int pathLength){ 
		// Calculates the targets that are pathLength distance from the startCell. 
		// The list of targets will be stored in an instance variable.
		visited = new HashSet<BoardCell>(); 
		targets = new HashSet<BoardCell>();
		
		visited.add(board[row][col]);
		findAllTargets(row, col, pathLength);

		return;
	}

	// Recursive portion to calculate targets
	public void findAllTargets(int row, int col, int numSteps){
		LinkedList<BoardCell> newAdj = new LinkedList<BoardCell>(getAdjList(row, col));
		for(int i = 0; i < newAdj.size(); i++){
			if (visited.contains(newAdj.get(i))) continue;
			visited.add(newAdj.get(i));
			if(numSteps == 1 || newAdj.get(i).isDoorway()) {
				targets.add(newAdj.get(i));
			}
			else{
				findAllTargets(newAdj.get(i).getRow(), newAdj.get(i).getCol(), numSteps - 1);
			}
			visited.remove(newAdj.get(i));
		}
	}
	

	// We will probably use this in the future...
	public static void main(String[] args) {
		/*
		String boardConfigFile = "ClueLayout.csv";
		String roomConfigFile = "ClueLegend.txt";

		Board board = new Board(boardConfigFile, roomConfigFile);
		board.initialize();
		BoardCell cell = board.getCellAt(4, 4);
		System.out.println(cell.toString());
		if(cell.isDoorway())
			System.out.println("is doorway");
		else
			System.out.println("is NOT doorway");


		System.out.println(board.getNumDoors());
		
		System.out.println("END MAIN");*/
	}
}
