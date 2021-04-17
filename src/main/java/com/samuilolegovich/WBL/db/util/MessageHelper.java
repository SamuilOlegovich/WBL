package com.samuilolegovich.WBL.db.util;

import com.samuilolegovich.WBL.db.Player;



public abstract class MessageHelper {
    public static String getPlayerNickName(Player player) {
        return player != null ? player.getNickName() : "<none>";
    }
}
