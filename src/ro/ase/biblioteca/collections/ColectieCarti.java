package ro.ase.biblioteca.collections;


import ro.ase.biblioteca.entities.Carte;
import ro.ase.biblioteca.enums.ColectieEnum;
import ro.ase.biblioteca.generics.Colectie;

import java.util.*;

public class ColectieCarti extends Colectie<ColectieEnum, HashSet<Carte>> {
    public ColectieCarti() {
        super();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CARTI");
        for (Map.Entry<ColectieEnum, HashSet<Carte>> entry : super.colectie.entrySet()) {
            sb.append("\n").append(entry.getKey());
            for (Carte c : entry.getValue()) {
                sb.append("\n").append(c);
            }
        }
        return sb.toString();
    }

    public Carte cautaCarte(String titlu) {
        Carte gasita = null;
        for (Map.Entry<ColectieEnum, HashSet<Carte>> entry : super.colectie.entrySet()) {
            for (Carte carte : entry.getValue()) {
                if (carte.getTitlu().toLowerCase(Locale.ROOT).equals(titlu.toLowerCase()) && carte.getNrExemplare() > 0) {
                    gasita = carte;
                    break;
                }
            }
        }
        return gasita;
    }

    public void adaugaCarte(Carte c) {
        if (colectie.containsKey(c.getColectie())) {
            super.getFromKey(c.getColectie()).add(c);
        } else {
            super.add(c.getColectie(), new HashSet<>(Collections.singleton(c)));
        }
    }

    public Carte[] getArray() {
        Carte[] carti = {};
        for (Map.Entry<ColectieEnum, HashSet<Carte>> entry : colectie.entrySet()) {
            for (Carte carte : entry.getValue()) {
                int n = carti.length;
                carti = Arrays.copyOf(carti, n + 1);
                carti[n] = carte;
            }
        }
        return carti;
    }

    public Carte[] cautaCartiAutor(String nume) {
        Carte[] cartiAutor = {};
        for (Map.Entry<ColectieEnum, HashSet<Carte>> entry : colectie.entrySet()) {
            for (Carte carte : entry.getValue()) {
                if (carte.getAutor().getNume().toLowerCase(Locale.ROOT).equals(nume.toLowerCase(Locale.ROOT))) {
                    int n = cartiAutor.length;
                    cartiAutor = Arrays.copyOf(cartiAutor, n + 1);
                    cartiAutor[n] = carte;
                }
            }
        }
        return cartiAutor;
    }

    public Carte[] cautaCartiEditura(String editura) {
        Carte[] cartiEditura = {};
        for (Map.Entry<ColectieEnum, HashSet<Carte>> entry : colectie.entrySet()) {
            for (Carte carte : entry.getValue()) {
                if (carte.getEditura().getDenumire().toLowerCase(Locale.ROOT).equals(editura.toLowerCase(Locale.ROOT))) {
                    int n = cartiEditura.length;
                    cartiEditura = Arrays.copyOf(cartiEditura, n + 1);
                    cartiEditura[n] = carte;
                }
            }
        }
        return cartiEditura;
    }
}
