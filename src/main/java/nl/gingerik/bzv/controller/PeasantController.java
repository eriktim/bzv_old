package nl.gingerik.bzv.controller;

import java.util.List;

import nl.gingerik.bzv.dao.PeasantDao;
import nl.gingerik.bzv.model.Peasant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ImportResource("classpath:applicationContext.xml")
public class PeasantController {
	
    @Autowired
    private PeasantDao peasantDao;

    @RequestMapping(value="/peasant", method=RequestMethod.GET)
    public List<Peasant> getList() {
		return peasantDao.list();
    }

    @RequestMapping(value="/peasant/{peasantId}", method=RequestMethod.GET)
    public Peasant getById(@PathVariable("peasantId") long peasantId) {
		return peasantDao.get(peasantId);
    }
}