package ro.ase.biblioteca.entities;

import java.io.Serializable;

public abstract class Entitate implements Serializable {
    private static int count = 420;
    private int id;

    public Entitate() {
        this.id = count++;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "" + id;
    }
}
