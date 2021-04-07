package com.samuilolegovich.WBL.repo;

import com.samuilolegovich.WBL.db.Donations;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface DonationsRepo extends CrudRepository<Donations, Long> {
    Donations findFirstByOrderByCreatedAtDesc();
}
