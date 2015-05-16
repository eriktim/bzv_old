package nl.gingerik.bzv.dao;

import java.util.List;

import nl.gingerik.bzv.model.User;

public interface UserDao {

	public List<User> list();
	public User get(long userId);
	public User save(User user);
	public void delete(User user);

}
