public class TriangleInequalityRealN {

    // Manually calculate the absolute value of a real number
    public static double absoluteValue(double x) {
        if (x < 0) {
            return -x; // If x is negative, make it positive
        }
        return x; // Otherwise, return x as is
    }

    // Method to check the triangle inequality for two real numbers
    public static boolean checkTriangleInequality(double a, double b) {
        // Calculate absolute values of a, b, and their sum
        double absA = absoluteValue(a);
        double absB = absoluteValue(b);
        double absSum = absoluteValue(a + b);

        // Check if |a + b| <= |a| + |b|
        return absSum <= absA + absB;
    }

    public static void main(String[] args) {
        // Test values
        double a = 3, b = -4;

        // Check the triangle inequality
        boolean isValid = checkTriangleInequality(a, b);

        // Print the result
        System.out.println("Triangle inequality holds for a = " + a + ", b = " + b + "? " + isValid);
    }
}

