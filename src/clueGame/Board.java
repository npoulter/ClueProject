package clueGame;
import java.awt.Color;
import java.awt.List;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
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
	private String playerConfigFile;
	private String weaponConfigFile;
	private Card[] deckOfCards;
	private Player[] playerArray;
	private ArrayList<Player> playerList;
	private ArrayList<Card> dealCardsList;
	private ArrayList<Card> roomCards;
	private ArrayList<Card> weaponCards;
	private ArrayList<Card> suspectCards;
	private Solution theSolution;
	private ArrayList<Card> solution;
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
		this.playerConfigFile = "Players.txt";
		this.weaponConfigFile = "Weapons.txt";
	}	

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
	
	// constructor with files
	public Board(String boardConfigFile, String roomConfigFile, String playerConfigFile, String weaponConfigFile) {
		super();
		board = new BoardCell[BOARD_SIZE][BOARD_SIZE];
		this.adjMtx = new HashMap<BoardCell, LinkedList<BoardCell>>();
		rooms = new HashMap<Character, String>();
		//this.visited = new HashSet<BoardCell>();
		//this.targets = new HashSet<BoardCell>();
		this.boardConfigFile = boardConfigFile;
		this.roomConfigFile = roomConfigFile;
		this.playerConfigFile = playerConfigFile;
		this.weaponConfigFile = weaponConfigFile;
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
		
		try {
			loadConfigFiles();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < deckOfCards.length; i++) {
			//System.out.println(deckOfCards[i]);
		}
		for (int i = 0; i < playerArray.length; i++) {
			//System.out.println(playerArray[i]);
		}
		calcAdjacencies();
	}

	// This will load in the files, read through them, and add the cards to the deck of cards
	public void loadConfigFiles() throws IOException{
		deckOfCards = new Card[21];
		playerArray = new Player[6];
		solution = new ArrayList<Card>();
		playerList = new ArrayList<Player>();
		FileReader playerConfigReader = new FileReader(playerConfigFile);
		FileReader weaponConfigReader = new FileReader(weaponConfigFile);
		FileReader roomConfigReader = new FileReader(roomConfigFile);
		String line;
		int counter = 0;
		Scanner playerReader = new Scanner(playerConfigReader);
		suspectCards = new ArrayList<Card>();
		
		while(playerReader.hasNextLine()){
			line = playerReader.nextLine();
			String[] temp = line.split(", ");
			Card nextCard = new Card(temp[0], "PERSON");
			deckOfCards[counter] = nextCard;
			suspectCards.add(nextCard);
			
			if(counter == 0) {
				solution.add(nextCard);
			}
			
			Color color;
			try {
			Field field = Class.forName("java.awt.Color").getField(temp[1]);
			color = (Color)field.get(null);
			} catch (Exception e){
				color = null;
			}
			Player nextPlayer = new Player(temp[0], 15, 11, color);
			playerList.add(nextPlayer);
			playerArray[counter] = nextPlayer;
			counter++;
		}
		
		Scanner weaponReader = new Scanner(weaponConfigReader);
		weaponCards = new ArrayList<Card>();
		
		while(weaponReader.hasNextLine()){
			line = weaponReader.nextLine();
			Card nextCard = new Card(line, "WEAPON");
			deckOfCards[counter] = nextCard;
			weaponCards.add(nextCard);
			
			if(counter == 6) {
				solution.add(nextCard);
			}
			counter++;
		}
		Scanner roomReader = new Scanner(roomConfigReader);
		roomCards = new ArrayList<Card>();
		
		while(roomReader.hasNextLine()){
			line = roomReader.nextLine();
			String[] temp = line.split(", ");
			Card nextCard = new Card(temp[1], "ROOM");
			roomCards.add(nextCard);
			deckOfCards[counter] = nextCard;
			
			if(counter == 12) {
				solution.add(nextCard);
			}
			counter++;
			
			if(counter == 21){
				break;
			}
		}
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
        	//System.out.println(line);
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
	
	//Deal the deck out
	public void dealCards(){
		//Turn the array into a list so it can be shuffled
		dealCardsList = new ArrayList<Card>(Arrays.asList(deckOfCards));
		Collections.shuffle(dealCardsList);
		
		Collections.shuffle(roomCards);
		Collections.shuffle(suspectCards);
		Collections.shuffle(weaponCards);
		
		
		theSolution = new Solution(suspectCards.get(0).getCardName(), roomCards.get(0).getCardName(), weaponCards.get(0).getCardName());
		
		// Remove solution cards from deck to be dealt
		dealCardsList.remove(suspectCards.get(0));
		dealCardsList.remove(roomCards.get(0));
		dealCardsList.remove(weaponCards.get(0));
		
		
		// Deal the cards out now that they're shuffled
		for (int i = 0; i < dealCardsList.size(); i++) {
				playerList.get(i % playerList.size()).givePlayerCard(dealCardsList.get(i)); 
		}
		//System.out.println("HERES THE FIRST PLAYERS CARDS: " + playerList.get(0).getCardList());
	}
	
	// TODO: Actually write function
	public void selectAnswer() {
		
	}
	
	// TODO: Actually write function
	public Card handleSuggestion(Solution suggestion, String accusingPlayer, BoardCell clicked) {
		return new Card();
	}
	
	// TODO: Actually write function
	public boolean checkAccusation(Solution accusation) {
		return true;
	}
	
	public Solution getSolution(){
		return theSolution;
	}
	
	public ArrayList<Card> getCardsList(){
		return dealCardsList;
	}
	
	public ArrayList<Player> getPlayerList(){
		return playerList;
	}
	
	public int getArraySize(){
		return playerArray.length;
	}
	
	public int getCardDeckSize(){
		return deckOfCards.length;
	}
	
	public Card[] getCardArray(){
		return deckOfCards;
	}
	
	public Player getPlayer(int whichPlayer){
		return playerArray[whichPlayer];
	}
	
	public ArrayList<Card> getCardList(){
		ArrayList<Card> list = new ArrayList<Card>(Arrays.asList(deckOfCards));
		return list;
	}

	// We will probably use this in the future...
	public static void main(String[] args) {
		
		String boardConfigFile = "ClueLayout.csv";
		String roomConfigFile = "ClueLegend.txt";
		String playerConfigFile = "Players.txt";
		String weaponConfigFile = "Weapons.txt";

		Board board = new Board(boardConfigFile, roomConfigFile, playerConfigFile, weaponConfigFile);
		board.initialize();
		board.dealCards();
		BoardCell cell = board.getCellAt(4, 4);
		//System.out.println(cell.toString());
		if(cell.isDoorway())
			System.out.println("is doorway");
		else 
			System.out.println("is NOT doorway");


		//System.out.println(board.getNumDoors());
		
		//System.out.println("END MAIN");
	}
}
