package com.example.readnovel.service;

import com.example.readnovel.constant.SearchCriteriaOperator;
import com.example.readnovel.criteriaFilter.RoleSpecification;
import com.example.readnovel.criteriaFilter.SearchCriteria;
import com.example.readnovel.customException.CustomException;
import com.example.readnovel.entity.Novel;
import com.example.readnovel.entity.Role;
import com.example.readnovel.repository.NovelRepository;
import com.example.readnovel.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    private RoleRepository repository;

    public Role save(Role novel) {
        if (novel.getName().isEmpty()) {
            throw new NullPointerException();
        }
        return repository.save(novel);
    }

    public List<Role> getList(String roleName) {
        Specification<Role> roleSpecification = Specification.where(null);
        if (!roleName.isEmpty()) {
        RoleSpecification filterName = new RoleSpecification(new SearchCriteria("name", SearchCriteriaOperator.Like,roleName));
        roleSpecification=roleSpecification.and(filterName);
        }
        return repository.findAll(roleSpecification);
    }

    public Role findById(String id) {
        return repository.findById(id).orElse(null);
    }

    public void delete(String id) {
        repository.deleteById(id);
    }

    public Object update(Role role) throws CustomException {
        Role old = findById(role.getId());
        if (old==null){
         throw  new CustomException("Role not found");
        }
        old.setName(role.getName());
        return save(old);
    }
}
