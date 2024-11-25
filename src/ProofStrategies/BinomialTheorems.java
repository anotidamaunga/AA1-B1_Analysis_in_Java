package ProofStrategies;

public class BinomialTheorems {
    public static void main(String[] args) {
        int a = 1; // Coefficient of 'a'
        int b = 2; // Coefficient of 'b'
        int n = 3; // Power

        // Expand and print the binomial
        System.out.println("Expansion of (" + a + " + " + b + ")^" + n + ":");
        expandBinomial(a, b, n);
    }

    // Method to expand (a + b)^n using the Binomial Theorem
    static void expandBinomial(int a, int b, int n) {
        for (int k = 0; k <= n; k++) { // Iterate from k = 0 to k = n
            int coefficient = binomialCoefficient(n, k); // Calculate binomial coefficient
            int term = coefficient * pow(a, n - k) * pow(b, k); // Compute the term
            System.out.print(term + (k < n ? " + " : "")); // Print the term with formatting
        }
        System.out.println();
    }

    // Manual calculation of binomial coefficient
    static int binomialCoefficient(int n, int k) {
        return factorial(n) / (factorial(k) * factorial(n - k)); // C(n, k) = n! / (k!(n-k)!)
    }

    // Manual factorial calculation
    static int factorial(int n) {
        int result = 1; // Start with 1
        for (int i = 1; i <= n; i++) { // Multiply numbers from 1 to n
            result *= i;
        }
        return result; // Return the factorial value
    }

    // Manual power calculation
    static int pow(int base, int exponent) {
        int result = 1; // Start with 1
        for (int i = 0; i < exponent; i++) { // Multiply base by itself exponent times
            result *= base;
        }
        return result; // Return the power result
    }
}
