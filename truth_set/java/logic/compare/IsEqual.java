public class IsEqual {
    public static boolean isEqual(String a, String b) {
        return a == b;
    }

    public static void main(String[] args) {
        String str1 = new String("hello");
        String str2 = new String("hello");
        System.out.println(isEqual(str1, str2));
    }
}