package nl.gingerik.bzv.repository;

import nl.gingerik.bzv.model.Vote;

import org.springframework.data.repository.CrudRepository;

public interface VoteRepository extends CrudRepository<Vote, Long> {

}
