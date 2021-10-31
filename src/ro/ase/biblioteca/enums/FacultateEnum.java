package ro.ase.biblioteca.enums;

import java.util.Locale;

public enum FacultateEnum {
    FABIZ,
    FAMP,
    BT,
    CSIE,
    CIG,
    DREPT,
    ETA,
    EAM,
    FABBV,
    MAN,
    MK,
    REI;

    public static boolean contains(String value){
        for(FacultateEnum facultate : FacultateEnum.values()){
            if(facultate.toString().equals(value.toUpperCase(Locale.ROOT))){
                return true;
            }
        }
        return false;
    }
}
