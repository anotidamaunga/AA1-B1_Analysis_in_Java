package SeriesandSequencies;

/*
 * In mathematical analysis, a sequence is a list of ordered numbers that follow a pattern.
 * Formally, it's a function from natural numbers (N) to real numbers (R).
 *
 * For example:
 * - The sequence 1/n: {1, 1/2, 1/3, 1/4, ...}
 * - The sequence n²: {1, 4, 9, 16, ...}
 *
 * A sequence can start from any natural number n₀ (usually 1 or 0).
 * We write sequences as {aₙ}ₙ≥ₙ₀ where:
 * - aₙ represents the nth term
 * - n₀ is the starting index
 */
public class SequencesAndBoundedness {
    // Class to represent a sequence of real numbers
    public static class Sequence {
        private double[] elements;  // Array to store sequence elements
        private int n0;            // Starting index of the sequence

        public Sequence(double[] elements, int startIndex) {
            this.elements = elements;
            this.n0 = startIndex;
        }

        /*
         * BOUNDEDNESS
         *
         * A sequence is bounded if all its terms lie between two real numbers.
         * - Bounded above: There exists B ∈ R such that aₙ ≤ B for all n ≥ n₀
         * - Bounded below: There exists b ∈ R such that aₙ ≥ b for all n ≥ n₀
         * - Bounded: The sequence is both bounded above and below
         *
         * Example:
         * - The sequence 1/n is bounded: 0 < 1/n ≤ 1 for all n ≥ 1
         * - The sequence n is unbounded above: no finite B can bound all terms
         */

        // Check if sequence is bounded from above
        public boolean isBoundedAbove() {
            if (elements.length == 0) return true;

            // Find the supremum (least upper bound)
            double B = elements[0];
            for (int i = 1; i < elements.length; i++) {
                if (elements[i] > B) {
                    B = elements[i];
                }
            }

            // Verify that B is indeed an upper bound
            for (double element : elements) {
                if (element > B) return false;
            }
            return true;
        }

        // Check if sequence is bounded from below
        public boolean isBoundedBelow() {
            if (elements.length == 0) return true;

            // Find the infimum (greatest lower bound)
            double b = elements[0];
            for (int i = 1; i < elements.length; i++) {
                if (elements[i] < b) {
                    b = elements[i];
                }
            }

            // Verify that b is indeed a lower bound
            for (double element : elements) {
                if (element < b) return false;
            }
            return true;
        }

        // Check if sequence is bounded (both above and below)
        public boolean isBounded() {
            return isBoundedAbove() && isBoundedBelow();
        }
    }

    // Test the implementation with examples
    public static void main(String[] args) {
        // Example 1: Sequence 1/n = {1, 1/2, 1/3, 1/4, 1/5}
        double[] convergentSeq = new double[5];
        for (int i = 0; i < convergentSeq.length; i++) {
            convergentSeq[i] = 1.0 / (i + 1);
        }
        Sequence seq1 = new Sequence(convergentSeq, 1);

        System.out.println("Sequence 1/n:");
        System.out.println("Values: ");
        for (double value : convergentSeq) {
            System.out.print(value + " ");
        }
        System.out.println("\nBounded above: " + seq1.isBoundedAbove());
        System.out.println("Bounded below: " + seq1.isBoundedBelow());
        System.out.println("Bounded: " + seq1.isBounded());

        // Example 2: Sequence n = {1, 2, 3, 4, 5}
        double[] divergentSeq = {1, 2, 3, 4, 5};
        Sequence seq2 = new Sequence(divergentSeq, 1);

        System.out.println("\nSequence n:");
        System.out.println("Values: ");
        for (double value : divergentSeq) {
            System.out.print(value + " ");
        }
        System.out.println("\nBounded above: " + seq2.isBoundedAbove());
        System.out.println("Bounded below: " + seq2.isBoundedBelow());
        System.out.println("Bounded: " + seq2.isBounded());
    }
}