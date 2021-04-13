package com.samuilolegovich.WBL.model;


import com.samuilolegovich.WBL.db.Player;
import com.samuilolegovich.WBL.model.enums.RedBlack;


public interface Bets {
    public Win calculateTheWin(Player player, int bet, RedBlack redBlack);
}
