package com.samuilolegovich.WBL.model;

import com.samuilolegovich.WBL.model.enums.Enums;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class Win {
    private Enums replyToBet;
    private long win;
}
