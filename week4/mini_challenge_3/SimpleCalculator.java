public class SimpleCalculator {

   
    public int add(int a, int b) {
        return a + b;
    }

    
    public int multiply(int a, int b) {
        return a * b;
    }

   
    public boolean isEven(int number) {
        return number % 2 == 0;
    }

    
    public static void main(String[] args) {
        SimpleCalculator calculator = new SimpleCalculator();

        int x = 5;
        int y = 3;

        System.out.println("Add: " + calculator.add(x, y));         // Output: 8
        System.out.println("Multiply: " + calculator.multiply(x, y)); // Output: 15
        System.out.println("Is " + x + " even? " + calculator.isEven(x)); // Output: false
    }
}
