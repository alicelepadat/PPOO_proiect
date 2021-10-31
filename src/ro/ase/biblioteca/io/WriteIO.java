package ro.ase.biblioteca.io;

import ro.ase.biblioteca.entities.Entitate;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import static ro.ase.biblioteca.data.FilePath.ROOT_PATH;

public class WriteIO {
    public static void scrieRaportExcel(Entitate[] raport, String numeFisier) {
        try {
            FileWriter fileWriter = new FileWriter(ROOT_PATH + numeFisier + ".csv");
            BufferedWriter writer = new BufferedWriter(fileWriter);
            for(Entitate e: raport){
                writer.write(e.toString());
                writer.append("\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
