package org.sysreg.sia.model;

import org.sysreg.sia.model.actuator.Actuator;
import org.sysreg.sia.model.sensor.Sensor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by joseant on 11/07/15.
 */
@Entity
@Table(name = "BOARDS")
public class Board implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private int id;

    @Column
    private String port;

    @Column
    private String description;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "TOWN_ID", referencedColumnName = "TOWN_ID"),
            @JoinColumn(name = "AGGREGATE", referencedColumnName = "AGGREGATE"),
            @JoinColumn(name = "ZONE", referencedColumnName = "ZONE"),
            @JoinColumn(name = "POLYGON", referencedColumnName = "POLYGON"),
            @JoinColumn(name = "PARCEL", referencedColumnName = "PARCEL"),
            @JoinColumn(name = "ENCLOSURE", referencedColumnName = "ENCLOSURE"),
    })
    private Enclosure enclosure;

    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER)
    private Set<Sensor> sensors = new HashSet<>();

    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER)
    private Set<Actuator> actuators = new HashSet<>();

    public Board() {
    }

    public Board(int id, String port, String description) {
        this.id = id;
        this.port = port;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Enclosure getEnclosure() {
        return enclosure;
    }

    public void setEnclosure(Enclosure enclosure) {
        this.enclosure = enclosure;
    }

    public Set<Sensor> getSensors() {
        return sensors;
    }

    public void setSensors(Set<Sensor> sensors) {
        this.sensors = sensors;
    }

    public Set<Actuator> getActuators() {
        return actuators;
    }

    public void setActuators(Set<Actuator> actuators) {
        this.actuators = actuators;
    }
}
