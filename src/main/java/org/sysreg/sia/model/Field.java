package org.sysreg.sia.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "FIELDS")
public class Field implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @TableGenerator(name = "sequenceStore", table = "SEQUENCE_STORE", pkColumnName = "SEQUENCE_NAME", pkColumnValue = "FIELDS_PK", valueColumnName = "SEQUENCE_VALUE", initialValue = 1, allocationSize = 1 )
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "sequenceStore")
	private int id;

	@Column
	private String name;

	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private User user;

	@OneToMany(mappedBy = "field")
	private Set<Parcel> parcels = new HashSet<Parcel>();

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

	public Set<Parcel> getParcels() {
		return parcels;
	}

	public void setParcels(Set<Parcel> parcels) {
		this.parcels = parcels;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}