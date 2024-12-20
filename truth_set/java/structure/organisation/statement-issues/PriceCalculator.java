public class PriceCalculator {
    public static double calculateTotalPrice(double[] items, double taxRate) {
        double subtotal;
        subtotal = 0;
        for (double item : items) {
            subtotal += item;
        }

        double total;
        total = subtotal + subtotal * taxRate;

        return total;
    }

    public static void main(String[] args) {
        double[] items = {29.99, 9.99, 4.99};
        double taxRate = 0.07; // 7% tax

        double totalPrice = calculateTotalPrice(items, taxRate);
        System.out.printf("Total Price: $%.2f%n", totalPrice);
    }
}