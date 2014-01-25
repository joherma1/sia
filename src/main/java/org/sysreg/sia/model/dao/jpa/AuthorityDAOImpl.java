package org.sysreg.sia.model.dao.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.sysreg.sia.model.Authority;
import org.sysreg.sia.model.User;
import org.sysreg.sia.model.dao.AuthorityDAO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class AuthorityDAOImpl implements AuthorityDAO {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional
	public void persist(Authority authority) {
		entityManager.persist(authority);
	}

	@Override
	@Transactional
	public Authority findById(int id) {
		return entityManager.find(Authority.class, id);
	}

	@Override
	@Transactional
	public List<Authority> findAll() {
		return entityManager.createQuery("from Authority",Authority.class).getResultList();
	}

	@Override
	@Transactional
	public Authority findByUser(User user) {
		Query q = entityManager.createQuery("select auth from Authority auth join auth.users user where user = ?1");
		q.setParameter(1, user);
		List<Authority> results = q.getResultList();
		if(results.size()>0)
			return results.get(0);
		else
			return null;
	}

    @Override
    @Transactional
    public Authority findByName(String name) {
        Query q = entityManager.createQuery("from Authority where name  = ?1", Authority.class);
        q.setParameter(1, name);
        List<Authority> results = q.getResultList();
        if(results.size()>0)
            return results.get(0);
        else
            return null;
    }

}
