package ro.ase.biblioteca.enums;

import java.util.Locale;

public enum CicluStudiuEnum {
    LICENTA,
    MASTERAT,
    DOCTORAT;

    public static boolean contains(String value){
        for(CicluStudiuEnum cicluStudiu : CicluStudiuEnum.values()){
            if(cicluStudiu.toString().toUpperCase(Locale.ROOT).equals(value.toUpperCase(Locale.ROOT))){
                return true;
            }
        }
        return false;
    }
}
