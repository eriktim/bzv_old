package nl.gingerik.bzv.controller;

import java.util.List;

import nl.gingerik.bzv.controller.ExceptionHandlingController.ResourceNotFoundException;
import nl.gingerik.bzv.dao.UserDao;
import nl.gingerik.bzv.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	
    @Autowired
    private UserDao userDao;

    @RequestMapping(value="/user", method=RequestMethod.GET)
    public List<User> getList() {
		return userDao.list();
    }

    @RequestMapping(value="/user/{userId}", method=RequestMethod.GET)
    public User getById(@PathVariable("userId") long userId) {
		User user = userDao.get(userId);
		if (user == null) {
			throw new ResourceNotFoundException();
		}
		return user;
    }
}