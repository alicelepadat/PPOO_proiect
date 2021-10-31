package ro.ase.biblioteca.io;

import java.io.*;

public class ReadIO {
    public static String citireTextFisier(String numeFisier) {
        InputStream inputStream = null;
        String textPreluat = null;
        try {
            inputStream = new FileInputStream(numeFisier);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(inputStreamReader);

            String linie = "";
            StringBuilder stringBuilder = new StringBuilder();

            while ((linie = reader.readLine()) != null) {
                stringBuilder.append(linie);
            }

            textPreluat = stringBuilder.toString();

            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return textPreluat;
    }
}
