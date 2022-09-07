package com.example.readnovel.repository;

import com.example.readnovel.entity.HistoryRead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HistoryReadRepository extends JpaRepository<HistoryRead,String> {
    @Query("from historyRead h join fetch h.account as a where a.username =:username ")
    Optional<HistoryRead> findByUsername(String username);
}
