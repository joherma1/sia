package org.sysreg.sia.model.sensor;

/**
 * Created by jose on 08/02/14.
 */
public class Pressure extends Sensor {
    private enum Units {PASCAL};
    private Units units = Units.PASCAL;
    private double temperature;
    private enum UnitsTemperature {CELSIUS, FAHRENHEIT, KELVIN};
    private UnitsTemperature unitsTemperature = UnitsTemperature.CELSIUS;
    private enum Forecast {SUNNY, CLOUDY, RAIN};

    public Pressure() {

    }

    public Pressure(String id, String code){
        this.setId(id);
        this.setCode(code);
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public UnitsTemperature getUnitsTemperature() {
        return unitsTemperature;
    }

    public void setUnitsTemperature(UnitsTemperature unitsTemperature) {
        this.unitsTemperature = unitsTemperature;
    }

    public Forecast getForecast() {
        return Forecast.SUNNY;
    }
}
