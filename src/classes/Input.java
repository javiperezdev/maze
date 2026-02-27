package classes;

import java.util.Scanner;

public class Input {
	private static Scanner keyboard = new Scanner(System.in);
	private static final String INT_ERROR = "\n\"Error: you should enter a number!\"";
	private static final String CONTINUE = "\nPress 'enter' to continue";
	
	public static int getInt() {
		int number = -1;
		try {
			number = Integer.parseInt(keyboard.nextLine().trim());
		} catch (Exception e) {
			System.out.println(INT_ERROR);
		}
		return number;
	}

	public static int getInt(String text) {
		int number = -1;
		System.out.print(text);
		try {
			number = Integer.parseInt(keyboard.nextLine().trim());
		} catch (Exception e) {
			System.out.println(INT_ERROR);
		}
		return number;
	}
	
	public static int getRows(String text, char[][] maze) {
		int number = -1;
		System.out.print(text);
		try {
			number = Integer.parseInt(keyboard.nextLine().trim());
			if (number >= 0 && number < maze.length) return number;
			System.out.println("Error: the entered row doesn't exist");
			number = -1;
		} catch (Exception e) {
			System.out.println(INT_ERROR);
		}
		return number;
	}
	
	public static int getCols(String text, char[][] maze) {
		int number = -1;
		System.out.print(text);
		try {
			number = Integer.parseInt(keyboard.nextLine().trim());
			if ((number >= 0 && number < maze[0].length)) return number;
			System.out.println("Error: the entered column doesn't exist");
			number = -1;
		} catch (Exception e) {
			System.out.println(INT_ERROR);
		}
		return number;
	}
	
	
	
	
	public static int getInt(String text, boolean repeat) {
		int number = -1;
		boolean success = false;
		do {
			System.out.print(text);
			try {
				number = Integer.parseInt(keyboard.nextLine().trim());
				success = true;
			} catch (Exception e) {
				System.out.println(INT_ERROR);
				if(!repeat) {
					return -1;
				}
			}
		} while(!success);
		return number;
	}

	public static String getString() {
		String value = keyboard.nextLine().trim();
		return value;
	}

	public static String getString(String text) {
		System.out.print(text);
		return keyboard.nextLine().trim();
	}

	public static void toContinue() {
		System.out.print(CONTINUE);
		try {
			System.in.read();
		} catch (Exception e) {
		}
	}
}

