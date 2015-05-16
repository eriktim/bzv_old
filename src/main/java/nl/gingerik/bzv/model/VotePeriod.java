package nl.gingerik.bzv.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="bzv_vote_periods")
public class VotePeriod {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
	
    private int year;
    
    @Column(name="date_start")
    private Date dateStart;

    @Column(name="date_end")
    private Date dateEnd;

    @Column(name="date_reference")
    private Date dateReference;

    @Column(name="vote_count")
    private int voteCount;

    public long getId() {
        return id;
    }
    
    public int getYear() {
    	return year;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public Date getDateReference() {
        return dateReference;
    }
    
    public int getVoteCount() {
    	return voteCount;
    }
}