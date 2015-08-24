package org.sysreg.sia.dtos;

import java.util.List;

/**
 * Created by joseant on 24/08/15.
 */
public class FieldDTO {

    private String description;

    private List<ParcelDTO> parcels;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ParcelDTO> getParcels() {
        return parcels;
    }

    public void setParcels(List<ParcelDTO> parcels) {
        this.parcels = parcels;
    }
}
