package classes;

/**
 * Clase estática para almacenar las constantes de configuración y 
 * textos de la interfaz gráfica del programa.
 * * @author Francisco Javier Pérez Pastor
 * @version 1.1.0
 */
public class Config {
	/** Versión actual del programa */
	public static final String VERSION = "v1.1.0";
	/** Texto de bienvenida mostrado al iniciar la aplicación */
	public static final String WELCOME = "+===========+\n|MAZE SOLVER|\n+===========+";
	/** Mensaje de despedida mostrado al salir de la aplicación */
	public static final String GOODBYE = "See you soon!";
	/** Menú de selección para algoritmos de laberintos */
	public static final String MAZE_SOLVER_MENU = "\nSelect a path:\n--------------\n1 - First path found\n2 - Shortest path\n0 - Leave";
	/** Menú mostrado para usuarios no autenticados */
	public static final String UNLOGGED_MENU = "\n1 - Login\n2 - Sign up\n0 - Leave";
	/** Menú principal mostrado para usuarios con sesión activa */
	public static final String LOGGED_MENU = "\n1 - Load Maze\n2 - Show current maze\n3 - Set start and end boxes\n4 - Find paths\n5 - Show current user\n6 - Logout\n0 - Leave";
}