package fr.sharkhendrix.zerogravity.client;

import java.text.MessageFormat;
import java.util.ResourceBundle;

public class Translation {

    private static ResourceBundle bundle = ResourceBundle.getBundle("translation/translation",
            Context.getSettings().getLocale());

    public static String getString(String key, Object... params) {
        return MessageFormat.format(bundle.getString(key), params);
    }
}
