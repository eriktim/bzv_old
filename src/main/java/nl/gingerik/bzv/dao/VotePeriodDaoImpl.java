package nl.gingerik.bzv.dao;

import java.util.List;

import javax.transaction.Transactional;

import nl.gingerik.bzv.model.VotePeriod;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository(value="votePeriodDao")
@Transactional
public class VotePeriodDaoImpl implements VotePeriodDao {
	
	@Autowired
    private SessionFactory sessionFactory;

	@Override
	@SuppressWarnings("unchecked")
	public List<VotePeriod> list() {
		return (List<VotePeriod>) sessionFactory.getCurrentSession().createCriteria(VotePeriod.class).list();
	}
	
	@Override
	public VotePeriod get(long votePeriodId) {
		return (VotePeriod) sessionFactory.getCurrentSession().get(VotePeriod.class, votePeriodId);
	}

	@Override
	public VotePeriod save(VotePeriod votePeriod) {
		sessionFactory.getCurrentSession().saveOrUpdate(votePeriod);
		return votePeriod;
	}

	@Override
	public void delete(VotePeriod votePeriod) {
		sessionFactory.getCurrentSession().delete(votePeriod);
	}

}
