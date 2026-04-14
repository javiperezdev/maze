package classes;

/**
 * Clase que representa un nodo o estado en la búsqueda del camino mas corto del laberinto.
 * Se utiliza para registrar la coordenada actual y de dónde proviene.
 * * @author Francisco Javier Pérez Pastor
 * @version 1.1.0
 */
public class Node {
	/** Coordenada actual del nodo */
	private Coordinate coord;
	/** Nodo padre del cual proviene este nodo (para reconstruir el camino) */
	private Node parent;
	
	/**
	 * Constructor del nodo.
	 * @param coord Coordenada asociada a este nodo.
	 * @param parent Nodo anterior en el recorrido.
	 */
	public Node(Coordinate coord, Node parent) {
		this.coord = coord;
		this.parent = parent;
	}

	/**
	 * Obtiene la coordenada del nodo.
	 * @return La coordenada asociada.
	 */
	public Coordinate getCoord() {
		return coord;
	}

	/**
	 * Establece la coordenada del nodo.
	 * @param coord Nueva coordenada a asignar.
	 */
	public void setCoord(Coordinate coord) {
		this.coord = coord;
	}

	/**
	 * Obtiene el nodo padre.
	 * @return El nodo padre.
	 */
	public Node getParent() {
		return parent;
	}

	/**
	 * Establece el nodo padre.
	 * @param parent Nuevo nodo padre a asignar.
	 */
	public void setParent(Node parent) {
		this.parent = parent;
	}
}