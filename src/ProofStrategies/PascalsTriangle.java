package ProofStrategies;

public class PascalsTriangle {
    public static void main(String[] args) {
        int rows = 5; // Number of rows to generate

        // Generate and print Pascal's Triangle
        generatePascalsTriangle(rows);
    }

    // Method to generate and display Pascal's Triangle
    static void generatePascalsTriangle(int rows) {
        for (int i = 0; i < rows; i++) { // Iterate over each row
            int number = 1; // Initialize the first number in the row
            for (int j = 0; j <= i; j++) { // Iterate over each element in the row
                System.out.print(number + " "); // Print the current number
                number = number * (i - j) / (j + 1); // Compute the next number
            }
            System.out.println(); // Move to the next row
        }
    }
}
