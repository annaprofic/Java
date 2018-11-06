package start;

/**
 * Klasa, ktora wykonuje dzielenie liczb
 * @author Ania Sarnavska
 */

public class Divide {
    /**
     * Metoda statyczna, wykonujaca dzielenie podanych parametrow
     * @param dzielna
     * @param dzielnik
     * @return dzielenie dwoch liczb zmiennoprzecinkowych result = dzielnik / dzielna
     * @throws DivideByZeroException
     */

    public static double div(double dzielna, double dzielnik) throws DivideByZeroException {



        if ( dzielnik == 0){

            throw new DivideByZeroException (Double.toString(dzielna), Double.toString(dzielnik));
        }

        double result = dzielna / dzielnik;
        return result;
    }

    /**
     * Metoda statyczna, przyjmujaca parametry z linii komend, ktora wywoluje metode dzielenia
     * @param args argumenty podane do main
     * @throws DivideByZeroException
     */

    public static void main(String[] args) throws DivideByZeroException {


        double a = Double.valueOf(args[0]);
        double b = Double.valueOf(args[1]);
        double result = Divide.div(a, b);


    }
}

