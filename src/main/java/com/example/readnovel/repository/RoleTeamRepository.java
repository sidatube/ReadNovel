package com.example.readnovel.repository;

import com.example.readnovel.entity.RoleTeam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface RoleTeamRepository extends JpaRepository<RoleTeam,String > {
    Optional<RoleTeam> findByName(String name);
    List<RoleTeam> findByNameIn(Collection<String> name);

}
