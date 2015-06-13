package nl.gingerik.bzv.repository;

import nl.gingerik.bzv.model.Candidate;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel="candidate", path="candidate")
public interface CandidateRepository extends CrudRepository<Candidate, Long> {

}
