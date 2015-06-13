package nl.gingerik.bzv.controller;

import nl.gingerik.bzv.controller.ExceptionHandlingController.ResourceNotFoundException;
import nl.gingerik.bzv.model.Vote;
import nl.gingerik.bzv.repository.VoteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VoteController {
	
    @Autowired
    private VoteRepository voteRepository;

    @RequestMapping(value="/vote", method=RequestMethod.GET)
    public Iterable<Vote> getList() {
		return voteRepository.findAll();
    }

    @RequestMapping(value="/vote/{voteId}", method=RequestMethod.GET)
    public Vote getById(@PathVariable("voteId") long voteId) {
		Vote vote = voteRepository.findOne(voteId);
		if (vote == null) {
			throw new ResourceNotFoundException();
		}
		return vote;
    }
}