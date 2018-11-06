package start;

/**
 * Interfejs wykonujacy metode compute liczb typu T
 * @param <T>
 * @author Ania Sarnavska
 */

@FunctionalInterface
public interface Computation<T> {
    T compute(T x);
}

