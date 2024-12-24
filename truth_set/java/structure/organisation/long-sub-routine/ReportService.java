import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class User {
    private int id;
    private String name; 
    private boolean isActive;

    public User(int id, String name, boolean isActive) {
        this.id = id;
        this.name = name;
        this.isActive = isActive;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public boolean isActive() { return isActive; }
}

class Transaction {
    private int id;
    private int userId;
    private double amount;

    public Transaction(int id, int userId, double amount) {
        this.id = id;
        this.userId = userId;
        this.amount = amount;
    }

    public int getId() { return id; }
    public int getUserId() { return userId; }
    public double getAmount() { return amount; }
}

class ReportService {
    public void generateMonthlyReport(List<User> users, List<Transaction> transactions) {
        List<User> activeUsers = new ArrayList<>();
        for (User user : users) {
            if (user.isActive()) {
                activeUsers.add(user);
            }
        }
        System.out.println("Active Users Count: " + activeUsers.size());

        double totalAmount = 0;
        for (Transaction tx : transactions) {
            totalAmount += tx.getAmount();
        }
        System.out.println("Total Transactions Amount: $" + totalAmount);

        String reportContent = "Monthly Report\n================\n" +
            "Active Users: " + activeUsers.size() + "\n" +
            "Total Transactions: $" + totalAmount + "\n";

        File reportsDir = new File("reports");
        if (!reportsDir.exists()) {
            reportsDir.mkdir();
        }

        String fileName = "monthly_report_" + (new Date().getMonth() + 1) + ".txt";
        File reportFile = new File(reportsDir, fileName);
        try {
            FileWriter writer = new FileWriter(reportFile);
            writer.write(reportContent);
            writer.close();
            System.out.println("Report saved to " + reportFile.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Error writing report: " + e.getMessage());
        }

        sendNotification("Monthly report generated: " + reportFile.getAbsolutePath());
    }

    private void sendNotification(String message) {
        System.out.println("Notification: " + message);
    }
}

class Main {
    public static void main(String[] args) {
        List<User> users = new ArrayList<>();
        users.add(new User(1, "Alice", true));
        users.add(new User(2, "Bob", false));
        users.add(new User(3, "Charlie", true));

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(101, 1, 250));
        transactions.add(new Transaction(102, 3, 450));
        transactions.add(new Transaction(103, 1, 150));

        ReportService reportService = new ReportService();
        reportService.generateMonthlyReport(users, transactions);
    }
}