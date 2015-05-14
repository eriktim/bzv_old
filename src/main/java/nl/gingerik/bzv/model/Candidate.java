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
@Table(name="bzv_candidates")
public class Candidate {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
	
    @ManyToOne
    @JoinColumn(name="peasantid")
    private Peasant peasant;
    
    private String name;
    
    @Column(name="date_elimination")
    private Date dateElimination;

    public long getId() {
        return id;
    }
    
    public Peasant getPeasant() {
    	return peasant;
    }

    public String getName() {
        return name;
    }

    public Date getDateElimination() {
        return dateElimination;
    }
}