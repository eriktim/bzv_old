package nl.gingerik.bzv.controller;

import nl.gingerik.bzv.controller.ExceptionHandlingController.ResourceNotFoundException;
import nl.gingerik.bzv.model.User;
import nl.gingerik.bzv.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value="/user", method=RequestMethod.GET)
    public Iterable<User> getList() {
		return userRepository.findAll();
    }

    @RequestMapping(value="/user/{userId}", method=RequestMethod.GET)
    public User getById(@PathVariable("userId") long userId) {
		User user = userRepository.findOne(userId);
		if (user == null) {
			throw new ResourceNotFoundException();
		}
		return user;
    }
}