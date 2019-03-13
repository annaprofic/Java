package messenger;

public class OrderedPair<K extends Comparable<? super K>, V> implements Comparable <OrderedPair<K, V>>, Pair<K,V> {
    private K key;
    private V value;

    OrderedPair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public K getKey() {
        return this.key;
    }

    @Override
    public V getValue() {
        return this.value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "( " + this.key + ", " + this.value + " )";
    }

    @Override
    public int compareTo(OrderedPair<K, V> o) {
        return Integer.compare(this.key.compareTo(o.getKey()), 0);
    }
}