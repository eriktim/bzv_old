package nl.gingerik.bzv.controller;

import java.util.List;

import nl.gingerik.bzv.controller.ExceptionHandlingController.ResourceNotFoundException;
import nl.gingerik.bzv.dao.VotePeriodDao;
import nl.gingerik.bzv.model.VotePeriod;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VotePeriodController {
	
    @Autowired
    private VotePeriodDao votePeriodDao;

    @RequestMapping(value="/vote/period", method=RequestMethod.GET)
    public List<VotePeriod> getList() {
		return votePeriodDao.list();
    }

    @RequestMapping(value="/vote/period/{votePeriodId}", method=RequestMethod.GET)
    public VotePeriod getById(@PathVariable("votePeriodId") long votePeriodId) {
		VotePeriod votePeriod = votePeriodDao.get(votePeriodId);
		if (votePeriod == null) {
			throw new ResourceNotFoundException();
		}
		return votePeriod;
    }
}