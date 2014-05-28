package org.sysreg.sia.model;

import org.sysreg.sia.model.actuator.Actuator;
import org.sysreg.sia.model.sensor.Sensor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ENCLOSURES")
@IdClass(EnclosureId.class)
public class Enclosure implements Serializable {
	private static final long serialVersionUID = 1L;

	// @Id
	@ManyToOne
	@JoinColumns({
			@JoinColumn(name = "TOWN_ID", referencedColumnName = "TOWN_ID"),
			@JoinColumn(name = "AGGREGATE", referencedColumnName = "AGGREGATE"),
			@JoinColumn(name = "ZONE", referencedColumnName = "ZONE"),
			@JoinColumn(name = "POLYGON", referencedColumnName = "POLYGON"),
			@JoinColumn(name = "PARCEL", referencedColumnName = "PARCEL"), })
	private Parcel parcel;
	@Id
	private int enclosure;

	@Column
	private float area;
	@Column
	private float slope;
	@Column
	private float irrigationCoef;
	@Embedded
	private Coordinates coordinates;

	@ManyToOne
	@JoinColumn(name = "USE_ID")
	private Use use;

    @OneToMany(mappedBy = "enclosure")
    private Set<Sensor> sensors =  new HashSet<Sensor>();

    @OneToMany(mappedBy = "enclosure")
    private Set<Actuator> actuators = new HashSet<Actuator>();

	public Parcel getParcel() {
		return parcel;
	}

	public void setParcel(Parcel parcel) {
		this.parcel = parcel;
	}

	public int getEnclosure() {
		return enclosure;
	}

	public void setEnclosure(int enclosure) {
		this.enclosure = enclosure;
	}

	public float getArea() {
		return area;
	}

	public void setArea(float area) {
		this.area = area;
	}

	public float getSlope() {
		return slope;
	}

	public void setSlope(float slope) {
		this.slope = slope;
	}

	public float getIrrigationCoef() {
		return irrigationCoef;
	}

	public void setIrrigationCoef(float irrigationCoef) {
		this.irrigationCoef = irrigationCoef;
	}

	public Coordinates getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(Coordinates coordinates) {
		this.coordinates = coordinates;
	}

	public Use getUse() {
		return use;
	}

	public void setUse(Use use) {
		this.use = use;
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

    public String toString() {
		return "Recinto [id=" + getEnclosure() + ", superficie=" + area
				+ ", pendiente=" + slope + ", coefRegadio=" + irrigationCoef
				+ ", coordinates=" + coordinates.toString() + ", use="
				+ use.getDescription() + "]";
	}

}