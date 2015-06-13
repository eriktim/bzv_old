package nl.gingerik.bzv.controller;

import nl.gingerik.bzv.controller.ExceptionHandlingController.ResourceNotFoundException;
import nl.gingerik.bzv.model.VotePeriod;
import nl.gingerik.bzv.repository.VotePeriodRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VotePeriodController {
	
    @Autowired
    private VotePeriodRepository votePeriodRepository;

    @RequestMapping(value="/vote/period", method=RequestMethod.GET)
    public Iterable<VotePeriod> getList() {
		return votePeriodRepository.findAll();
    }

    @RequestMapping(value="/vote/period/{votePeriodId}", method=RequestMethod.GET)
    public VotePeriod getById(@PathVariable("votePeriodId") long votePeriodId) {
		VotePeriod votePeriod = votePeriodRepository.findOne(votePeriodId);
		if (votePeriod == null) {
			throw new ResourceNotFoundException();
		}
		return votePeriod;
    }
}