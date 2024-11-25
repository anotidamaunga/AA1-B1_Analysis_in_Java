// Main class for set theory operations
public class Main {

    // Combines elements from two sets without duplicates
    public static int[] union(int[] setA, int[] setB) {
        // Result array can be at most sum of both sets' lengths
        int[] result = new int[setA.length + setB.length];
        int index = 0;

        // First add all elements from setA to result
        for(int i = 0; i < setA.length; i++) {
            result[index++] = setA[i];
        }

        // Check each element in setB
        for(int i = 0; i < setB.length; i++) {
            boolean exists = false;
            // Compare with all elements already in result
            for(int j = 0; j < index; j++) {
                if(setB[i] == result[j]) {
                    exists = true;  // Element already exists
                    break;
                }
            }
            // If element not found, add it
            if(!exists) {
                result[index++] = setB[i];
            }
        }

        // Create new array with exact size needed
        int[] trimmed = new int[index];
        for(int i = 0; i < index; i++) {
            trimmed[i] = result[i];
        }
        return trimmed;
    }

    // Finds elements that exist in both sets
    public static int[] intersection(int[] setA, int[] setB) {
        // Result cannot be larger than smallest set
        int[] temp = new int[Math.min(setA.length, setB.length)];
        int index = 0;

        // Check each element in setA
        for(int i = 0; i < setA.length; i++) {
            // Compare with each element in setB
            for(int j = 0; j < setB.length; j++) {
                if(setA[i] == setB[j]) {
                    // Found a match, add to result
                    temp[index++] = setA[i];
                    break;  // Move to next element in setA
                }
            }
        }

        // Create final array of exact size
        int[] result = new int[index];
        for(int i = 0; i < index; i++) {
            result[i] = temp[i];
        }
        return result;
    }

    // Finds elements in setA that are not in setB
    public static int[] difference(int[] setA, int[] setB) {
        // Result cannot be larger than setA
        int[] temp = new int[setA.length];
        int index = 0;

        // Check each element in setA
        for(int i = 0; i < setA.length; i++) {
            boolean exists = false;
            // Look for it in setB
            for(int j = 0; j < setB.length; j++) {
                if(setA[i] == setB[j]) {
                    exists = true;  // Found in setB
                    break;
                }
            }
            // If not in setB, add to result
            if(!exists) {
                temp[index++] = setA[i];
            }
        }

        // Create final array of exact size
        int[] result = new int[index];
        for(int i = 0; i < index; i++) {
            result[i] = temp[i];
        }
        return result;
    }

    // Checks if all elements of setA exist in setB
    public static boolean isSubset(int[] setA, int[] setB) {
        // Check each element in setA
        for(int i = 0; i < setA.length; i++) {
            boolean found = false;
            // Look for match in setB
            for(int j = 0; j < setB.length; j++) {
                if(setA[i] == setB[j]) {
                    found = true;
                    break;  // Found this element, move to next
                }
            }
            // If any element not found, not a subset
            if(!found) return false;
        }
        return true;  // All elements were found
    }

    // Checks if setA is subset of setB and not equal
    public static boolean isProperSubset(int[] setA, int[] setB) {
        return isSubset(setA, setB) && setA.length < setB.length;
    }

    // Creates pairs of all possible combinations
    public static int[][] cartesianProduct(int[] setA, int[] setB) {
        // Result will have size of setA * setB
        int[][] product = new int[setA.length * setB.length][2];
        int index = 0;

        // Take each element from setA
        for(int i = 0; i < setA.length; i++) {
            // Pair it with each element from setB
            for(int j = 0; j < setB.length; j++) {
                product[index][0] = setA[i];  // First element of pair
                product[index][1] = setB[j];  // Second element of pair
                index++;
            }
        }
        return product;
    }

    public static void main(String[] args) {


    }
}