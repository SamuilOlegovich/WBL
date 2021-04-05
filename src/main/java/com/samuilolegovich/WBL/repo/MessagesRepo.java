package com.samuilolegovich.WBL.repo;


import com.samuilolegovich.WBL.db.Player;
import org.springframework.data.repository.CrudRepository;


public interface MessagesRepo extends CrudRepository<Player, Long> {
}
