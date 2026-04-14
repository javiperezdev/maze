package main;

import java.util.Scanner;

import classes.Config;
import classes.Input;
import classes.Maze;
import classes.Session;

/**
 * Clase principal que ejecuta el sistema de resolución de laberintos.
 * Controla el ciclo principal del programa y el flujo de los menús de usuario.
 * * @author Francisco Javier Pérez Pastor
 * @version 1.0.0 
 */
public class Main {
	/** Lector global de teclado */
	public static Scanner sc = new Scanner(System.in); 	
	/** Instancia global de la sesión del usuario */
	public static Session session = new Session();
	/** Instancia global del laberinto en ejecución */
	public static Maze maze = new Maze();
	
	/**
	 * Método principal que arranca el bucle del menú principal del sistema.
	 * @param args Argumentos de la línea de comandos.
	 */
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
	
	/**
	 * Muestra y gestiona las opciones de menú para un usuario sin sesión activa.
	 * Permite login, registro y salir.
	 * @return true para mantener el ciclo en ejecución, false para terminar el programa.
	 */
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
	
	/**
	 * Muestra y gestiona las opciones de búsqueda de caminos dentro del laberinto.
	 * Dependiendo de la elección ejecuta distintos algoritmos.
	 */
	public static void mazeSolverMenu() {
		String option = Input.getString(Config.MAZE_SOLVER_MENU + "\nChoose an option: ");
			
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
	
	/**
	 * Muestra y gestiona el menú para usuarios logueados, 
	 * desde donde pueden manipular laberintos y configuraciones.
	 * @return true para continuar la ejecución de la aplicación, false para cerrarla.
	 */
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
				return false;
			default:
				System.out.println("The option entered is not valid!\n");
				break;
		}
		return true;
	}
}