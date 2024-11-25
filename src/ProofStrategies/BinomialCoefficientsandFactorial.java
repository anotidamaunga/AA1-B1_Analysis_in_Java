package ProofStrategies;

public class BinomialCoefficientsandFactorial {

    public static void main(String[] args) {
        // Example: Calculate factorials and binomial coefficients
        int n = 5; // Example value for n
        int k = 3; // Example value for k

        // Calculate factorial iteratively
        System.out.println("Iterative Factorial of " + n + ": " + factorialIterative(n));

        // Calculate factorial recursively
        System.out.println("Recursive Factorial of " + n + ": " + factorialRecursive(n));

        // Calculate binomial coefficient
        System.out.println("Binomial Coefficient (" + n + " choose " + k + "): " + binomialCoefficient(n, k));
    }

    // 1. Iterative Factorial
    // This method calculates n! using a loop
    static long factorialIterative(int n) {
        long result = 1; // Start with 1 as factorials multiply
        for (int i = 1; i <= n; i++) {
            result *= i; // Multiply result by i for each step
        }
        return result; // Return the final factorial value
    }

    // 2. Recursive Factorial
    // This method calculates n! using recursion
    static long factorialRecursive(int n) {
        if (n == 0 || n == 1) { // Base case: 0! = 1! = 1
            return 1;
        }
        return n * factorialRecursive(n - 1); // Recursive case: n! = n * (n-1)!
    }

    // 3. Binomial Coefficient
    // Calculate "n choose k" using the formula: C(n, k) = n! / (k! * (n - k)!)
    static long binomialCoefficient(int n, int k) {
        // Check if k is valid
        if (k < 0 || k > n) {
            return 0; // Binomial coefficient is 0 if k is invalid
        }
        // Use the factorial method to compute the formula
        return factorialIterative(n) / (factorialIterative(k) * factorialIterative(n - k));
    }
}
