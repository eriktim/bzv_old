package nl.gingerik.bzv.controller;

import java.util.List;

import nl.gingerik.bzv.model.VotePeriod;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ImportResource("classpath:config.xml")
public class VotePeriodController {
	
	@Autowired
	private SqlSession mSqlSession;

    @RequestMapping(value="/voteperiod", method=RequestMethod.GET)
    public List<VotePeriod> getList() {
    	return mSqlSession.selectList("data-mapper.selectAllVotePeriods");
    }

    @RequestMapping(value="/voteperiod/{votePeriodId}", method=RequestMethod.GET)
    public VotePeriod getById(@PathVariable("votePeriodId") long votePeriodId) {
    	return mSqlSession.selectOne("data-mapper.selectVotePeriod", votePeriodId);
    }
}