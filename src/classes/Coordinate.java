package classes;

import java.util.Objects;

public class Coordinate {
	private int i;
	private int j;
	private int direction;

	public Coordinate(int i, int j, int direction) {
		this.i = i;
		this.j = j;
		this.direction = direction;
	}

	public int getI() {
		return i;
	}

	public int getJ() {
		return j;
	}

	public char getDirection() {
		if (this.direction == 1) return '^';
		else if (this.direction == 2) return '>';
		else if (this.direction == 3) return 'v';
		else return '<';
	}

	public String getReadableDirection() {
		if (this.direction == 1) return "Up";
		else if (this.direction == 2) return "Right";
		else if (this.direction == 3) return "Down";
		else return "Left";
	}


	@Override
	public int hashCode() {
		return Objects.hash(i, j);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordinate other = (Coordinate) obj;
		return i == other.i && j == other.j;
	}
}