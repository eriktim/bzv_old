package nl.gingerik.bzv.dao;

import java.util.List;

import javax.transaction.Transactional;

import nl.gingerik.bzv.model.Peasant;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository(value="peasantDao")
@Transactional
public class PeasantDaoImpl implements PeasantDao {
	
	@Autowired
    private SessionFactory sessionFactory;

	@Override
	@SuppressWarnings("unchecked")
	public List<Peasant> list() {
		return (List<Peasant>) sessionFactory.getCurrentSession().createCriteria(Peasant.class).list();
	}
	
	@Override
	public Peasant get(long peasantId) {
		return (Peasant) sessionFactory.getCurrentSession().get(Peasant.class, peasantId);
	}

	@Override
	public Peasant save(Peasant peasant) {
		sessionFactory.getCurrentSession().saveOrUpdate(peasant);
		return peasant;
	}

	@Override
	public void delete(Peasant peasant) {
		sessionFactory.getCurrentSession().delete(peasant);
	}

}
