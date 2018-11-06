import java.util.Scanner;

/**
 * Klasa implementujaca interfejs DateTimeInfo
 * @author Anna Sarnavska
 */

public class Note implements DateTimeInfo {

    String note;
    String time;
    String name;

    /**
     * Metoda statyczna ktora wyswietla menu wyboru
     * @return zwraca wybor pryjety od uzytkownika
     */

    @Override
    public String donow() {
        return "Ok";
    }

    public static int showMenu(){
        Scanner scan = new Scanner(System.in);
        System.out.println("What do you want?\n" + "1. Add a note\n" + "2. Check the last note\n" + "3. Exit\n");
        int choice = scan.nextInt();
        return choice;
    }

    /**
     * Metoda statyczna pozwalajaca na zapisywanie notatki
     * Odczytuje zapisane dane za pomoca Scannera
     * @return note - podane dane uzytkownika
     */

    public static String addNote() {

        System.out.println("My note:");

        Scanner scan = new Scanner(System.in);
        String note = scan.nextLine();

        return note;
    }

    /**
     * Metoda, ktora przyjmuje stworzana notatke, date jej stworzenia i autora i zapisuje ich do parametrow klasy
     * @param note notatka uzytkownika
     * @param time data jej napisania
     * @param name imie uzytkownika
     */

    public void saveNote(String note, String time, String name) {
        this.note = note;
        this.time = time;
        this.name = name;
    }

    /**
     * Metoda, ktora wypisuje ostatnia stworzana notatke
     * @return note, time, name
     */

    public String printNote() {
        return this.note + "\n" + this.time + "\n" + this.name + "\n";
    }

    public static void main(String[] args) {
        Note n = new Note();

        System.out.println("Please, enter your name: ");
        Scanner scan = new Scanner(System.in);
        String name = scan.nextLine();

        Note ob = new Note();
        while (true) {
            switch (showMenu()) {

                case 1:


                    String note = addNote();
                    String time = ob.donow();
                    System.out.println(time);
                    String nameU = DateTimeInfo.sign(name);
                    System.out.println(nameU);
                    System.out.println();
                    n.saveNote(note, time, nameU);

                    break;

                case 2:
                    System.out.println(n.printNote());
                    break;

                case 3:
                    System.exit(0);
            }
        }

    }
}
