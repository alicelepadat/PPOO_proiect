package ro.ase.biblioteca.menu.studenti;

import ro.ase.biblioteca.collections.ColectieStudenti;
import ro.ase.biblioteca.data.StoredData;
import ro.ase.biblioteca.entities.Student;
import ro.ase.biblioteca.menu.principal.NavigarePrincipala;
import ro.ase.biblioteca.menu.subcategorie.Subcategorie;

import java.util.Scanner;

public class NavigareStudenti {
    private NavigareStudentiEnum[] optiuni;

    private static NavigareStudenti instanta = null;

    private NavigareStudenti() {
        optiuni = NavigareStudentiEnum.values();
    }

    public static synchronized NavigareStudenti getInstanta() {
        if (instanta == null)
            instanta = new NavigareStudenti();
        return instanta;
    }

    public static void selectareOptiune() {
        StoredData dateBiblioteca = StoredData.getInstanta();
        ColectieStudenti studenti = dateBiblioteca.getStudenti();
        Student[] raportStudenti = null;
        System.out.println("Selectati o optiune:");
        System.out.println(NavigareStudenti.getInstanta());
        Scanner scanner = new Scanner(System.in);
        int optiune = scanner.nextInt();

        switch (optiune) {
            case 1:
                raportStudenti = studenti.getColectie().values().toArray(new Student[0]);
                for (Student s : raportStudenti) {
                    System.out.println(s);
                }
                Subcategorie.selectareOptiune(raportStudenti);
                break;
            case 2:
                System.out.println("Introduceti facultate:");
                scanner = new Scanner(System.in);
                raportStudenti = studenti.cautaStudentiFacultate(scanner.nextLine());
                if (raportStudenti != null) {
                    for (Student s : raportStudenti) {
                        System.out.println(s);
                    }
                    Subcategorie.selectareOptiune(raportStudenti);
                } else {
                    System.out.println("Nu exista studenti.");
                    NavigareStudenti.selectareOptiune();
                }
                break;
            case 3:
                studenti.adaugaStudent();
                NavigareStudenti.selectareOptiune();
                break;
            case 4:
                NavigarePrincipala.selectareOptiune();
                break;
            default:
                System.out.println("Optiune nevalida, incercati din nou.");
                NavigareStudenti.selectareOptiune();
                break;
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
