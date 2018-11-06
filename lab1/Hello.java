public class Hello {

    // statyczne wyrazenie iniciujace
    static {
        System.out.println("Klasa zostala zaladowana...");
    }
    // konstruktor
    public Hello() {
        System.out.println("Zostal wywolany konstruktor.");
    }

    public static void main(String[] args) {

        System.out.println("Hello world");

        // tworzenie obiektu klasy
        Hello hej = new Hello();

        if (args.length != 0) { //sprawdanie czy sa podane argumenty
            System.out.println("Podane argumenty:");

            //wypisywanie argumentow za pomoca petli foreach
            for (String i : args) {
                System.out.println(i);
            }
        } else
            System.out.println("Argumenty nie zostaly podane :)");

    }
}
