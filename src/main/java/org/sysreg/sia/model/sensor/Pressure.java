package org.sysreg.sia.model.sensor;

import javax.persistence.*;

/**
 * Created by jose on 08/02/14.
 */
@Entity
@DiscriminatorValue("PRESSURE")
@AttributeOverrides({
        @AttributeOverride(name="units", column = @Column(name="PRESSURE_UNITS"))
})
public class Pressure extends Sensor {

    private enum Units {PASCAL};
    @Enumerated(EnumType.STRING)
    private Units units = Units.PASCAL;

    @Column(name = "PRESSURE_TEMPERATURE")
    private double temperature;

    private enum UnitsTemperature {CELSIUS, FAHRENHEIT, KELVIN};
    @Column(name = "PRESSURE_TEMPERATURE_UNITS")
    @Enumerated(EnumType.STRING)
    private UnitsTemperature unitsTemperature = UnitsTemperature.CELSIUS;

    private enum Forecast {SUNNY, CLOUDY, RAIN};
    @Column
    private Forecast forecast;

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
        return forecast;
    }

    public void setForecast(Forecast forecast) {
        this.forecast = forecast;
    }
}
