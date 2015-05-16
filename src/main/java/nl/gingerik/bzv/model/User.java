package nl.gingerik.bzv.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="bzv_users")
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
	
    private int year;
    private String name;
    private String email;
    private String hash;

    public long getId() {
        return id;
    }

    public int getYear() {
        return year;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getHash() {
        return hash;
    }
}