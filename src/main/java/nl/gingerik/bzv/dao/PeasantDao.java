package nl.gingerik.bzv.dao;

import java.util.List;

import nl.gingerik.bzv.model.Peasant;

public interface PeasantDao {

	public List<Peasant> list();
	public Peasant get(long peasantId);
	public Peasant save(Peasant peasant);
	public void delete(Peasant peasant);

}
