package org.sysreg.sia.model.sensor;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jose on 08/02/14.
 */
public class SensorsTest {

    @Test
    public void testCreation(){
        ArrayList<Sensor> sensors = new ArrayList<Sensor>();

        //Temperature
        sensors.add(new DS18B20("28f5e9af020000d2"));
        sensors.add(new DS18B20("282ddbaf020000b0"));
        sensors.add(new Temperature());

        //Pressure
        sensors.add(new BMP085("p1r1"));

        //Humidity
        sensors.add(new HH10D("p1r1"));
        sensors.add(new SoilMoisture("p1r1"));

        for(Sensor i: sensors)
            System.out.println(i.toString());
    }
}
