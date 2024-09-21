// Title:   NewtonRaphson.java
// Author:  Jacob Bello
// Course:  CST 336
// Date:    9/20/2024
// Abstract: This program allows users to enter coefficients and we will output a polynomial. Then we will
//           search for a root iteratively based on the users guess and using the Newton-Raphson method. It will keep
//           estimating until it finds a root within the epsilon threshold or reaches the maximum iterations.

import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;

import java.util.Scanner;

public class NewtonRaphson {
    private PolynomialFunction polyFunc;
    private double guess;
    private double root;
    private int estimateCount;
    private static final double EPSILON = 1e-12;
    private static final int MAX_ITERATIONS = 55;

    public NewtonRaphson() {
        // take user input for polynomial
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the coEf of your polynomial from lowest to highest\n" + "separated by a \",\":");

        // break down userInput into array of strings
        String userInput = scanner.nextLine();
        String[] nums = userInput.split(",");

        // construct the coefficient array
        double[] coEf = new double[nums.length];
        for (int i = 0; i < nums.length; i++) {
            coEf[i] = parseCoefficient(nums[i].trim());
        }

        polyFunc = new PolynomialFunction(coEf);
        System.out.println("Here is your polynomial: " + formatPolynomial(coEf));

        // take guess for root
        System.out.println("Enter an initial guess: ");
        guess = scanner.nextDouble();
        estimateCount = 0;
    }




    public boolean rootCalc() {
        // get derivative of polynomial
        PolynomialFunction derivative = polyFunc.polynomialDerivative();

        // start algorithm
        for (estimateCount = 0; estimateCount < MAX_ITERATIONS; estimateCount++) {
            double fx = polyFunc.value(guess);
            double fxPrime = derivative.value(guess);

            if (fxPrime == 0) {
                return false;
            }

            double nextEstimate = guess - (fx / fxPrime);
            System.out.printf("Current estimate is: %.12f%n", nextEstimate);

            // condition for if we found it
            if (Math.abs(nextEstimate - guess) < EPSILON) {
                root = nextEstimate;
                return true;
            }

            // else update guess for next iteration
            guess = nextEstimate;
        }
        return false;
        // fail case
    }

    // getters
    public double getRoot() {
        return root;
    }

    public double getGuess() {
        return guess;
    }

    public int getEstimateCount() {
        return estimateCount;
    }

    public PolynomialFunction getPolyFunc() {
        return polyFunc;
    }

    // return if root passed
    public boolean testRoot() {
        double result = polyFunc.value(root);
        if (Math.abs(result) < EPSILON) {
            System.out.println("This is an excellent approximation!");
            return true;
        } else {
            System.out.println("The approximation is not good!");
            return false;
            // or not
        }
    }

    public String toString() {
        return String.format("An approximate root of the polynomial %s is %.9f.%nThe calculation took %d iterations.",
                formatPolynomial(polyFunc.getCoefficients()), root, estimateCount);
    }

    // if input is coEf then convert
    private double parseCoefficient(String input) {
        if (input.contains("/")) {
            // handle fractions
            String[] fractionParts = input.split("/");
            double numerator = Double.parseDouble(fractionParts[0].trim());
            double denominator = Double.parseDouble(fractionParts[1].trim());
            return numerator / denominator;
        } else {
            // if not a fraction
            return Double.parseDouble(input);
        }
    }

    // print out polynomials in nice format
    private String formatPolynomial(double[] coEf) {
        StringBuilder sb = new StringBuilder();
        // Loop through the coefficients
        for (int i = 0; i < coEf.length; i++) {
            if (coEf[i] != 0) {
                if (i != 0) {
                    if (coEf[i] > 0) {
                        sb.append(" + ");
                    } else {
                        sb.append(" - ");
                        // after adding minus sign add positive value to the string
                        coEf[i] = -coEf[i];
                    }


                } else if (coEf[i] < 0) {
                    sb.append("-");
                }


                if (i == 0) {
                    sb.append(Math.abs(coEf[i]));
                } else if (i == 1) {
                    sb.append(Math.abs(coEf[i])).append(" x");
                } else {
                    sb.append(Math.abs(coEf[i])).append(" x^").append(i);
                }
            }
        }
        return sb.toString();
    }
}