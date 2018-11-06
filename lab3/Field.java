public interface Field<T> {

    T add(T obj);
    T sub(T obj);
    T mul(T obj);
    T div(T obj) throws DivideByZeroException;
    String toString();
}
