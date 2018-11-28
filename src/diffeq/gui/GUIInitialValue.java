package diffeq.gui;

import diffeq.math.*;
import javax.swing.*;
import java.awt.layout.*;

/**
 * This is a panel that holds a 2-vector
 * representing an initial value.
 */

public class GUIInitialValue extends JPanel {
	private JComponent parent;
	private Vector val;
	private GUIMatrix mat;

	public GUIInitialValue(JComponent parent, Vector val) {
		super(new BorderLayout());
		this.parent = parent;
		this.val = val;
		initGUI();
	}

	private void initGUI() {
		JButton remove = new JButton("x");
		remove.addActionListener(() -> {
			parent.remove(GUIInitialValue.this);
		});
		add(remove, BorderLayout.EAST);
		add((mat = new GUIMatrix(val)), BorderLayout.WEST);
	}
}
