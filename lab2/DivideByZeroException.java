package start;

/**
 * Klasa dla odslugi wyjatku dzielenia przez zero
 * @author Ania Sarnavska
 */

public class DivideByZeroException extends java.lang.Exception {
    String message;

    /**
     * Konstruktor, wypisujacy komunikat o bledzie
     * @param a dzielna
     * @param b dzielnik
     */

    public DivideByZeroException(String a, String b) {

        message = "Ups! I can't divide " + a + " by " + b + "...";
    }

    /**
     * Metoda, zwracajaca komunikat o bedzie (dzielenie przez zero)
     * @return message
     */

    @Override
    public String getMessage() {
        return message;
    }
}

