import java.lang.Math;

public class Polynomial {
    double[] coefficients;

    public Polynomial() {
        this.coefficients = new double[1];
    }

    public Polynomial(double[] coefficients) {
        this.coefficients = coefficients;
    }

    public int getLength() {
        return this.coefficients.length;
    }

    public double getCoefficient(int index) {
        return this.coefficients[index];
    }

    public Polynomial add(Polynomial addedCoefficients) {
        double[] newPolynomial = new double[Math.max(this.getLength(), addedCoefficients.getLength())];
        for (int i = 0; i < newPolynomial.length; i++) {
            if (i >= this.getLength()) {
                newPolynomial[i] = addedCoefficients.getCoefficient(i);
            }
            else if(i >= addedCoefficients.getLength()) {
                newPolynomial[i] = this.getCoefficient(i);
            }
            else {
                newPolynomial[i] = this.getCoefficient(i) + addedCoefficients.getCoefficient(i);
            }
        }
        return new Polynomial(newPolynomial);
    }

    public double evaluate(double val) {
        double sum = 0;
        for (int i = 0; i < this.coefficients.length; i++) {
            sum += this.coefficients[i] * Math.pow(val, i);
        }
        return sum;
    }

    public boolean hasRoot(double val) {
        double sum = evaluate(val);
        return sum == 0;
    }
}