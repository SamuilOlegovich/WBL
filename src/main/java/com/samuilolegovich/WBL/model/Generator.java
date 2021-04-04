package com.samuilolegovich.WBL.model;

public class Generator {
    // генерирует число
    public static synchronized byte generate() {
        return 42;
    }
}
