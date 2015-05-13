package nl.gingerik.bzv.controller;

import java.util.List;

import nl.gingerik.bzv.model.Peasant;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ImportResource("classpath:applicationContext.xml")
@Transactional
public class PeasantController {
	
    @Autowired
    private SessionFactory sessionFactory;

    @RequestMapping(value="/peasant", method=RequestMethod.GET)
    public List<Peasant> getList() {
		return (List<Peasant>) sessionFactory.getCurrentSession().createCriteria(Peasant.class).list();
    }

    @RequestMapping(value="/peasant/{peasantId}", method=RequestMethod.GET)
    public Peasant getById(@PathVariable("peasantId") long peasantId) {
    	return (Peasant) sessionFactory.getCurrentSession().load(Peasant.class, peasantId);
    }
}