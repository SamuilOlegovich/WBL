package com.samuilolegovich.WBL.repo;


import com.samuilolegovich.WBL.db.Player;
import org.springframework.data.repository.CrudRepository;


public interface PlayerRepo extends CrudRepository<Player, Long> {
    Player findById(long id);
}
