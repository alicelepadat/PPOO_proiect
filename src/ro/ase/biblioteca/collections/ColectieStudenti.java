package ro.ase.biblioteca.collections;

import ro.ase.biblioteca.entities.PermisBiblioteca;
import ro.ase.biblioteca.entities.Student;
import ro.ase.biblioteca.enums.CicluStudiuEnum;
import ro.ase.biblioteca.enums.FacultateEnum;
import ro.ase.biblioteca.generics.Colectie;

import java.time.DateTimeException;
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
        FacultateEnum facultate = getFacultate();

        System.out.println("Ciclu studiu:");
        CicluStudiuEnum cicluStudiu = getCicluStudiu();

        System.out.println("An studiu:");
        scanner = new Scanner(System.in);
        int an = scanner.nextInt();

        System.out.println("Permis biblioteca:");

        System.out.println("\tdata eliberare (yyyy-MM-dd):");
        LocalDate dataEliberarePermis = setDataEliberareIfValid();

        System.out.println("\tdata expirare (yyyy-MM-dd):");
        LocalDate dataExpirarePermis = setDataExpirareIfValid(dataEliberarePermis);

        Student deAdaugat = new Student(
                nume,
                facultate,
                cicluStudiu,
                an,
                new PermisBiblioteca(
                        dataEliberarePermis,
                        dataExpirarePermis
                )
        );
        super.add(deAdaugat.getPermisBiblioteca().getId(), deAdaugat);
    }

    private boolean parsareData(String data) {
        try {
            LocalDate.parse(data);
            return true;
        } catch (DateTimeException e) {
            System.out.println("Data invalida. Incercati din nou.");
            return false;
        }
    }

    private LocalDate getDataPermis() {
        Scanner scanner = new Scanner(System.in);
        String data = scanner.nextLine();
        if (parsareData(data)) {
            return LocalDate.parse(data);
        } else {
            return getDataPermis();
        }
    }

    private FacultateEnum getFacultate() {
        Scanner scanner = new Scanner(System.in);
        String facultate = scanner.nextLine();
        if (FacultateEnum.contains(facultate)) {
            return FacultateEnum.valueOf(facultate.toUpperCase(Locale.ROOT));
        } else {
            System.out.println("Facultatea introdusa nu este corecta, incercati din nou.");
            return getFacultate();
        }
    }

    private CicluStudiuEnum getCicluStudiu() {
        Scanner scanner = new Scanner(System.in);
        String cicluStudiu = scanner.nextLine();
        if (CicluStudiuEnum.contains(cicluStudiu)) {
            return CicluStudiuEnum.valueOf(cicluStudiu.toUpperCase(Locale.ROOT));
        } else {
            System.out.println("Ciclul de studiu introdus nu este corect, incercati din nou.");
            return getCicluStudiu();
        }
    }

    private LocalDate setDataEliberareIfValid() {
        LocalDate dataEliberare = getDataPermis();

        if(dataEliberare.compareTo(LocalDate.now())<0){
            System.out.println("Data de eliberare nu poate fi o data din trecut.");
            return setDataEliberareIfValid();
        }

        return dataEliberare;
    }

    private LocalDate setDataExpirareIfValid(LocalDate dataEliberare) {
        LocalDate dataExpirare = getDataPermis();

        if(dataExpirare.compareTo(dataEliberare)<0){
            System.out.println("Data de expirare trebuie sa fie mai mare decat data de eliberare.");
            return setDataExpirareIfValid(dataEliberare);
        }

        return dataExpirare;
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
