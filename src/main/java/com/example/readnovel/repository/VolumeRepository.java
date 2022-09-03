package com.example.readnovel.repository;

import com.example.readnovel.entity.Volume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VolumeRepository extends JpaRepository<Volume,String > {
}
