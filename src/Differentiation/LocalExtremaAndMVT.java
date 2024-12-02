package Differentiation;

/*
 * Implementation of:
 * 1. Local extrema detection (minima and maxima)
 * 2. Rolle's Theorem
 * 3. Mean Value Theorem
 * 4. First and Second Derivative Tests
 */
public class LocalExtremaAndMVT {
    interface Function {
        double evaluate(double x);
    }

    private static final double H = 1e-7;  // Small value for derivative approximation
    private static final double TOLERANCE = 1e-10;  // Tolerance for zero comparisons

    /*
     * Custom absolute value and comparison functions
     */
    private static double absoluteValue(double x) {
        return x >= 0 ? x : -x;
    }

    private static boolean isZero(double x) {
        return absoluteValue(x) < TOLERANCE;
    }

    /*
     * Calculate first derivative at a point
     */
    private static double firstDerivative(Function f, double x) {
        return (f.evaluate(x + H) - f.evaluate(x)) / H;
    }

    /*
     * Calculate second derivative at a point
     * Uses the derivative of the derivative
     */
    private static double secondDerivative(Function f, double x) {
        Function firstDeriv = (t) -> firstDerivative(f, t);
        return firstDerivative(firstDeriv, x);
    }

    /*
     * Check if a point is a stationary point (f'(x) = 0)
     */
    public static boolean isStationaryPoint(Function f, double x) {
        return isZero(firstDerivative(f, x));
    }

    /*
     * First Derivative Test
     * Returns: 1 for local minimum, -1 for local maximum, 0 for neither
     */
    public static int firstDerivativeTest(Function f, double x) {
        if (!isStationaryPoint(f, x)) {
            return 0;  // Not a stationary point
        }

        double leftDeriv = firstDerivative(f, x - H);
        double rightDeriv = firstDerivative(f, x + H);

        if (leftDeriv < 0 && rightDeriv > 0) {
            return 1;  // Local minimum
        } else if (leftDeriv > 0 && rightDeriv < 0) {
            return -1;  // Local maximum
        }

        return 0;  // Neither
    }

    /*
     * Second Derivative Test
     * Returns: 1 for local minimum, -1 for local maximum, 0 for inconclusive
     */
    public static int secondDerivativeTest(Function f, double x) {
        if (!isStationaryPoint(f, x)) {
            return 0;  // Not a stationary point
        }

        double secondDeriv = secondDerivative(f, x);

        if (secondDeriv > 0) {
            return 1;  // Local minimum
        } else if (secondDeriv < 0) {
            return -1;  // Local maximum
        }

        return 0;  // Inconclusive
    }

    /*
     * Check if function satisfies Rolle's Theorem conditions on [a,b]
     * 1. f is continuous on [a,b]
     * 2. f is differentiable on (a,b)
     * 3. f(a) = f(b)
     */
    public static boolean satisfiesRolleTheorem(Function f, double a, double b) {
        // Check if f(a) = f(b)
        return isZero(f.evaluate(a) - f.evaluate(b));
    }

    /*
     * Find approximate c that satisfies Mean Value Theorem
     * Returns a point c in (a,b) where f'(c) = [f(b) - f(a)]/(b - a)
     */
    public static double findMeanValuePoint(Function f, double a, double b) {
        double meanSlope = (f.evaluate(b) - f.evaluate(a)) / (b - a);

        // Search for point where derivative equals mean slope
        int steps = 1000;
        double stepSize = (b - a) / steps;

        for (int i = 0; i < steps; i++) {
            double x = a + i * stepSize;
            if (isZero(firstDerivative(f, x) - meanSlope)) {
                return x;
            }
        }

        throw new RuntimeException("Mean value point not found");
    }

    public static void main(String[] args) {
        // Test function f(x) = x³ - 3x
        Function f = x -> x * x * x - 3 * x;

        System.out.println("Analysis of f(x) = x³ - 3x");

        // Find critical points
        double[] testPoints = {-2, -1, 0, 1, 2};
        System.out.println("\nCritical Points Analysis:");
        for (double x : testPoints) {
            if (isStationaryPoint(f, x)) {
                System.out.printf("x = %.1f is a stationary point\n", x);
                int result = secondDerivativeTest(f, x);
                if (result == 1) {
                    System.out.println("It's a local minimum");
                } else if (result == -1) {
                    System.out.println("It's a local maximum");
                }
            }
        }

        // Test Mean Value Theorem
        double a = -1, b = 1;
        System.out.println("\nMean Value Theorem on [-1, 1]:");
        try {
            double c = findMeanValuePoint(f, a, b);
            System.out.printf("Found c = %.4f where f'(c) = average rate of change\n", c);
            double actualDeriv = firstDerivative(f, c);
            double meanSlope = (f.evaluate(b) - f.evaluate(a)) / (b - a);
            System.out.printf("f'(c) = %.4f, average rate = %.4f\n",
                    actualDeriv, meanSlope);
        } catch (RuntimeException e) {
            System.out.println("Could not find mean value point");
        }
    }
}