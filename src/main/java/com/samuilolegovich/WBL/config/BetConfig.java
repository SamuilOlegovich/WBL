package com.samuilolegovich.WBL.config;

import com.samuilolegovich.WBL.model.Bet;
import com.samuilolegovich.WBL.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class BetConfig {
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


    @Bean
    public Bet getBet() {
        return Bet.getInstance(conditionRepo, donationsRepo, arsenalRepo, playerRepo, lottoRepo);
    }
}
