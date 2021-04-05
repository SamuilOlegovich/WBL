package com.samuilolegovich.WBL.model;


import com.samuilolegovich.WBL.db.Arsenal;
import com.samuilolegovich.WBL.db.Condition;
import com.samuilolegovich.WBL.db.Lotto;
import com.samuilolegovich.WBL.db.Player;
import com.samuilolegovich.WBL.repo.ArsenalRepo;
import com.samuilolegovich.WBL.repo.ConditionRepo;
import com.samuilolegovich.WBL.repo.LottoRepo;
import com.samuilolegovich.WBL.repo.PlayerRepo;
import org.springframework.beans.factory.annotation.Autowired;



public class Bet implements Bets {
    @Autowired
    private ConditionRepo conditionRepo;
    @Autowired
    private ArsenalRepo arsenalRepo;
    @Autowired
    private PlayerRepo playerRepo;
    @Autowired
    private LottoRepo lottoRepo;



    public synchronized Win calculateTheWin(long id, long bet, RedBlack redBlack) {
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

        // генерируем число
        byte generatedLotto = Generator.generate();

        // получаем данные по состоянию
        long lottoCredits = lotto.getLottoCredits();
        int bias = condition.getBias();


        // если смещение больше нуля то проверяем на выигрыш
        if (bias > Constants.ZERO_BIAS) {
            if (generatedLotto == Constants.POINT) return point(player, playerCredits, lottoCredits);
            if (generatedLotto == Constants.SUPER_LOTTO) return superLotto(player, playerCredits, lottoCredits);
        }

        // дописать логику








        // если сюда дошли то применяем смещение
        return takeIntoAccountTheBias(player, condition, redBlack, arsenalCredit, lottoCredits, playerCredits, bet, bias);
    }





    private Win point(Player player, long playerCredits, long lottoCredits) {
        // добавить откусывание 10 процентов в фонд
        long onePercent = lottoCredits / 100;
        long tenPercent = onePercent * 9;

        Lotto lotto = new Lotto();
        lotto.setLottoCredits(lottoCredits - (onePercent * 10));
        player.setCredits(playerCredits + tenPercent);

        lottoRepo.save(lotto);
        playerRepo.save(player);

        return new Win(RedBlack.POINT, tenPercent / Constants.FOR_USER_CALCULATIONS);
    }



    private Win superLotto(Player player, long playerCredits, long lottoCredits) {
        // добавить откусывание 10 процентов в фонд
        long onePercent = lottoCredits / 100;
        long allLotto = onePercent * 90;

        Lotto lotto = new Lotto();
        lotto.setLottoCredits(0);
        player.setCredits(playerCredits + allLotto);

        lottoRepo.save(lotto);
        playerRepo.save(player);

        return new Win(RedBlack.POINT, allLotto / Constants.FOR_USER_CALCULATIONS);
    }



    private Win takeIntoAccountTheBias(Player player, Condition condition, RedBlack redBlack,
                                       long arsenalCredit, long lottoCredits, long playerCredits, long bet, int bias) {

        player.setCredits(playerCredits - (bet * Constants.FOR_USER_CALCULATIONS));

        // перенос средств в лото или арсенал
        if (bias == Constants.ONE_BIAS) {
            Lotto lotto = new Lotto();
            lotto.setLottoCredits(lottoCredits + (bet * Constants.FOR_USER_CALCULATIONS));
            lottoRepo.save(lotto);
        } else {
            Arsenal arsenal = new Arsenal();
            arsenal.setCredits(arsenalCredit + (bet * Constants.FOR_USER_CALCULATIONS));
            arsenalRepo.save(arsenalRepo);
        }

        // уменьшаем смещение
        condition.setBias(bias - 1);

        conditionRepo.save(condition);
        playerRepo.save(player);

        return new Win(redBlack.equals(RedBlack.RED) ? RedBlack.BLACK : RedBlack.RED, 0);
    }
}
