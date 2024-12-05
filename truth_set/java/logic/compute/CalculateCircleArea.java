public class CalculateCircleArea {
    public static double calculateCircleArea(double radius) {
        double diameter = radius * 2;
        return Math.PI * diameter * diameter;
    }

    public static void main(String[] args) {
        double radius = 5;
        System.out.println(calculateCircleArea(radius));
    }
}