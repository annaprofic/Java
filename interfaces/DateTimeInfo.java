import java.time.*;

/**
 * Interfejs, ktory ma w sobie dwie metody: domyslna i statyczna
 * @author Anna Sarnavska
 */

public interface DateTimeInfo {

    /**
     * Metoda domyslna wypisujaca lokalny czas i date za pomoca biblioteki time
     * @return LocalTime.now() i LocalDate.now();
     */

    default String now() {
        return LocalTime.now() + "\n" + LocalDate.now();
    }

    default String donow() {
        return now();
    }

    /**
     * Metoda statyczna, ktora wypisuje podpis autora notatki
     * @param author imie uzytkownika, ktory stworzyl notatke
     * @return podpis uzytkownika
     */

    static String sign(String author) {
        return "Created by " + author;
    }

}


