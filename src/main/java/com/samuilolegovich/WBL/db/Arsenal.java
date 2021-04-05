package com.samuilolegovich.WBL.db;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



@Entity
public class Arsenal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long credits;



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCredits() {
        return credits;
    }

    public void setCredits(long credits) {
        this.credits = credits;
    }
}
