package com.samuilolegovich.WBL.model;


import com.samuilolegovich.WBL.db.*;
import com.samuilolegovich.WBL.model.util.Constants;
import com.samuilolegovich.WBL.model.util.Converter;
import com.samuilolegovich.WBL.model.util.Generator;
import com.samuilolegovich.WBL.repo.*;
import org.springframework.beans.factory.annotation.Autowired;




public class Bet implements Bets {
    private static Bet bet = null;

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


    private Bet() {}




    public synchronized Win calculateTheWin(long id, int bet, RedBlack redBlackBet) {
        // получаем игрока и данные о его кредитах
        Player player = playerRepo.findById(id);
        long playerCredits = player.getCredits();

        // если ставка выше допустимой
        if (Constants.MAXIMUM_RATE < bet)
            return new Win(RedBlack.MAXIMUM_RATE, 0);

        // если недостаточно кредитов то ...
        if ((playerCredits / Constants.FOR_USER_CALCULATIONS) < bet)
            return new Win(RedBlack.INSUFFICIENT_FUNDS, 0);

        Arsenal arsenal = arsenalRepo.findFirstByOrderByCreatedAtDesc();
        long arsenalCredit = arsenal.getCredits();

        // проверяем достаточно ли кредитов в запасе на ответ ставке
        if ((arsenalCredit / Constants.FOR_USER_CALCULATIONS) <= bet)
            return new Win(RedBlack.NOT_ENOUGH_CREDIT_FOR_ANSWER, 0);

        // Получаем состояния системы
        Lotto lotto = lottoRepo.findFirstByOrderByCreatedAtDesc();
        Condition condition = conditionRepo.findByBet(bet);

        // получаем данные по состоянию
        long lottoCredits = lotto.getLottoCredits();
        int bias = condition.getBias();

        // генерируем число
        byte generatedLotto = Generator.generate();

        // если смещение больше нуля то проверяем на выигрыш
        if (bias > Constants.ZERO_BIAS) {
            // если лото позволяет дробление
            if (checkForWinningsLotto(lottoCredits)) {
                if (generatedLotto == Constants.POINT) return point(player, playerCredits, lottoCredits);
                if (generatedLotto == Constants.SUPER_LOTTO) return superLotto(player, playerCredits, lottoCredits);
            }
            return takeIntoAccountTheBias(player, playerCredits, bet, redBlackBet, arsenalCredit,
                    lottoCredits, condition, bias);
        }

        return wonOrNotWon(player, playerCredits, bet, redBlackBet, generatedLotto,
                arsenalCredit, lottoCredits, condition);
    }





    private Win point(Player player, long playerCredits, long lottoCredits) {
        long onePercent = lottoCredits / 100;
        long boobyPrize = onePercent * Constants.BOOBY_PRIZE;
        long totalDonation = donationsRepo.findFirstByOrderByCreatedAtDesc().getTotalDonations() + onePercent;

        player.setCredits(playerCredits + boobyPrize);

        lottoRepo.save(new Lotto(lottoCredits - (boobyPrize + onePercent)));
        donationsRepo.save(new Donations(onePercent, totalDonation, RedBlack.POINT));
        playerRepo.save(player);

        return new Win(RedBlack.POINT, boobyPrize / Constants.FOR_USER_CALCULATIONS);
    }



    private Win superLotto(Player player, long playerCredits, long lottoCredits) {
        // добавить откусывание 10 процентов в фонд
        long onePercent = lottoCredits / 100;
        long donation = onePercent * Constants.DONATE;
        long allLotto = onePercent * Constants.PRIZE;
        long totalDonations = donationsRepo.findFirstByOrderByCreatedAtDesc().getTotalDonations() + donation;

        player.setCredits(playerCredits + allLotto);

        donationsRepo.save(new Donations(donation, totalDonations, RedBlack.POINT));
        lottoRepo.save(new Lotto(0));
        playerRepo.save(player);

        return new Win(RedBlack.POINT, allLotto / Constants.FOR_USER_CALCULATIONS);
    }



    private Win takeIntoAccountTheBias(Player player, long playerCredits, int bet, RedBlack redBlackBet,
                                       long arsenalCredit, long lottoCredits, Condition condition, int bias) {

        int resultCredits = bet * Constants.FOR_USER_CALCULATIONS;

        player.setCredits(playerCredits - resultCredits);

        // перенос средств в лото или арсенал
        if (bias == Constants.ONE_BIAS) {
            lottoRepo.save(new Lotto(lottoCredits + resultCredits));
        } else {
            arsenalRepo.save(new Arsenal(arsenalCredit + resultCredits));
        }

        // уменьшаем смещение
        condition.setBias(bias - 1);

        conditionRepo.save(condition);
        playerRepo.save(player);

        return new Win(redBlackBet.equals(RedBlack.RED) ? RedBlack.BLACK : RedBlack.RED, 0);
    }

    

    private Win wonOrNotWon(Player player, long playerCredits, int bet, RedBlack redBlackBet, byte generatedLotto,
                            long arsenalCredits, long lottoCredits, Condition condition) {

        // если лото позволяет дробление
        if (checkForWinningsLotto(lottoCredits)) {
            if (generatedLotto == Constants.POINT) return point(player, playerCredits, lottoCredits);
            if (generatedLotto == Constants.SUPER_LOTTO) return superLotto(player, playerCredits, lottoCredits);
        }

        RedBlack redBlackConvert = Converter.convert(generatedLotto);

        int resultCredits = bet * Constants.FOR_USER_CALCULATIONS;

        // если игрок выиграл
        if (redBlackConvert.equals(redBlackBet)) {
            condition.setBias(Constants.BIAS);
            player.setCredits(playerCredits + resultCredits);

            arsenalRepo.save(new Arsenal(arsenalCredits - resultCredits));
            conditionRepo.save(condition);
            playerRepo.save(player);

            return new Win(redBlackConvert, bet);
        }

        player.setCredits(playerCredits - resultCredits);

        lottoRepo.save(new Lotto(lottoCredits + resultCredits));
        playerRepo.save(player);

        return new Win(redBlackConvert, 0);
    }



    private boolean checkForWinningsLotto(long lottoCredits) {
        if (lottoCredits >= 1000) return true;
        return false;
    }



    public static synchronized Bet getInstance() {
        if (bet == null) bet = new Bet();
        return bet;
    }
}
