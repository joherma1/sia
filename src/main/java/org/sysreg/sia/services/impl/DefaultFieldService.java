package org.sysreg.sia.services.impl;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.sysreg.sia.daos.EnclosureDAO;
import org.sysreg.sia.daos.FieldDAO;
import org.sysreg.sia.daos.UserDAO;
import org.sysreg.sia.model.Enclosure;
import org.sysreg.sia.model.Field;
import org.sysreg.sia.model.User;
import org.sysreg.sia.services.FieldService;

import java.util.List;

/**
 * Created by joseant on 24/08/15.
 */
public class DefaultFieldService implements FieldService {

    private FieldDAO fieldDAO;

    private EnclosureDAO enclosureDAO;

    private UserDAO userDAO;

    @Override
    public List<Field> getFieldsForUser(String user) throws EmptyResultDataAccessException{
        //TODO
        //findByUsername shall not manage exceptions, shall return a list and we check the integrity
        final User userField = userDAO.findByUsername(user);
        if(userField == null){
            throw new EmptyResultDataAccessException("User with username " + user + " not found",1);
        }
        else{
            final List<Field> fields  = fieldDAO.findByUser(userField);
            if(fields.isEmpty())
                throw new EmptyResultDataAccessException("Fields not found for user" + user,1);
            else{
                return fields;
            }
        }
    }

    @Override
    public Enclosure getEnclosureById(String user, String id) throws DuplicateKeyException, EmptyResultDataAccessException, DataRetrievalFailureException{
        final User userField = userDAO.findByUsername(user);
        if(userField == null){
            throw new EmptyResultDataAccessException("User with username " + user + " not found",1);
        }
        else{
            //TODO
            //findById shall return a list and we manage the integrity
            final Enclosure enclosure = enclosureDAO.findById(id);
            if(enclosure == null){
                throw new EmptyResultDataAccessException("Enclosure with id " + id + " not found",1);
            }
            else{
                if(!userField.getUsername().equals(enclosure.getParcel().getField().getUser().getUsername()))
                    throw new DataRetrievalFailureException("Enclosure found does not belong to the user provided");
                else
                    return enclosure;
            }
        }
    }

    @Required
    public void setFieldDAO(FieldDAO fieldDAO) {
        this.fieldDAO = fieldDAO;
    }

    @Required
    public void setEnclosureDAO(EnclosureDAO enclosureDAO) {
        this.enclosureDAO = enclosureDAO;
    }

    @Required
    public void setUserDAO(UserDAO userDAO){
        this.userDAO = userDAO;
    }
}
