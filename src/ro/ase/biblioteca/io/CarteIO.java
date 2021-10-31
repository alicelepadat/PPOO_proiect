package ro.ase.biblioteca.io;

import org.json.JSONArray;
import org.json.JSONObject;
import ro.ase.biblioteca.collections.ColectieCarti;
import ro.ase.biblioteca.entities.Autor;
import ro.ase.biblioteca.entities.Carte;
import ro.ase.biblioteca.entities.Editura;
import ro.ase.biblioteca.enums.ColectieEnum;

import java.io.*;
import java.util.HashSet;
import java.util.Map;

public class CarteIO {

    public static ColectieCarti citireCartiJSON(String numeFisier) {
        ColectieCarti colectieCarti = new ColectieCarti();
        String textPreluat = ReadIO.citireTextFisier(numeFisier);

        if (textPreluat != null) {
            JSONObject jsonObject = new JSONObject(textPreluat);
            JSONArray listaCarti = jsonObject.getJSONArray("carti");

            for (int i = 0; i < listaCarti.length(); i++) {
                JSONObject obiect = listaCarti.getJSONObject(i);
                String titlu = obiect.getString("titlu");
                ColectieEnum colectie = ColectieEnum.valueOf(obiect.getString("colectie"));
                String numeAutor = obiect.getString("autor");
                Autor autor = new Autor(numeAutor);
                JSONObject obiectEditura = obiect.getJSONObject("editura");
                String numeEditura = obiectEditura.getString("denumire");
                String adresaEditura = obiectEditura.getString("adresa");
                Editura editura = new Editura(numeEditura, adresaEditura);
                int nrExemplare = obiect.getInt("nr_exemplare");
                Carte carte = new Carte(titlu, colectie, autor, editura, nrExemplare);

                colectieCarti.adaugaCarte(carte);
            }
        }

        return colectieCarti;
    }

    public static void scrieCartiJson(String numeFisier, ColectieCarti colectie) {
        if (!colectie.isEmpty()) {
            JSONObject obiect = new JSONObject();
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(numeFisier));
                JSONArray colectieCarti = new JSONArray();
                for (Map.Entry<ColectieEnum, HashSet<Carte>> entry : colectie.getColectie().entrySet()) {
                    for (Carte c : entry.getValue()) {
                        JSONObject carte = new JSONObject();
                        carte.put("titlu", c.getTitlu());
                        carte.put("colectie", c.getColectie());
                        carte.put("autor", c.getAutor().getNume());
                        JSONObject editura = new JSONObject();
                        editura.put("denumire", c.getEditura().getDenumire());
                        editura.put("adresa", c.getEditura().getAdresa());
                        carte.put("editura", editura);
                        carte.put("nr_exemplare", c.getNrExemplare());
                        colectieCarti.put(carte);
                    }
                }
                obiect.put("carti", colectieCarti);
                writer.write(obiect.toString());
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
