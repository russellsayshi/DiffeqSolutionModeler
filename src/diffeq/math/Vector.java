package diffeq.math;

/**
 * This class is just a matrix
 * with only one column.
 */

public class Vector extends Matrix {
	public Vector(int rows) {
		super(rows, 1);
	}

	public int dimension() {
		return super.rows();
	}

	public double get(int row) {
		return super.get(row, 1);
	}

	public void set(int row, double val) {
		super.set(row, 1, val);
	}
}
