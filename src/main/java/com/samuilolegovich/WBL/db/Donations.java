package com.samuilolegovich.WBL.db;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.samuilolegovich.WBL.model.RedBlack;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
public class Donations {
    @Id // @ID - Важно чтобы была из библиотеке -> javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long donations;
    private long totalDonations;
    private RedBlack typeWin;
    @Column(updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime creationDate;


    public Donations() {
    }

    public Donations(long donations, long totalDonations, RedBlack typeWin) {
        this.donations = donations;
        this.totalDonations = totalDonations;
        this.typeWin = typeWin;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getDonations() {
        return donations;
    }

    public void setDonations(long donations) {
        this.donations = donations;
    }

    public long getTotalDonations() {
        return totalDonations;
    }

    public void setTotalDonations(long totalDonations) {
        this.totalDonations = totalDonations;
    }

    public RedBlack getTypeWin() {
        return typeWin;
    }

    public void setTypeWin(RedBlack typeWin) {
        this.typeWin = typeWin;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }


}
