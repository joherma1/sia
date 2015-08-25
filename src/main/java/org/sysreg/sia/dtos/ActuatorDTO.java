package org.sysreg.sia.dtos;

/**
 * Created by joseant on 24/08/15.
 */
public class ActuatorDTO {
    private String id;

    private String value;

    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
