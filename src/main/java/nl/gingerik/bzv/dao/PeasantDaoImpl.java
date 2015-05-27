package nl.gingerik.bzv.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import nl.gingerik.bzv.model.Peasant;

import org.springframework.stereotype.Repository;

@Repository(value="peasantDao")
@Transactional
public class PeasantDaoImpl implements PeasantDao {

    @PersistenceContext(unitName="punit")
    private EntityManager entityManager;

	@Override
	public List<Peasant> list() {
		return new ArrayList<Peasant>();// sessionFactory.getCurrentSession().createCriteria(Peasant.class).list();
	}
	
	@Override
	public Peasant get(long peasantId) {
		return entityManager.find(Peasant.class, peasantId);
	}

	@Override
	public Peasant save(Peasant peasant) {
		entityManager.persist(peasant);
		return peasant;
	}

	@Override
	public void delete(Peasant peasant) {
		entityManager.remove(peasant);
	}

}
