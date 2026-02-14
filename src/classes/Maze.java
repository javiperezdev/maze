package classes;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Maze {
	private char[][] map;
	private String filename;
	private boolean loaded = false;
	private int startI = -1;
	private int startJ = -1;
	private int endI = -1;
	private int endJ = -1;
	
	private final String MAZES_PATH = "./assets/mazes";
	
	public void loadMaze() {
		String fileToOpen = fileMenu();
		if (fileToOpen == null) {
			System.out.print("File not Found!\n");	
			return;
		}
		this.filename = fileToOpen;
		
		ArrayList<String> mazeContent = extractMaze();
		
		char[][] maze = new char[mazeContent.size()][mazeContent.get(0).length()];
		
		for (int i = 0; i < maze.length; i++) {
			maze[i] = mazeContent.get(i).toCharArray();
		}
		
		this.loaded = true;
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
			
			int selected = Input.getInt("Select a maze: ");	
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
            	if (this.startI == i && this.startJ == j) {
            		System.out.print("I");
            	}
            	if (this.endI == i && this.endJ == j) {
            		System.out.print("F");
            	}
            	else {
            		System.out.print(this.map[i][j] + " ");
            	}
            }
            System.out.println();
        }
    }
	
	public void setStartEnd() {
		if (!this.loaded) {
			System.out.println("Loaded maze is needed before setting start and end!");
			return;
		}
		this.startI = Input.getRows("Enter the starting row number: ", this.map);
		if (startI == -1) return;
		this.startJ = Input.getCols("Enter the starting column number: ", this.map);
		if (startJ == -1) return;
		
		if (this.startI == this.startJ) {
			System.out.println("'j' can't be placed at the same position as 'i'!");
			return;
		}
		if (this.map[startI][startJ] == '#') {
			System.out.println("Start box can't be placed in a position containing '#'!");
			return;
		}

		this.endI = Input.getRows("Enter the ending row number: ", this.map);
		if (endI == -1) return;
		this.endJ = Input.getCols("Enter the ending column number: ", this.map);
		if (endJ == -1) return;
		
		if (this.endI == this.endJ) {
			System.out.println("'j' can't be placed at the same position as 'i'!");
			return;
		}
		if (this.map[startI][startJ] == '#') {
			System.out.println("End box can't be placed in a position containing '#'!");
			return;
		}
	}
}
