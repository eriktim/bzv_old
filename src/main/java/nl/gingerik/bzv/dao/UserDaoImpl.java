package nl.gingerik.bzv.dao;

import java.util.List;

import javax.transaction.Transactional;

import nl.gingerik.bzv.model.User;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository(value="userDao")
@Transactional
public class UserDaoImpl implements UserDao {
	
	@Autowired
    private SessionFactory sessionFactory;

	@Override
	@SuppressWarnings("unchecked")
	public List<User> list() {
		return (List<User>) sessionFactory.getCurrentSession().createCriteria(User.class).list();
	}
	
	@Override
	public User get(long userId) {
		return (User) sessionFactory.getCurrentSession().get(User.class, userId);
	}

	@Override
	public User save(User user) {
		sessionFactory.getCurrentSession().saveOrUpdate(user);
		return user;
	}

	@Override
	public void delete(User user) {
		sessionFactory.getCurrentSession().delete(user);
	}

}
