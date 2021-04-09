package com.samuilolegovich.WBL.model;

import com.samuilolegovich.WBL.model.enums.RedBlack;

public interface Bets {
    public Win calculateTheWin(long id, int bet, RedBlack redBlack);
}
