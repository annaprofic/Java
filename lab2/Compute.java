package start;

import java.util.Random;

/**
 * klasa Compute, ktora implementuje interface Computation
 * @author Ania Sarnavska
 */

public class Compute {

    /**
     * Metoda, ktora wypelnia elementy tablicy wartosciami compute(orginalna wartosc elementu)
     * @param array tablica elementow
     * @param f instancja interfejsu funkcyjnego
     */

    public static void apply(double[] array, Computation<Double> f) {


        for (int i = 0; i < array.length; i ++ ) {
            array[i] = f.compute(array[i]);
        }

    }

    public static void randomTab (double array[]) {
        Random generator = new Random();
        for(int i = 0; i < array.length; i++) {
            array[i] = generator.nextDouble()*10;
        }
    }

    /**
     * Metoda, ktora wypisuje wartosc (od min do max) i wartosc, zawracana metoda compute z rownymi odstepami
     * @param xmin minimalna wartosc przekazana
     * @param xmax maksymalna wartosc przekazana
     * @param f instancja interfejsu funkcyjnego
     */

    public static void plot(double xmin, double xmax, Computation<Double> f) throws DivideByZeroException {

        final int n = 6;

        if ( xmin != xmax ) {

            double gap = xmax - xmin; // roznica pomiedzy wartoscia maksymalna a minimalna

            try {
                // odstep miedzy kolejnymi wartosciami
                double interval = Divide.div(gap, (n - 1));

                for (double i = xmin; i < xmax; i += interval) {
                    System.out.format("x: %.1f  " + "f(x): %.1f %n", i, f.compute(i)); // formatowanie do jednej liczy po przycinku
                }

            } catch (DivideByZeroException e ) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static Double computeStatic(Double x) {
        return 2.0 * x;
    }

    /**
     * Metoda statyczna, ktora:
     * - wywoluje metode plot na 5 roznych sposobow
     *      instancja klasy implementujacej intefejs funkcyjny
     *      obiekt klasy anonimowej
     *      wyrazenie lambda
     *      referencja do metody statycznej
     *      referencja do metody niestatycznej
     * - tworzy tablice 20-el. wywoluje na niej apply i wypisuje wynik
     * @param args argumenty metody statycznej
     */


    public static void main(String[] args) {

        System.out.println("\nUsing Multiplier instance: ");
        Multiplier mult = new Multiplier(2.0); // tworzymy obiekt klasy Multiplier

        try {
            plot(2.0, 4.0, mult); // przekazujemy jako instancje interfejsu
        } catch (DivideByZeroException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\nUsing Anonymous class: ");

        try {
            plot(2.0, 4.0, new Computation<Double>() {  // tworzymy klase anonimowa tworzac obiekt interfejsu
                @Override
                public Double compute(Double x) { // nadpisujemy metode compute
                    try {
                        Divide.div(5, 0); // wyrzucamy wyjatek dzielenie przez 0
                    } catch (DivideByZeroException e) {
                        System.out.println(e.getMessage());
                    }
                    return x * 2.0;
                }
            });

        } catch (DivideByZeroException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\nUsing Lambda Expressions: "); // wyrazenie lambda
        try {
            plot(2.0, 4.0, x -> x * 2.0);
        } catch (DivideByZeroException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\nUsing Static expression: "); // matoda statyczna

        try {
            plot(2.0, 4.0, Compute::computeStatic); // referencja do metody statycznej klasy Compute
        } catch (DivideByZeroException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\nUsing Nonstatic expression: ");

        try {
            plot(2.0, 4.0, mult::compute); // referencja do metody niestatycznej klasy Meltiplier
        } catch (DivideByZeroException e) {
            System.out.println(e.getMessage());
        }


        double[] testArray = new double[20]; // tworzymy tablice double[20]
        randomTab(testArray);  // wypelniamy randomowo
        System.out.println("\nOriginal array: ");
        for( double i : testArray ) {
            System.out.format("%.2f%n",  i); // wypisujemy petla oryginalne wartosci for each
        }
        System.out.println("\nOutput array after apply: ");
        apply(testArray, mult); // wywolujemy na nia apply
        for( double i : testArray ) {
            System.out.format("%.2f%n", i); // wypisujemy petla for each
        }

    }



}
