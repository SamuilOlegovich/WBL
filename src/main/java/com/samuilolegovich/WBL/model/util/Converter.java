package com.samuilolegovich.WBL.model.util;


import com.samuilolegovich.WBL.model.enums.Enums;
import com.samuilolegovich.WBL.model.enums.Prize;
import com.samuilolegovich.WBL.model.enums.RedBlack;


public class Converter {
    // конвертирует число в енум
    public static Enums convert(byte b) {
        if (b == Constants.LOTTO) return Prize.LOTTO;
        if (b == Constants.SUPER_LOTTO) return Prize.SUPER_LOTTO;
        if (b > Constants.START && b < Constants.MIDDLE) return RedBlack.RED;
        if (b > Constants.MIDDLE && b < Constants.STOP) return RedBlack.BLACK;
        return Prize.ZERO;
    }
}
