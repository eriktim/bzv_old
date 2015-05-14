package nl.gingerik.bzv.dao;

import java.util.List;

import nl.gingerik.bzv.model.Candidate;

public interface CandidateDao {

	public List<Candidate> list();
	public Candidate get(long candidateId);
	public Candidate save(Candidate candidate);
	public void delete(Candidate candidate);

}
