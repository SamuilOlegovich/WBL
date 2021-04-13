package com.samuilolegovich.WBL.model;


import java.util.Random;

public class Start {
    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
        Random random = new Random();
            int r = random.nextInt(101);
            if (r == 100)
            System.out.println(r + "");
        }
    }

}
