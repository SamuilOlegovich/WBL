package com.samuilolegovich.WBL.repo;

import com.samuilolegovich.WBL.db.Condition;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ConditionRepo extends CrudRepository<Condition, Long> {
    Condition findByBet(long bet);
    Condition findById(int id);
}
