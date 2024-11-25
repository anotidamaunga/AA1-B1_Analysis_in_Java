package ProofStrategies;

// This program demonstrates the concept of mathematical induction using Java.
// Specifically, we will verify the formula for the sum of the first n positive integers: S(n) = n * (n + 1) / 2

public class MathematicalInduction {
    public static void main(String[] args) {
        // Define the range up to which we want to verify the formula
        int n = 10; // This means we are checking for n = 1 to n = 10

        // Call the main induction verification function
        if (verifyUsingInduction(n)) {
            System.out.println("The formula holds for all numbers from 1 to " + n);
        } else {
            System.out.println("The formula does not hold for one or more cases.");
        }
    }

    // This method combines the base case and inductive step for verification
    static boolean verifyUsingInduction(int n) {
        // Check the base case
        if (!baseCase()) { // If the base case fails, stop and return false
            return false;
        }

        // Check the inductive step
        return inductiveStep(n); // Returns true if all steps pass
    }

    // Base case: Verify the formula holds for the smallest input (n = 1)
    static boolean baseCase() {
        int n = 1; // Start with the smallest value of n
        int formulaResult = n * (n + 1) / 2; // Use the formula S(1) = n(n+1)/2
        int actualSum = 1; // For n = 1, the sum is just 1

        // Check if the calculated result matches the actual sum
        if (formulaResult == actualSum) {
            System.out.println("Base case passed: S(1) = " + formulaResult);
            return true; // Base case is verified
        } else {
            System.out.println("Base case failed: S(1) = " + formulaResult + ", but actual sum = " + actualSum);
            return false; // Base case failed
        }
    }

    // Inductive step: Assume S(k) holds and verify it holds for S(k + 1)
    static boolean inductiveStep(int n) {
        // Loop through values of k from 1 to n-1
        for (int k = 1; k < n; k++) {
            // Calculate S(k) using the formula
            int assumedSum = k * (k + 1) / 2;

            // Calculate S(k + 1) using the inductive definition: S(k+1) = S(k) + (k+1)
            int actualSum = assumedSum + (k + 1);

            // Verify it matches the formula for S(k + 1): (k+1)(k+2)/2
            int formulaResult = (k + 1) * (k + 2) / 2;

            // If the results don't match, print an error and return false
            if (actualSum != formulaResult) {
                System.out.println("Inductive step failed for k = " + k +
                        ": calculated = " + actualSum + ", formula = " + formulaResult);
                return false;
            }
        }

        // If all cases pass, print success and return true
        System.out.println("Inductive step passed for all k.");
        return true;
    }
}
