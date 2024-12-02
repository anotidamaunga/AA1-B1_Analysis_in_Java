package SeriesandSequencies;

/*
 * SERIES
 *
 * A series is the sum of terms in a sequence: ∑aₙ = a₁ + a₂ + a₃ + ...
 *
 * Important types of series:
 * 1. Geometric series: ar⁰ + ar¹ + ar² + ...
 * 2. Harmonic series: 1 + 1/2 + 1/3 + 1/4 + ...
 * 3. p-series: 1 + 1/2ᵖ + 1/3ᵖ + 1/4ᵖ + ...
 * 4. Alternating series: Terms alternate between positive and negative
 */
public class SeriesAndConvergences{
    public static class Series {
        private double[] terms;     // Original sequence terms
        private double[] partialSums; // Sequence of partial sums

        public Series(double[] terms) {
            this.terms = terms;
            computePartialSums();
        }

        // Custom helper functions
        private double absoluteValue(double x) {
            return x >= 0 ? x : -x;
        }

        private boolean isLessThan(double a, double b) {
            return (b - a) > 1e-10;
        }

        /*
         * Compute partial sums
         * Sₙ = a₁ + a₂ + ... + aₙ
         */
        private void computePartialSums() {
            partialSums = new double[terms.length];
            partialSums[0] = terms[0];

            for (int i = 1; i < terms.length; i++) {
                partialSums[i] = partialSums[i-1] + terms[i];
            }
        }

        /*
         * Check if series appears to converge
         * Uses ratio test: if |aₙ₊₁/aₙ| < 1 eventually, series converges
         */
        public boolean appearsToConverge() {
            for (int i = 1; i < terms.length; i++) {
                if (absoluteValue(terms[i]) >= absoluteValue(terms[i-1])) {
                    return false;
                }
            }
            return true;
        }

        /*
         * Generate geometric series: a, ar, ar², ar³, ...
         */
        public static Series geometricSeries(double a, double r, int n) {
            double[] terms = new double[n];
            double currentTerm = a;

            for (int i = 0; i < n; i++) {
                terms[i] = currentTerm;
                currentTerm *= r;
            }

            return new Series(terms);
        }

        /*
         * Generate harmonic series: 1, 1/2, 1/3, 1/4, ...
         */
        public static Series harmonicSeries(int n) {
            double[] terms = new double[n];

            for (int i = 0; i < n; i++) {
                terms[i] = 1.0 / (i + 1);
            }

            return new Series(terms);
        }

        /*
         * Generate alternating series: a₁, -a₂, a₃, -a₄, ...
         */
        public static Series alternatingSeries(double[] baseTerms) {
            double[] alternatingTerms = new double[baseTerms.length];

            for (int i = 0; i < baseTerms.length; i++) {
                alternatingTerms[i] = (i % 2 == 0) ? baseTerms[i] : -baseTerms[i];
            }

            return new Series(alternatingTerms);
        }

        /*
         * Test if series satisfies alternating series test
         * For convergence:
         * 1. Terms alternate in sign
         * 2. |aₙ| decreases
         * 3. lim aₙ = 0
         */
        public boolean satisfiesAlternatingTest() {
            if (terms.length < 2) return false;

            // Check alternating signs
            for (int i = 1; i < terms.length; i++) {
                if ((terms[i] > 0 && terms[i-1] > 0) ||
                        (terms[i] < 0 && terms[i-1] < 0)) {
                    return false;
                }
            }

            // Check decreasing absolute values
            for (int i = 1; i < terms.length; i++) {
                if (absoluteValue(terms[i]) >= absoluteValue(terms[i-1])) {
                    return false;
                }
            }

            // Check if terms appear to approach 0
            return absoluteValue(terms[terms.length - 1]) < 0.1;
        }

        // Get the nth partial sum
        public double getPartialSum(int n) {
            if (n < 0 || n >= partialSums.length) {
                throw new IllegalArgumentException("Invalid index");
            }
            return partialSums[n];
        }
    }

    public static void main(String[] args) {
        // Test Case 1: Geometric Series (r = 1/2)
        System.out.println("Testing geometric series (a=1, r=1/2):");
        Series geometric = Series.geometricSeries(1.0, 0.5, 10);
        System.out.println("First few terms:");
        for (int i = 0; i < 5; i++) {
            System.out.printf("S_%d = %.4f%n", i+1, geometric.getPartialSum(i));
        }
        System.out.println("Appears to converge: " + geometric.appearsToConverge());

        // Test Case 2: Harmonic Series
        System.out.println("\nTesting harmonic series:");
        Series harmonic = Series.harmonicSeries(10);
        System.out.println("First few terms:");
        for (int i = 0; i < 5; i++) {
            System.out.printf("S_%d = %.4f%n", i+1, harmonic.getPartialSum(i));
        }
        System.out.println("Appears to converge: " + harmonic.appearsToConverge());

        // Test Case 3: Alternating Series (1/n)
        System.out.println("\nTesting alternating harmonic series:");
        double[] baseTerms = new double[10];
        for (int i = 0; i < 10; i++) {
            baseTerms[i] = 1.0 / (i + 1);
        }
        Series alternating = Series.alternatingSeries(baseTerms);
        System.out.println("First few terms:");
        for (int i = 0; i < 5; i++) {
            System.out.printf("S_%d = %.4f%n", i+1, alternating.getPartialSum(i));
        }
        System.out.println("Satisfies alternating series test: " +
                alternating.satisfiesAlternatingTest());
    }
}