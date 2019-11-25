package de.mb.swing;

import javax.swing.*;
import java.awt.*;

public class JInfoDialog extends JAbstractDialog {

    /**
     * Public constructor
     *
     * @param owner
     * @param title   Application name
     * @param version Application version
     * @param authors Authors
     */
    public JInfoDialog(
            Frame owner,
            String title,
            String version,
            String authors) {
        super(owner);

        _width = 350;
        _height = 150;

        this.setModal(true);
        setTitle(title);

        JLabel _title_text = new JLabel(title);
        this.add(_title_text, BorderLayout.NORTH);

        JLabel _version_text = new JLabel(version);
        JLabel _authors_text = new JLabel(authors);

        JPanel panel = new JPanel();
        LayoutManager _layout = new GridLayout(2, 2);
        panel.setLayout(_layout);
        JLabel _version_label = new JLabel("Version:");
        panel.add(_version_label);
        panel.add(_version_text);
        JLabel _authors_label = new JLabel("Autoren:");
        panel.add(_authors_label);
        panel.add(_authors_text);

        this.add(panel, BorderLayout.CENTER);

    }

    public static void main(String[] args) {
    }

}
