package start;

/**
 * Klasa, implementujaca interfejs Computation typu Double
 * @author Ania Sarnavska
 */

public class Multiplier implements Computation<Double> {

    double a;

    /**
     * Konstruktor, przyjmujacy parametr
     * @param a
     */
    public Multiplier(double a) {
        this.a = a;
    }

    /**
     * Metoda, ktora przeciazamy interfejsu Computation
     * @param x
     * @return a * x
     */
    @Override
    public Double compute(Double x) {
        return a * x;
    }

}

