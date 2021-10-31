package ro.ase.biblioteca.menu.subcategorie;

import ro.ase.biblioteca.entities.Carte;
import ro.ase.biblioteca.entities.Entitate;
import ro.ase.biblioteca.entities.Student;
import ro.ase.biblioteca.io.WriteIO;
import ro.ase.biblioteca.menu.carti.NavigareCarti;
import ro.ase.biblioteca.menu.studenti.NavigareStudenti;

import java.util.Scanner;

public class Subcategorie {
    private SubcategorieEnum[] subcategorii;

    private static Subcategorie instanta = null;

    private Subcategorie() {
        subcategorii = SubcategorieEnum.values();
    }

    public static synchronized Subcategorie getInstanta() {
        if (instanta == null)
            instanta = new Subcategorie();
        return instanta;
    }

    public static void selectareOptiune(Entitate[] raport) {
        System.out.println("Selectati o optiune:");
        System.out.println(Subcategorie.getInstanta());
        Scanner scanner = new Scanner(System.in);
        int optiune = scanner.nextInt();
        switch (optiune){
            case 1:
                System.out.println("Introduceti nume fisier:");
                String numeFisier = scanner.next();
                WriteIO.scrieRaportExcel(raport, numeFisier);
                if(raport instanceof Carte[]){
                    NavigareCarti.selectareOptiune();
                    break;
                }
                if(raport instanceof Student[]){
                    NavigareStudenti.selectareOptiune();
                    break;
                }
                break;
            case 2:
                if(raport instanceof Carte[]){
                    NavigareCarti.selectareOptiune();
                    break;
                }
                if(raport instanceof Student[]){
                    NavigareStudenti.selectareOptiune();
                    break;
                }
                break;
            default:
                System.out.println("Optiune nevalida, incercati din nou.");
                Subcategorie.selectareOptiune(raport);
                break;
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < subcategorii.length; i++) {
            sb.append(i + 1).append('.').append(subcategorii[i]).append('\t');
        }
        return sb.toString();
    }
}
