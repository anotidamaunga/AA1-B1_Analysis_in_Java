public class SetFunctions {

    // Function to add numbers manually and track used values
    public static boolean isInjective(int[] domain) {
        // Store calculated outputs
        int[] usedOutputs = new int[domain.length];
        int outputCount = 0;

        // Go through each input value
        for(int i = 0; i < domain.length; i++) {
            // Calculate output (example: multiply by 2)
            int output = domain[i] * 2;

            // Check if output was already used
            for(int j = 0; j < outputCount; j++) {
                if(output == usedOutputs[j]) {
                    return false;  // Found duplicate output
                }
            }

            // If not used, store the output
            usedOutputs[outputCount] = output;
            outputCount++;
        }
        return true;  // No duplicates found
    }

    // Check if function maps to all codomain values
    public static boolean isSurjective(int[] domain, int[] codomain) {
        // Check each value in codomain
        for(int i = 0; i < codomain.length; i++) {
            boolean found = false;

            // Look through domain values
            for(int j = 0; j < domain.length; j++) {
                // Calculate output (example: multiply by 2)
                int output = domain[j] * 2;

                if(output == codomain[i]) {
                    found = true;  // Found matching output
                    break;
                }
            }

            if(!found) {
                return false;  // Missing codomain value
            }
        }
        return true;  // All values mapped to
    }

    // Map function to each domain value
    public static int[] mapFunction(int[] domain) {
        int[] results = new int[domain.length];

        // Apply function to each value
        for(int i = 0; i < domain.length; i++) {
            results[i] = domain[i] * 2;  // Example function
        }

        return results;
    }

    public static void main(String[] args) {

    }
}