package ProofStrategies;

public class RecursiveSummation {
    public static void main(String[] args) {
        // Example array for summation
        int[] array = {1, 2, 3, 4, 5};
        // Define the start and end indices (1-based)
        int m = 1; // Start of range
        int n = 5; // End of range

        // Calculate the sum recursively and print the result
        System.out.println("Recursive sum from index " + m + " to " + n + ": " + sumRecursive(array, m, n));
    }

    // Method to compute summation using recursion
    static int sumRecursive(int[] array, int start, int end) {
        // Base case: If start equals end, return the element at that position
        if (start == end) {
            return array[start - 1]; // Convert 1-based index to 0-based index
        }
        // Recursive step: Add the current element to the sum of the rest of the range
        return array[start - 1] + sumRecursive(array, start + 1, end);
    }
}
