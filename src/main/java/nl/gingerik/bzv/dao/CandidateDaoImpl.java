package nl.gingerik.bzv.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import nl.gingerik.bzv.model.Candidate;

import org.springframework.stereotype.Repository;

@Repository(value="candidateDao")
@Transactional
public class CandidateDaoImpl implements CandidateDao {

    @PersistenceContext(unitName="punit")
    private EntityManager entityManager;

	@Override
	public List<Candidate> list() {
		return new ArrayList<Candidate>();//entityManager.find(Candidate.class).getResultList();
	}
	
	@Override
	public Candidate get(long candidateId) {
		return entityManager.find(Candidate.class, candidateId);
	}

	@Override
	public Candidate save(Candidate candidate) {
		entityManager.persist(candidate);
		return candidate;
	}

	@Override
	public void delete(Candidate candidate) {
		entityManager.remove(candidate);
	}

}
