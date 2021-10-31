package ro.ase.biblioteca.data;

import ro.ase.biblioteca.collections.ColectieCarti;
import ro.ase.biblioteca.collections.ColectieFiseLectura;
import ro.ase.biblioteca.collections.ColectieStudenti;
import ro.ase.biblioteca.io.CarteIO;
import ro.ase.biblioteca.io.FisaLecturaIO;
import ro.ase.biblioteca.io.StudentIO;

import static ro.ase.biblioteca.data.FilePath.*;

public class StoredData {

    private ColectieCarti carti;
    private ColectieStudenti studenti;
    private ColectieFiseLectura fiseLectura;

    private static StoredData instanta = null;

    public StoredData() {
        carti = CarteIO.citireCartiJSON(FISIER_CARTI);
        studenti = StudentIO.citireStudentiJSON(FISIER_STUDENTI);
        fiseLectura = FisaLecturaIO.citesteFiseLectura(FISIER_FISE_LECTURA);
    }

    public static synchronized StoredData getInstanta() {
        if (instanta == null)
            instanta = new StoredData();
        return instanta;
    }

    public ColectieCarti getCarti() {
        return carti;
    }

    public ColectieStudenti getStudenti() {
        return studenti;
    }

    public ColectieFiseLectura getFiseLectura() {
        return fiseLectura;
    }
}
