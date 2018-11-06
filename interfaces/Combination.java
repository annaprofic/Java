/**
 * Klasa implementujaca interfejs funkcyjny Formula typu Double
 * @author Anna Sarnavska
 */

public class Combination implements Formula<Double>{

    int x = 1;

    /**
     * Metoda, obliczajaca liczebnosc wszytskich mozliwosci zdarzenia
     * @param x ilosc wszystkich elementow
     */

    public Combination(int x) {
        for (int i = x; i > (x - 3); i -- ) {
            this.x *= i;
        }
        System.out.println(this.x);
    }

    /**
     * Metoda nadpisana po interfejsie Formula
     * Oblicza prawdopodobienstwo zdarzenia
     * @param a liczba sukcesu
     * @return prawdopodobienstwo zdarzenia = liczba sukcesu / liczbe wszystkich mozliwosci
     */
    @Override
    public Double count(Double a) {
        return a/x;
    }
}
