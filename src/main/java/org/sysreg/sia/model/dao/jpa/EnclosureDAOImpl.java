package org.sysreg.sia.model.dao.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.sysreg.sia.model.Enclosure;
import org.sysreg.sia.model.Parcel;
import org.sysreg.sia.model.dao.EnclosureDAO;

import java.util.List;

@Repository
public class EnclosureDAOImpl implements EnclosureDAO {
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	@Transactional
	public void persist(Enclosure enclosure) {
		entityManager.persist(enclosure);
	}

    @Override
    public Enclosure findById(String id) {
        Query q = entityManager.createQuery("from Enclosure " +
                "where parcel.town.id = ?1 and " +
                "parcel.aggregate = ?2 and " +
                "parcel.zone = ?3 and " +
                "parcel.polygon = ?4 and " +
                "parcel.parcel = ?5 and " +
                "enclosure = ?6");
        q.setParameter(1, Integer.parseInt(id.substring(0,5)));
        q.setParameter(2, Integer.parseInt(id.substring(5,8)));
        q.setParameter(3, Integer.parseInt(id.substring(8,11)));
        q.setParameter(4, Integer.parseInt(id.substring(11,14)));
        q.setParameter(5, Integer.parseInt(id.substring(14,17)));
        q.setParameter(6, Integer.parseInt(id.substring(17,20)));
        List<Enclosure> results = q.getResultList();
        if(results.size()>0)
            return results.get(0);
        else
            return null;
    }

}
