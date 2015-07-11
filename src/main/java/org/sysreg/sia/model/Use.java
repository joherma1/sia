package org.sysreg.sia.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

//import org.sysreg.sia.shared.UsoDTO;

@Entity
@Table(name = "USES")
public class Use implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @Column(length = 2)
	private String id; //2 Char code

	@Column(name = "DESCRIPTION", length = 255)
	private String description;

	@OneToMany(mappedBy = "use")
	private Set<Enclosure> enclosures;

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

}