package org.sysreg.sia.model;


import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by joseant on 07/09/15.
 */
@Entity
@Table(name = "VARIETIES")
public class Variety {
    private static final long serialVersionUID = 1L;

    @Id
    @TableGenerator(name = "sequenceStore", table = "SEQUENCE_STORE", pkColumnName = "SEQUENCE_NAME", pkColumnValue = "VARIETIES_PK", valueColumnName = "SEQUENCE_VALUE", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "sequenceStore")
    private int id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private Date startSeason;

    @Column
    private Date endSeason;

    @OneToMany(mappedBy = "variety", fetch = FetchType.EAGER)
    private Set<Enclosure> enclosure = new HashSet<>();

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartSeason() {
        return startSeason;
    }

    public void setStartSeason(Date startSeason) {
        this.startSeason = startSeason;
    }

    public Date getEndSeason() {
        return endSeason;
    }

    public void setEndSeason(Date endSeason) {
        this.endSeason = endSeason;
    }

    public Set<Enclosure> getEnclosure() {
        return enclosure;
    }

    public void setEnclosure(Set<Enclosure> enclosure) {
        this.enclosure = enclosure;
    }
}
