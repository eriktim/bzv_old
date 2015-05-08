package nl.gingerik.bzv.model;

import java.sql.Date;

public class Candidate {

    private long id;
    private long peasantId;
    private String name;
    private Date dateElimination;

    public long getId() {
        return id;
    }
    
    public long getPeasantId() {
    	return peasantId;
    }

    public String getName() {
        return name;
    }

    public Date getDateElimination() {
        return dateElimination;
    }
}