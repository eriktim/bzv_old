package nl.gingerik.bzv.dao;

import java.util.List;

import javax.transaction.Transactional;

import nl.gingerik.bzv.model.Vote;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository(value="voteDao")
@Transactional
public class VoteDaoImpl implements VoteDao {
	
	@Autowired
    private SessionFactory sessionFactory;

	@Override
	@SuppressWarnings("unchecked")
	public List<Vote> list() {
		return (List<Vote>) sessionFactory.getCurrentSession().createCriteria(Vote.class).list();
	}
	
	@Override
	public Vote get(long voteId) {
		return (Vote) sessionFactory.getCurrentSession().get(Vote.class, voteId);
	}

	@Override
	public Vote save(Vote vote) {
		sessionFactory.getCurrentSession().saveOrUpdate(vote);
		return vote;
	}

	@Override
	public void delete(Vote vote) {
		sessionFactory.getCurrentSession().delete(vote);
	}

}
