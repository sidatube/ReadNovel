package com.example.readnovel.repository;

import com.example.readnovel.entity.DonateHistory;
import com.example.readnovel.entity.group.DonateInMonth;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface DonateRepository extends JpaRepository<DonateHistory, String>, JpaSpecificationExecutor<DonateHistory> {
    @Transactional
    @Modifying
    @Query(value = "select sum(d.amount) as total ,month(d.created_at) as month  " +
            " from donate_history d  where year(d.created_at)=:year group by month(d.created_at) ",nativeQuery = true)
    List<DonateInMonth> findDonateCount(int year);
    @Transactional
    @Modifying
    @Query(value = "select * from donateHistory d where year(d.created_at)=:year and month(d.created_at)=:month ",nativeQuery = true)
    List<DonateHistory> findDonateInMonth(int month, int year, Pageable pageable);
}
