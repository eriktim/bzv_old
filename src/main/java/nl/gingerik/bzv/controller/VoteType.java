package nl.gingerik.bzv.controller;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ImportResource("classpath:config.xml")
public class VoteType {
	
	@Autowired
	private SqlSession mSqlSession;

    @RequestMapping(value="/votetype", method=RequestMethod.GET)
    public List<VoteType> getList() {
    	return mSqlSession.selectList("data-mapper.selectAllVoteTypes");
    }

    @RequestMapping(value="/votetype/{voteTypeId}", method=RequestMethod.GET)
    public VoteType getById(@PathVariable("voteTypeId") long voteTypeId) {
    	return mSqlSession.selectOne("data-mapper.selectVoteType", voteTypeId);
    }
}