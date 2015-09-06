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
    private String protocol;

    @Column
    private String uri;

    @Column
    private String description;

    @ManyToOne
    @JoinColumn(name = "SERVER_ID")
    private Server server;

    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER)
    private Set<Sensor> sensors = new HashSet<>();

    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER)
    private Set<Actuator> actuators = new HashSet<>();

    public Board() {
    }

    public Board(int id, String protocol, String uri, String description) {
        this.id = id;
        this.protocol = protocol;
        this.uri = uri;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String port) {
        this.protocol = port;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
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
