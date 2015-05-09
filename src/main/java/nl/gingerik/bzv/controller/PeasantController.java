package nl.gingerik.bzv.controller;

import java.util.List;

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
    private PeasantRepository peasants;

    @RequestMapping(value="/peasant", method=RequestMethod.GET)
    public List<Peasant> getList() {
    	return peasants.getAll();
    }

    @RequestMapping(value="/peasant/{peasantId}", method=RequestMethod.GET)
    public Peasant getById(@PathVariable("peasantId") long peasantId) {
    	return peasants.getById(peasantId);
    }
}