import javax.swing.*;
import java.awt.*;
import java.awt.layout.*;

/**
 * This class holds and manages a JFrame which
 * contains all of the other GUI stuff. It also
 * contains the main method.
 */
public class GUIContainer {
	private JFrame frame;
	private Matrix matrix;
	private InitialValues initialValues;
	private GUIMatrix guiMatrix;
	private GUIInitialValues guiInitialvalues;
	private SolutionPlotter solutionPlotter;

	public GUIContainer() {
		matrix = new Matrix(2, 2); //must be 2x2 because we're showing this in 2D
		initialValues = new InitialValues();
	}

	public void createAndShowGUI() {
		frame = new JFrame("Differential Equations Solution Modeler");
		JPanel mainPanel = new JPanel(new BorderLayout());
		JPanel sidePanel = new JPanel(new BorderLayout());
		frame.setContentPane(mainPanel);
		mainPanel.add(sidePanel, BorderLayout.WEST);
		sidePanel.add((guiInitialValues = new GUIInitialValues(initialValues)), BorderLayout.SOUTH);
		sidePanel.add((guiMatrix = new GUIMatrix(matrix)), BorderLayout.NORTH);
		mainPanel.add((solutionPlotter = new SolutionPlotter(matrix, initialValues)));
		mainPanel.setSize(600, 400);
		mainPanel.setLocationRelativeTo(null);
		mainPanel.setVisible(true);
	}

	public static void main(String[] args) {
		new GUIContainer().createAndShowGUI();
	}
}
