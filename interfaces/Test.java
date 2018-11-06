/**
 * Klasa Test, ktora pokazuje dzialanie klas Variation i Combination, ktore implementuja interfejs Formula
 * @author Anna Sarnavska
 */
public class Test {
    /**
     * Metoda statyczna, ktora oblicza silnie
     * @param i z czego powinna byc obliczona silnia
     * @return 1, kiedy element mniejszy od 1 i silnia od i w innych przypadkach
     */
    private static int fact(int i) {
        if (i < 1)
            return 1;
        else
            return i * fact(i - 1);
    }

    /**
     * Metoda statyczna obliczajaca pule sukcesu ( kiedy wydarzenie sie zdarzy )
     * i wywolujaca metoda interfejsu funkcyjnego count
     * @param a ilosc elementuw n dla wariacji
     * @param b ilosc elementow k dla wariacji
     * @param f obiekt interfejsu
     */

    public static void ProbabilityVar( int a, int b, Formula<Double> f) {
        double success = fact(a)/ fact(b);
        System.out.println(f.count(success));

    }

    /**
     * Metoda statyczna obliczajaca pule sukcesu ( kiedy wydarzenie sie zdarzy )
     * i wywolujaca metoda interfejsu funkcyjnego count
     * @param a ilosc elementuw n dla dwumiana Newtona
     * @param b ilosc elementow k dla dwumiana Newtona
     * @param f obiekt interfejsu
     */

    public static void ProbabilityCom (int a, int b, Formula<Double> f) {
        double success = fact(a) / (fact (a - b) * fact(b));
        System.out.println(f.count(success));

    }

    public static void main(String[] args) {

        Variation var = new Variation(6);
        ProbabilityVar(4, 2, var );

        Combination com = new Combination(5);
        ProbabilityCom(4,2, com );
    }
}

