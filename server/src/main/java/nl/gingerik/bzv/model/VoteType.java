package nl.gingerik.bzv.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="bzv_vote_types")
public class VoteType {
	
	public enum Name {
		HEARTH,
		GOOD,
		BAD
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
	
    private String name;

    public long getId() {
        return id;
    }
    
    public String getName() {
    	return name;
    }
}