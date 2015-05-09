package nl.gingerik.bzv.model;

import java.sql.Date;

public class VotePeriod {

    private long id;
    private int year;
    private Date dateStart;
    private Date dateEnd;
    private Date dateReference;
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