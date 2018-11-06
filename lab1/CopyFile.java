import java.io.*;

public class CopyFile {
    public static void main(String[] args) {

        //imicjalizacja dla strumieni

        DataInputStream from = null;        //pliku wejsciowego
        DataOutputStream to = null;         //pliku wyjsciowego

        //sczytywanie argumentow wejsciowych

        String plik1 = args[0];             // przypisywanie pliku
        String plik2 = args[1];

        try {

            from = new DataInputStream(new FileInputStream(plik1));
            to = new DataOutputStream(new FileOutputStream(plik2));

        } catch (FileNotFoundException e) {
            System.out.println("Nie znaleziono takiego pliku.");
        }

        byte[] buff = new byte[10*1024];
        int bytes = 0;                      // przeczytane bajty

        try {
            while ((bytes = from.read(buff)) != -1) {   // zapisuje kolejne bajty do bufora
                                                        // jezeli nie ma juz bajtow do zapisywania metoda zwraca -1
                to.write(buff, 0, bytes);           // offset - od ktorego miejsca zapisujemy dane = 0, len - ilosc danych( dlugosc)
            }
            System.out.println("Plik zostal poprawnie skopiowany!");

        } catch (IOException e) {
            System.out.println("Blad wejscia / wyjscia.");
        }

        //zamykanie strumieni wejscia/wyjscia
        try {
            if(from != null && to != null) {    // sprawdzamy czy nie sa puste
                from.close();
                to.close();
            }
        } catch (IOException e) {
            System.out.println("Blad zamykania strumienia dla pliku.");
        }


    }

}


