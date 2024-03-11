package com.burcu.utility.enums;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public enum ECity {
    //TODO: SOR!

    ISTANBUL (Arrays.asList("KADIKOY", "ATASEHIR", " LEVENT")),
    ANKARA(Arrays.asList("KECIOREN", "BEYPAZARI", "KIZILCAHAMAM")),
    ANTALYA(Arrays.asList("LARA", "KAS"));

   private  List<String> distirctList;

    ECity(List<String> distinct) {
        this.distirctList = new ArrayList<>(distinct);
    }

    public List<String> getDistrictList() {
        return new ArrayList<>(distirctList);
    }
}
