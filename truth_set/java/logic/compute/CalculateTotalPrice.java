public class CalculateTotalPrice {
    public static double calculateTotalPrice(double price, double discount, double tax) {
        return price * (1 - discount + tax);
    }

    public static void main(String[] args) {
        double price = 100;
        double discount = 0.10;
        double tax = 0.05;
        System.out.println(calculateTotalPrice(price, discount, tax));
    }
}