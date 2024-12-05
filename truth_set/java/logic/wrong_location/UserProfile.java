import java.util.Map;
import java.util.HashMap;

public class UserProfile {
    private Map<String, String> data;

    public UserProfile() {
        fetchData();
    }

    private void fetchData() {
        new Thread(() -> {
            try {
                Thread.sleep(1000);
                data = new HashMap<>();
                data.put("name", "Alice");
                System.out.println("Data fetched: " + data);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void printData() {
        System.out.println("User Data: " + data);
    }

    public static void main(String[] args) {
        UserProfile profile = new UserProfile();
        profile.printData();
    }
}