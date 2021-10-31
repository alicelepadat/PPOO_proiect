package ro.ase.biblioteca.menu.principal;

import ro.ase.biblioteca.data.StoredData;
import ro.ase.biblioteca.io.CarteIO;
import ro.ase.biblioteca.io.FisaLecturaIO;
import ro.ase.biblioteca.io.StudentIO;
import ro.ase.biblioteca.menu.carti.NavigareCarti;
import ro.ase.biblioteca.menu.fise.NavigareFiseLectura;
import ro.ase.biblioteca.menu.studenti.NavigareStudenti;

import java.util.Scanner;

import static ro.ase.biblioteca.data.FilePath.*;

public class NavigarePrincipala {
    private NavigarePrincipalaEnum[] optiuni;

    private static NavigarePrincipala instanta = null;

    private NavigarePrincipala() {
        optiuni = NavigarePrincipalaEnum.values();
    }

    public static synchronized NavigarePrincipala getInstanta() {
        if (instanta == null)
            instanta = new NavigarePrincipala();
        return instanta;
    }

    public static void selectareOptiune(){
        Scanner scanner = new Scanner(System.in);
        System.out.println(NavigarePrincipala.getInstanta());
        int optiune = scanner.nextInt();
        switch (optiune){
            case 1:
                NavigareCarti.selectareOptiune();
                break;
            case 2:
                NavigareStudenti.selectareOptiune();
                break;
            case 3:
                NavigareFiseLectura.selectareOptiune();
                break;
            case 4:
                StoredData dateBiblioteca = StoredData.getInstanta();
                CarteIO.scrieCartiJson(FISIER_CARTI, dateBiblioteca.getCarti());
                StudentIO.scrieStudentiJson(FISIER_STUDENTI, dateBiblioteca.getStudenti());
                FisaLecturaIO.scrieFiseLectura(FISIER_FISE_LECTURA, dateBiblioteca.getFiseLectura());
                System.exit(0);
            default:
                System.out.println("Optiune nevalida, incercati din nou.");
                NavigarePrincipala.selectareOptiune();
                break;
        }
        scanner.close();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        for(int i=0;i<optiuni.length;i++){
            sb.append(i+1).append('.').append(optiuni[i]).append('\t');
        }
        return sb.toString();
    }
}
