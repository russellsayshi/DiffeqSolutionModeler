package diffeq.gui;

import diffeq.math.*;
import javax.swing.*;
import javax.swing.text.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Holds a matrix and lets the user
 * edit it.
 */

public class GUIMatrix extends JPanel {
	private final Matrix mat;

	public GUIMatrix(Matrix mat) {
		super(new GridLayout(mat.rows(), mat.cols()));
		this.mat = mat;
		initGUI();
	}

	private class TextAreaListener implements DocumentListener {
		private final int row, col;
		private final JTextArea jta;
		public TextAreaListener(int row, int col, JTextArea jta) {
			this.row = row;
			this.col = col;
			this.jta = jta;
		}

		private void update(DocumentEvent e) {
			//can't modify from the same thread as
			//the event
			SwingUtilities.invokeLater(() -> {
				try {
					Document d = e.getDocument();
					String text = d.getText(0, d.getLength());
					if(!text.equals("")) {
						mat.set(row, col, Double.valueOf(text));
					}
				} catch(NumberFormatException nfe) {
					jta.setText("0.0");
				} catch(BadLocationException ble) {
					//if this fires, it means math has failed.
					//just print and ignore as the world
					//descends into chaos
					ble.printStackTrace();
				}
			});
		}

		public void insertUpdate(DocumentEvent e) {
			update(e);
		}

		public void removeUpdate(DocumentEvent e) {
			update(e);
		}

		public void changedUpdate(DocumentEvent e) {
			update(e);
		}
	}

	private void initGUI() {
		for(int i = 0; i < mat.rows(); i++) {
			for(int o = 0; o < mat.cols(); o++) {
				JTextArea field = new JTextArea("" + mat.get(i, o), 1, 10);
				field.getDocument().addDocumentListener(new TextAreaListener(i, o, field));
				add(field);
			}
		}
	}

	public Matrix getMatrixCopy() {
		return new Matrix(mat);
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("test");
		frame.setContentPane(new GUIMatrix(new Matrix(2, 2)));
		frame.setSize(600, 400);
		frame.setVisible(true);
	}
}
