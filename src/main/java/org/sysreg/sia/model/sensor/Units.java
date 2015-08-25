package org.sysreg.sia.model.sensor;

/**
 * Created by joseant on 24/08/15.
 */
public enum Units {
    CELSIUS("Celsius"), FAHRENHEIT("Fahrenheit"), KELVIN("Kelvin"), PASCAL("Pascal"), PERCENT("Percent");

    private String name;

    private Units(String name){
        this.name = name;
    }

    @Override
    public String toString(){
        return name;
    }
}
