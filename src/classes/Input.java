package classes;

import java.util.Scanner;

/**
 * Clase de utilidad para gestionar la entrada de datos por teclado.
 * * @author Francisco Javier Pérez Pastor
 * @version 1.1.0
 */
public class Input {
	/** Objeto Scanner para leer la entrada del usuario */
	private static Scanner keyboard = new Scanner(System.in);
	/** Mensaje de error genérico al introducir un número inválido */
	private static final String INT_ERROR = "\n\"Error: you should enter a number!\"";
	/** Mensaje para pausar la ejecución del programa */
	private static final String CONTINUE = "\nPress 'enter' to continue";
	
	/**
	 * Lee un número entero por teclado.
	 * @return El número leído o -1 si hay un error.
	 */
	public static int getInt() {
		int number = -1;
		try {
			number = Integer.parseInt(keyboard.nextLine().trim());
		} catch (Exception e) {
			System.out.println(INT_ERROR);
		}
		return number;
	}

	/**
	 * Muestra un texto y lee un número entero por teclado.
	 * @param text Mensaje a mostrar al usuario.
	 * @return El número leído o -1 si hay un error.
	 */
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
	
	/**
	 * Solicita un número de fila válido dentro de los límites del laberinto.
	 * @param text Mensaje a mostrar al usuario.
	 * @param maze Matriz bidimensional del laberinto.
	 * @return El número de fila válido o -1 si es inválido.
	 */
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
	
	/**
	 * Solicita un número de columna válido dentro de los límites del laberinto.
	 * @param text Mensaje a mostrar al usuario.
	 * @param maze Matriz bidimensional del laberinto.
	 * @return El número de columna válido o -1 si es inválido.
	 */
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
	
	/**
	 * Muestra un texto y lee un número entero, permitiendo repetir en caso de error.
	 * @param text Mensaje a mostrar al usuario.
	 * @param repeat Si es true, repite la solicitud hasta obtener un valor válido.
	 * @return El número leído o -1 si hay error y repeat es false.
	 */
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

	/**
	 * Lee una cadena de texto por teclado.
	 * @return La cadena de texto introducida.
	 */
	public static String getString() {
		String value = keyboard.nextLine().trim();
		return value;
	}

	/**
	 * Muestra un texto y lee una cadena de texto por teclado.
	 * @param text Mensaje a mostrar.
	 * @return La cadena de texto introducida.
	 */
	public static String getString(String text) {
		System.out.print(text);
		return keyboard.nextLine().trim();
	}

	/**
	 * Pausa el programa hasta que el usuario pulse 'enter'.
	 */
	public static void toContinue() {
		System.out.print(CONTINUE);
		try {
			System.in.read();
		} catch (Exception e) {
		}
	}
}