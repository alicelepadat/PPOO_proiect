package ro.ase.biblioteca.collections;

import ro.ase.biblioteca.entities.PermisBiblioteca;
import ro.ase.biblioteca.entities.Student;
import ro.ase.biblioteca.enums.CicluStudiuEnum;
import ro.ase.biblioteca.enums.FacultateEnum;
import ro.ase.biblioteca.generics.Colectie;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class ColectieStudenti extends Colectie<Integer, Student> {

    public ColectieStudenti() {
        super();
    }

    public Student cautaStudentDupaNume(String nume) {
        Student gasit = null;
        for (Student s : super.getColectie().values()) {
            if (s.getNume().toUpperCase(Locale.ROOT).equals(nume.toUpperCase(Locale.ROOT))) {
                gasit = s;
                break;
            }
        }
        if (gasit != null) {
            if (gasit.getPermisBiblioteca().permisBibliotecaValid()) {
                return gasit;
            }
        }
        return null;
    }

    public Student[] cautaStudentiFacultate(String numeFacultate) {
        Student[] studentiFacultate = {};
        boolean existaFacultate = false;
        for (FacultateEnum facultate : FacultateEnum.values()) {
            if (facultate.toString().equals(numeFacultate.toUpperCase(Locale.ROOT))) {
                existaFacultate = true;
                break;
            }
        }
        if (existaFacultate) {
            for (Student s : super.getColectie().values()) {
                if (s.getFacultate().toString().equals(numeFacultate)) {
                    int n = studentiFacultate.length;
                    studentiFacultate = Arrays.copyOf(studentiFacultate, n + 1);
                    studentiFacultate[n] = s;
                }
            }
        } else {
            System.out.println("Facultate inexistenta.");
        }
        return studentiFacultate;
    }

    public void adaugaStudent() {
        System.out.println("Nume:");
        Scanner scanner = new Scanner(System.in);
        String nume = scanner.nextLine();

        System.out.println("Facultate:");
        scanner = new Scanner(System.in);
        String facultate = scanner.nextLine();

        System.out.println("Ciclu studiu:");
        scanner = new Scanner(System.in);
        String cicluStudiu = scanner.nextLine();

        System.out.println("An studiu:");
        scanner = new Scanner(System.in);
        int an = scanner.nextInt();

        System.out.println("Permis biblioteca:");

        System.out.println("\tdata eliberare (yyyy-MM-dd):");
        scanner = new Scanner(System.in);
        LocalDate dataEliberarePermis = LocalDate.parse(scanner.nextLine());

        System.out.println("\tdata expirare (yyyy-MM-dd):");
        scanner = new Scanner(System.in);
        LocalDate dataExpirarePermis = LocalDate.parse(scanner.nextLine());

        boolean valid = true;

        if (!FacultateEnum.contains(facultate)) {
            valid = false;
            System.out.println("Facultatea introdusa nu este corecta.");
        } else if (!CicluStudiuEnum.contains(facultate)) {
            valid = false;
            System.out.println("Ciclul de studiu introdus nu este corect.");
        }
        if (valid) {
            Student deAdaugat = new Student(
                    nume,
                    FacultateEnum.valueOf(facultate),
                    CicluStudiuEnum.valueOf(cicluStudiu),
                    an,
                    new PermisBiblioteca(
                            dataEliberarePermis,
                            dataExpirarePermis
                    )
            );
            super.add(deAdaugat.getPermisBiblioteca().getId(), deAdaugat);
        } else {
            System.out.println("Incercati din nou.");
            adaugaStudent();
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("STUDENTI");
        for (Map.Entry<Integer, Student> entry : super.colectie.entrySet()) {
            sb.append("\n").append(entry.getKey());
            sb.append("\n").append(entry.getValue());
        }
        return sb.toString();
    }
}
