package nl.gingerik.bzv.controller;

import nl.gingerik.bzv.controller.ExceptionHandlingController.ResourceNotFoundException;
import nl.gingerik.bzv.model.VoteType;
import nl.gingerik.bzv.repository.VoteTypeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VoteTypeController {

    @Autowired
    private VoteTypeRepository voteTypeRepository;

    @RequestMapping(value="/vote/type", method=RequestMethod.GET)
    public Iterable<VoteType> getList() {
		return voteTypeRepository.findAll();
    }

    @RequestMapping(value="/vote/type/{voteTypeId}", method=RequestMethod.GET)
    public VoteType getById(@PathVariable("voteTypeId") long voteTypeId) {
		VoteType voteType = voteTypeRepository.findOne(voteTypeId);
		if (voteType == null) {
			throw new ResourceNotFoundException();
		}
		return voteType;
    }
}