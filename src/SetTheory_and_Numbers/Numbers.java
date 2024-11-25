public class Numbers {
    // Natural numbers (N)
    public static boolean isNatural(int n) {
        return n > 0; // Natural numbers are positive integers
    }

    // Whole numbers (N0)
    public static boolean isWhole(int n) {
        return n >= 0; // Whole numbers include 0
    }

    // Integers (Z)
    public static boolean isInteger(double n) {
        return n % 1 == 0; // No fractional part
    }

    // Rational numbers (Q)
    public static boolean isRational(int numerator, int denominator) {
        return denominator != 0; // Any fraction with a non-zero denominator
    }

    // Real numbers (R) - Java supports double for real numbers
    public static boolean isReal(double n) {
        return !Double.isNaN(n); // Excludes NaN (Not-a-Number)
    }

    public static void main(String[] args) {
        System.out.println("5 is natural? " + isNatural(5));
        System.out.println("0 is whole? " + isWhole(0));
        System.out.println("4.0 is an integer? " + isInteger(4.0));
        System.out.println("1/2 is rational? " + isRational(1, 2));
        System.out.println("2.718 is real? " + isReal(2.718));
    }
}
