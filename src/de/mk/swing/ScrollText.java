package de.mk.swing;

import javax.swing.*;

/**
 * @author Martin Koose
 * <p>
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

    public int lines() {
        return text.getLineCount();
    }

    public String getText() {
        return text.getText();
    }

    public void setText(String string) {
        text.setText(string);
    }

    private void initialize(String txt) {

        text = new JTextArea(8, 40);
        this.setText(txt);

        scroll = new JScrollPane(text);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    }

    public JScrollPane getPane() {
        return scroll;

    }
}
