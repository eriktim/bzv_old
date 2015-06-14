package nl.gingerik.bzv;

import nl.gingerik.bzv.model.Candidate;
import nl.gingerik.bzv.model.Peasant;
import nl.gingerik.bzv.model.User;
import nl.gingerik.bzv.model.Vote;
import nl.gingerik.bzv.model.VotePeriod;
import nl.gingerik.bzv.model.VoteType;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;

@Configuration
public class RestConfiguration extends RepositoryRestMvcConfiguration {
 
    @Override
    protected void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(Candidate.class, Peasant.class, User.class,
        		Vote.class, VotePeriod.class, VoteType.class);
    }
}