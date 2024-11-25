public class ComplexNumber {
    // Fields to store the real and imaginary parts of the complex number
    private double real;       // Real part of the complex number
    private double imaginary;  // Imaginary part of the complex number

    // Constructor to initialize a complex number with real and imaginary parts
    public ComplexNumber(double real, double imaginary) {
        this.real = real;            // Set the real part
        this.imaginary = imaginary; // Set the imaginary part
    }

    /**
     * Adds this complex number to another complex number.
     * Formula: (a + bi) + (c + di) = (a + c) + (b + d)i
     *
     * @param other The other complex number to add
     * @return A new ComplexNumber that is the sum of the two numbers
     */
    public ComplexNumber add(ComplexNumber other) {
        double newReal = this.real + other.real;             // Add real parts
        double newImaginary = this.imaginary + other.imaginary; // Add imaginary parts
        return new ComplexNumber(newReal, newImaginary);     // Return a new ComplexNumber
    }

    /**
     * Multiplies this complex number by another complex number.
     * Formula: (a + bi)(c + di) = (ac - bd) + (ad + bc)i
     *
     * @param other The other complex number to multiply with
     * @return A new ComplexNumber that is the product of the two numbers
     */
    public ComplexNumber multiply(ComplexNumber other) {
        double newReal = (this.real * other.real) - (this.imaginary * other.imaginary); // Calculate real part
        double newImaginary = (this.real * other.imaginary) + (this.imaginary * other.real); // Calculate imaginary part
        return new ComplexNumber(newReal, newImaginary); // Return a new ComplexNumber
    }

    /**
     * Computes the modulus (absolute value) of the complex number.
     * Formula: |z| = sqrt(a^2 + b^2)
     *
     * @return The modulus of the complex number
     */
    public double modulus() {
        return Math.sqrt(this.real * this.real + this.imaginary * this.imaginary); // Return sqrt(a^2 + b^2)
    }

    /**
     * Computes the conjugate of the complex number.
     * Formula: Conjugate of (a + bi) is (a - bi)
     *
     * @return A new ComplexNumber that is the conjugate
     */
    public ComplexNumber conjugate() {
        return new ComplexNumber(this.real, -this.imaginary); // Return a new ComplexNumber with negated imaginary part
    }

    /**
     * Returns a string representation of the complex number in the form "a + bi".
     *
     * @return String representation of the complex number
     */
    @Override
    public String toString() {
        return this.real + " + " + this.imaginary + "i"; // Format as "a + bi"
    }

    public static void main(String[] args) {
        // Create two complex numbers
        ComplexNumber z1 = new ComplexNumber(3, 4); // z1 = 3 + 4i
        ComplexNumber z2 = new ComplexNumber(1, -2); // z2 = 1 - 2i

        // Demonstrate addition
        ComplexNumber sum = z1.add(z2);
        System.out.println("Sum: " + sum); // Output: Sum of z1 and z2

        // Demonstrate multiplication
        ComplexNumber product = z1.multiply(z2);
        System.out.println("Product: " + product); // Output: Product of z1 and z2

        // Compute the conjugate of z1
        ComplexNumber conjugate = z1.conjugate();
        System.out.println("Conjugate of z1: " + conjugate); // Output: Conjugate of z1

        // Compute the modulus of z1
        double modulus = z1.modulus();
        System.out.println("Modulus of z1: " + modulus); // Output: Modulus of z1
    }
}
