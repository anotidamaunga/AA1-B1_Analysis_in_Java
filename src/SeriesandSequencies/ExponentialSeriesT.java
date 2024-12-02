package SeriesandSequencies;

/*
 * EXPONENTIAL SERIES
 *
 * The exponential series is defined as:
 * e^x = 1 + x + x²/2! + x³/3! + x⁴/4! + ...
 *
 * This series:
 * 1. Converges for all real x
 * 2. Defines the exponential function
 * 3. Has important properties like e^(x+y) = e^x * e^y
 *
 * We'll implement:
 * - Factorial calculation
 * - Power series computation
 * - Exponential series evaluation
 */
public class ExponentialSeriesT {
    public static class ExponentialSeries {
        private int maxTerms;      // Maximum number of terms to use
        private double tolerance;   // Error tolerance

        // Custom helper functions
        private double absoluteValue(double x) {
            return x >= 0 ? x : -x;
        }

        public ExponentialSeries(int maxTerms, double tolerance) {
            this.maxTerms = maxTerms;
            this.tolerance = tolerance;
        }

        /*
         * Calculate factorial without using recursion
         * n! = 1 * 2 * 3 * ... * n
         */
        private long factorial(int n) {
            if (n < 0) {
                throw new IllegalArgumentException("Factorial undefined for negative numbers");
            }

            long result = 1;
            for (int i = 2; i <= n; i++) {
                result *= i;
            }
            return result;
        }

        /*
         * Calculate power without using Math.pow
         * x^n = x * x * x * ... (n times)
         */
        private double power(double x, int n) {
            if (n < 0) {
                return 1.0 / power(x, -n);
            }

            double result = 1.0;
            for (int i = 0; i < n; i++) {
                result *= x;
            }
            return result;
        }

        /*
         * Calculate e^x using the series expansion
         * Stops when:
         * 1. Reached maxTerms
         * 2. Terms become smaller than tolerance
         */
        public double calculateExp(double x) {
            double sum = 1.0;  // First term is always 1
            double term = 1.0;

            for (int n = 1; n < maxTerms; n++) {
                // Calculate next term: x^n/n!
                term *= x / n;

                // Add term to sum
                sum += term;

                // Check if terms are getting small enough
                if (absoluteValue(term) < tolerance) {
                    break;
                }
            }

            return sum;
        }

        /*
         * Calculate terms of the series up to n terms
         * Returns array of partial sums
         */
        public double[] getPartialSums(double x, int n) {
            double[] sums = new double[n];
            sums[0] = 1.0;  // First term
            double term = 1.0;

            for (int i = 1; i < n; i++) {
                term *= x / i;
                sums[i] = sums[i-1] + term;
            }

            return sums;
        }

        /*
         * Estimate error using the next term
         * Error ≤ |next term| when series alternates and terms decrease
         */
        public double estimateError(double x, int terms) {
            if (terms <= 0) return Double.POSITIVE_INFINITY;

            return absoluteValue(
                    power(x, terms) / factorial(terms)
            );
        }
    }

    public static void main(String[] args) {
        ExponentialSeries exp = new ExponentialSeries(20, 1e-10);

        // Test Case 1: Calculate e^x for various x
        double[] testValues = {0, 1, -1, 0.5, -0.5, 2, -2};
        System.out.println("Exponential Function Values:");
        for (double x : testValues) {
            System.out.printf("e^%.1f ≈ %.10f%n", x, exp.calculateExp(x));
        }

        // Test Case 2: Show convergence for e^1
        System.out.println("\nConvergence of e^1 series:");
        double[] partialSums = exp.getPartialSums(1.0, 10);
        for (int i = 0; i < partialSums.length; i++) {
            System.out.printf("First %d terms: %.10f (error ≤ %.10f)%n",
                    i+1, partialSums[i], exp.estimateError(1.0, i+1));
        }

        // Test Case 3: Show convergence speed for different x
        System.out.println("\nConvergence speed for different x:");
        double[] xValues = {0.5, 1.0, 2.0};
        for (double x : xValues) {
            System.out.printf("\nFor x = %.1f:%n", x);
            for (int terms = 1; terms <= 5; terms++) {
                double error = exp.estimateError(x, terms);
                System.out.printf("Terms: %d, Error ≤ %.10f%n",
                        terms, error);
            }
        }
    }
}