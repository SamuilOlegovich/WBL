package com.samuilolegovich.WBL.db;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.samuilolegovich.WBL.model.enums.RedBlack;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
//  Статистика выигрышей и проигрышей
public class StatisticsOfWinsAndLosses {
    @Id // @ID - Важно чтобы была из библиотеке -> javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long userId;
    private long bet;
    private long win;
    private RedBlack typeBet;
    private RedBlack typeWin;
    // указываем что поле не обновляемое
    @Column(updatable = false)
    // указываем как у нас будет сохранятся дата при сериализации
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime creationDate;
}
