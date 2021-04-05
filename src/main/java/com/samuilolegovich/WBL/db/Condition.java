package com.samuilolegovich.WBL.db;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



@Entity
// данные о состоянии игры
public class Condition {
    @Id // @ID - Важно чтобы была из библиотеке -> javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id;
    private long bet;
    // доп смещении для генератора
    private int bias;




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getBet() {
        return bet;
    }

    public void setBet(long bet) {
        this.bet = bet;
    }

    public int getBias() {
        return bias;
    }

    public void setBias(int bias) {
        this.bias = bias;
    }
}
