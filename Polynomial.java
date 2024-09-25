import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.Math;
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
            if (this.getCoefficient(i) != 0) {
                size++;
            }
        }
        //Add non-zero entries to initialize polynomial
        filteredCoefficients = new double[size];
        filteredExponents = new int[size];
        for (int j = 0; j < coefficients.length; j++) {
            if (this.getCoefficient(j) != 0) {
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

    }

    public int getLength() {
        return this.coefficients.length;
    }

    public double getCoefficient(int index) {
        return this.coefficients[index];
    }

    public Polynomial add(Polynomial addedPolynomial) {
        if (addedPolynomial == null) {
            return this;
        }
        //Edit
        //add polynomials
        int size = this.getLength() + addedPolynomial.getLength();
    }

    public double evaluate(double val) {
        double sum = 0;
        for (int i = 0; i < this.coefficients.length; i++) {
            sum += this.coefficients[i] * Math.pow(val, this.exponents[i]);
        }
        return sum;
    }

    public boolean hasRoot(double val) {
        double sum = evaluate(val);
        return sum == 0;
    }

    public Polynomial multiply(Polynomial polynomial) {
        if (polynomial == null) {
            return this;
        }
        for (int i = 0; i < this.getLength(); i++) {
            for (int j = 0; j < polynomial.getLength(); j++) {
                //multiply polynomial
            }
        }
        return null;
    }

    public void saveToFile(String fileName) throws Exception {
        FileWriter writer = new FileWriter(fileName);
        writer.write(this.toString());
        writer.close();
    }

    @Override
    public String toString() {
        return "";
    }
}