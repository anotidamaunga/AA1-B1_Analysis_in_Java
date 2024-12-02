package SeriesandSequencies;
/*
 * CURVES IN ANALYSIS
 *
 * A curve can be represented in several ways:
 * 1. Parametric form: x = x(t), y = y(t)
 * 2. Explicit form: y = f(x)
 * 3. Implicit form: F(x,y) = 0
 *
 * We'll focus on parametric curves and their properties:
 * - Continuity
 * - Length
 * - Geometric properties
 */
public class Curves {
    public static class Point2D {
        private double x, y;

        public Point2D(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public double getX() { return x; }
        public double getY() { return y; }
    }

    public static class Curve {
        private interface ParametricFunction {
            double evaluate(double t);
        }

        private ParametricFunction xFunction;
        private ParametricFunction yFunction;
        private double tStart;
        private double tEnd;

        // Custom helper functions
        private double absoluteValue(double x) {
            return x >= 0 ? x : -x;
        }

        private double squareRoot(double x) {
            if (x < 0) throw new IllegalArgumentException("Cannot take square root of negative number");
            if (x == 0) return 0;

            // Newton's method for square root
            double guess = x / 2;
            double epsilon = 1e-10;

            while (absoluteValue(guess * guess - x) > epsilon) {
                guess = (guess + x/guess) / 2;
            }

            return guess;
        }

        public Curve(ParametricFunction x, ParametricFunction y,
                     double start, double end) {
            this.xFunction = x;
            this.yFunction = y;
            this.tStart = start;
            this.tEnd = end;
        }

        /*
         * Get point on curve at parameter t
         */
        public Point2D getPoint(double t) {
            if (t < tStart || t > tEnd) {
                throw new IllegalArgumentException("t out of range");
            }
            return new Point2D(xFunction.evaluate(t), yFunction.evaluate(t));
        }

        /*
         * Calculate approximate curve length using discrete points
         */
        public double approximateLength(int numPoints) {
            if (numPoints < 2) {
                throw new IllegalArgumentException("Need at least 2 points");
            }

            double length = 0;
            double dt = (tEnd - tStart) / (numPoints - 1);
            Point2D prevPoint = getPoint(tStart);

            for (int i = 1; i < numPoints; i++) {
                double t = tStart + i * dt;
                Point2D currentPoint = getPoint(t);

                // Calculate distance between points
                double dx = currentPoint.getX() - prevPoint.getX();
                double dy = currentPoint.getY() - prevPoint.getY();
                length += squareRoot(dx * dx + dy * dy);

                prevPoint = currentPoint;
            }

            return length;
        }

        /*
         * Generate points along the curve
         */
        public Point2D[] generatePoints(int numPoints) {
            Point2D[] points = new Point2D[numPoints];
            double dt = (tEnd - tStart) / (numPoints - 1);

            for (int i = 0; i < numPoints; i++) {
                double t = tStart + i * dt;
                points[i] = getPoint(t);
            }

            return points;
        }

        /*
         * Check if point is approximately on curve
         */
        public boolean isPointOnCurve(Point2D point, double tolerance) {
            int numChecks = 100;
            double dt = (tEnd - tStart) / (numChecks - 1);

            for (int i = 0; i < numChecks; i++) {
                double t = tStart + i * dt;
                Point2D curvePoint = getPoint(t);

                double dx = curvePoint.getX() - point.getX();
                double dy = curvePoint.getY() - point.getY();
                double distance = squareRoot(dx * dx + dy * dy);

                if (distance < tolerance) {
                    return true;
                }
            }

            return false;
        }
    }

    public static void main(String[] args) {
        // Test Case 1: Circle (x = cos t, y = sin t)
        Curve circle = new Curve(
                t -> Math.cos(t),  // x(t) = cos t
                t -> Math.sin(t),  // y(t) = sin t
                0, 2 * Math.PI     // Full circle
        );

        System.out.println("Circle Analysis:");
        System.out.printf("Approximate circumference: %.6f%n",
                circle.approximateLength(1000));

        // Test some points
        Point2D[] testPoints = {
                new Point2D(1, 0),    // On circle
                new Point2D(0, 1),    // On circle
                new Point2D(2, 2)     // Not on circle
        };

        for (Point2D point : testPoints) {
            System.out.printf("Point (%.1f, %.1f) on curve? %b%n",
                    point.getX(), point.getY(),
                    circle.isPointOnCurve(point, 0.01));
        }

        // Test Case 2: Parabola (x = t, y = t²)
        Curve parabola = new Curve(
                t -> t,       // x(t) = t
                t -> t * t,   // y(t) = t²
                -1, 1        // t from -1 to 1
        );

        System.out.println("\nParabola Analysis:");
        System.out.printf("Approximate arc length: %.6f%n",
                parabola.approximateLength(1000));

        // Generate some points
        Point2D[] points = parabola.generatePoints(5);
        System.out.println("Points on parabola:");
        for (Point2D point : points) {
            System.out.printf("(%.2f, %.2f)%n",
                    point.getX(), point.getY());
        }
    }
}