import java.util.Map;
import java.util.HashMap;

public class ConfigManager {
    private static ConfigManager instance;
    private Map<String, String> config;

    private ConfigManager() {}

    public static ConfigManager getInstance() {
        if (instance == null) {
            instance = new ConfigManager();
            instance.loadConfig();
        }
        return instance;
    }

    private void loadConfig() {
        new Thread(() -> {
            try {
                Thread.sleep(1000);
                config = new HashMap<>();
                config.put("apiUrl", "https://api.example.com");
                System.out.println("Config loaded: " + config);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static void main(String[] args) {
        ConfigManager config = ConfigManager.getInstance();
        System.out.println("API URL: " + config.config.get("apiUrl"));
    }
}