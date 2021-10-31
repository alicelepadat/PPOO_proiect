package ro.ase.biblioteca.collections;

import ro.ase.biblioteca.entities.Carte;
import ro.ase.biblioteca.entities.Imprumut;
import ro.ase.biblioteca.entities.Student;
import ro.ase.biblioteca.generics.Colectie;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;

public class ColectieFiseLectura extends Colectie<Integer, HashSet<Imprumut>> {
    public ColectieFiseLectura() {
        super();
    }

    private boolean valideazaImprumut(Imprumut imprumut) {
        boolean valid = true;
        HashSet<Imprumut> fisaLectura = super.getFromKey(imprumut.getIdPermisStudent());
        if (fisaLectura != null) {
            for (Imprumut i : fisaLectura) {
                if (i.getIdCarte() == imprumut.getIdCarte() && !i.dataImprumutValida()) {
                    valid = false;
                    break;
                }
            }
        }
        return valid;
    }

    public void inregistreazaImprumut(Student student, Carte carte) {
        Imprumut imprumut = new Imprumut(student.getPermisBiblioteca().getId(), carte.getId());
        if (!super.isEmpty() && !valideazaImprumut(imprumut)) {
            System.out.println("Studentul a imprumutat deja cartea " + carte.getTitlu() + " in data de " + imprumut.getDataImprumut());
        } else {
            carte.decrementNrExemplare();
            if(colectie.containsKey(student.getPermisBiblioteca().getId())) {
                super.getFromKey(student.getPermisBiblioteca().getId()).add(imprumut);
            } else {
                super.add(student.getPermisBiblioteca().getId(), new HashSet<>(Collections.singleton(imprumut)));
            }
            System.out.println("Studentul " + student.getNume() + " a imprumutat cartea " + carte.getTitlu() + ".");
        }
    }

    public void actualizeazaImprumut(int idPermisStudent, Carte carte) {
        HashSet<Imprumut> fisaLecturaStudent = super.getFromKey(idPermisStudent);
        for (Imprumut imprumut : fisaLecturaStudent) {
            if (imprumut.getIdCarte() == carte.getId() && imprumut.getDataReturnare()==null) {
                imprumut.setDataReturnare(LocalDate.now());
                carte.incrementNrExemplare();
                System.out.println("Cartea " + carte.getTitlu() + " a fost returnata.");
            } else{
                System.out.println("Cartea a fost deja returnata");
            }
            break;
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("FISE LECTURA");
        for (Map.Entry<Integer, HashSet<Imprumut>> entry : super.colectie.entrySet()) {
            sb.append("\n").append(entry.getKey());
            for (Imprumut imprumut : entry.getValue()) {
                sb.append("\n").append(imprumut);
            }
        }
        return sb.toString();
    }

    public void adaugaFisaLectura(Imprumut i) {
        if (colectie.containsKey(i.getIdPermisStudent())) {
            super.getFromKey(i.getIdPermisStudent()).add(i);
        } else {
            super.add(i.getIdPermisStudent(), new HashSet<>(Collections.singleton(i)));
        }
    }
}
