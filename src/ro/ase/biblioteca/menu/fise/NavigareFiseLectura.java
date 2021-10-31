package ro.ase.biblioteca.menu.fise;

import ro.ase.biblioteca.collections.ColectieFiseLectura;
import ro.ase.biblioteca.data.StoredData;
import ro.ase.biblioteca.entities.Carte;
import ro.ase.biblioteca.entities.Student;
import ro.ase.biblioteca.menu.principal.NavigarePrincipala;

import java.util.Scanner;


public class NavigareFiseLectura {
    private final NavigareFiseLecturaEnum[] optiuni;

    private static NavigareFiseLectura instanta = null;

    private NavigareFiseLectura() {
        optiuni = NavigareFiseLecturaEnum.values();
    }

    public static synchronized NavigareFiseLectura getInstanta() {
        if (instanta == null)
            instanta = new NavigareFiseLectura();
        return instanta;
    }

    public static void selectareOptiune() {
        StoredData dateBiblioteca = StoredData.getInstanta();
        ColectieFiseLectura fiseLectura = dateBiblioteca.getFiseLectura();
        System.out.println("Selectati o optiune:");
        System.out.println(NavigareFiseLectura.getInstanta());
        Scanner scanner = new Scanner(System.in);
        int optiune = scanner.nextInt();
        switch (optiune) {
            case 1:
                System.out.println(fiseLectura);
                break;
            case 2:
                System.out.println("Introduceti nume student:");
                scanner = new Scanner(System.in);
                String numeStudent = scanner.nextLine();
                System.out.println("Introduceti titlu carte:");
                scanner = new Scanner(System.in);
                String titluCarte = scanner.nextLine();
                NavigareFiseLectura.realizareImprumut(numeStudent, titluCarte);
                break;
            case 3:
                System.out.println("Introduceti nume student:");
                scanner = new Scanner(System.in);
                numeStudent = scanner.nextLine();
                System.out.println("Introduceti titlu carte:");
                scanner = new Scanner(System.in);
                titluCarte = scanner.nextLine();
                NavigareFiseLectura.restituireImprumut(numeStudent, titluCarte);
                break;
            case 4:
                NavigarePrincipala.selectareOptiune();
                break;
            default:
                System.out.println("Optiune nevalida, incercati din nou.");
                NavigareFiseLectura.selectareOptiune();
                break;
        }
        if (optiune != 4) {
            NavigareFiseLectura.selectareOptiune();
        }
        scanner.close();
    }

    private static void realizareImprumut(String numeStudent, String titluCarte) {
        Student s = StoredData.getInstanta().getStudenti().cautaStudentDupaNume(numeStudent);
        if (s != null) {
            Carte gasita = StoredData.getInstanta().getCarti().cautaCarte(titluCarte);
            if (gasita != null) {
                StoredData.getInstanta().getFiseLectura().inregistreazaImprumut(s, gasita);
            } else {
                System.out.println("Cartea " + gasita.getTitlu() + " nu este disponibila.");
            }
        } else {
            System.out.println("Studentul nu este inregistrat sau are permisul invalid!");
        }
    }

    private static void restituireImprumut(String numeStudent, String titluCarte) {
        Student s = StoredData.getInstanta().getStudenti().cautaStudentDupaNume(numeStudent);
        if (s != null) {
            Carte carteGasita = StoredData.getInstanta().getCarti().cautaCarte(titluCarte);
            if (carteGasita != null) {
                StoredData.getInstanta().getFiseLectura().actualizeazaImprumut(s.getPermisBiblioteca().getId(), carteGasita);
            } else {
                System.out.println("Cartea nu exist.");
            }
        } else {
            System.out.println("Studentul nu este inregistrat sau are permisul invalid!");
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < optiuni.length; i++) {
            sb.append(i + 1).append('.').append(optiuni[i]).append('\t');
        }
        return sb.toString();
    }
}
