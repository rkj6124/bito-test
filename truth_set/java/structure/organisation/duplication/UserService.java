import java.util.Date;

class User {
    private String id;
    private String name;
    private int age;
    private boolean isActive;
    private String email;
    private String phone;
    private Date updatedAt;
    private boolean hasEmailChanged;
    private boolean hasPhoneChanged;

    public User(String id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.isActive = true;
    }

    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public boolean isActive() { return isActive; }
    public void setActive(boolean active) { isActive = active; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public Date getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Date updatedAt) { this.updatedAt = updatedAt; }
    public boolean isHasEmailChanged() { return hasEmailChanged; }
    public void setHasEmailChanged(boolean hasEmailChanged) { this.hasEmailChanged = hasEmailChanged; }
    public boolean isHasPhoneChanged() { return hasPhoneChanged; }
    public void setHasPhoneChanged(boolean hasPhoneChanged) { this.hasPhoneChanged = hasPhoneChanged; }
}

class UserService {
    private Database database;

    public UserService(Database database) {
        this.database = database;
    }

    public User createUser(String name, int age) {
        User user = new User(generateUniqueId(), name, age);
        database.save(user);
        return user;
    }

    public void updateUserEmail(String userId, String email) {
        User user = database.findUserById(userId);
        if (user != null) {
            user.setEmail(email);
            user.setUpdatedAt(new Date());
            user.setHasEmailChanged(true);
            database.save(user);
        }
    }

    public void updateUserPhone(String userId, String phone) {
        User user = database.findUserById(userId);
        if (user != null) {
            user.setPhone(phone);
            user.setUpdatedAt(new Date());
            user.setHasPhoneChanged(true);
            database.save(user);
        }
    }

    public boolean isAdult(int age) {
        return age >= 18;
    }

    public boolean canVote(int age) {
        if (age >= 18) {
            return true;
        }
        return false;
    }

    public boolean canDrink(int age) {
        if (age >= 21) {
            return true;
        }
        return false;
    }

    private double calculateRateBasedAmount(double price, double rate) {
        return price * rate;
    }
    public double calculateDiscount(double price) {
        return calculateRateBasedAmount(price, 0.1);
    }
    public double calculateTax(double price) {
        return calculateRateBasedAmount(price, 0.1);
    }

    private String generateUniqueId() {
        return String.valueOf(Math.random()).substring(2, 11);
    }
}

class Database {
    public void save(User user) {
        System.out.println("User " + user.getId() + " saved.");
    }

    public User findUserById(String id) {
        return null;
    }
}