import java.util.Map;
import java.util.HashMap;

class UserService {
    public String fetchUserProfile(int userId) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "User Profile for ID: " + userId;
    }

    public boolean checkUserEligibility(int age) {
        return age >= 18;
    }
}

class ProductService {
    public String getProduct(int productId) {
        Map<Integer, String> products = new HashMap<>();
        products.put(101, "Laptop");
        products.put(102, "Smartphone"); 
        products.put(103, "Headphones");
        return products.getOrDefault(productId, "Product Not Found");
    }

    public boolean isInStock(int stock) {
        return stock > 0;
    }
}

class OrderService {
    public String retrieveOrder(int orderId) {
        Map<Integer, String> orders = new HashMap<>();
        orders.put(5001, "Order #5001: Shipped");
        orders.put(5002, "Order #5002: Processing");
        orders.put(5003, "Order #5003: Delivered");
        return orders.getOrDefault(orderId, "Order Not Found");
    }

    public boolean checkOrderStatus(boolean completed) {
        return completed;
    }
}

public class Supermarket {
    public static void main(String[] args) {
        UserService userService = new UserService();
        System.out.println(userService.fetchUserProfile(1));
        System.out.println(userService.checkUserEligibility(20));

        ProductService productService = new ProductService();
        System.out.println(productService.getProduct(101));
        System.out.println(productService.isInStock(0));

        OrderService orderService = new OrderService();
        System.out.println(orderService.retrieveOrder(5002));
        System.out.println(orderService.checkOrderStatus(true));
    }
}