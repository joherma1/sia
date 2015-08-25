package org.sysreg.sia.ws.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Created by joseant on 11/07/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)//ignore properties not described
public class AlRegBoardDTO {

    private String _id;

    private String id;

    private String description;

    private AlRegSensorDTO[] sensors;

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

    public AlRegSensorDTO[] getSensors() {
        return sensors;
    }

    public void setSensors(AlRegSensorDTO[] sensors) {
        this.sensors = sensors;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 31). // two randomly chosen prime numbers
                append(_id).append(id).append(description).
                toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof AlRegBoardDTO))
            return false;
        if (obj == this)
            return true;

        AlRegBoardDTO rhs = (AlRegBoardDTO) obj;
        return new EqualsBuilder().
                append(_id, rhs._id).
                append(id, rhs.id).
                append(description, rhs.description).
                isEquals();
    }
}
