/**
 * Klasa, implementujaca interfejs funkcyjny Formula typu Double
 * @author Anna Sarnavska
 */

public class Variation implements Formula<Double> {

    int x;

    /**
     * Metoda, obliczajaca liczebnosc wszytskich mozliwosci zdarzenia
     * @param x ilosc wszytskich elementow
     */
    public Variation(int x) { //oblicza wszystkie mozliwosci
        this.x = x * (x - 1);
    }

    /**
     * Metoda nadpisana po interfejsie Formula
     * Oblicza prawdopodobienstwo zdarzenia
     * @param s liczba sukcesu
     * @return prawdopodobienstwo zdarzenia = liczba sukcesu / liczbe wszystkich mozliwosci
     */

    @Override
    public Double count(Double s) {
        return s/x;
    }

}
