package ro.ase.biblioteca.io;

import ro.ase.biblioteca.collections.ColectieFiseLectura;
import ro.ase.biblioteca.entities.Imprumut;

import java.io.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;

public class FisaLecturaIO {
    public static ColectieFiseLectura citesteFiseLectura(String numeFisier) {
        ColectieFiseLectura colectieFiseLectura = new ColectieFiseLectura();
        Scanner scanner;
        File file = new File(numeFisier);
        if (!file.exists()) {
            System.out.println("Biblioteca nu a inregistrat nicio fisa de lectura.");
            return colectieFiseLectura;
        }
        try {
            scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String linie = scanner.nextLine();
                Imprumut i = parsareLinie(linie);
                colectieFiseLectura.adaugaFisaLectura(i);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return colectieFiseLectura;
    }

    private static Imprumut parsareLinie(String linie) {
        Imprumut imprumut = new Imprumut();
        Scanner linieScanner = new Scanner(linie);
        linieScanner.useDelimiter(",");
        while (linieScanner.hasNext()) {
            imprumut.setIdPermisStudent(linieScanner.nextInt());
            imprumut.setIdCarte(linieScanner.nextInt());
            imprumut.setDataImprumut(LocalDate.parse(linieScanner.next()));
            String dataReturnare = linieScanner.next();
            if (!dataReturnare.equals("null")) {
                imprumut.setDataReturnare(LocalDate.parse(dataReturnare));
            }
        }
        linieScanner.close();
        return imprumut;
    }

    public static void scrieFiseLectura(String numeFisier, ColectieFiseLectura colectieFiseLectura) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(numeFisier));
            for (Map.Entry<Integer, HashSet<Imprumut>> entry : colectieFiseLectura.getColectie().entrySet()) {
                for (Imprumut imprumut : entry.getValue()) {
                    writer.write(imprumut.getIdPermisStudent() + "");
                    writer.append(",");
                    writer.write(imprumut.getIdCarte() + "");
                    writer.append(",");
                    writer.write(imprumut.getDataImprumut() + "");
                    writer.append(",");
                    writer.write(imprumut.getDataReturnare() + "");
                    writer.append("\n");
                }
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
