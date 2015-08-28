package nl.gingerik.bzv.repository;

import nl.gingerik.bzv.model.Vote;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel="vote", path="vote")
public interface VoteRepository extends CrudRepository<Vote, Long> {

}
