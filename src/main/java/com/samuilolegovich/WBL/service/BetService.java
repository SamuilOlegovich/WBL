package com.samuilolegovich.WBL.service;


import com.samuilolegovich.WBL.db.Player;
import com.samuilolegovich.WBL.model.Bet;
import com.samuilolegovich.WBL.model.Bets;
import com.samuilolegovich.WBL.model.Win;
import com.samuilolegovich.WBL.model.enums.Enums;
import com.samuilolegovich.WBL.model.enums.InformationAboutRates;
import com.samuilolegovich.WBL.model.enums.Prize;
import com.samuilolegovich.WBL.model.enums.RedBlack;
import com.samuilolegovich.WBL.model.util.Constants;
import com.samuilolegovich.WBL.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class BetService {
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
    @Autowired
    private Bet betClass;




    public void placeBet(Player player, int bet, RedBlack redBlackBet) {

        // недопустимая ставка
        if (bet <= 0) {

        }

        // ставка выше допустимой
        if (bet > Constants.MAXIMUM_RATE) {

        }

        // если недостаточно кредитов у юзера для ставки
        if ((player.getCredits() / Constants.FOR_USER_CALCULATIONS) < bet) {

        }

        // если все хорошо делаем ставку
        Win win = betClass.calculateTheWin(player, bet, redBlackBet);
        Enums enums = win.getReplyToBet();

        // обрабатываем ответы по ставке

        // выиграл
        if (enums.equals(Prize.WIN)) {

        }

        // проиграл
        if (enums.equals(Prize.ZERO)) {

        }

        // выиграл лото 21
        if (enums.equals(Prize.LOTTO)) {

        }

        // выиграл супер лото 42
        if (enums.equals(Prize.SUPER_LOTTO)) {

        }

        // не достаточно кредитов в запасе на ответ ставке
        if (enums.equals(InformationAboutRates.NOT_ENOUGH_CREDIT_FOR_ANSWER)) {

        }
    }
}
