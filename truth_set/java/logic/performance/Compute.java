public class Compute {
    private static double compute(double x) {
        // Expensive computation
        double result = 0;
        for (int i = 0; i < 1000000; i++) {
            result += Math.sqrt(x + i);
        }
        return result;
    }

    public static void main(String[] args) {
        double[] numbers = {10, 20, 30};
        for (double num : numbers) {
            System.out.printf("Value: %f%n", compute(num));
            System.out.printf("Double: %f%n", compute(num) * 2);
            System.out.printf("Half: %f%n", compute(num) / 2);
        }
    }
}