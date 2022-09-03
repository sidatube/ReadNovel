package com.example.readnovel.service;

import com.example.readnovel.constant.SearchCriteriaOperator;
import com.example.readnovel.criteriaFilter.SearchCriteria;
import com.example.readnovel.criteriaFilter.TypeSpecification;
import com.example.readnovel.customException.CustomException;
import com.example.readnovel.entity.Type;
import com.example.readnovel.entity.dto.TypeDto;
import com.example.readnovel.repository.TypeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class TypeService {
    @Autowired
    private TypeRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    public Object addType(TypeDto dto) {
        Type type = modelMapper.map(dto,Type.class);
        return repository.save(type);
    }

    public Object findAll(String name) {
        Specification<Type> specification = Specification.where(null);
        if (!name.isEmpty()) {
            TypeSpecification nameFilter = new TypeSpecification(new SearchCriteria("name", SearchCriteriaOperator.Like, name));
            specification = specification.and(nameFilter);
        }
        return  repository.findAll(specification);
    }

    public Type findById(String id) throws CustomException {
        if (id.isEmpty()){
            throw new CustomException("Id is empty!");
        }
        Type type=repository.findById(id).orElse(null);
        if (type==null)
            throw new CustomException("Type not Found");
        return type;
    }

    public Object update(TypeDto dto) throws CustomException {
        Type old = findById(dto.getId());
        old.setDescription(dto.getDescription());
        old.setName(dto.getName());
        return repository.save(old);
    }

    public Object delete(String id) throws CustomException {
        Type old = findById(id);
        repository.delete(old);
        return true;
    }
}
