package com.samuilolegovich.WBL.model.util;


public class Generator {
    // генерирует число
    public static byte generate() {
        return (byte) ( Math.random() * 101 );
    }
}
