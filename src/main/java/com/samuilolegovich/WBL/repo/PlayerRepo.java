package com.samuilolegovich.WBL.repo;


import com.samuilolegovich.WBL.db.Player;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PlayerRepo extends CrudRepository<Player, Long> {
    Player findById(long id);
}
