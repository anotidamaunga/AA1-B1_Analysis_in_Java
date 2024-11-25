package ProofStrategies;

public class SumAndProduct {
    public static void main(String[] args) {
        // Example array for calculations
        int[] array = {1, 2, 3, 4, 5};
        // Define the start and end indices (1-based)
        int m = 1; // Start of range
        int n = 5; // End of range

        // Calculate and print the sum and product
        System.out.println("Sum from index " + m + " to " + n + ": " + sum(array, m, n));
        System.out.println("Product from index " + m + " to " + n + ": " + product(array, m, n));
    }

    // Method to compute the sum of elements in a given range
    static int sum(int[] array, int start, int end) {
        int result = 0; // Initialize the sum to 0
        for (int i = start - 1; i < end; i++) { // Loop through the range (convert 1-based to 0-based index)
            result += array[i]; // Add each element to the result
        }
        return result; // Return the total sum
    }

    // Method to compute the product of elements in a given range
    static int product(int[] array, int start, int end) {
        int result = 1; // Initialize the product to 1
        for (int i = start - 1; i < end; i++) { // Loop through the range (convert 1-based to 0-based index)
            result *= array[i]; // Multiply each element to the result
        }
        return result; // Return the total product
    }
}
