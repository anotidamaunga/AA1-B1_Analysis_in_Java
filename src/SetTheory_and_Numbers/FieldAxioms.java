public class FieldAxioms {
    // Commutativity: Check if addition and multiplication are commutative
    // a + b = b + a, and a * b = b * a
    public static boolean commutativity(double a, double b) {
        // Check if the order of operation does not change the result
        return (a + b == b + a) && (a * b == b * a);
    }

    // Associativity: Check if grouping of operations does not change the result
    // (a + b) + c = a + (b + c), and (a * b) * c = a * (b * c)
    public static boolean associativity(double a, double b, double c) {
        // Test for both addition and multiplication
        return ((a + b) + c == a + (b + c)) && ((a * b) * c == a * (b * c));
    }

    // Neutral Elements: Check if 0 is neutral for addition, and 1 is neutral for multiplication
    // a + 0 = a, and a * 1 = a
    public static boolean neutralElements(double a) {
        // Addition with 0 and multiplication with 1 should not change the number
        return (a + 0 == a) && (a * 1 == a);
    }

    // Inverses: Check if adding the negative of a number results in 0
    // And multiplying by the reciprocal results in 1 (excluding division by zero)
    // a + (-a) = 0, and a * (1/a) = 1
    public static boolean inverses(double a) {
        return (a + (-a) == 0) && (a != 0 && a * (1 / a) == 1);
    }

    // Distributive Law: Check if multiplication distributes over addition
    // a * (b + c) = a * b + a * c
    public static boolean distributiveLaw(double a, double b, double c) {
        return a * (b + c) == (a * b) + (a * c);
    }

    public static void main(String[] args) {
        // Test values
        double a = 3, b = 4, c = 5;

        // Test and display results for each field axiom
        System.out.println("Commutativity: " + commutativity(a, b)); // Should return true
        System.out.println("Associativity: " + associativity(a, b, c)); // Should return true
        System.out.println("Neutral Elements: " + neutralElements(a)); // Should return true
        System.out.println("Inverses: " + inverses(a)); // Should return true
        System.out.println("Distributive Law: " + distributiveLaw(a, b, c)); // Should return true
    }
}
