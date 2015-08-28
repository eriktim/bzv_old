package nl.gingerik.bzv.repository;

import nl.gingerik.bzv.model.VotePeriod;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel="voteperiod", path="voteperiod")
public interface VotePeriodRepository extends CrudRepository<VotePeriod, Long> {

}
