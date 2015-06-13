package nl.gingerik.bzv.repository;

import nl.gingerik.bzv.model.VoteType;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel="votetype", path="votetype")
public interface VoteTypeRepository extends CrudRepository<VoteType, Long> {

}
