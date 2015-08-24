package org.sysreg.sia.facades.impl;

import org.springframework.beans.factory.annotation.Required;
import org.sysreg.sia.dtos.EnclosureDTO;
import org.sysreg.sia.dtos.FieldDTO;
import org.sysreg.sia.dtos.ParcelDTO;
import org.sysreg.sia.facades.FieldFacade;
import org.sysreg.sia.model.Enclosure;
import org.sysreg.sia.model.Field;
import org.sysreg.sia.model.Parcel;
import org.sysreg.sia.services.FieldService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joseant on 24/08/15.
 */
public class DefaultFieldFacade implements FieldFacade {

    private FieldService fieldService;

    @Override
    public List<FieldDTO> getFields(String user) {
        if(user == null || user.length() < 1)
            throw new IllegalArgumentException("User with username " + user + " not found");

        List<Field> fieldList = fieldService.getFieldsForUser(user);
        List<FieldDTO> fieldDTOList = new ArrayList<>();
        for(Field field : fieldList){
            FieldDTO fieldDTO = new FieldDTO();
            fieldDTO.setDescription(field.toString());
            //Parcels
            List<ParcelDTO> parcelDTOList = new ArrayList<>();
            for(Parcel parcel : field.getParcels()){
                ParcelDTO parcelDTO = new ParcelDTO();
                parcelDTO.setDescription(parcel.toString());
                //Enclosures
                List<EnclosureDTO> enclosureDTOList = new ArrayList<>();
                for(Enclosure enclosure : parcel.getEnclosures()){
                    EnclosureDTO enclosureDTO = new EnclosureDTO();
                    enclosureDTO.setId(enclosure.getId());
                    enclosureDTO.setDescription(enclosure.toString());
                    enclosureDTOList.add(enclosureDTO);
                }
                parcelDTO.setEnclosures(enclosureDTOList);
                parcelDTOList.add(parcelDTO);
            }
            fieldDTO.setParcels(parcelDTOList);
            fieldDTOList.add(fieldDTO);
        }
        return fieldDTOList;
    }

    @Override
    public EnclosureDTO getEnclosure(String user, String id) {
        if(user == null || user.length() < 1)
            throw new IllegalArgumentException("User with username " + user + " not found");
        if(id == null || id.length() < 1)
        throw new IllegalArgumentException("Enclosure with id " + id + " not found");

        Enclosure enclosureModel = fieldService.getEnclosureById(user, id);
        EnclosureDTO enclosureData = new EnclosureDTO();
        enclosureData.setId(enclosureModel.getId());

        return enclosureData;
    }

    @Required
    public void setFieldService(FieldService fieldService) {
        this.fieldService = fieldService;
    }
}
