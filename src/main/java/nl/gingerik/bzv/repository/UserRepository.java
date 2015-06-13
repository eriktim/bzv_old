package nl.gingerik.bzv.repository;

import nl.gingerik.bzv.model.User;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

}
