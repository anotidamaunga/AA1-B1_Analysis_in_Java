package ProofStrategies;

import java.util.ArrayList;
import java.util.List;

public class Permutations {
    public static void main(String[] args) {
        // Input array for permutations
        int[] array = {1, 2, 3};

        // Generate and print all permutations
        List<List<Integer>> permutations = generatePermutations(array, 0);
        System.out.println("All permutations:");
        for (List<Integer> perm : permutations) {
            System.out.println(perm);
        }
    }

    // Method to generate all permutations using backtracking
    static List<List<Integer>> generatePermutations(int[] array, int start) {
        List<List<Integer>> results = new ArrayList<>(); // Store the result permutations

        // Base case: If we've reached the last element, record the current permutation
        if (start == array.length - 1) {
            List<Integer> current = new ArrayList<>();
            for (int num : array) { // Convert array to list
                current.add(num);
            }
            results.add(current); // Add this permutation to results
            return results; // Return the results
        }

        // Recursive case: Iterate over all elements to form permutations
        for (int i = start; i < array.length; i++) {
            swap(array, start, i); // Swap the current element with the start
            results.addAll(generatePermutations(array, start + 1)); // Recurse for the rest
            swap(array, start, i); // Swap back to restore the original array
        }
        return results; // Return the complete list of permutations
    }

    // Helper method to swap two elements in the array
    static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}

