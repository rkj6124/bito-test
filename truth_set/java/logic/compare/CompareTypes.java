public class CompareTypes {
    public static boolean compareTypes(Object a, Object b) {
        return a.toString().compareTo(b.toString()) > 0;
    }

    public static void main(String[] args) {
        System.out.println(compareTypes("1", 2));  
        System.out.println(compareTypes("3", 2)); 
        System.out.println(compareTypes("abc", 2)); 
    }
}