package main;

import java.util.Scanner;

import classes.Config;
import classes.Input;
import classes.LogGenerator;
import classes.Maze;
import classes.Session;

public class Main {
	public static Scanner sc = new Scanner(System.in); 	
	public static Session session = new Session();
	public static Maze maze = new Maze();

	public static void main(String[] args) {
		boolean repeat = true;
		
		System.out.println(Config.WELCOME);
		LogGenerator.generateLog("Progam started", "status: successful");
		
		while (repeat) {
			if (session.isLogged()) {
				repeat = loggedMenu();
			}
			else {
				repeat = unloggedMenu();
			}
		}
	}
	
	public static boolean unloggedMenu() {
		String option = Input.getString(Config.UNLOGGED_MENU + "\nChoose an option: ");
			
		switch (option) {
			case "1": 
				session.login();
				break;
					
			case "2": 
				session.signUp();
				break;
					
			case "0": 
				System.out.println(Config.GOODBYE);
		        LogGenerator.generateLog("Progam finalized", "status: successful");
				return false;
					
			default: 
				System.out.println("The option entered is not valid!\n");
				break;
		
		}
		return true;
	}

	public static void mazeSolverMenu() {
		String option = Input.getString(Config.MAZE_SOLVER_MENU + "\nChoose an option: ");
		LogGenerator.generateLog("User entered in the maze solver menu", "status: successful");

			
		switch (option) {
			case "1": 
				if (maze.findFirstPath()) {
					maze.showMaze();
					System.out.println();
					maze.showPath();
				}
				break;
					
			case "2": 
				if (maze.findShortestPath()) {
					maze.showMaze();
					System.out.println();
					maze.showPath();
				}
				break;
					
			case "0": 
				break;
					
			default: 
				System.out.println("The option entered is not valid!\n");
				break;
		
		}
	}

	public static boolean loggedMenu() {
		String option = Input.getString(Config.LOGGED_MENU + "\nChoose an option: ");
		
		switch (option) {
			case "1": 
				maze.loadMaze();
				break;
		
			case "2": 
				System.out.println();
				maze.showMaze();
				Input.toContinue();
				break;
			case "3": 
				System.out.println();
				maze.setStartEnd();
				Input.toContinue();
				break;
			case "4": 
			    if (!maze.isLoaded()) {
			        System.out.println("Error: maze must be loaded!");
			        break;
			    }
				mazeSolverMenu();
				break;
			case "5": 
				session.showUser();
				Input.toContinue();
				break;
			case "6": 
				session.logout();
				maze = new Maze();
				break;	
			case "0": 
				System.out.println(Config.GOODBYE);
		        LogGenerator.generateLog("Progam finalized", "status: successful");
				return false;
			default:
				System.out.println("The option entered is not valid!\n");
				break;
		}
		return true;
	}
}