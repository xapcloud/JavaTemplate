package src;

import java.io.Serializable;
import java.util.Objects;

public class Pair<K, V> implements Comparable<Pair<K, V>>, Serializable {
    private static final long serialVersionUID = 1L;

    private K key;
    private V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    @Override
    public String toString() {
        return key + "=" + value;
    }

    @Override
    public int hashCode() {
        return key.hashCode() * 13 + (value == null ? 0 : value.hashCode());
    }

    @Override
    @SuppressWarnings("rawtypes")
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o instanceof Pair) {
            Pair pair = (Pair) o;
            if (!Objects.equals(key, pair.key) || !Objects.equals(value, pair.value))
                return false;
            return true;
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    private int compare(Object a, Object b) {
        return a == null ? (b == null ? 0 : -1) : (b == null) ? 1 : ((Comparable<Object>) a).compareTo(b);
    }

    @Override
    public int compareTo(Pair<K, V> pair) {
        int c = compare(key, pair.getKey());
        return c == 0 ? compare(value, pair.getValue()) : c;
    }
}