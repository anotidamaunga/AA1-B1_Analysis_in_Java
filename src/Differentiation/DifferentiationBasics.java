package Differentiation;

/*
 * Step 1: Implementing Difference Quotient
 *
 * The difference quotient is defined as:
 * [f(x + h) - f(x)] / h
 *
 * This is the foundation for understanding derivatives, as the derivative
 * is the limit of this quotient as h approaches 0.
 */
public class DifferentiationBasics {
    // Interface to represent a mathematical function f(x)
    interface Function {
        double evaluate(double x);
    }

    /*
     * Custom absolute value function to avoid using Math.abs
     * Returns the absolute value of a number
     */
    private static double absoluteValue(double x) {
        return x >= 0 ? x : -x;
    }

    /*
     * Calculates difference quotient for a given function at point x with step h
     * This represents the slope of the secant line through points (x, f(x)) and (x+h, f(x+h))
     */
    public static double differenceQuotient(Function f, double x, double h) {
        // Check if h is zero to avoid division by zero
        if (absoluteValue(h) < 1e-10) {
            throw new IllegalArgumentException("h must not be zero");
        }

        // Calculate f(x + h) and f(x)
        double fx = f.evaluate(x);
        double fxPlusH = f.evaluate(x + h);

        // Return the difference quotient
        return (fxPlusH - fx) / h;
    }

    /*
     * Approximates the derivative at a point by using a very small h
     * This gives us a numerical approximation of the derivative
     */
    public static double approximateDerivative(Function f, double x) {
        // Use a small value for h
        double h = 1e-7;
        return differenceQuotient(f, x, h);
    }

    public static void main(String[] args) {
        // Example 1: f(x) = x²
        Function quadratic = new Function() {
            public double evaluate(double x) {
                return x * x;  // f(x) = x²
            }
        };

        // Test different h values to see how they affect the approximation
        // For f(x) = x², the actual derivative is f'(x) = 2x
        double x = 2.0;  // Test at x = 2, where f'(2) should be 4
        double[] hValues = {1.0, 0.1, 0.01, 0.001};

        System.out.println("Testing difference quotients for f(x) = x² at x = 2");
        System.out.println("Actual derivative should be 4.0");
        System.out.println("\nh-value\t\tApproximation");
        System.out.println("--------\t-------------");

        for (double h : hValues) {
            double approx = differenceQuotient(quadratic, x, h);
            System.out.printf("%.3f\t\t%.6f\n", h, approx);
        }

        // Example 2: f(x) = x³
        Function cubic = x -> x * x * x;  // f(x) = x³

        System.out.println("\nTesting difference quotients for f(x) = x³ at x = 2");
        System.out.println("Actual derivative should be 12.0");
        System.out.println("\nh-value\t\tApproximation");
        System.out.println("--------\t-------------");

        for (double h : hValues) {
            double approx = differenceQuotient(cubic, x, h);
            System.out.printf("%.3f\t\t%.6f\n", h, approx);
        }

        // Demonstrate derivative approximation
        System.out.println("\nApproximate derivatives at x = 2:");
        System.out.printf("f(x) = x²: %.6f\n", approximateDerivative(quadratic, x));
        System.out.printf("f(x) = x³: %.6f\n", approximateDerivative(cubic, x));
    }
}