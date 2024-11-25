public class OrderedField {
    // Positivity Check: Determines if a number is positive (greater than 0)
    public static boolean isPositive(double x) {
        return x > 0; // Positive numbers are greater than zero
    }

    // Addition Preserves Positivity: If a > 0 and b > 0, then a + b > 0
    public static boolean additionPreservesPositivity(double a, double b) {
        // Ensure both numbers are positive and check if their sum is positive
        return isPositive(a) && isPositive(b) && isPositive(a + b);
    }

    // Multiplication Preserves Positivity: If a > 0 and b > 0, then a * b > 0
    public static boolean multiplicationPreservesPositivity(double a, double b) {
        // Ensure both numbers are positive and check if their product is positive
        return isPositive(a) && isPositive(b) && isPositive(a * b);
    }

    public static void main(String[] args) {
        // Test values
        double a = 3, b = 4;

        // Test and display results for ordered field properties
        System.out.println("a is positive? " + isPositive(a)); // Should return true
        System.out.println("Addition preserves positivity? " + additionPreservesPositivity(a, b)); // True
        System.out.println("Multiplication preserves positivity? " + multiplicationPreservesPositivity(a, b)); // True
    }
}
