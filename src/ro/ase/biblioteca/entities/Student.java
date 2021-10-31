package ro.ase.biblioteca.entities;

import ro.ase.biblioteca.enums.CicluStudiuEnum;
import ro.ase.biblioteca.enums.FacultateEnum;

public class Student extends Entitate {
    private String nume;
    private FacultateEnum facultate;
    private CicluStudiuEnum cicluStudiu;
    private int anStudiu;
    private PermisBiblioteca permisBiblioteca;

    public Student() {
        super();
    }

    public Student(String nume, FacultateEnum facultate, CicluStudiuEnum cicluStudiu, int anStudiu, PermisBiblioteca permisBiblioteca) {
        this.nume = nume;
        this.facultate = facultate;
        this.cicluStudiu = cicluStudiu;
        this.anStudiu = anStudiu;
        this.permisBiblioteca = permisBiblioteca;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public FacultateEnum getFacultate() {
        return facultate;
    }

    public void setFacultate(FacultateEnum facultate) {
        this.facultate = facultate;
    }

    public CicluStudiuEnum getCicluStudiu() {
        return cicluStudiu;
    }

    public void setCicluStudiu(CicluStudiuEnum cicluStudiu) {
        this.cicluStudiu = cicluStudiu;
    }

    public int getAnStudiu() {
        return anStudiu;
    }

    public void setAnStudiu(int anStudiu) {
        if (anStudiu <= 3 && anStudiu > 0) {
            this.anStudiu = anStudiu;
        }
    }

    public PermisBiblioteca getPermisBiblioteca() {
        return permisBiblioteca;
    }

    public void setPermisBiblioteca(PermisBiblioteca permisBiblioteca) {
        this.permisBiblioteca = permisBiblioteca;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("STUDENT[");
        sb.append("id=").append(super.getId());
        sb.append(", nume='").append(nume).append('\'');
        sb.append(", facultate=").append(facultate);
        sb.append(", ciclu studiu=").append(cicluStudiu);
        sb.append(", an=").append(anStudiu);
        sb.append(", permis biblioteca=id=").append(permisBiblioteca.getId());
        sb.append(", de la: ").append(permisBiblioteca.getDataEliberarii());
        sb.append(", pana la: ").append(permisBiblioteca.getDataExpirarii());
        sb.append(']');
        return sb.toString();
    }
}
