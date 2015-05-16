package nl.gingerik.bzv.controller;

import java.util.List;

import nl.gingerik.bzv.controller.ExceptionHandlingController.ResourceNotFoundException;
import nl.gingerik.bzv.dao.CandidateDao;
import nl.gingerik.bzv.model.Candidate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CandidateController {
	
    @Autowired
    private CandidateDao candidateDao;

    @RequestMapping(value="/candidate", method=RequestMethod.GET)
    public List<Candidate> getList() {
		return candidateDao.list();
    }

    @RequestMapping(value="/candidate/{candidateId}", method=RequestMethod.GET)
    public Candidate getById(@PathVariable("candidateId") long candidateId) {
		Candidate candidate = candidateDao.get(candidateId);
		if (candidate == null) {
			throw new ResourceNotFoundException();
		}
		return candidate;
    }
}