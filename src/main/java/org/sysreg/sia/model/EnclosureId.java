package org.sysreg.sia.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Embeddable
public class EnclosureId implements Serializable {
	private static final long serialVersionUID = 1L;

	private Parcel parcel;
	private int enclosure;

	public EnclosureId() {
	}

	public EnclosureId(Parcel parcel, int enclosure) {
		super();
		this.parcel = parcel;
		this.enclosure = enclosure;
	}

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

	public int hashCode() {
		return new HashCodeBuilder(17, 31)
				. // two randomly chosen prime numbers
					// if deriving: appendSuper(super.hashCode()).
				append(parcel.getTown().getId())
				.append(parcel.getAggregate()).append(parcel.getZone())
				.append(parcel.getPolygon()).append(parcel.getParcel())
				.append(enclosure).toHashCode();
	}

	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj == this)
			return true;
		if (!(obj instanceof ParcelId))
			return false;

		EnclosureId rhs = (EnclosureId) obj;
		return new EqualsBuilder()
				.
				// if deriving: appendSuper(super.equals(obj)).
				append(parcel.getTown().getId(),
						rhs.getParcel().getTown().getId())
				.append(parcel.getAggregate(), rhs.getParcel().getAggregate())
				.append(parcel.getZone(), rhs.getParcel().getZone())
				.append(parcel.getPolygon(), rhs.getParcel().getPolygon())
				.append(parcel.getParcel(), rhs.getParcel().getParcel())
				.append(enclosure, rhs.getEnclosure()).isEquals();
	}

}