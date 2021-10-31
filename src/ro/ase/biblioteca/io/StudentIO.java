package ro.ase.biblioteca.io;

import org.json.JSONArray;
import org.json.JSONObject;
import ro.ase.biblioteca.collections.ColectieStudenti;
import ro.ase.biblioteca.entities.PermisBiblioteca;
import ro.ase.biblioteca.entities.Student;
import ro.ase.biblioteca.enums.CicluStudiuEnum;
import ro.ase.biblioteca.enums.FacultateEnum;

import java.io.*;
import java.time.LocalDate;

public class StudentIO {

    public static ColectieStudenti citireStudentiJSON(String numeFisier) {
        ColectieStudenti colectieStudenti = new ColectieStudenti();
        String textPreluat = ReadIO.citireTextFisier(numeFisier);

        if (textPreluat != null) {
            JSONObject jsonObject = new JSONObject(textPreluat);
            JSONArray listaStudenti = jsonObject.getJSONArray("studenti");

            for (int i = 0; i < listaStudenti.length(); i++) {
                JSONObject obiect = listaStudenti.getJSONObject(i);
                String nume = obiect.getString("nume");
                FacultateEnum facultate = FacultateEnum.valueOf(obiect.getString("facultate"));
                CicluStudiuEnum cicluStudiu = CicluStudiuEnum.valueOf(obiect.getString("ciclu_studiu"));
                int an = obiect.getInt("an");
                JSONObject obiectPermis = obiect.getJSONObject("permis_biblioteca");
                LocalDate dataEliberarePermis = LocalDate.parse(obiectPermis.getString("data_eliberarii"));
                LocalDate dataExpirarePermis = LocalDate.parse(obiectPermis.getString("data_expirarii"));
                PermisBiblioteca permisBiblioteca = new PermisBiblioteca(dataEliberarePermis, dataExpirarePermis);
                Student student = new Student(nume, facultate, cicluStudiu, an, permisBiblioteca);

                colectieStudenti.add(student.getPermisBiblioteca().getId(), student);
            }
        }

        return colectieStudenti;
    }

    public static void scrieStudentiJson(String numeFisier, ColectieStudenti colectie) {
        if (!colectie.isEmpty()) {
            JSONObject obiect = new JSONObject();
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(numeFisier));
                JSONArray colectieStudenti = new JSONArray();
                for (Student s : colectie.getColectie().values()) {
                    JSONObject student = new JSONObject();
                    student.put("nume", s.getNume());
                    student.put("facultate", s.getFacultate());
                    student.put("ciclu_studiu", s.getCicluStudiu());
                    student.put("an", s.getAnStudiu());
                    JSONObject permis = new JSONObject();
                    permis.put("data_eliberarii", s.getPermisBiblioteca().getDataEliberarii());
                    permis.put("data_expirarii", s.getPermisBiblioteca().getDataExpirarii());
                    student.put("permis_biblioteca", permis);
                    colectieStudenti.put(student);
                }
                obiect.put("studenti", colectieStudenti);
                writer.write(obiect.toString());
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
