package com.samuilolegovich.WBL.model;



public class Converter {
    // конвертирует число в енум
    public static RedBlack convert(byte b) {
        if (b == Constants.SUPER_LOTTO) return RedBlack.SUPER_LOTTO;
        if (b == Constants.POINT) return RedBlack.POINT;
        if (b > Constants.START && b < Constants.MIDDLE) return RedBlack.RED;
        if (b > Constants.MIDDLE && b < Constants.STOP) return RedBlack.BLACK;
        return RedBlack.ZERO;
    }
}
