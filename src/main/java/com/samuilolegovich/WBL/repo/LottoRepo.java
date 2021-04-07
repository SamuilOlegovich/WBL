package com.samuilolegovich.WBL.repo;


import com.samuilolegovich.WBL.db.Lotto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LottoRepo extends CrudRepository<Lotto, Long> {
    Lotto findFirstByOrderByCreatedAtDesc();
}
