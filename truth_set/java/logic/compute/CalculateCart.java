public class CalculateCart {
    public static String calculateCart(String price1, String price2) {
        return price1 + price2;
    }

    public static void main(String[] args) {
        System.out.println(calculateCart("19.99", "3"));
    }
}