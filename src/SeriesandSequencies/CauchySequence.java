package SeriesandSequencies;

/*
 * CAUCHY SEQUENCES
 *
 * A sequence {aₙ} is Cauchy if:
 * For any ε > 0, there exists N such that |aₘ - aₙ| < ε for all m,n ≥ N
 *
 * In simpler terms:
 * - The terms of the sequence get arbitrarily close to each other
 * - Terms become closer and closer as we move further in the sequence
 *
 * Examples:
 * - 1/n is Cauchy: terms get arbitrarily close to 0 and each other
 * - n is not Cauchy: terms keep getting further apart
 */
public class CauchySequence {
    public static class Sequence {
        private double[] elements;
        private int n0;

        public Sequence(double[] elements, int startIndex) {
            this.elements = elements;
            this.n0 = startIndex;
        }

        // Custom absolute value function
        private double absoluteValue(double x) {
            return x >= 0 ? x : -x;
        }

        // Custom minimum function
        private int minimum(int a, int b) {
            return a <= b ? a : b;
        }

        // Custom maximum function
        private int maximum(int a, int b) {
            return a >= b ? a : b;
        }

        /*
         * Calculate distance between two terms
         * Replaces Math.abs(a - b) with our custom implementation
         */
        private double distance(double a, double b) {
            return absoluteValue(a - b);
        }

        /*
         * Check if sequence is Cauchy for a given epsilon
         * Parameters:
         * - epsilon: the error tolerance ε
         * - windowSize: how many terms to check (for practical implementation)
         */
        public boolean isCauchyWithWindow(double epsilon, int windowSize) {
            if (elements.length < 2) return true;

            // Look for the point N where terms get close enough
            for (int N = 0; N < elements.length; N++) {
                boolean isCauchyFromN = true;

                // Check terms in windows of size windowSize after N
                for (int i = N; i < elements.length; i++) {
                    int endWindow = minimum(i + windowSize, elements.length);

                    // Compare all terms in the window with each other
                    for (int j = i + 1; j < endWindow; j++) {
                        if (distance(elements[i], elements[j]) >= epsilon) {
                            isCauchyFromN = false;
                            break;
                        }
                    }

                    if (!isCauchyFromN) break;
                }

                if (isCauchyFromN) return true;
            }

            return false;
        }

        /*
         * Check if sequence appears to be Cauchy
         * This method checks with progressively smaller epsilon values
         */
        public boolean isCauchy() {
            // Test with different epsilon values
            double[] epsilonTests = {1.0, 0.1, 0.01};

            for (double epsilon : epsilonTests) {
                if (!isCauchyWithWindow(epsilon, 5)) {
                    return false;
                }
            }
            return true;
        }

        /*
         * Estimate how quickly sequence converges (if it does)
         * Returns the rate of convergence (distance between consecutive terms)
         */
        public double[] getConvergenceRate(int numTerms) {
            numTerms = minimum(numTerms, elements.length - 1);
            double[] rates = new double[numTerms];

            for (int i = 0; i < numTerms; i++) {
                rates[i] = distance(elements[i], elements[i + 1]);
            }

            return rates;
        }
    }

    public static void main(String[] args) {
        // Test Case 1: 1/n sequence (Cauchy)
        System.out.println("Testing sequence 1/n:");
        double[] convergentSeq = new double[10];
        for (int i = 0; i < convergentSeq.length; i++) {
            convergentSeq[i] = 1.0 / (i + 1);
        }
        Sequence seq1 = new Sequence(convergentSeq, 1);

        System.out.println("Is Cauchy? " + seq1.isCauchy());
        System.out.println("Is Cauchy with ε=0.1? " + seq1.isCauchyWithWindow(0.1, 3));
        System.out.println("\nConvergence rates:");
        double[] rates1 = seq1.getConvergenceRate(5);
        for (int i = 0; i < rates1.length; i++) {
            System.out.printf("Between terms %d and %d: %f%n", i+1, i+2, rates1[i]);
        }

        // Test Case 2: n sequence (not Cauchy)
        System.out.println("\nTesting sequence n:");
        double[] divergentSeq = new double[10];
        for (int i = 0; i < divergentSeq.length; i++) {
            divergentSeq[i] = i + 1;
        }
        Sequence seq2 = new Sequence(divergentSeq, 1);

        System.out.println("Is Cauchy? " + seq2.isCauchy());
        System.out.println("Is Cauchy with ε=0.1? " + seq2.isCauchyWithWindow(0.1, 3));
        System.out.println("\nConvergence rates:");
        double[] rates2 = seq2.getConvergenceRate(5);
        for (int i = 0; i < rates2.length; i++) {
            System.out.printf("Between terms %d and %d: %f%n", i+1, i+2, rates2[i]);
        }

        // Test Case 3: Constant sequence (Cauchy)
        System.out.println("\nTesting constant sequence {1, 1, 1, 1, 1}:");
        double[] constantSeq = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        Sequence seq3 = new Sequence(constantSeq, 1);

        System.out.println("Is Cauchy? " + seq3.isCauchy());
        System.out.println("Is Cauchy with ε=0.1? " + seq3.isCauchyWithWindow(0.1, 3));
        System.out.println("\nConvergence rates:");
        double[] rates3 = seq3.getConvergenceRate(5);
        for (int i = 0; i < rates3.length; i++) {
            System.out.printf("Between terms %d and %d: %f%n", i+1, i+2, rates3[i]);
        }
    }
}