public class ProcessData {
    private static double[] processData(double[] data) {
        double[] newData = data.clone();
        for (int i = 0; i < newData.length; i++) {
            newData[i] *= 2;
        }
        return newData;
    }

    public static void main(String[] args) {
        double[] numbers = new double[1000000];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = i;
        }
        double[] processedArray = processData(numbers);
    }
}