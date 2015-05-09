package nl.gingerik.bzv.controller;

import java.util.List;

import nl.gingerik.bzv.model.User;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ImportResource("classpath:config.xml")
public class UserController {
	
	@Autowired
	private SqlSession mSqlSession;

    @RequestMapping(value="/user", method=RequestMethod.GET)
    public List<User> getList() {
    	return mSqlSession.selectList("data-mapper.selectAllUsers");
    }

    @RequestMapping(value="/user/{userId}", method=RequestMethod.GET)
    public User getById(@PathVariable("userId") long userId) {
    	return mSqlSession.selectOne("data-mapper.selectUser", userId);
    }
}