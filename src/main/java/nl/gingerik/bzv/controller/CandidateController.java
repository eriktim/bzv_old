package nl.gingerik.bzv.controller;

import java.util.List;

import nl.gingerik.bzv.model.Candidate;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ImportResource("classpath:config.xml")
public class CandidateController {
	
	@Autowired
	private SqlSession mSqlSession;

    @RequestMapping(value="/candidate", method=RequestMethod.GET)
    public List<Candidate> getList() {
    	return mSqlSession.selectList("data-mapper.selectAllCandidates");
    }

    @RequestMapping(value="/candidate/{candidateId}", method=RequestMethod.GET)
    public Candidate getById(@PathVariable("candidateId") long candidateId) {
    	return mSqlSession.selectOne("data-mapper.selectCandidate", candidateId);
    }
}