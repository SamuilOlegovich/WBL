package com.samuilolegovich.WBL.repo;


import com.samuilolegovich.WBL.db.Arsenal;
import org.springframework.data.repository.CrudRepository;


public interface ArsenalRepo extends CrudRepository<ArsenalRepo, Long> {
    Arsenal findFirstByOrderByCreatedAtDesc();

}
