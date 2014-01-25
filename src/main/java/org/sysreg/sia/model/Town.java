package org.sysreg.sia.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TOWNS")
public class Town implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;
	@Column
	private String name;
	@ManyToOne
	@JoinColumn(name = "REGION_ID")
	private Region region;
	@OneToMany(mappedBy = "town")
	private Set<Parcel> parcels;
	@OneToMany(mappedBy = "town")
	private Set<User> users;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public Province getProvince() {
		return region.getProvince();
	}

	public void setProvince(Province province) {
		this.region.setProvince(province);
	}

	@Override
	public String toString() {
		return "Municipio [codigo=" + id + ", nombre=" + name
				+ ", region=" + region.getName() + ", province="
				+ getProvince().getName() + "]";
	}
}