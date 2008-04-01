package de.mk.swing;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * @author Martin Koose
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class ScrollText extends JScrollPane {

	private JTextArea text;
	private JScrollPane scroll;

	public ScrollText() {

		this.initialize("");
	}

	public ScrollText(String text) {
		this.initialize(text);
	}

	public void setText(String string) {
		text.setText(string);
	}
	
	public int lines() {
		return text.getLineCount();
	}
	
	public String getText() {
		return text.getText();	
	}

	private void initialize(String txt) {

		text = new JTextArea(8, 40);
		this.setText(txt);

		scroll = new JScrollPane(text);
		scroll.setHorizontalScrollBarPolicy(scroll.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll.setVerticalScrollBarPolicy(scroll.VERTICAL_SCROLLBAR_ALWAYS);
	}
	public JScrollPane getPane() {
		return scroll;

	}
}
