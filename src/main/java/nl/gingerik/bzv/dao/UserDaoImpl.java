package nl.gingerik.bzv.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import nl.gingerik.bzv.model.User;

import org.springframework.stereotype.Repository;

@Repository(value="userDao")
@Transactional
public class UserDaoImpl implements UserDao {

    @PersistenceContext(unitName="punit")
    private EntityManager entityManager;

	@Override
	public List<User> list() {
		return new ArrayList<User>();// sessionFactory.getCurrentSession().createCriteria(User.class).list();
	}
	
	@Override
	public User get(long userId) {
		return entityManager.find(User.class, userId);
	}

	@Override
	public User save(User user) {
		entityManager.persist(user);
		return user;
	}

	@Override
	public void delete(User user) {
		entityManager.remove(user);
	}

}
