public class ArchimedeanProperty {
    // Find the smallest n such that n * a > b
    public static int findN(double a, double b) {
        // Archimedean property applies only to positive numbers
        if (a <= 0 || b <= 0) {
            throw new IllegalArgumentException("Both a and b must be positive.");
        }

        int n = 1; // Start with the smallest natural number
        while (n * a <= b) { // Check if n * a is still less than or equal to b
            n++; // Increment n
        }
        return n; // Return the first n satisfying n * a > b
    }

    public static void main(String[] args) {
        // Test values
        double a = 0.5, b = 5;

        // Display the result of the Archimedean property
        System.out.println("Smallest n such that n * a > b: " + findN(a, b)); // Should return 11
    }
}
