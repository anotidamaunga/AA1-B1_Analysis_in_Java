package Differentiation;

/*
 * Implementation of basic differentiation rules:
 * 1. Sum Rule: (f + g)' = f' + g'
 * 2. Product Rule: (f·g)' = f'g + fg'
 * 3. Quotient Rule: (f/g)' = (f'g - fg')/g²
 * 4. Chain Rule: (f∘g)' = (f'∘g)·g'
 */
public class DifferentiationRules {
    // Interface to represent a mathematical function
    interface Function {
        double evaluate(double x);
    }

    // Small value for derivative approximation
    private static final double H = 1e-7;

    /*
     * Custom absolute value implementation
     */
    private static double absoluteValue(double x) {
        return x >= 0 ? x : -x;
    }

    /*
     * Base derivative approximation using difference quotient
     */
    private static double derivative(Function f, double x) {
        return (f.evaluate(x + H) - f.evaluate(x)) / H;
    }

    /*
     * Sum Rule: (f + g)' = f' + g'
     * Returns a new Function representing the derivative of f + g
     */
    public static Function sumRule(final Function f, final Function g) {
        return new Function() {
            public double evaluate(double x) {
                return derivative(f, x) + derivative(g, x);
            }
        };
    }

    /*
     * Product Rule: (f·g)' = f'g + fg'
     * Returns a new Function representing the derivative of f * g
     */
    public static Function productRule(final Function f, final Function g) {
        return new Function() {
            public double evaluate(double x) {
                return derivative(f, x) * g.evaluate(x) +
                        f.evaluate(x) * derivative(g, x);
            }
        };
    }

    /*
     * Quotient Rule: (f/g)' = (f'g - fg')/g²
     * Returns a new Function representing the derivative of f/g
     */
    public static Function quotientRule(final Function f, final Function g) {
        return new Function() {
            public double evaluate(double x) {
                double gx = g.evaluate(x);
                if (absoluteValue(gx) < H) {
                    throw new ArithmeticException("Division by zero in quotient rule");
                }
                return (derivative(f, x) * gx - f.evaluate(x) * derivative(g, x)) /
                        (gx * gx);
            }
        };
    }

    /*
     * Chain Rule: (f∘g)' = (f'∘g)·g'
     * Returns a new Function representing the derivative of f(g(x))
     */
    public static Function chainRule(final Function f, final Function g) {
        return new Function() {
            public double evaluate(double x) {
                return derivative(f, g.evaluate(x)) * derivative(g, x);
            }
        };
    }

    public static void main(String[] args) {
        // Test functions
        Function f = x -> x * x;        // f(x) = x²
        Function g = x -> x + 1;        // g(x) = x + 1
        Function h = x -> 2 * x + 3;    // h(x) = 2x + 3

        System.out.println("Testing Differentiation Rules at x = 2:");
        double x = 2.0;

        // Test Sum Rule
        System.out.println("\nSum Rule Test:");
        System.out.println("f(x) = x², g(x) = x + 1");
        Function sumDerivative = sumRule(f, g);
        System.out.printf("(f + g)'(2) = %.4f\n", sumDerivative.evaluate(x));
        System.out.println("Expected: 5.0000 (2·2 + 1)");

        // Test Product Rule
        System.out.println("\nProduct Rule Test:");
        System.out.println("f(x) = x², g(x) = x + 1");
        Function productDerivative = productRule(f, g);
        System.out.printf("(f·g)'(2) = %.4f\n", productDerivative.evaluate(x));
        System.out.println("Expected: 7.0000 (2x(x+1) + x²)");

        // Test Quotient Rule
        System.out.println("\nQuotient Rule Test:");
        System.out.println("f(x) = x², g(x) = x + 1");
        Function quotientDerivative = quotientRule(f, g);
        System.out.printf("(f/g)'(2) = %.4f\n", quotientDerivative.evaluate(x));

        // Test Chain Rule
        System.out.println("\nChain Rule Test:");
        System.out.println("f(x) = x², g(x) = 2x + 3");
        Function chainDerivative = chainRule(f, h);
        System.out.printf("(f∘h)'(2) = %.4f\n", chainDerivative.evaluate(x));
        System.out.println("Expected: 14.0000 (2·(2·2 + 3)·2)");
    }
}