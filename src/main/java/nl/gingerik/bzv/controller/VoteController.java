package nl.gingerik.bzv.controller;

import java.util.List;

import nl.gingerik.bzv.controller.ExceptionHandlingController.ResourceNotFoundException;
import nl.gingerik.bzv.dao.VoteDao;
import nl.gingerik.bzv.model.Vote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VoteController {
	
    @Autowired
    private VoteDao voteDao;

    @RequestMapping(value="/vote", method=RequestMethod.GET)
    public List<Vote> getList() {
		return voteDao.list();
    }

    @RequestMapping(value="/vote/{voteId}", method=RequestMethod.GET)
    public Vote getById(@PathVariable("voteId") long voteId) {
		Vote vote = voteDao.get(voteId);
		if (vote == null) {
			throw new ResourceNotFoundException();
		}
		return vote;
    }
}