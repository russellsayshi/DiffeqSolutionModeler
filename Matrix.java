/**
 * This class just holds a matrix and lets
 * you multiply it by a vector (array) to
 * get a vector out. All values are doubles.
 */

public class Matrix {
	protected double[][] mat;

	public Matrix(int rows, int cols) {
		mat = new double[rows][cols];
	}

	public void set(int row, int col, double val) {
		mat[row][col] = val;
	}

	public double get(int row, int col) {
		return mat[row][col];
	}

	public int rows() {
		return mat.length;
	}

	public int cols() {
		return mat[0].length;
	}

	public Vector multiply(Vector vec) {
		if(mat[0].length != vec.dimension()) {
			throw new IllegalArgumentException("Vector dimension does not match matrix dimension.");
		}
		Vector ret = new Vector(mat.length);
		for(int i = 0; i < mat.length; i++) {
			for(int o = 0; o < mat[i].length; o++) {
				ret.set(i, vec.get(o) * mat[i][o]);
			}
		}
		return ret;
	}
}
