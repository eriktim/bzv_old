package nl.gingerik.bzv.controller;

import nl.gingerik.bzv.controller.ExceptionHandlingController.ResourceNotFoundException;
import nl.gingerik.bzv.model.Candidate;
import nl.gingerik.bzv.repository.CandidateRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CandidateController {
	
    @Autowired
    private CandidateRepository candidateRepository;

    @RequestMapping(value="/candidate", method=RequestMethod.GET)
    public Iterable<Candidate> getList() {
		return candidateRepository.findAll();
    }

    @RequestMapping(value="/candidate/{candidateId}", method=RequestMethod.GET)
    public Candidate getById(@PathVariable("candidateId") long candidateId) {
		Candidate candidate = candidateRepository.findOne(candidateId);
		if (candidate == null) {
			throw new ResourceNotFoundException();
		}
		return candidate;
    }
}