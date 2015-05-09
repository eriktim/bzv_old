package nl.gingerik.bzv.model;

import java.sql.Date;

public class Vote {

    private long id;
    private long userId;
    private long candidateId;
    private Date dateVoted;
    private long voteTypeId;
    private long votePeriodId;

    public long getId() {
        return id;
    }
    
    public long getUserId() {
    	return userId;
    }
    
    public long getCandidateId() {
    	return candidateId;
    }

    public Date getDateVoted() {
        return dateVoted;
    }
    
    public long getVoteTypeId() {
    	return voteTypeId;
    }
    
    public long getVotePeriodId() {
    	return votePeriodId;
    }
}