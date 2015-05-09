package nl.gingerik.bzv.model;

public class Peasant {

    private long id;
    private int year;
    private String name;
    
    public Peasant(long id, int year, String name) {
    	this.id = id;
    	this.year = year;
    	this.name = name;
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
}