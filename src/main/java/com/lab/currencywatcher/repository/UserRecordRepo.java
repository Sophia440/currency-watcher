package com.lab.currencywatcher.repository;

import com.lab.currencywatcher.model.UserRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRecordRepo extends JpaRepository<UserRecord, Integer> {
}
