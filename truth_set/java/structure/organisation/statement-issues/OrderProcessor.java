import java.util.List;
import java.util.Arrays;

class Item {
    private String name;
    private double price;
    private int quantity;

    public Item(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
}

class Order {
    private String id;
    private List<Item> items;
    private String discountCode;

    public Order(String id, List<Item> items, String discountCode) {
        this.id = id;
        this.items = items;
        this.discountCode = discountCode;
    }

    public String getId() {
        return id;
    }

    public List<Item> getItems() {
        return items;
    }

    public String getDiscountCode() {
        return discountCode;
    }
}

public class OrderProcessor {
    public static double processOrder(Order order, double taxRate) {
        double itemsTotal = calculateItemsTotal(order.getItems());
        double discount = order.getDiscountCode() != null ? 50 : 0;
        double total = (itemsTotal - discount) * (1 + taxRate);
        
        System.out.printf("Order %s processed. Total after tax: $%.2f%n", 
                         order.getId(), total);
        return total;
    }

    private static double calculateItemsTotal(List<Item> items) {
        double sum = 0;
        for (Item item : items) {
            sum += item.getPrice() * item.getQuantity();
        }
        return sum;
    }

    public static void main(String[] args) {
        List<Item> items = Arrays.asList(
            new Item("Laptop", 999.99, 1),
            new Item("Mouse", 49.99, 2),
            new Item("Keyboard", 79.99, 1)
        );
        
        Order order = new Order("ORD123", items, "SUMMER21");
        double taxRate = 0.08;
        double total = processOrder(order, taxRate);
        System.out.println(total);
    }
}
