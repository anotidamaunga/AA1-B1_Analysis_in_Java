public class TriangleInequalityC {

    // Class to represent a complex number
    public static class ComplexNumber {
        double real; // Real part
        double imaginary; // Imaginary part

        // Constructor
        public ComplexNumber(double real, double imaginary) {
            this.real = real;
            this.imaginary = imaginary;
        }

        // Method to manually calculate the modulus (absolute value) of the complex number
        public double modulus() {
            // First calculate the square of the real and imaginary parts
            double realSquared = real * real;
            double imaginarySquared = imaginary * imaginary;

            // Sum of squares of real and imaginary parts
            double sumOfSquares = realSquared + imaginarySquared;

            // Use Newton's method to approximate the square root
            double guess = sumOfSquares / 2; // Initial guess
            double epsilon = 0.0001; // Precision of the result

            while (Math.abs(guess * guess - sumOfSquares) > epsilon) {
                guess = (guess + sumOfSquares / guess) / 2; // Newton's method
            }

            return guess; // Return the computed modulus (approximated square root)
        }

        // Method to add two complex numbers
        public ComplexNumber add(ComplexNumber other) {
            return new ComplexNumber(this.real + other.real, this.imaginary + other.imaginary);
        }

        @Override
        public String toString() {
            return real + " + " + imaginary + "i";
        }
    }

    // Method to check the triangle inequality for two complex numbers
    public static boolean checkTriangleInequality(ComplexNumber z1, ComplexNumber z2) {
        // Calculate the modulus of individual numbers
        double modZ1 = z1.modulus();
        double modZ2 = z2.modulus();

        // Calculate the modulus of their sum
        ComplexNumber sum = z1.add(z2);
        double modSum = sum.modulus();

        // Check if |z1 + z2| <= |z1| + |z2|
        return modSum <= modZ1 + modZ2;
    }

    public static void main(String[] args) {
        // Define two complex numbers
        ComplexNumber z1 = new ComplexNumber(3, 4); // 3 + 4i
        ComplexNumber z2 = new ComplexNumber(1, -2); // 1 - 2i

        // Check the triangle inequality
        boolean isValid = checkTriangleInequality(z1, z2);

        // Print the result
        System.out.println("z1: " + z1);
        System.out.println("z2: " + z2);
        System.out.println("Triangle inequality holds for z1 and z2? " + isValid);
    }
}
