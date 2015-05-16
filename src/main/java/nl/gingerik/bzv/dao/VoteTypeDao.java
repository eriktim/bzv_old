package nl.gingerik.bzv.dao;

import java.util.List;

import nl.gingerik.bzv.model.VoteType;

public interface VoteTypeDao {

	public List<VoteType> list();
	public VoteType get(long voteTypeId);
	public VoteType save(VoteType voteType);
	public void delete(VoteType voteType);

}
