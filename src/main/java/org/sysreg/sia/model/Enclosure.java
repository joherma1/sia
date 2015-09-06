package org.sysreg.sia.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ENCLOSURES")
@IdClass(EnclosureId.class)
public class Enclosure implements Serializable {
    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "TOWN_ID", referencedColumnName = "TOWN_ID"),
            @JoinColumn(name = "AGGREGATE", referencedColumnName = "AGGREGATE"),
            @JoinColumn(name = "ZONE", referencedColumnName = "ZONE"),
            @JoinColumn(name = "POLYGON", referencedColumnName = "POLYGON"),
            @JoinColumn(name = "PARCEL", referencedColumnName = "PARCEL"),})
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

    @OneToMany(mappedBy = "enclosure", fetch = FetchType.EAGER)
    @OrderBy("id ASC")
    private Set<Server> servers = new HashSet<>();

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

    public Set<Server> getServers() {
        return servers;
    }

    public void setServers(Set<Server> servers) {
        this.servers = servers;
    }

    public String toString() {
        return "Recinto [id=" + getEnclosure() + ", superficie=" + area
                + ", pendiente=" + slope + ", coefRegadio=" + irrigationCoef
                + ", coordinates=" + (coordinates != null ? coordinates.toString() : "-") + ", use="
                + (use != null ? use.getDescription() : "-") + "]";
    }

    public String getId() {
        String id = String.format("%05d", getParcel().getTown().getId());
        id += String.format("%03d", getParcel().getAggregate()) + String.format("%03d", getParcel().getZone()) + String.format("%03d", getParcel().getPolygon()) + String.format("%03d", getParcel().getParcel());
        id += String.format("%03d", getEnclosure());
        return id;
    }

}