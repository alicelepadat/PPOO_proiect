package ro.ase.biblioteca.entities;

import ro.ase.biblioteca.contracts.IImprumut;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Imprumut extends Entitate implements IImprumut {
    private static final int timpRestituire = 14;

    private int idPermisStudent;
    private int idCarte;
    private LocalDate dataImprumut;
    private LocalDate dataReturnare;

    public Imprumut() {
        super();
    }

    public Imprumut(int idPermisStudent, int idCarte) {
        this.idPermisStudent = idPermisStudent;
        this.idCarte = idCarte;
        this.dataImprumut = LocalDate.now();
        this.dataReturnare = null;
    }

    public int getIdPermisStudent() {
        return idPermisStudent;
    }

    public void setIdPermisStudent(int idPermisStudent) {
        this.idPermisStudent = idPermisStudent;
    }

    public int getIdCarte() {
        return idCarte;
    }

    public void setIdCarte(int idCarte) {
        this.idCarte = idCarte;
    }

    public LocalDate getDataImprumut() {
        return dataImprumut;
    }

    public void setDataImprumut(LocalDate dataImprumut) {
        this.dataImprumut = dataImprumut;
    }

    public LocalDate getDataReturnare() {
        return dataReturnare;
    }

    public void setDataReturnare(LocalDate dataReturnare) {
        this.dataReturnare = dataReturnare;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("IMPRUMUT[");
        sb.append("idStudent=").append(idPermisStudent);
        sb.append(", carte=").append(idCarte);
        sb.append(", dataImprumut=").append(dataImprumut);
        sb.append(", dataReturnare=").append(dataReturnare);
        sb.append(']');
        return sb.toString();
    }

    @Override
    public boolean dataImprumutValida() {
        long days = ChronoUnit.DAYS.between(dataImprumut, LocalDate.now());
        return !(dataReturnare == null && days >=0 && days<= timpRestituire);
    }
}
