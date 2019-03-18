package generics;

public class OrderedPair<K extends Comparable<? super K>, V>  implements Comparable <OrderedPair<K, V>>, Pair<K, V> {
    K key;
    V value;

    public OrderedPair(K key, V value){
        this.key = key;
        this.value = value;
    }

    public OrderedPair(OrderedPair<K, V> pair){
        this.key = pair.getKey();
        this.value = pair.getValue();
    }

    @Override
    public K getKey() {
        return key;
    }

    @Override
    public V getValue() {
        return value;
    }

    @Override
    public int compareTo(OrderedPair<K, V> o) {
        return Integer.compare(key.compareTo(o.getKey()), 0);
    }

    public String toString(){
        return "(" + this.key.toString() + ", " + this.value.toString() + ")";
    }
}
