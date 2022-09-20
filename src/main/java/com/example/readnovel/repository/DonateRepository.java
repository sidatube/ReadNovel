package com.example.readnovel.repository;

import com.example.readnovel.entity.DonateHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface DonateRepository extends JpaRepository<DonateHistory,String>, JpaSpecificationExecutor<DonateHistory> {
}
