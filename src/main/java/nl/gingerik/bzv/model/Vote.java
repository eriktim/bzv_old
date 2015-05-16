package nl.gingerik.bzv.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="bzv_votes")
public class Vote {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name="userid")
    private User user;

    @ManyToOne
    @JoinColumn(name="candidateid")
    private Candidate candidate;

    @Column(name="date_voted")
    private Date dateVoted;

    @ManyToOne
    @JoinColumn(name="vote_typeid")
    private VoteType voteType;

    @ManyToOne
    @JoinColumn(name="vote_periodid")
    private VotePeriod votePeriod;

    public long getId() {
        return id;
    }
    
    public User getUser() {
    	return user;
    }
    
    public Candidate getCandidate() {
    	return candidate;
    }

    public Date getDateVoted() {
        return dateVoted;
    }
    
    public VoteType getVoteType() {
    	return voteType;
    }
    
    public VotePeriod getVotePeriod() {
    	return votePeriod;
    }
}