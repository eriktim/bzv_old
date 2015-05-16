package nl.gingerik.bzv.controller;

import java.util.List;

import nl.gingerik.bzv.dao.VoteTypeDao;
import nl.gingerik.bzv.model.VoteType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VoteTypeController {

    @Autowired
    private VoteTypeDao voteTypeDao;

    @RequestMapping(value="/vote/type", method=RequestMethod.GET)
    public List<VoteType> getList() {
		return voteTypeDao.list();
    }

    @RequestMapping(value="/vote/type/{voteTypeId}", method=RequestMethod.GET)
    public VoteType getById(@PathVariable("voteTypeId") long voteTypeId) {
		return voteTypeDao.get(voteTypeId);
    }
}