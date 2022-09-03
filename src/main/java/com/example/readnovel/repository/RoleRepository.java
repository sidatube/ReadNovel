package com.example.readnovel.repository;

import com.example.readnovel.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
@Repository
public interface RoleRepository extends JpaRepository<Role,String >, JpaSpecificationExecutor<Role> {
    Optional<Role> findByName(String name);
    List<Role> findByNameIn(Collection<String> roles);

}
