package org.sysreg.sia.model.sensor;

import javax.persistence.*;

/**
 * Created by jose on 08/02/14.
 */
@Entity
@DiscriminatorValue("PRESSURE")
public class Pressure extends Sensor {

    @Column(name = "PRESSURE_TEMPERATURE")
    private double temperature;

    @Column(name = "PRESSURE_TEMPERATURE_UNITS")
    @Enumerated(EnumType.STRING)
    private Units unitsTemperature = Units.CELSIUS;

    private enum Forecast {SUNNY, CLOUDY, RAIN};
    @Column
    @Enumerated(EnumType.STRING)
    private Forecast forecast = Forecast.SUNNY;

    public Pressure() {
        this.setUnits(Units.PASCAL);
    }

    public Pressure(String id, String code){
        this.setUnits(Units.PASCAL);
        this.setId(id);
        this.setCode(code);
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public Forecast getForecast() {
        return forecast;
    }

    public void setForecast(Forecast forecast) {
        this.forecast = forecast;
    }
}
