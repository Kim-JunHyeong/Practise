package exam.crazy.designpatterns._01_creational_patterns._01_singleton.teach;

public class Settings {

    private static Settings instance;

    private Settings() {}

    public static Settings getInstance() {
        if (instance == null) {
            instance = new Settings();
        }
        return instance;
    }
}
