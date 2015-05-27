package nl.gingerik.bzv.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import nl.gingerik.bzv.model.VotePeriod;

import org.springframework.stereotype.Repository;

@Repository(value="votePeriodDao")
@Transactional
public class VotePeriodDaoImpl implements VotePeriodDao {

    @PersistenceContext(unitName="punit")
    private EntityManager entityManager;

	@Override
	public List<VotePeriod> list() {
		return new ArrayList<VotePeriod>();// sessionFactory.getCurrentSession().createCriteria(VotePeriod.class).list();
	}
	
	@Override
	public VotePeriod get(long votePeriodId) {
		return entityManager.find(VotePeriod.class, votePeriodId);
	}

	@Override
	public VotePeriod save(VotePeriod votePeriod) {
		entityManager.persist(votePeriod);
		return votePeriod;
	}

	@Override
	public void delete(VotePeriod votePeriod) {
		entityManager.remove(votePeriod);
	}

}
