package com.samuilolegovich.WBL.db;


import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
//  вывод средств
public class WithdrawalOfFunds {
    @Id // @ID - Важно чтобы была из библиотеке -> javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long userId;
    // куда ушли
    private String  wentToWallet;
    // откуда ушли
    private String leftTheWallet;
    private long numberOfCoins;
    private String coinName;
    private String tag;
    // указываем что поле не обновляемое
    @Column(updatable = false)
    // указываем как у нас будет сохранятся дата при сериализации
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime creationDate;
}
