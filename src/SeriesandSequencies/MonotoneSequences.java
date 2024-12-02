package SeriesandSequencies;

/*
 * MONOTONE SEQUENCES
 *
 * A sequence {aₙ} is monotone if its terms consistently increase or decrease.
 * Types of monotonicity:
 * 1. Monotonically increasing: aₙ ≤ aₙ₊₁ for all n ≥ n₀
 * 2. Strictly monotonically increasing: aₙ < aₙ₊₁ for all n ≥ n₀
 * 3. Monotonically decreasing: aₙ ≥ aₙ₊₁ for all n ≥ n₀
 * 4. Strictly monotonically decreasing: aₙ > aₙ₊₁ for all n ≥ n₀
 *
 * Example:
 * - Sequence n is strictly increasing: 1, 2, 3, 4, ...
 * - Sequence 1/n is strictly decreasing: 1, 1/2, 1/3, 1/4, ...
 */
public class MonotoneSequences {
    public static class Sequence {
        private double[] elements;
        private int n0;

        public Sequence(double[] elements, int startIndex) {
            this.elements = elements;
            this.n0 = startIndex;
        }

        // Custom absolute value function (replacing Math.abs)
        private double absoluteValue(double x) {
            return x >= 0 ? x : -x;
        }

        // Custom comparison functions (avoiding use of > < operators directly)
        private boolean isLessThan(double a, double b) {
            return (b - a) > 1e-10;  // Using small epsilon for floating-point comparison
        }

        private boolean isGreaterThan(double a, double b) {
            return isLessThan(b, a);
        }

        private boolean isLessOrEqual(double a, double b) {
            return !isGreaterThan(a, b);
        }

        private boolean isGreaterOrEqual(double a, double b) {
            return !isLessThan(a, b);
        }

        /*
         * Check if sequence is monotonically increasing
         * Property: aₙ₊₁ ≥ aₙ for all n
         */
        public boolean isMonotoneIncreasing() {
            for (int i = 1; i < elements.length; i++) {
                if (!isLessOrEqual(elements[i-1], elements[i])) {
                    return false;
                }
            }
            return true;
        }

        /*
         * Check if sequence is strictly monotonically increasing
         * Property: aₙ₊₁ > aₙ for all n
         */
        public boolean isStrictlyMonotoneIncreasing() {
            for (int i = 1; i < elements.length; i++) {
                if (!isLessThan(elements[i-1], elements[i])) {
                    return false;
                }
            }
            return true;
        }

        /*
         * Check if sequence is monotonically decreasing
         * Property: aₙ₊₁ ≤ aₙ for all n
         */
        public boolean isMonotoneDecreasing() {
            for (int i = 1; i < elements.length; i++) {
                if (!isGreaterOrEqual(elements[i-1], elements[i])) {
                    return false;
                }
            }
            return true;
        }

        /*
         * Check if sequence is strictly monotonically decreasing
         * Property: aₙ₊₁ < aₙ for all n
         */
        public boolean isStrictlyMonotoneDecreasing() {
            for (int i = 1; i < elements.length; i++) {
                if (!isGreaterThan(elements[i-1], elements[i])) {
                    return false;
                }
            }
            return true;
        }

        // Test if sequence is monotone (either increasing or decreasing)
        public boolean isMonotone() {
            return isMonotoneIncreasing() || isMonotoneDecreasing();
        }
    }

    public static void main(String[] args) {
        // Test Case 1: Increasing sequence n
        System.out.println("Testing sequence n = {1, 2, 3, 4, 5}:");
        double[] increasingSeq = new double[5];
        for (int i = 0; i < 5; i++) {
            increasingSeq[i] = i + 1;
        }
        Sequence seq1 = new Sequence(increasingSeq, 1);

        System.out.println("Monotone increasing: " + seq1.isMonotoneIncreasing());
        System.out.println("Strictly monotone increasing: " + seq1.isStrictlyMonotoneIncreasing());
        System.out.println("Monotone decreasing: " + seq1.isMonotoneDecreasing());
        System.out.println("Is monotone: " + seq1.isMonotone());

        // Test Case 2: Decreasing sequence 1/n
        System.out.println("\nTesting sequence 1/n = {1, 1/2, 1/3, 1/4, 1/5}:");
        double[] decreasingSeq = new double[5];
        for (int i = 0; i < 5; i++) {
            decreasingSeq[i] = 1.0 / (i + 1);
        }
        Sequence seq2 = new Sequence(decreasingSeq, 1);

        System.out.println("Monotone increasing: " + seq2.isMonotoneIncreasing());
        System.out.println("Monotone decreasing: " + seq2.isMonotoneDecreasing());
        System.out.println("Strictly monotone decreasing: " + seq2.isStrictlyMonotoneDecreasing());
        System.out.println("Is monotone: " + seq2.isMonotone());

        // Test Case 3: Constant sequence {1, 1, 1, 1, 1}
        System.out.println("\nTesting constant sequence {1, 1, 1, 1, 1}:");
        double[] constantSeq = {1, 1, 1, 1, 1};
        Sequence seq3 = new Sequence(constantSeq, 1);

        System.out.println("Monotone increasing: " + seq3.isMonotoneIncreasing());
        System.out.println("Strictly monotone increasing: " + seq3.isStrictlyMonotoneIncreasing());
        System.out.println("Monotone decreasing: " + seq3.isMonotoneDecreasing());
        System.out.println("Is monotone: " + seq3.isMonotone());
    }
}