package Differentiation;

/*
 * Implementation of convexity and concavity concepts:
 * 1. A function is convex if f''(x) ≥ 0 for all x
 * 2. A function is concave if f''(x) ≤ 0 for all x
 * 3. Inflection points occur where f''(x) = 0 and f'' changes sign
 * 4. For convex functions, any local minimum is a global minimum
 */
public class ConvexityAnalysis {
    interface Function {
        double evaluate(double x);
    }

    private static final double H = 1e-7;       // For derivative approximation
    private static final double TOLERANCE = 1e-10; // For zero comparisons

    /*
     * Custom math helper functions
     */
    private static double absoluteValue(double x) {
        return x >= 0 ? x : -x;
    }

    private static boolean isZero(double x) {
        return absoluteValue(x) < TOLERANCE;
    }

    /*
     * First derivative approximation
     */
    private static double firstDerivative(Function f, double x) {
        return (f.evaluate(x + H) - f.evaluate(x)) / H;
    }

    /*
     * Second derivative approximation
     */
    private static double secondDerivative(Function f, double x) {
        return (firstDerivative(f, x + H) - firstDerivative(f, x)) / H;
    }

    /*
     * Checks if function is convex on an interval [a,b]
     * A function is convex if f''(x) ≥ 0 for all x in [a,b]
     */
    public static boolean isConvex(Function f, double a, double b, int numPoints) {
        double step = (b - a) / numPoints;

        for (double x = a; x <= b; x += step) {
            if (secondDerivative(f, x) < -TOLERANCE) {
                return false;
            }
        }
        return true;
    }

    /*
     * Checks if function is concave on an interval [a,b]
     * A function is concave if f''(x) ≤ 0 for all x in [a,b]
     */
    public static boolean isConcave(Function f, double a, double b, int numPoints) {
        double step = (b - a) / numPoints;

        for (double x = a; x <= b; x += step) {
            if (secondDerivative(f, x) > TOLERANCE) {
                return false;
            }
        }
        return true;
    }

    /*
     * Finds inflection points in interval [a,b]
     * Returns array of x-values where f''(x) = 0 and f'' changes sign
     */
    public static double[] findInflectionPoints(Function f, double a, double b, int numPoints) {
        double step = (b - a) / numPoints;
        double[] possiblePoints = new double[numPoints];
        int count = 0;

        double prevSecondDeriv = secondDerivative(f, a);

        for (double x = a + step; x <= b; x += step) {
            double currentSecondDeriv = secondDerivative(f, x);

            // Check if second derivative changes sign
            if (prevSecondDeriv * currentSecondDeriv < 0 ||
                    isZero(currentSecondDeriv)) {
                possiblePoints[count++] = x;
            }

            prevSecondDeriv = currentSecondDeriv;
        }

        // Create array of exact size
        double[] result = new double[count];
        for (int i = 0; i < count; i++) {
            result[i] = possiblePoints[i];
        }
        return result;
    }

    /*
     * Verifies convexity by checking if the line segment between any two points
     * lies above the graph of f
     */
    public static boolean verifyConvexityByDefinition(Function f, double a, double b, int numPoints) {
        double step = (b - a) / numPoints;

        for (double x1 = a; x1 <= b; x1 += step) {
            for (double x2 = x1 + step; x2 <= b; x2 += step) {
                // Test points between x1 and x2
                double lambda = 0.5; // Test midpoint
                double convexPoint = x1 + lambda * (x2 - x1);
                double functionValue = f.evaluate(convexPoint);
                double lineValue = f.evaluate(x1) + lambda * (f.evaluate(x2) - f.evaluate(x1));

                if (functionValue > lineValue + TOLERANCE) {
                    return false;  // Found a point that violates convexity
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        // Test with some common functions
        System.out.println("Analyzing various functions for convexity/concavity:");

        // Test 1: f(x) = x²
        Function quadratic = x -> x * x;
        System.out.println("\nFunction: f(x) = x²");
        System.out.println("Is convex on [-1, 1]: " +
                isConvex(quadratic, -1, 1, 100));
        System.out.println("Is concave on [-1, 1]: " +
                isConcave(quadratic, -1, 1, 100));
        System.out.println("Verified by definition: " +
                verifyConvexityByDefinition(quadratic, -1, 1, 100));

        // Test 2: f(x) = -x²
        Function negativeQuadratic = x -> -x * x;
        System.out.println("\nFunction: f(x) = -x²");
        System.out.println("Is convex on [-1, 1]: " +
                isConvex(negativeQuadratic, -1, 1, 100));
        System.out.println("Is concave on [-1, 1]: " +
                isConcave(negativeQuadratic, -1, 1, 100));

        // Test 3: f(x) = x³
        Function cubic = x -> x * x * x;
        System.out.println("\nFunction: f(x) = x³");
        System.out.println("Inflection points in [-2, 2]:");
        double[] inflectionPoints = findInflectionPoints(cubic, -2, 2, 100);
        for (double point : inflectionPoints) {
            System.out.printf("x = %.4f\n", point);
        }

        // Test 4: f(x) = sin(x) using Taylor series approximation
        Function sine = x -> {
            double sum = 0;
            for (int n = 0; n < 10; n++) {
                int sign = (n % 2 == 0) ? 1 : -1;
                sum += sign * power(x, 2*n + 1) / factorial(2*n + 1);
            }
            return sum;
        };

        System.out.println("\nFunction: f(x) = sin(x)");
        System.out.println("Inflection points in [0, 2π]:");
        inflectionPoints = findInflectionPoints(sine, 0, 2*3.14159, 100);
        for (double point : inflectionPoints) {
            System.out.printf("x = %.4f\n", point);
        }
    }

    // Helper methods for sine approximation
    private static double power(double x, int n) {
        double result = 1;
        for (int i = 0; i < n; i++) {
            result *= x;
        }
        return result;
    }

    private static double factorial(int n) {
        double result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }
}