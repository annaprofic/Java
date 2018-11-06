import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Print {

    public static void main(String[] args) {


        try {

            File file = null; // tworzenie zmiennej typu File
            if (args.length != 0) { //sprawdanie czy zostaly podane argumenty

                file = new File(args[0]); // jako file zostal podany pierwszy argument wpisany przy wywolaniu
            } else
                System.out.println("Niepoprawna ilosc argumentow:" + args.length + "prosze wpisac nazwe pliku 'nazwa.txt'");

            Scanner in = new Scanner(file); // odczytujemy zawartosc pliku

            System.out.println("Dane przepisane z pliku: ");

            //zapisywanie zawartosci
            while (in.hasNextLine()) { //dopoki linije sie nie koncza
                String zdaniezpliku = in.nextLine(); // do stringa zapisujemy odskanowana linije
                System.out.println(zdaniezpliku);
            }


        } catch (FileNotFoundException e) { // wyjatek, w przypadku nie znalezienia pliku
            System.out.println("Plik nie znaleziony.");
        } catch (NoSuchElementException e) { // wyjatek, w przypadku kiedy plik jest pusty
            System.out.println("Plik jest pusty. Prosze wpisac dane do pliku!");
        }
    }
}

