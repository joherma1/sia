package org.sysreg.sia.webservices;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by joseant on 11/07/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BoardDTO {

    private String _id;

    private String id;

    private String description;

    //private SensorDTO sensors;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

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
