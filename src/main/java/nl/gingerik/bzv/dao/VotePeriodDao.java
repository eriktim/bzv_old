package nl.gingerik.bzv.dao;

import java.util.List;

import nl.gingerik.bzv.model.VotePeriod;

public interface VotePeriodDao {

	public List<VotePeriod> list();
	public VotePeriod get(long votePeriodId);
	public VotePeriod save(VotePeriod votePeriod);
	public void delete(VotePeriod votePeriod);

}
