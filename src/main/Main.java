package main;
/**
 * Main.java
 * Main program to solve mazes.
 * JAVI - 2026.01.30
 * version 0.2.0
 */

import java.util.Scanner;

import classes.Config;
import classes.Input;
import classes.Session;
import classes.Maze;

public class Main {
	public static Scanner sc = new Scanner(System.in); 	
	public static Session session = new Session();
	public static Maze maze = new Maze();
	
	public static void main(String[] args) {
		boolean repeat = true;
		
		System.out.println(Config.WELCOME);
		
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
				return false;
					
			default: 
				System.out.println("The option entered is not valid!\n");
				break;
		
		}
		return true;
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
				break;
			case "4": 
				System.out.println("COMING SOON!");
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
				return false;
			default:
				System.out.println("The option entered is not valid!\n");
				break;
		}
		return true;
	}
}	

