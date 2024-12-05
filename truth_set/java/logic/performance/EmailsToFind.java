public class EmailsToFind {
    static class User {
        int id;
        String email;
        
        User(int id, String email) {
            this.id = id;
            this.email = email;
        }
    }

    public static void main(String[] args) {
        User[] users = new User[100000];
        for (int i = 0; i < users.length; i++) {
            users[i] = new User(i + 1, "user" + (i + 1) + "@example.com");
        }

        String[] emailsToFind = {
            "user100@example.com",
            "user200@example.com",
            "user300@example.com"
        };

        for (String email : emailsToFind) {
            for (User user : users) {
                if (user.email.equals(email)) {
                    System.out.println(user.id);
                    break;
                }
            }
        }
    }
}