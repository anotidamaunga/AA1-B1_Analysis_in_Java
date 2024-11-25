public class Intervals {
    // Check if a number is in a closed interval [a, b]
    public static boolean isInClosedInterval(double x, double a, double b) {
        return x >= a && x <= b; // x must be greater than or equal to a and less than or equal to b
    }

    // Check if a number is in an open interval (a, b)
    public static boolean isInOpenInterval(double x, double a, double b) {
        return x > a && x < b; // x must be strictly between a and b
    }

    // Check if a number is in a semi-open interval [a, b)
    public static boolean isInSemiOpenInterval(double x, double a, double b) {
        return x >= a && x < b; // x must be greater than or equal to a but strictly less than b
    }

    public static void main(String[] args) {
        // Test values
        double x = 3, a = 2, b = 5;

        // Display results for interval membership
        System.out.println("x in closed interval [a, b]? " + isInClosedInterval(x, a, b)); // True
        System.out.println("x in open interval (a, b)? " + isInOpenInterval(x, a, b)); // True
        System.out.println("x in semi-open interval [a, b)? " + isInSemiOpenInterval(x, a, b)); // True
    }
}
