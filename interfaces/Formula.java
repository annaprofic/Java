/**
 * Interfejs funkcyjny przechowujacy metode count
 * @param <T> okresla typ danych
 * @author Anna Sarnavska
 */
@FunctionalInterface

public interface Formula<T> {
    T count (T x);
}
