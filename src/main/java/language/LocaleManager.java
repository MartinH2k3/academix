package language;

import java.util.Locale;
import java.util.ResourceBundle;

public class LocaleManager {
    private static LocaleManager instance;
    private ResourceBundle messages;
    private Locale currentLocale;

    private LocaleManager() {
        currentLocale = Locale.getDefault();
        messages = ResourceBundle.getBundle("languages/messages",currentLocale);
    }

    public static LocaleManager getInstance() {
        if (instance == null) {
            instance = new LocaleManager();
        }
        return instance;
    }

    public void setLocale(Locale locale) {
        this.currentLocale = locale;
        messages = ResourceBundle.getBundle("languages/messages", currentLocale);
    }

    public ResourceBundle getMessages() {
        return messages;
    }
}
