package classes;

import java.util.Objects;

/**
 * Clase que almacena la posición espacial (fila y columna) 
 * y la dirección tomada en el laberinto.
 * * @author Francisco Javier Pérez Pastor
 * @version 1.1.0
 */
public class Coordinate {
	/** Fila de la coordenada */
	private int i;
	/** Columna de la coordenada */
	private int j;
	/** Dirección tomada para llegar a esta coordenada (1:Arriba, 2:Derecha, 3:Abajo, 4:Izquierda) */
	private int direction;

	/**
	 * Constructor que inicializa una coordenada con su dirección.
	 * @param i Índice de la fila.
	 * @param j Índice de la columna.
	 * @param direction Dirección tomada para llegar aquí.
	 */
	public Coordinate(int i, int j, int direction) {
		this.i = i;
		this.j = j;
		this.direction = direction;
	}
	
	/**
	 * Obtiene la posición en el eje de las filas.
	 * @return La fila (i).
	 */
	public int getI() {
		return i;
	}

	/**
	 * Obtiene la posición en el eje de las columnas.
	 * @return La columna (j).
	 */
	public int getJ() {
		return j;
	}

	/**
	 * Obtiene el carácter representativo de la dirección para ser mostrado dentro del laberinto.
	 * @return Carácter ('^', '>', 'v' o '<') dependiendo de la dirección.
	 */
	public char getDirection() {
		if (this.direction == 1) return '^';
		else if (this.direction == 2) return '>';
		else if (this.direction == 3) return 'v';
		else return '<';
	}
	
	/**
	 * Obtiene el nombre legible de la dirección para mostrar en el camino.
	 * @return Texto de la dirección ("Up", "Right", "Down", "Left").
	 */
	public String getReadableDirection() {
		if (this.direction == 1) return "Up";
		else if (this.direction == 2) return "Right";
		else if (this.direction == 3) return "Down";
		else return "Left";
	}

	/**
	 * Genera un código hash basado en la fila y la columna.
	 * @return El código hash.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(i, j);
	}

	/**
	 * Compara si dos objetos de tipo Coordinate son iguales basándose en su fila y columna.
	 * @param obj El objeto a comparar.
	 * @return true si coinciden en fila y columna, false en caso contrario.
	 */
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