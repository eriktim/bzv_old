package nl.gingerik.bzv.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import nl.gingerik.bzv.model.Vote;

import org.springframework.stereotype.Repository;

@Repository(value="voteDao")
@Transactional
public class VoteDaoImpl implements VoteDao {

    @PersistenceContext(unitName="punit")
    private EntityManager entityManager;

	@Override
	public List<Vote> list() {
		return new ArrayList<Vote>();// sessionFactory.getCurrentSession().createCriteria(Vote.class).list();
	}
	
	@Override
	public Vote get(long voteId) {
		return entityManager.find(Vote.class, voteId);
	}

	@Override
	public Vote save(Vote vote) {
		entityManager.persist(vote);
		return vote;
	}

	@Override
	public void delete(Vote vote) {
		entityManager.remove(vote);
	}

}
