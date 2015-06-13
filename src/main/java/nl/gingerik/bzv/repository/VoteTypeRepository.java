package nl.gingerik.bzv.repository;

import nl.gingerik.bzv.model.VoteType;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel="votetype", path="votetype")
public interface VoteTypeRepository extends PagingAndSortingRepository<VoteType, Long> {

}
