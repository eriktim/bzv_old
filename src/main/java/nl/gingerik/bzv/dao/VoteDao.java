package nl.gingerik.bzv.dao;

import java.util.List;

import nl.gingerik.bzv.model.Vote;

public interface VoteDao {

	public List<Vote> list();
	public Vote get(long voteId);
	public Vote save(Vote vote);
	public void delete(Vote vote);

}
