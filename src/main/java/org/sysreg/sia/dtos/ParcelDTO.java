package org.sysreg.sia.dtos;

import java.util.List;

/**
 * Created by joseant on 24/08/15.
 */
public class ParcelDTO {

    private String description;

    private List<EnclosureDTO> enclosures;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<EnclosureDTO> getEnclosures() {
        return enclosures;
    }

    public void setEnclosures(List<EnclosureDTO> enclosures) {
        this.enclosures = enclosures;
    }
}
