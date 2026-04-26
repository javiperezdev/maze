package classes;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Maze {
	private char[][] map;
	private String filename;
	private boolean loaded = false;
	private int startI = -1;
	private int startJ = -1;
	private int endI = -1;
	private int endJ = -1;
	private Stack<Coordinate> path = new Stack<>();
	private boolean[][] visited;
	private static final String MAZES_PATH = "./assets/mazes";
	

	public boolean isLoaded() {
		return this.loaded;
	}
	
	public String getFilename() {
		return this.filename;
	}

	public char[][] getMap() {
		return map;
	}
	
	public int getStartI() {
		return startI;
	}

	public int getStartJ() {
		return startJ;
	}

	public int getEndI() {
		return endI;
	}

	public int getEndJ() {
		return endJ;
	}

	public Stack<Coordinate> getPath() {
		return path;
	}

	public void loadMaze() {
		String fileToOpen = fileMenu();
		if (fileToOpen == null) {
			LogGenerator.generateLog("Failed to load maze", "file: not defined");
			return;
		}
		this.filename = fileToOpen;
		
		ArrayList<String> mazeContent = extractMaze();
		char[][] maze = new char[mazeContent.size()][mazeContent.get(0).length()];
		
		this.startI = -1;
		this.startJ = -1;
		this.endI = -1;
		this.endJ = -1;
		this.path = new Stack<>();
		this.visited = new boolean[maze.length][maze[0].length];
		
		for (int i = 0; i < maze.length; i++) {
			maze[i] = mazeContent.get(i).toCharArray();
		}
		
		this.loaded = true;
		LogGenerator.generateLog("Maze loaded successfully", "file: " + this.filename);
		this.map = maze;
	}


	private String fileMenu() {
		File folder = new File(MAZES_PATH);
		String[] files = folder.list();
		
		System.out.println("\n+=====+\n|MAZES|\n+=====+");
		if (files != null) {
			for (int i = 0; i < files.length; i++) {
				System.out.println((i+1)+"- " + files[i]);
			}
			System.out.println("0- exit");
			
			int selected = Input.getInt("Select a maze: ");	
			if (selected == 0) return null;
			for (int i = 0; i < files.length; i++) {
				if (i == (selected - 1)) return files[i];
			}
		}
		return null;
	}
	
	private ArrayList<String> extractMaze() {
		ArrayList<String> mazeContent = new ArrayList<>();
		File f = new File(MAZES_PATH + "/" + filename);
		try (Scanner sc = new Scanner(f)) {
			while (sc.hasNextLine()) {
				mazeContent.add(sc.nextLine());
			}
		}
		catch (Exception e) {
			System.out.println("An error has occurred: " + e.getMessage());
		}
		return mazeContent;
	}
	
	public void showMaze() {
        if (!this.loaded) {
            System.out.println("There are no mazes loaded");
            LogGenerator.generateLog("Failed to show maze", "file: not defined");
            return;
        }

        int cols = this.map[0].length;
        String margin = "     "; 

        if (cols > 10) { 
            System.out.print(margin);
            for (int j = 0; j < cols; j++) {
                if (j > 9) {
                    System.out.print((j / 10) + " ");
                } else {
                    System.out.print("  ");
                }
            }
            System.out.println(); 
        }

        System.out.print(margin);
        for (int j = 0; j < cols; j++) {
            System.out.print((j % 10) + " ");
        }
        
        System.out.println();
        System.out.print(margin);
        for (int j = 0; j < cols; j++) { 
            System.out.print("--");
        }
        
        System.out.println();
        for (int i = 0; i < this.map.length; i++) {
            System.out.printf("%2d | ", i);
            for (int j = 0; j < cols; j++) {
                Coordinate stepAhead = null;
                boolean nextStep = false;
                for (Coordinate c : this.path) {
                    if (nextStep) {
                    	stepAhead = c;
                    	break;
                    }
                    if (c.getI() == i && c.getJ() == j) {
                        nextStep = true;
                    }
                }

                if (this.startI == i && this.startJ == j) {
                    System.out.print("I "); 
                } 
                else if (this.endI == i && this.endJ == j) {
                    System.out.print("F "); 
                } 
                else if (stepAhead != null) {
                    System.out.print(stepAhead.getDirection() + " "); 
                } 
                else {
                    System.out.print(this.map[i][j] + " ");
                }
            }
            System.out.println(); 
        }
        LogGenerator.generateLog("Maze shown succesfully", "file: " + this.filename);
    }
	
	public void setStartEnd() {
		if (!this.loaded) {
			System.out.println("Loaded maze is needed before setting start and end!");
            LogGenerator.generateLog("Failed to set boxes", "details: No maze was loaded");
			return;
		}
		this.startI = Input.getRows("Enter the starting row number: ", this.map);
		if (startI == -1) return;
		this.startJ = Input.getCols("Enter the starting column number: ", this.map);
		if (startJ == -1) return;
		
		if (this.map[startI][startJ] == '#') {
			System.out.println("Start box can't be placed in a position containing '#'!");
	        LogGenerator.generateLog("Failed to set boxes", "details: User tried to set start in a postion containing '#'");
			return;
		}

		this.endI = Input.getRows("Enter the ending row number: ", this.map);
		if (endI == -1) return;
		this.endJ = Input.getCols("Enter the ending column number: ", this.map);
		if (endJ == -1) return;
		
		if (this.map[endI][endJ] == '#') {
			System.out.println("End box can't be placed in a position containing '#'!");
	        LogGenerator.generateLog("Failed to set boxes", "details: User tried to set end in a postion containing '#'");
			return;
		}
		
		
		if (startI == endI && startJ == endJ) {
			System.out.println("Start box and end box can't be placed in the same box!");
	        LogGenerator.generateLog("Failed to set boxes", "details: User tried to set start and end in the same position");
			return;
		}
		
        LogGenerator.generateLog("Successfully setted boxes", "postions: start=(" + startI + ", " + startJ + ") end=(" + endI + ", " + endJ + ")");
	}
	
	public boolean findFirstPath() {		  
		this.visited = new boolean[this.map.length][this.map[0].length];
		
		if (startI == -1) {
			System.out.println("Error: Start and end boxes should be set!");
			return false;
		}
		
		path.push(new Coordinate(startI, startJ, 0));
		while (!path.isEmpty()) {
			Coordinate currentBox = path.peek();
			
			if (currentBox.getI() == endI && currentBox.getJ()== endJ) {
		        LogGenerator.generateLog("First path found successfully", "steps: " + this.path.toArray().length);
				return true;
			}
			
			if (isValid(currentBox.getI() - 1, currentBox.getJ(), 1) && !visited[currentBox.getI()-1][currentBox.getJ()]) {
				path.push(new Coordinate(currentBox.getI() - 1, currentBox.getJ(), 1));
			}
			else if (isValid(currentBox.getI(), currentBox.getJ() + 1, 2)  && !visited[currentBox.getI()][currentBox.getJ() + 1]) {
				path.push(new Coordinate(currentBox.getI(), currentBox.getJ() + 1, 2));
			}
			else if (isValid(currentBox.getI() + 1, currentBox.getJ(), 3)  && !visited[currentBox.getI() + 1][currentBox.getJ()]) {
				path.push(new Coordinate(currentBox.getI() + 1, currentBox.getJ(), 3));
			}
			else if (isValid(currentBox.getI(), currentBox.getJ() - 1, 4)  && !visited[currentBox.getI()][currentBox.getJ() - 1]) {
				path.push(new Coordinate(currentBox.getI(), currentBox.getJ() - 1, 4));
			}
			else {				
					Coordinate foundBox = this.path.pop();
					visited[foundBox.getI()][foundBox.getJ()] = true;
			}
		}
		
		LogGenerator.generateLog("Failed to find first path", "details: The maze has no solution for the entered coordinates");
		return false;
	}
	
	public boolean findShortestPath() {
	    Queue<Node> shortPath = new LinkedList<>();
	    
	    if (startI == -1) {
	        System.out.println("Error: Start and end boxes should be set!");
	        return false;
	    }

	    this.visited = new boolean[this.map.length][this.map[0].length];
	    
	    shortPath.offer(new Node(new Coordinate(startI, startJ, 0), null));
	    this.visited[startI][startJ] = true; 
	    
	    Node endNode = null;

	    while (!shortPath.isEmpty()) {
	        Node currentBox = shortPath.poll(); 
	        Coordinate c = currentBox.getCoord();
	        int i = c.getI();
	        int j = c.getJ();

	        if (i == endI && j == endJ) {
	            endNode = currentBox; 
	            break; 
	        }
	       
	        if (isValid(i - 1, j, 1) && !visited[i - 1][j]) {
	            visited[i - 1][j] = true; 
	            shortPath.offer(new Node(new Coordinate(i - 1, j, 1), currentBox));
	        }
	        if (isValid(i, j + 1, 2) && !visited[i][j + 1]) {
	            visited[i][j + 1] = true;
	            shortPath.offer(new Node(new Coordinate(i, j + 1, 2), currentBox));
	        }
	        if (isValid(i + 1, j, 3) && !visited[i + 1][j]) {
	            visited[i + 1][j] = true;
	            shortPath.offer(new Node(new Coordinate(i + 1, j, 3), currentBox));
	        }
	        if (isValid(i, j - 1, 4) && !visited[i][j - 1]) {
	            visited[i][j - 1] = true;
	            shortPath.offer(new Node(new Coordinate(i, j - 1, 4), currentBox));
	        }
	    }

	    if (endNode == null) {
			LogGenerator.generateLog("Failed to find shortest path", "details: The maze has no solution for the entered coordinates");
	        return false;
	    }

	    Node tracker = endNode;
	    while (tracker != null) {
	        this.path.add(0, tracker.getCoord()); 
	        tracker = tracker.getParent(); 
	    }
        LogGenerator.generateLog("Shortest path found successfully", "steps: " + this.path.toArray().length);
	    return true;
	}

	public void showPath() {
		Coordinate[] stepsArray = this.path.toArray(new Coordinate[0]);
		System.out.println("Steps: " + stepsArray.length);

		for (int k = 0; k < stepsArray.length - 1; k++) {
		    Coordinate current = stepsArray[k];
		    Coordinate next = stepsArray[k+1];
		    System.out.println("(" + current.getI() + ", " + current.getJ() + ") " + next.getReadableDirection());	
		}	
	}
	
	public boolean isValid(int i, int j, int direction) {
		if ((i >= 0 && i < this.map.length)&&(j>=0 && j < this.map[0].length)) {
			if (this.path.contains(new Coordinate(i, j, direction))) return false;
			if (this.map[i][j] != '#') return true;
		}
		return false;
	}
}