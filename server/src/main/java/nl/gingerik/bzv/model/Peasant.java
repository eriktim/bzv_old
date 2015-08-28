package nl.gingerik.bzv.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="bzv_peasants")
public class Peasant {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
	
    private int year;
    private String name;
    
    public Peasant() {
    	// no-argument constructor
    }

    public long getId() {
        return id;
    }

    public int getYear() {
        return year;
    }

    public String getName() {
        return name;
    }
    
    public Candidate getWinner() {
    	return null;
    }
}