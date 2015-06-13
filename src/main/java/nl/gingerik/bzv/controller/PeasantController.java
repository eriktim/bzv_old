package nl.gingerik.bzv.controller;

import nl.gingerik.bzv.controller.ExceptionHandlingController.ResourceNotFoundException;
import nl.gingerik.bzv.model.Peasant;
import nl.gingerik.bzv.repository.PeasantRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PeasantController {
	
    @Autowired
    private PeasantRepository peasantRepository;

    @RequestMapping(value="/peasant", method=RequestMethod.GET)
    public Iterable<Peasant> getList() {
		return peasantRepository.findAll();
    }

    @RequestMapping(value="/peasant/{peasantId}", method=RequestMethod.GET)
    public Peasant getById(@PathVariable("peasantId") long peasantId) {
		Peasant peasant = peasantRepository.findOne(peasantId);
		if (peasant == null) {
			throw new ResourceNotFoundException();
		}
		return peasant;
    }
}