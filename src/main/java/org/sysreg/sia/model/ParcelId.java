package org.sysreg.sia.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Embeddable
public class ParcelId implements Serializable {
	private static final long serialVersionUID = 1L;

	private Town town;
	private int aggregate;
	private int zone;
	private int polygon;
	private int parcel;

	public ParcelId() {
	}

	public ParcelId(Town town, int aggregate, int zone, int polygon,
                    int parcel) {
		super();
		this.town = town;
		this.aggregate = aggregate;
		this.zone = zone;
		this.polygon = polygon;
		this.parcel = parcel;
	}

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

	public int hashCode() {
		return new HashCodeBuilder(17, 31)
				. // two randomly chosen prime numbers
					// if deriving: appendSuper(super.hashCode()).
				append(town.getId()).append(aggregate).append(zone)
				.append(polygon).append(parcel).toHashCode();
	}

	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj == this)
			return true;
		if (!(obj instanceof ParcelId))
			return false;

		ParcelId rhs = (ParcelId) obj;
		return new EqualsBuilder()
				.
				// if deriving: appendSuper(super.equals(obj)).
				append(town.getId(), rhs.getTown().getId())
				.append(aggregate, rhs.getAggregate())
				.append(zone, rhs.getZone())
				.append(polygon, rhs.getPolygon())
				.append(parcel, rhs.getParcel()).isEquals();
	}
}