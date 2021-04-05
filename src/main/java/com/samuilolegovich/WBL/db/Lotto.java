package com.samuilolegovich.WBL.db;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



@Entity
public class Lotto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long lottoCredits;



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getLottoCredits() {
        return lottoCredits;
    }

    public void setLottoCredits(long lottoCredits) {
        this.lottoCredits = lottoCredits;
    }
}
