package SeriesandSequencies;

/*
 * SUBSEQUENCES AND ACCUMULATION POINTS
 *
 * A subsequence is a sequence obtained by taking some terms of the original sequence
 * while maintaining their order.
 *
 * For example, if sequence is {1, 2, 3, 4, 5}
 * Valid subsequences include:
 * - {1, 3, 5}
 * - {2, 4}
 * - {1, 2, 3}
 *
 * An accumulation point (or cluster point) is a value that the sequence
 * terms get arbitrarily close to infinitely often.
 */
public class SubsequencesAndAccumulationPoint {
    public static class Sequence {
        private double[] elements;
        private int n0;

        public Sequence(double[] elements, int startIndex) {
            this.elements = elements;
            this.n0 = startIndex;
        }

        // Custom helper functions
        private double absoluteValue(double x) {
            return x >= 0 ? x : -x;
        }

        private double distance(double a, double b) {
            return absoluteValue(a - b);
        }

        private boolean isLessThan(double a, double b) {
            return (b - a) > 1e-10;
        }

        /*
         * Extract a subsequence based on given indices
         * Parameters:
         * - indices: array of indices to select from original sequence
         * Returns: new Sequence containing the subsequence
         */
        public Sequence getSubsequence(int[] indices) {
            // Validate indices
            for (int index : indices) {
                if (index < 0 || index >= elements.length) {
                    throw new IllegalArgumentException("Invalid index: " + index);
                }
            }

            // Create subsequence
            double[] subsequenceElements = new double[indices.length];
            for (int i = 0; i < indices.length; i++) {
                subsequenceElements[i] = elements[indices[i]];
            }

            return new Sequence(subsequenceElements, n0);
        }

        /*
         * Get all monotone subsequences
         * Uses Erdős–Szekeres theorem: Any sequence of length n contains a monotone
         * subsequence of length at least √n
         */
        public Sequence[] getMonotoneSubsequences() {
            // Store indices of increasing and decreasing subsequences
            int[] increasingIndices = new int[elements.length];
            int[] decreasingIndices = new int[elements.length];
            int incLength = 0, decLength = 0;

            for (int i = 0; i < elements.length; i++) {
                boolean foundIncreasing = false;
                boolean foundDecreasing = false;

                for (int j = 0; j < i; j++) {
                    if (isLessThan(elements[j], elements[i])) {
                        foundIncreasing = true;
                    }
                    if (isLessThan(elements[i], elements[j])) {
                        foundDecreasing = true;
                    }
                }

                if (!foundIncreasing) {
                    increasingIndices[incLength++] = i;
                }
                if (!foundDecreasing) {
                    decreasingIndices[decLength++] = i;
                }
            }

            // Create subsequences
            int[] incSubseq = new int[incLength];
            int[] decSubseq = new int[decLength];

            for (int i = 0; i < incLength; i++) {
                incSubseq[i] = increasingIndices[i];
            }
            for (int i = 0; i < decLength; i++) {
                decSubseq[i] = decreasingIndices[i];
            }

            return new Sequence[]{
                    getSubsequence(incSubseq),
                    getSubsequence(decSubseq)
            };
        }

        /*
         * Check if a value is an accumulation point
         * A value is an accumulation point if infinitely many terms
         * of the sequence get arbitrarily close to it
         */
        public boolean isAccumulationPoint(double point, double epsilon) {
            int count = 0;
            for (double element : elements) {
                if (distance(element, point) < epsilon) {
                    count++;
                }
            }

            // If we find enough terms close to the point, it might be an accumulation point
            return count >= 3; // Simplified check for finite sequences
        }
    }

    public static void main(String[] args) {
        // Test Case 1: Simple sequence {1, 2, 3, 4, 5}
        System.out.println("Testing sequence {1, 2, 3, 4, 5}:");
        double[] simpleSeq = {1, 2, 3, 4, 5};
        Sequence seq1 = new Sequence(simpleSeq, 1);

        // Test subsequence extraction
        int[] indices = {0, 2, 4}; // Should give {1, 3, 5}
        Sequence subseq = seq1.getSubsequence(indices);
        System.out.println("Subsequence {1, 3, 5}:");
        for (double element : subseq.elements) {
            System.out.print(element + " ");
        }

        // Test monotone subsequences
        System.out.println("\n\nMonotone subsequences:");
        Sequence[] monotoneSubseqs = seq1.getMonotoneSubsequences();
        for (int i = 0; i < monotoneSubseqs.length; i++) {
            System.out.print("Subsequence " + (i+1) + ": ");
            for (double element : monotoneSubseqs[i].elements) {
                System.out.print(element + " ");
            }
            System.out.println();
        }

        // Test Case 2: Oscillating sequence {1, -1, 1, -1, 1}
        System.out.println("\nTesting oscillating sequence {1, -1, 1, -1, 1}:");
        double[] oscillatingSeq = {1, -1, 1, -1, 1};
        Sequence seq2 = new Sequence(oscillatingSeq, 1);

        // Test accumulation points
        System.out.println("Is 1 an accumulation point (ε=0.1)? " +
                seq2.isAccumulationPoint(1.0, 0.1));
        System.out.println("Is -1 an accumulation point (ε=0.1)? " +
                seq2.isAccumulationPoint(-1.0, 0.1));
        System.out.println("Is 0 an accumulation point (ε=0.1)? " +
                seq2.isAccumulationPoint(0.0, 0.1));
    }
}