import java.util.List;
import java.util.ArrayList;

class Order {
    private int id;
    private String product;
    private int quantity;

    public Order(int id, String product, int quantity) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public String getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }
}

class OrderService {
    private List<Order> orders;

    public OrderService() {
        this.orders = new ArrayList<>();
    }

    public void placeOrder(Order order) {
        orders.add(order);
        notify("Order placed: " + order.getId());
    }

    public void cancelOrder(int orderId) {
        orders.removeIf(order -> order.getId() == orderId);
        notify("Order canceled: " + orderId);
    }

    private void notify(String message) {
        System.out.println("Notification: " + message);
    }
}