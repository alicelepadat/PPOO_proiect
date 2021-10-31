package ro.ase.biblioteca.generics;

public interface IColectie<K, V> {
    int getSize();

    boolean isEmpty();

    void add(K key, V value);

    boolean containsKey(K key);

    V getFromKey(K key);


}
