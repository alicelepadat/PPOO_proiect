package ro.ase.biblioteca.entities;

public class Autor extends Entitate {
    private String nume;

    public Autor() {
        super();
    }

    public Autor(String nume) {
        super();
        this.nume = nume;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AUTOR[");
        sb.append("id=").append(super.getId());
        sb.append(", nume='").append(nume).append('\'');
        sb.append(']');
        return sb.toString();
    }
}
