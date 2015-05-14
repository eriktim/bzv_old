package nl.gingerik.bzv.dao;

import java.util.List;

import javax.transaction.Transactional;

import nl.gingerik.bzv.model.Candidate;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository(value="candidateDao")
@Transactional
public class CandidateDaoImpl implements CandidateDao {
	
	@Autowired
    private SessionFactory sessionFactory;

	@Override
	@SuppressWarnings("unchecked")
	public List<Candidate> list() {
		return (List<Candidate>) sessionFactory.getCurrentSession().createCriteria(Candidate.class).list();
	}
	
	@Override
	public Candidate get(long candidateId) {
		return (Candidate) sessionFactory.getCurrentSession().get(Candidate.class, candidateId);
	}

	@Override
	public Candidate save(Candidate candidate) {
		sessionFactory.getCurrentSession().saveOrUpdate(candidate);
		return candidate;
	}

	@Override
	public void delete(Candidate candidate) {
		sessionFactory.getCurrentSession().delete(candidate);
	}

}
