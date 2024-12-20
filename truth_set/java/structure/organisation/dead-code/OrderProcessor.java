import java.util.ArrayList;
import java.util.List;


class OrderProcessor {
    private List<Order> orders = new ArrayList<>();
    private double taxRate = 0.07;

    private String errorMessage = "Order was not processed";
    private Logger logger;

    public OrderProcessor(Logger logger) {
        this.logger = logger;
    }

    private double calculateDiscount(double amount) {
        double discountRate = 0.1;
        return amount * discountRate;
    }

    public void processOrder(Order order) {
        orders.add(order);
        logger.log("Processing order ID: " + order.getId());

        applyTax(order);

        if (false) {
            sendConfirmationEmail(order);
        }

        int processingTime = calculateProcessingTime(order);
        logger.log("Processing time for order ID " + order.getId() + ": " + processingTime + "ms");
    }

    private void applyTax(Order order) {
        order.setTotal(order.getTotal() + order.getTotal() * taxRate);
        logger.log("Applied tax to order ID: " + order.getId());
    }

     public void processOrder(Order order) {
         orders.add(order);
         logger.log("Processing order ID: " + order.getId());

         applyTax(order);
         applyDiscount(order);

         int processingTime = calculateProcessingTime(order);

    private void sendConfirmationEmail(Order order) {
        logger.log("Sent confirmation email for order ID: " + order.getId());
    }

    private int calculateProcessingTime(Order order) {
        return 100;
    }
}

class Order {
    private int id;
    private double total;

    public Order(int id, double total) {
        this.id = id;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}

class Logger {
    public void log(String message) {
        System.out.println(message);
    }
}

class Main {
    public static void main(String[] args) {
        Logger logger = new Logger();
        OrderProcessor processor = new OrderProcessor(logger);

        Order newOrder = new Order(1, 200);
        processor.processOrder(newOrder);
    }
}