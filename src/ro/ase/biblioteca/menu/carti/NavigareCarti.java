package ro.ase.biblioteca.menu.carti;

import ro.ase.biblioteca.collections.ColectieCarti;
import ro.ase.biblioteca.data.StoredData;
import ro.ase.biblioteca.entities.Carte;
import ro.ase.biblioteca.entities.Student;
import ro.ase.biblioteca.enums.ColectieEnum;
import ro.ase.biblioteca.menu.subcategorie.Subcategorie;
import ro.ase.biblioteca.menu.principal.NavigarePrincipala;

import java.util.Locale;
import java.util.Scanner;

public class NavigareCarti {

    private NavigareCartiEnum[] optiuni;

    private static NavigareCarti instanta = null;

    private NavigareCarti() {
        optiuni = NavigareCartiEnum.values();
    }

    public static synchronized NavigareCarti getInstanta() {
        if (instanta == null)
            instanta = new NavigareCarti();
        return instanta;
    }

    public static void selectareOptiune() {
        StoredData dateBiblioteca = StoredData.getInstanta();
        ColectieCarti carti = dateBiblioteca.getCarti();
        Carte[] raportCarti = null;

        System.out.println("Selectati o optiune:");
        System.out.println(NavigareCarti.getInstanta());
        Scanner scanner = new Scanner(System.in);
        int optiune = scanner.nextInt();

        switch (optiune) {
            case 1:
                raportCarti = carti.getArray();
                break;
            case 2:
                System.out.println("Introduceti nume autor:");
                scanner = new Scanner(System.in);
                String numeAutor = scanner.nextLine();
                raportCarti = carti.cautaCartiAutor(numeAutor);
                break;
            case 3:
                System.out.println("Introduceti nume editura:");
                scanner = new Scanner(System.in);
                String numeEditura = scanner.nextLine();
                raportCarti = carti.cautaCartiEditura(numeEditura);
                break;
            case 4:
                System.out.println("Introduceti nume colectie:");
                scanner = new Scanner(System.in);
                String numeColectie = scanner.nextLine();
                boolean notFound = ColectieEnum.contains(numeColectie);
                if (!notFound) {
                    System.out.println("Colectia nu exista in biblioteca ASE.");
                } else {
                    ColectieEnum colectie = ColectieEnum.valueOf(numeColectie.toUpperCase(Locale.ROOT));
                    raportCarti = carti.getColectie().get(colectie).toArray(new Carte[0]);
                }
                break;
            case 5:
                NavigarePrincipala.selectareOptiune();
                break;
            default:
                System.out.println("Optiune nevalida, incercati din nou.");
                NavigareCarti.selectareOptiune();
                break;
        }
        if (raportCarti != null) {
            for (Carte c : raportCarti) {
                System.out.println(c);
            }
            Subcategorie.selectareOptiune(raportCarti);
        } else {
            System.out.println("Nu s-au gasit carti dupa criteriul specificat.");
            NavigareCarti.selectareOptiune();
        }
        scanner.close();
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
