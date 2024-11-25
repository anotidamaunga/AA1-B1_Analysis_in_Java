import java.util.List;

public class Completeness {

    /**
     * Finds the supremum (least upper bound) of a set manually.
     * Supremum is the largest number in the set.
     *
     * @param set The list of numbers
     * @return The supremum of the set
     */
    public static double manualSupremum(List<Double> set) {
        double supremum = Double.NEGATIVE_INFINITY; // Start with the smallest possible value

        // Loop through each number in the set
        for (double number : set) {
            // If the current number is greater than the current supremum, update it
            if (number > supremum) {
                supremum = number;
            }
        }
        return supremum; // Return the largest value found
    }

    /**
     * Finds the infimum (greatest lower bound) of a set manually.
     * Infimum is the smallest number in the set.
     *
     * @param set The list of numbers
     * @return The infimum of the set
     */
    public static double manualInfimum(List<Double> set) {
        double infimum = Double.POSITIVE_INFINITY; // Start with the largest possible value

        // Loop through each number in the set
        for (double number : set) {
            // If the current number is smaller than the current infimum, update it
            if (number < infimum) {
                infimum = number;
            }
        }
        return infimum; // Return the smallest value found
    }

    public static void main(String[] args) {
        // Define a finite set of numbers
        List<Double> set = List.of(3.5, 1.2, 7.8, 4.4, 2.9);

        // Calculate the supremum and infimum manually
        double supremum = manualSupremum(set);
        double infimum = manualInfimum(set);

        // Print the results
        System.out.println("Manual Supremum: " + supremum); // Should return 7.8
        System.out.println("Manual Infimum: " + infimum); // Should return 1.2
    }
}
