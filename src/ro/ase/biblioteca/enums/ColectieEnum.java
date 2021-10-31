package ro.ase.biblioteca.enums;

import java.util.Locale;

public enum ColectieEnum {
    AFACERI,
    CONTABILITATE,
    DREPT,
    ECONOMIE,
    FINANTE,
    INFORMATICA,
    LEGISLATIE,
    MANAGEMENT,
    MARKETING,
    MATEMATICA,
    MEDICINA,
    RELATII_INTERNATIONALE,
    RESURSE_UMANE,
    STIINTE_POLITICE,
    STIINTE_SOCIALE,
    TURISM;

    public static boolean contains(String value){
        for(ColectieEnum colectie : ColectieEnum.values()){
            if(colectie.toString().equals(value.toUpperCase(Locale.ROOT))){
                return true;
            }
        }
        return false;
    }
}
