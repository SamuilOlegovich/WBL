package com.samuilolegovich.WBL.db;

import com.samuilolegovich.WBL.db.util.MessageHelper;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;



@Entity
public class Messages {
    @Id // @ID - Важно чтобы была из библиотеке -> javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String tag;
    private String message;

    // много сообщений к одному юзеру
    // и сразу выдергиваем автора к каждому сообщению
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "player_id")
    private Player author;

    @ManyToMany
    @JoinTable(
            name = "messages_likes",
            joinColumns = { @JoinColumn(name = "messages_id") },
            inverseJoinColumns = { @JoinColumn(name = "player_id")}
    )
    private Set<Player> likes = new HashSet<>();



    public Messages() {
    }

    public Messages(String teg, String message) {
        this.message = message;
        this.tag = teg;
    }

    public Messages(String tag, String message, Player user) {
        this.message = message;
        this.author = user;
        this.tag = tag;
    }


    public String getPlayerNickName() {
        return MessageHelper.getPlayerNickName(author);
    }
}
