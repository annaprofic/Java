package mandelbrot;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Klasa w ktorej sa umieszczone metody dla operacji na liczbach zespolonych
 * @author Anna Sarnavska
 */

public class Complex  {
    private double r, i;

    /**
     * Konstrukor domyslny, ktory przypisuje wartosci 0.0
     */

    public Complex(){
        r = 0.0;
        i = 0.0;
    }

    /**
     * Konstruktor, ktory przyjmuje czesc rzeczywista, i dodaje urojona domyslnie jako 0.0
     * @param re real
     */

    public Complex(double re) {
        this.r = r;
        i = 0.0;
    }

    /**
     * Konstruktor, ktory przyjmuje czesc rzeczywista i urojona
     * @param r real
     * @param i imaginary
     */

    public Complex(double r, double i){
        this.r = r;
        this.i = i;
    }

    /**
     * Konstruktor, ktory przyjmuje obiekt klasy Complex
     * @param number obiekt klasy Complex
     */

    public Complex(Complex number) {
        this.r = number.r;
        this.r = number.i;
    }

    /**
     * Konstruktor, ktory przyjmuje tekst i zwraca liczbe poprzez funkcje ValueOf
     * @param text liczba zespolona w postaci String
     */

    public Complex(String text) {

        Complex number = valueOf(text);
        this.r = number.r;
        this.i = number.i;

    }

    /**
     * Metoda niestatyczna, ktora dodaje czesci liczby zespolonej
     * @param number obiekt klasy Complex
     * @return przypisane wartosci parametrow klasy Complex
     */


    public Complex add(Complex number){
        this.r += number.r;
        this.i += number.i;

        return this;
    }

    /**
     * Metoda statyczna, ktora dodaje obiekty klasy Complex (liczby zespolone)
     * @param number1 pierwsza liczba zespolona klasy Complex
     * @param number2 druga liczba zespolona klasy Complex
     * @return wynik dodawania liczb zespolonych
     */

    public static Complex add(Complex number1, Complex number2){
        Complex result = new Complex();
        result.r = number1.r + number2.r;
        result.i = number1.i + number2.i;

        return result;
    }


    /**
     * Metoda niestatyczna, ktora odejmuje czesci liczby zespolonej
     * @param number obiekt klasy Complex
     * @return przypisane wartosci parametrow klasy Complex
     */

    public Complex sub(Complex number) {
        this.r -= number.r;
        this.i -= number.i;

        return this;

    }


    /**
     * Metoda statyczna, ktora odejmuje obiekty klasy Complex (liczby zespolone)
     * @param number1 pierwsza liczba zespolona klasy Complex
     * @param number2 druga liczba zespolona klasy Complex
     * @return wynik odejmowania liczb zespolonych
     */

    public static Complex sub(Complex number1 , Complex number2) {
        Complex result = new Complex();
        result.r = number1.r - number2.r;
        result.i = number1.i - number2.i;

        return result;
    }


    /**
     * Metoda niestatyczna, ktora mnozy obiekty klasy Complex (liczby zespolone)
     * @param number liczba zespolona klasy Complex
     * @return this - wynik mnozenia
     */

    public Complex mul(Complex number) {

        double re = this.r * number.r - this.i * number.i;
        double im = this.r * number.i + this.i * number.r;
        this.r = re;
        this.i = im;

        return this;
    }


    /**
     * Metoda statyczna, ktora mnozy obiekty klasy Complex (liczby zespolone)
     * (a1 + b1(i))( a2 + b2(i)) = a1 * a2 + a1 * b2(i) + b1(i) * a2 - b1 * b2
     * real = a1 * a2 - b1 * b2
     * imaginary = a1 * b2(i) + b1(i) * a2
     * @param number1 pierwsza liczba zespolona klasy Complex
     * @param number2 druga liczba zespolona klasy Complex
     * @return wynik mnozenia liczb zespolonych
     */

    public static Complex mul(Complex number1 , Complex number2){   // (a1 + b1(i))( a2 + b2(i)) =
        Complex result = new Complex();                             // a1 * a2 + a1 * b2(i) + b1(i) * a2 - b1 * b2
        result.r = number1.r * number2.r -  number1.i * number2.i;  // real = a1 * a2 - b1 * b2
        result.i = number1.r * number2.i + number1.i * number2.r;   // imaginary = a1 * b2(i) + b1(i) * a2

        return result;
    }


    /**
     * Metoda niesatyczna, ktora dzieli obiekty klasy Complex (liczby zespolone)
     * @param number liczba zespolona klasy Complex
     * @return this - wynik dzielenia
     */

    public Complex div(Complex number) throws DivideByZeroException {

        double re = (r * number.r + i * number.i) / (Math.pow(number.r, 2)  + Math.pow(number.i, 2));
        double im = (i * number.r - r * number.i) / (Math.pow(number.r, 2)  + Math.pow(number.i, 2));
        this.r = re;
        this.i = im;

        return this;
    }



    /**
     * Metoda statyczna, ktora dzieli obiekty klasy Complex (liczby zespolone)
     * @param number1 dzielna - pierwsza liczba zespolona klasy Complex
     * @param number2 dzielnik - druga liczba zespolona klasy Complex
     * @return wynik dzielenia liczb zespolonych
     */

    public static Complex div(Complex number1, Complex number2) throws DivideByZeroException {
        Complex result = new Complex();
        result.r = (number1.r * number2.r + number1.i * number2.i) / (Math.pow(number2.r, 2) + Math.pow(number2.i, 2));
        result.i = (number1.i * number2.r - number1.r * number2.i) / (Math.pow(number2.r, 2) + Math.pow(number2.i, 2));
        return result;
    }


    /**
     * Metoda niestatyczna, ktora oblicza modul liczby zespolonej
     * @return modul liczby zespolonej
     */

    public double abs() {
        return Math.sqrt(Math.pow(r, 2) + Math.pow(i, 2));
    }


    /**
     * Metoda statyczna, ktora oblicza modul liczby zespolonej klasy Complex
     * @param number obiekt klasy Complex, liczba zespolona
     * @return modul liczby zespolonej
     */

    public static double abs(Complex number){

        double abs = Math.sqrt( sqrAbs(number));
        return abs;
    }

    /**
     *  Metoda niestatyczna, ktora oblicza kwadrat modulu liczby zespolonej
     * @return kwadrat modulu liczby zespolonej
     */

    public double sqrAbs() {
        return Math.pow(r, 2) + Math.pow(i, 2);
    }

    /**
     * Metoda statyczna, ktora oblicza kwadrat liczby zespolonej
     * @param number obiekt klasy Complex, liczba zespolona
     * @return wynik kwadratu liczby zespolonej
     */

    public static double sqrAbs(Complex number) {
        double result = number.r * number.r + number.i * number.i;
        return result;
    }

    /**
     * Metoda niestatyczna, ktora oblicza faze liczby zespolonej
     * @param number liczba zespolona obiekt Complex
     * @return zwraca blas przy niepowodzeniu
     */

    public double phase(Complex number)  {

        double result = 0;

        if (number.r == 0) {
            if (number.i > 0 ) return 0.5 * Math.PI;
            if (number.i < 0) return  - 0.5 * Math.PI;
        }

        if (number.i == 0) {
            if ( number.r > 0 ) return result;
            if (number.i < 0) return Math.PI;
        }
        else {
            if (number.r > 0) return Math.atan(number.r/number.i);
            if (number.i < 0) return Math.atan(number.r/number.i) + Math.PI;
        }

        return -1;
    }

    /**
     * Metoda statyczna, ktora oblicza faze liczby zespolonej
     * @param number liczba zespolona obiekt Complex
     * @return -1 przy niepowodzeniu
     */

    public static double phaseStatic(Complex number) {

        double result = 0;

        if (number.r == 0) {
            if (number.i > 0 ) return 0.5 * Math.PI;
            if (number.i < 0) return  - 0.5 * Math.PI;
        }

        if (number.i == 0) {
            if ( number.r > 0 ) return result;
            if (number.i < 0) return Math.PI;
        }
        else {
            if (number.r > 0) return Math.atan(number.r/number.i);
            if (number.i < 0) return Math.atan(number.r/number.i) + Math.PI;
        }

        return -1;
    }


    /**
     * Metoda niestatyczna, ktora zwraca parametr klasy Complex
     * @return real klasy Complex
     */
    public double re() {
        return this.r;
    }

    /**
     * Metoda niestatyczna, ktora zwraca parametr klasy Complex
     * @return imaginary klasy Complex
     */

    public double im() {
        return this.i;
    }



    /**
     * Metoda, ktora przypisuje czesc rzeczywista
     * @param number obiekt klasy Complex, liczba zespolona
     * @return real od liczby zespolonej, obiektu klasy Complex
     */

    public static double re(Complex number) {
        return number.r;
    }

    /**
     * Metods, ktora przypisuje czesc urojona
     * @param number imaginary od liczbe zespolonej, obiektu klasy Complex
     * @return imaginary od liczby zespolonej, obiektu klasy Complex
     */

    public static double im(Complex number) {
        return number.i;
    }


    /**
     * Metoda, ktora zwraca liczbe zespolona w postaci tekstu, przyjmujac ja w postaci liczby
     * @return liczba zespolona w postaci liczby
     */

    @Override
    public String toString() {
        if ( i < 0 ) {
            return r + (i + "i");
        }
        return r + "+" + i + "i";
    }

    /**
     * Dodatkowa metoda statyczna, ktora konwertuje tekst w zmienne w zaleznosci od znaku
     * @param text tekst formatu number +/- number albo number (jezeli jest tylko czesc urojona
     * @return liczba zespolona, obiekt Complex
     */


    public static Complex complexParse (String text) {

        if (text.contains("+")) {

            String[] newText = text.split("\\+");

            Complex number = new Complex();
            number.r  = Double.parseDouble(newText[0]);
            number.i = Double.parseDouble(newText[1]);

            return number;
        }

        if (text.contains("-")) {

            String[] newText = text.split("-");
            Complex number = new Complex();
            number.r  = Double.parseDouble(newText[0]);
            number.i = -Double.parseDouble(newText[1]);
            return number;
        }

        Complex number = new Complex();
        number.r = 0.0;
        number.i = -Double.parseDouble(text);

        return number;
    }

    /**
     * Metoda statyczna, ktora konwertuje podany ciag znakow w liczbe zespolona, ktora jest w postaci
     * obiektu klasy Complex
     * @param text liczba zespolona przedstawiona jako ciag znakow
     * @return licza zespolona jakos obiekt klasy Complex
     */

    public static Complex valueOf(String text) {

        text = text.trim(); // pozbycie sie bialych znakow

        //wyrazenia regularne wykorzystane dla liczby zespolonej typu 2.29i+12.3 albo 1i+2
        Pattern pattern = Pattern.compile("([0-9]{1,}(\\.[0-9]{1,})?)i[+-]([0-9]{1,}(\\.[0-9]{1,})?)");
        Matcher matcher = pattern.matcher(text);

        if (matcher.find()) {

            if (text.charAt(0) == '-') {
                text = text.replaceFirst("-", "");

                if (text.contains("-")) {

                    Complex number = new Complex();
                    number.r = -Double.parseDouble(matcher.group(3));
                    number.i = -Double.parseDouble(matcher.group(1));
                    return number;
                }

                Complex number = new Complex();
                number.r = -Double.parseDouble(matcher.group(3));
                number.i = Double.parseDouble(matcher.group(1));
                return number;

            }
        }

        if (text.contains("i")) {

            text = text.replace("i", "");

            if (text.charAt(0) == '-') {
                text = text.replaceFirst("-", "");
                Complex mNumber = complexParse(text);
                mNumber.r = - mNumber.r;
                return mNumber;
            }

            Complex pNumber = complexParse(text);
            return pNumber;
        }

        if (text.charAt(0) == '-') {
            Complex mReal = new Complex();
            mReal.r = -Double.parseDouble(text);
            mReal.i = 0.0;
            return mReal;
        }

        Complex pReal = new Complex();
        pReal.r = Double.parseDouble(text);
        pReal.i = 0.0;
        return pReal;
    }

    /**
     * Metoda, przypisujaca czesc rzeczywista
     * @param real real od liczby zespolonej
     */

    public void setRe(double real) {
        this.r = real;
    }

    /**
     * Metoda, ktora przypisuje czesc urojona
     * @param imaginary imaginary od liczby zespolonej
     */

    public void setIm(double imaginary) {
        this.i = imaginary;
    }

    /**
     * Metoda, ktora przypisuje wartosci czesci rzeczywistej i czesci urojonej
     * @param number liczba urojona, obiekt klasy Complex
     */

    public void setVal(Complex number) {
        this.r = number.r;
        this.i = number.i;
    }

    /**
     * Metoda, ktora przypisuje wartosci czesci urojonej i czesci rzeczywistej
     * @param real real od liczby zespolonej,
     * @param imaginary imaginary imaginary od liczby zespolonej
     */

    public void setVal(double real, double imaginary) {
        this.r = real;
        this.i = imaginary;
    }
}

