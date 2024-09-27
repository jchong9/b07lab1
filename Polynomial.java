import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.Math;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Polynomial {
    double[] coefficients;
    int[] exponents;

    public Polynomial() {
        this.coefficients = null;
        this.exponents = null;
    }

    public Polynomial(double[] coefficients, int[] exponents) {
        double[] filteredCoefficients;
        int[] filteredExponents;
        int size = 0;
        int currIndex = 0;
        //Find size without any zeroes
        for (int i = 0; i < coefficients.length; i++) {
            if (coefficients[i] != 0) {
                size++;
            }
        }
        //Add non-zero entries to initialize polynomial
        filteredCoefficients = new double[size];
        filteredExponents = new int[size];
        for (int j = 0; j < coefficients.length; j++) {
            if (coefficients[j] != 0) {
                filteredCoefficients[currIndex] = coefficients[j];
                filteredExponents[currIndex] = exponents[j];
                currIndex++;
            }
        }
        this.coefficients = filteredCoefficients;
        this.exponents = filteredExponents;
    }

    public Polynomial(File file) throws IOException {
        Scanner sc = new Scanner(file);
        String line = sc.nextLine();
        if (line == null) {
            this.coefficients = null;
            this.exponents = null;
        }
        String currCoefficient = "";
        String currExponent = "";
        boolean startCoefficient = true;
        boolean startExponent = false;
        int size = line.length() - line.replace("x", "").length();
        this.coefficients = new double[size];
        this.exponents = new int[size];
        int counter = 0;
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == '+' || (line.charAt(i) == '-' && i != 0)) {
                startCoefficient = true;
                startExponent = false;
                this.exponents[counter] = Integer.parseInt(currExponent);
                currExponent = "";
                counter++;
            }
            else if (line.charAt(i) == 'x') {
                startExponent = true;
                startCoefficient = false;
                this.coefficients[counter] = Double.parseDouble(currCoefficient);
                currCoefficient = "";
                continue;
            }
            if (startCoefficient) {
                currCoefficient += line.charAt(i);
            }
            if (startExponent) {
                currExponent += line.charAt(i);
            }
        }
        this.exponents[counter] = Integer.parseInt(currExponent);
    }

    public Polynomial(Map<Integer, Double> polynomial) {
        this.coefficients = new double[polynomial.size()];
        this.exponents = new int[polynomial.size()];
        int counter = 0;
        for (int key : polynomial.keySet()) {
            this.coefficients[counter] = polynomial.get(key);
            this.exponents[counter] = key;
            counter++;
        }
    }

    public int getLength() {
        return this.coefficients.length;
    }

    public double getCoefficient(int index) {
        return this.coefficients[index];
    }
    public int getExponent(int index) {
        return this.exponents[index];
    }

    public Polynomial add(Polynomial addedPolynomial) {
        if (addedPolynomial == null) {
            return this;
        }
        Map<Integer, Double> polynomialTerms = new HashMap<Integer, Double>();
        //Combine like terms
        for (int i = 0; i < this.getLength(); i++) {
            polynomialTerms.put(this.getExponent(i), this.getCoefficient(i));
        }
        for (int j = 0; j < addedPolynomial.getLength(); j++) {
            if (polynomialTerms.get(addedPolynomial.getExponent(j)) == null) {
                polynomialTerms.put(addedPolynomial.getExponent(j), addedPolynomial.getCoefficient(j));
            }
            else {
                polynomialTerms.put(addedPolynomial.getExponent(j), polynomialTerms.get(addedPolynomial.getExponent(j)) + addedPolynomial.getCoefficient(j));
            }
        }
        //Turn hashmap of unique terms into polynomial
        return new Polynomial(polynomialTerms);
    }

    public double evaluate(double val) {
        double sum = 0;
        if (this.coefficients == null) {
            return 0;
        }
        for (int i = 0; i < this.coefficients.length; i++) {
            sum += this.coefficients[i] * Math.pow(val, this.exponents[i]);
        }
        return sum;
    }

    public boolean hasRoot(double val) {
        double sum = evaluate(val);
        return sum == 0;
    }

    public Polynomial multiply(Polynomial polynomial2) {
        if (polynomial2 == null) {
            return this;
        }
        Map<Integer, Double> polynomialTerms = new HashMap<Integer, Double>();
        double currCoefficient = 0;
        int currExponent = 0;
        for (int i = 0; i < this.getLength(); i++) {
            for (int j = 0; j < polynomial2.getLength(); j++) {
                currCoefficient = this.getCoefficient(i) * polynomial2.getCoefficient(j);
                currExponent = this.getExponent(i) + polynomial2.getExponent(j);
                if (polynomialTerms.get(currExponent) == null) {
                    polynomialTerms.put(currExponent, currCoefficient);
                }
                else {
                    polynomialTerms.put(currExponent, polynomialTerms.get(currExponent) + currCoefficient);
                }
            }
        }
        return new Polynomial(polynomialTerms);
    }

    public void saveToFile(String fileName) throws Exception {
        FileWriter writer = new FileWriter(fileName);
        writer.write(this.toString());
        writer.close();
    }

    @Override
    public String toString() {
        String polynomialString = "";
        for (int i = 0; i < this.getLength(); i++) {
            if (this.getCoefficient(i) > 0) {
                if (i == 0) {
                    polynomialString += this.getCoefficient(i) + "x" + this.getExponent(i);
                }
                else {
                    polynomialString += "+" + this.getCoefficient(i) + "x" + this.getExponent(i);
                }
            }
            else {
                polynomialString += this.getCoefficient(i) + "x" + this.getExponent(i);
            }
        }
        return polynomialString;
    }
}