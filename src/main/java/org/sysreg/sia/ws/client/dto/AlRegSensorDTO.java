package org.sysreg.sia.ws.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Date;

/**
 * Created by joseant on 11/07/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AlRegSensorDTO {

    private String _id;

    private String code;

    private Float value;

    private String description;

    private Date timestamp;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public int hashCode() {
        // Value is not used to compare, must be consistent
        return new HashCodeBuilder(17, 31). // two randomly chosen prime numbers
                append(_id).append(code).append(description).
                toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof AlRegSensorDTO))
            return false;
        if (obj == this)
            return true;

        AlRegSensorDTO rhs = (AlRegSensorDTO) obj;
        return new EqualsBuilder().
                append(_id, rhs._id).
                append(code, rhs.code).
                append(description, rhs.description).
                isEquals();
    }
}
