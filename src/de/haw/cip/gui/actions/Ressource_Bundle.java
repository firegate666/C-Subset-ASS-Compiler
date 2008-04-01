package de.haw.cip.gui.actions;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Ressource_Bundle {

    private static final String BUNDLE_NAME = "de.haw.cip.gui.actions.dialog"; //$NON-NLS-1$

    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

    private Ressource_Bundle() {
    }

    public static String getString(String key) {
        try {
            return RESOURCE_BUNDLE.getString(key);
        } catch (MissingResourceException e) {
            return '!' + key + '!';
        }
    }
}
