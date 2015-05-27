package nl.gingerik.bzv.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import nl.gingerik.bzv.model.VoteType;

import org.springframework.stereotype.Repository;

@Repository(value="voteTypeDao")
@Transactional
public class VoteTypeDaoImpl implements VoteTypeDao {

    @PersistenceContext(unitName="punit")
    private EntityManager entityManager;

	@Override
	public List<VoteType> list() {
		return new ArrayList<VoteType>();// sessionFactory.getCurrentSession().createCriteria(VoteType.class).list();
	}
	
	@Override
	public VoteType get(long voteTypeId) {
		return entityManager.find(VoteType.class, voteTypeId);
	}

	@Override
	public VoteType save(VoteType voteType) {
		entityManager.persist(voteType);
		return voteType;
	}

	@Override
	public void delete(VoteType voteType) {
		entityManager.remove(voteType);
	}

}
