package classes;

public class Node {
	private Coordinate coord;
	private Node parent;

	public Node(Coordinate coord, Node parent) {
		this.coord = coord;
		this.parent = parent;
	}

	public Coordinate getCoord() {
		return coord;
	}

	public void setCoord(Coordinate coord) {
		this.coord = coord;
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}
}