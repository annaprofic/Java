import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class PrintFile {
    public static void main(String[] args) {

        FileReader file = null;         //inicjalizacja FileReader strumien danych
        String line;
        int i = 0;

        try {

            if ( args.length != 0 ) {               // sprawdzanie ilosci argumentow
                String plik = args[0];
                file = new FileReader(plik);        // przypisywanie zmiennej plik do file
            } else
                System.out.println("Niepoprawna ilosc argumentow:" + args.length + "prosze wpisac nazwe pliku 'nazwa.txt'");

        } catch (FileNotFoundException e) {
            System.out.println("Plik nie zostal znaleziony!");
        }

        BufferedReader buff = new BufferedReader(file);     // BufferedReader umozliwia odczytywanie tekstu liniam

        try {

            while ((line = buff.readLine()) != null) {      // metoda readLine zwraca null, kiedy nie pozostaje juz danych do odczytywania
                System.out.print(i + 1 + ". ");             // wpisujemy numeracje do liniek
                System.out.println(line);
                i ++;
            }

        } catch (IOException e) {
            System.out.println("Blad odczytu z  pliku.");
        }

        try {
            file.close();                                   // zamykamy file
        } catch (IOException e) {
            System.out.println("Blad przy zamykaniu pliku.");
        }
    }
}

