package com.example.readnovel.repository;

import com.example.readnovel.entity.HistoryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryItemRepository extends JpaRepository<HistoryItem,String> {
}
