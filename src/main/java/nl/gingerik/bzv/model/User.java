package nl.gingerik.bzv.model;

public class User {

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