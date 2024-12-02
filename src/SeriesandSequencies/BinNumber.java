package SeriesandSequencies;

/*
 * B-ADIC NUMBERS
 *
 * A b-adic number is a representation of numbers in base b.
 * For example:
 * - Binary (base 2): uses digits {0,1}
 * - Decimal (base 10): uses digits {0,1,2,3,4,5,6,7,8,9}
 * - Hexadecimal (base 16): uses {0-9,A-F}
 *
 * Any rational number can be expressed as a b-adic expansion,
 * which may be finite or infinite (repeating).
 */
public class BinNumber {
    public static class BAdicNumber {
        private int base;          // The base b
        private int[] digits;      // Array of digits in base b
        private boolean isNegative;
        private boolean isRepeating;
        private int repeatStart;   // Where the repetition begins

        // Custom helper functions
        private int absoluteValue(int x) {
            return x >= 0 ? x : -x;
        }

        public BAdicNumber(int base, int[] digits, boolean isNegative) {
            this.base = base;
            this.digits = digits;
            this.isNegative = isNegative;
            this.isRepeating = false;
            this.repeatStart = -1;
        }

        /*
         * Convert a decimal integer to b-adic representation
         * Uses repeated division by base
         */
        public static BAdicNumber fromInteger(int number, int base) {
            if (base < 2) {
                throw new IllegalArgumentException("Base must be at least 2");
            }

            boolean isNeg = number < 0;
            int num = absoluteValue(number);

            // Count how many digits we'll need
            int temp = num;
            int digitCount = 0;
            do {
                digitCount++;
                temp /= base;
            } while (temp > 0);

            int[] digits = new int[digitCount];

            // Convert to base b
            for (int i = digitCount - 1; i >= 0; i--) {
                digits[i] = num % base;
                num /= base;
            }

            return new BAdicNumber(base, digits, isNeg);
        }

        /*
         * Convert a rational number to b-adic representation
         * May result in a repeating expansion
         */
        public static BAdicNumber fromRational(int numerator, int denominator, int base, int maxDigits) {
            if (denominator == 0) {
                throw new IllegalArgumentException("Denominator cannot be zero");
            }

            boolean isNeg = (numerator < 0) != (denominator < 0);
            numerator = absoluteValue(numerator);
            denominator = absoluteValue(denominator);

            int[] digits = new int[maxDigits];
            int[] remainders = new int[denominator]; // To detect repetition

            int dividend = numerator;
            int digitIndex = 0;
            int repeatStart = -1;

            // Perform long division
            while (dividend != 0 && digitIndex < maxDigits) {
                dividend *= base;
                digits[digitIndex] = dividend / denominator;

                dividend %= denominator;

                // Check for repetition
                for (int i = 0; i < digitIndex; i++) {
                    if (remainders[i] == dividend) {
                        repeatStart = i;
                        digitIndex = maxDigits; // Exit loop
                        break;
                    }
                }

                if (digitIndex < maxDigits) {
                    remainders[digitIndex] = dividend;
                    digitIndex++;
                }
            }

            BAdicNumber result = new BAdicNumber(base, digits, isNeg);
            if (repeatStart != -1) {
                result.isRepeating = true;
                result.repeatStart = repeatStart;
            }

            return result;
        }

        /*
         * Convert b-adic number back to decimal string representation
         */
        public String toDecimalString() {
            StringBuilder result = new StringBuilder();
            if (isNegative) result.append("-");

            // Convert non-repeating part
            boolean firstNonZero = false;
            for (int i = 0; i < digits.length; i++) {
                if (digits[i] != 0) firstNonZero = true;
                if (firstNonZero) {
                    if (i == repeatStart && isRepeating) result.append("(");
                    result.append(digits[i]);
                }
            }

            if (isRepeating) result.append(")");

            return result.toString();
        }
    }

    public static void main(String[] args) {
        // Test Case 1: Converting integers to different bases
        System.out.println("Converting integers to different bases:");
        int number = 42;

        System.out.println("\nDecimal " + number + " in different bases:");
        for (int base = 2; base <= 16; base++) {
            BAdicNumber badic = BAdicNumber.fromInteger(number, base);
            System.out.printf("Base %2d: %s%n", base, badic.toDecimalString());
        }

        // Test Case 2: Converting rational numbers (may produce repeating expansions)
        System.out.println("\nConverting rational numbers:");
        int[][] rationals = {
                {1, 2},  // 0.5
                {1, 3},  // 0.333...
                {2, 5},  // 0.4
                {1, 6},  // 0.166...
        };

        for (int[] rational : rationals) {
            BAdicNumber badic = BAdicNumber.fromRational(rational[0], rational[1], 10, 20);
            System.out.printf("%d/%d = %s%n",
                    rational[0], rational[1],
                    badic.toDecimalString());
        }

        // Test Case 3: Converting rational numbers to different bases
        System.out.println("\nRational 1/3 in different bases:");
        for (int base = 2; base <= 16; base++) {
            BAdicNumber badic = BAdicNumber.fromRational(1, 3, base, 20);
            System.out.printf("Base %2d: %s%n", base, badic.toDecimalString());
        }
    }
}