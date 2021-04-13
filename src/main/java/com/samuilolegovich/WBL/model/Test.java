package com.samuilolegovich.WBL.model;

import com.samuilolegovich.WBL.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


public class Test {
    private static Test bet = null;

    @Autowired
    private ConditionRepo conditionRepo;
    @Autowired
    private DonationsRepo donationsRepo;
    @Autowired
    private ArsenalRepo arsenalRepo;
    @Autowired
    private PlayerRepo playerRepo;
    @Autowired
    private LottoRepo lottoRepo;


    private Test() {
    }

    public static synchronized Test getInstance() {
        if (bet == null) bet = new Test();
        return bet;
    }


    public void tt() {
        System.out.println(lottoRepo);
    }
}
