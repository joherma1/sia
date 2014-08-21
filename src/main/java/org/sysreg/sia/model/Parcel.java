package org.sysreg.sia.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "PARCELS")
@IdClass(ParcelId.class)
public class Parcel implements Serializable {
	private static final long serialVersionUID = 1L;

	// @Id Commented o avoid town insertion again
	@ManyToOne
	@JoinColumn(name = "TOWN_ID")
	private Town town;
	@Id
	private int aggregate;
	@Id
	private int zone;
	@Id
	private int polygon;
	@Id
	private int parcel;
	@Column
	private float area;
	@ManyToOne
	@JoinColumn(name = "FIELD_ID")
	private Field field;
	@OneToMany(mappedBy = "parcel", fetch = FetchType.EAGER)
	private Set<Enclosure> enclosures = new HashSet<Enclosure>();
	@Embedded
	private Coordinates coordinates;

	public Town getTown() {
		return town;
	}

	public void setTown(Town town) {
		this.town = town;
	}

	public int getAggregate() {
		return aggregate;
	}

	public void setAggregate(int aggregate) {
		this.aggregate = aggregate;
	}

	public int getZone() {
		return zone;
	}

	public void setZone(int zone) {
		this.zone = zone;
	}

	public int getPolygon() {
		return polygon;
	}

	public void setPolygon(int polygon) {
		this.polygon = polygon;
	}

	public int getParcel() {
		return parcel;
	}

	public void setParcel(int parcel) {
		this.parcel = parcel;
	}

	public float getArea() {
		return area;
	}

	public void setArea(float area) {
		this.area = area;
	}

	public Set<Enclosure> getEnclosures() {
		return enclosures;
	}

	public void setEnclosures(Set<Enclosure> enclosure) {
		this.enclosures = enclosure;
	}

	public Coordinates getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(Coordinates coordinates) {
		this.coordinates = coordinates;
	}

	public Field getField() {
		return field;
	}

	public void setField(Field field) {
		this.field = field;
	}
}
