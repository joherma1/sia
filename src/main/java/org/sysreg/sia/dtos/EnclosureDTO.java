package org.sysreg.sia.dtos;

import java.util.List;

/**
 * Created by joseant on 24/08/15.
 */
public class EnclosureDTO {

    private String id;

    private String description;

    private String town;

    private String region;

    private String province;

    private int aggregate;

    private int zone;

    private int polygon;

    private int parcel;

    private int enclosure;

    private float area;

    private float slope;

    private float irrigationCoef;

    private String coordinates;

    private String use;

    private List<ServerInfoDTO> servers;

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

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
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

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public String getUse() {
        return use;
    }

    public void setUse(String use) {
        this.use = use;
    }

    public List<ServerInfoDTO> getServers() {
        return servers;
    }

    public void setServers(List<ServerInfoDTO> servers) {
        this.servers = servers;
    }
}
