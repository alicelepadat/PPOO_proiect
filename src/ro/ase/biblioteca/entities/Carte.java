package ro.ase.biblioteca.entities;


import ro.ase.biblioteca.contracts.ICarte;
import ro.ase.biblioteca.enums.ColectieEnum;

public class Carte extends Entitate implements ICarte {
    private String titlu;
    private ColectieEnum colectie;
    private Autor autor;
    private Editura editura;
    private int nrExemplare;

    public Carte() {
        super();
    }

    public Carte(String titlu, ColectieEnum colectie, Autor autor, Editura editura, int nrExemplare) {
        super();
        this.titlu = titlu;
        this.colectie = colectie;
        this.autor = autor;
        this.editura = editura;
        this.nrExemplare = nrExemplare;
    }

    public String getTitlu() {
        return titlu;
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    public ColectieEnum getColectie() {
        return colectie;
    }

    public void setColectie(ColectieEnum colectie) {
        this.colectie = colectie;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Editura getEditura() {
        return editura;
    }

    public void setEditura(Editura editura) {
        this.editura = editura;
    }

    public int getNrExemplare() {
        return nrExemplare;
    }

    public void setNrExemplare(int nrExemplare) {
        this.nrExemplare = nrExemplare;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CARTE[");
        sb.append("id=").append(super.getId());
        sb.append(", titlu='").append(titlu).append('\'');
        sb.append(", colectie=").append(colectie);
        sb.append(", autor=").append(autor.getNume());
        sb.append(", editura=").append(editura.getDenumire());
        sb.append(", ").append(editura.getAdresa());
        sb.append(", nr. exemplare=").append(nrExemplare);
        sb.append(']');
        return sb.toString();
    }

    @Override
    public void decrementNrExemplare() {
        if (nrExemplare > 0) {
            nrExemplare--;
        } else {
            System.out.println("Cartea nu este disponibila pentru imprumut.");
        }
    }

    @Override
    public void incrementNrExemplare() {
        nrExemplare++;
    }
}
