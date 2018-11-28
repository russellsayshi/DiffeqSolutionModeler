import javax.swing.*;
import java.awt.*;

/**
 * So I have a test on this soon so I decided to
 * for the time being skip making the whole grand
 * shebang with the great GUI and maintainable
 * code and for now just make something that works
 * so I feel good on my test.
 * To use this, just set the mat, where the mat
 * is A in dx = A*x, set your initial value, and
 * then you can use dval1 and dval2 as two degrees
 * of freedom with which you can create a gridlike
 * pattern to get a more full solution. Use
 * numVecs1 and numVecs2 to determine the number
 * of those grid repetitions. Step determines
 * the step in euler's approximation for diffeqs,
 * and numCalcs determines the number of steps.
 * the startX, startY, etc. determine your window.
 * Hopefully someone will find this useful.
 */

public class QuickNDirty extends JPanel {
	final double[][] mat = {{1, 0}, {2, 1}};
	final double[] val = {-20, -20};
	final double[] dval1 = {0, 1};
	final double[] dval2 = {1, 0};
	final int numVecs1 = 40;
	final int numVecs2 = 40;
	final double step = 0.1;
	final int numCalcs = 100;
	final double startX = -20, startY = -20, endX = 20, endY = 20;

	public QuickNDirty() {}

	public static void main(String[] args) {
		JFrame frame = new JFrame("DIFFEQ SOLUTIONS");
		frame.setContentPane(new QuickNDirty());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setSize(600, 400);
		frame.setVisible(true);
	}

	private void updateVec(double[] vec, double[][] mat, double step) {
		double[] dvec = {vec[0] * mat[0][0] + vec[1] * mat[0][1], vec[0] * mat[1][0] + vec[1] * mat[1][1]};
		vec[0] += dvec[0] * step;
		vec[1] += dvec[1] * step;
	}

	private void drawLine(Graphics g, double x1, double y1, double x2, double y2) {
		int x1p = (int)(getWidth()*(x1-startX)/(endX-startX));
		int x2p = (int)(getWidth()*(x2-startX)/(endX-startX)+0.5);
		int y1p = (int)(getHeight()-(getHeight()*(y1-startY)/(endY-startY)));
		int y2p = (int)(getHeight()-(getHeight()*(y2-startY)/(endY-startY))+0.5);
		g.drawLine(x1p, y1p, x2p, y2p);
	}

	@Override
	public void paintComponent(Graphics gOld) {
		Graphics2D g = (Graphics2D)gOld;
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.BLACK);
		drawLine(g, startX, 0, endX, 0);
		drawLine(g, 0, startY, 0, endY);
		g.setColor(Color.BLUE);
		double[] initialValueStart = {val[0], val[1]};
		for(int a = 0; a < numVecs1; a++) {
			double[] initialValue = {initialValueStart[0], initialValueStart[1]};
			for(int b = 0; b < numVecs2; b++) {
				double[] v = {initialValue[0], initialValue[1]};
				for(int o = 0; o < numCalcs; o++) {
					double[] vOld = {v[0], v[1]};
					updateVec(v, mat, step);
					drawLine(g, vOld[0], vOld[1], v[0], v[1]);
				}
				initialValue[0] += dval2[0];
				initialValue[1] += dval2[1];
			}
			initialValueStart[0] += dval1[0];
			initialValueStart[1] += dval1[1];
		}
	}
}

