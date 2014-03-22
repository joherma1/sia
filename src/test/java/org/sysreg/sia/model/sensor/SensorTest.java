package org.sysreg.sia.model.sensor;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.sysreg.sia.model.dao.SensorDAO;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by jose on 08/02/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/applicationContext.xml")
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class SensorTest {
    @Autowired
    private SensorDAO sensorDao;

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

    @Test
    public void testInsertSelect(){
        ArrayList<Sensor> sensors = new ArrayList<Sensor>();
        Sensor sensor;

        //Temperature
        sensor = new DS18B20("28f5e9af020000d2");
        sensor.setDescription("Digital temperatures sensor DS18B20");
        sensor.setValue(35.6D);
        sensors.add(sensor);

        //Pressure
        sensor = new BMP085("p1r1");
        sensor.setDescription("Pressure sensor BMP085");
        sensors.add(sensor);

        //Humidity
        sensor = new HH10D("p1r2");
        sensor.setDescription("Humidity sensor BMP085");
        sensors.add(sensor);

        sensor = new SoilMoisture("p1r3");
        sensor.setDescription("Soil Humidity sensor");
        sensors.add(sensor);

        //Generic Temperature
        sensor = new Temperature("p2r1", "Generic");
        sensor.setDescription("Generic Temperature sensor");
        sensors.add(sensor);

        //Generic Pressure
        sensor = new Pressure("p2r2", "Generic");
        sensor.setDescription("Generic Pressure sensor");
        sensors.add(sensor);

        //Generic Humidity
        sensor = new Humidity("p2r3", "Generic");
        sensor.setDescription("Generic Humidity sensor");
        sensors.add(sensor);

        //Generic Sensor
        sensor = new Sensor();
        sensor.setId("p2r4");
        sensor.setCode("Generic");
        sensor.setDescription("Generic sensor");
        sensors.add(sensor);

        for(Sensor i: sensors)
            sensorDao.persist(i);

        //SELECT
        sensor = sensorDao.findById("28f5e9af020000d2");
        assertEquals(sensor, sensors.get(0));
        sensor = sensorDao.findById("p1r1");
        assertEquals(sensor, sensors.get(1));
        sensor = sensorDao.findById("p1r2");
        assertEquals(sensor, sensors.get(2));
        sensor = sensorDao.findById("p1r3");
        assertEquals(sensor, sensors.get(3));
        sensor = sensorDao.findById("p2r1");
        assertEquals(sensor, sensors.get(4));
        sensor = sensorDao.findById("p2r2");
        assertEquals(sensor, sensors.get(5));
        sensor = sensorDao.findById("p2r3");
        assertEquals(sensor, sensors.get(6));
        sensor = sensorDao.findById("p2r4");
        assertEquals(sensor, sensors.get(7));

    }
}
