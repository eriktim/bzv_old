package nl.gingerik.bzv.controller;

import java.util.List;

import nl.gingerik.bzv.model.Vote;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ImportResource("classpath:config.xml")
public class VoteController {
	
	@Autowired
	private SqlSession mSqlSession;

    @RequestMapping(value="/vote", method=RequestMethod.GET)
    public List<Vote> getList() {
    	return mSqlSession.selectList("data-mapper.selectAllVotes");
    }

    @RequestMapping(value="/vote/{voteId}", method=RequestMethod.GET)
    public Vote getById(@PathVariable("voteId") long voteId) {
    	return mSqlSession.selectOne("data-mapper.selectVote", voteId);
    }
}