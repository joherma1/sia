package org.sysreg.sia.dtos;

import java.util.List;

/**
 * Created by joseant on 24/08/15.
 */
public class BoardDTO {
    private String id;

    private String description;

    private List<SensorDTO> sensors;

    private List<ActuatorDTO> actuators;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<SensorDTO> getSensors() {
        return sensors;
    }

    public void setSensors(List<SensorDTO> sensors) {
        this.sensors = sensors;
    }

    public List<ActuatorDTO> getActuators() {
        return actuators;
    }

    public void setActuators(List<ActuatorDTO> actuators) {
        this.actuators = actuators;
    }
}
