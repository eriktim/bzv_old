package nl.gingerik.bzv.repository;

import nl.gingerik.bzv.model.Peasant;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel="peasant", path="peasant")
public interface PeasantRepository extends CrudRepository<Peasant, Long> {

}
