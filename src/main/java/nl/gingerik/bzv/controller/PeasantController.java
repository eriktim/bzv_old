package nl.gingerik.bzv.controller;

import java.util.List;

import nl.gingerik.bzv.model.Peasant;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ImportResource("classpath:config.xml")
public class PeasantController {
	
	@Autowired
	private SqlSession mSqlSession;

    @RequestMapping(value="/peasant", method=RequestMethod.GET)
    public List<Peasant> getList() {
    	return mSqlSession.selectList("data-mapper.selectAllPeasants");
    }

    @RequestMapping(value="/peasant/{peasantId}", method=RequestMethod.GET)
    public Peasant getById(@PathVariable("peasantId") long peasantId) {
    	return mSqlSession.selectOne("data-mapper.selectPeasant", peasantId);
    }
}