package ro.ase.biblioteca.generics;

import java.util.HashMap;

public class Colectie<K, V> implements IColectie<K, V> {
    protected HashMap<K, V> colectie;

    public HashMap<K, V> getColectie() {
        return colectie;
    }

    public Colectie() {
        colectie = new HashMap<>();
    }

    @Override
    public int getSize() {
        return colectie.values().size();
    }

    @Override
    public boolean isEmpty() {
        return colectie.isEmpty();
    }

    @Override
    public void add(K key, V value) {
        colectie.put(key, value);
    }

    @Override
    public boolean containsKey(K key) {
        return colectie.containsKey(key);
    }

    @Override
    public V getFromKey(K key) {
        return colectie.get(key);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("colectie=").append(colectie);
        return sb.toString();
    }
}
