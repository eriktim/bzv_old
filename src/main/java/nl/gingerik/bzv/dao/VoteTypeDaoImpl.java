package nl.gingerik.bzv.dao;

import java.util.List;

import javax.transaction.Transactional;

import nl.gingerik.bzv.model.VoteType;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository(value="voteTypeDao")
@Transactional
public class VoteTypeDaoImpl implements VoteTypeDao {
	
	@Autowired
    private SessionFactory sessionFactory;

	@Override
	@SuppressWarnings("unchecked")
	public List<VoteType> list() {
		return (List<VoteType>) sessionFactory.getCurrentSession().createCriteria(VoteType.class).list();
	}
	
	@Override
	public VoteType get(long voteTypeId) {
		return (VoteType) sessionFactory.getCurrentSession().get(VoteType.class, voteTypeId);
	}

	@Override
	public VoteType save(VoteType voteType) {
		sessionFactory.getCurrentSession().saveOrUpdate(voteType);
		return voteType;
	}

	@Override
	public void delete(VoteType voteType) {
		sessionFactory.getCurrentSession().delete(voteType);
	}

}
