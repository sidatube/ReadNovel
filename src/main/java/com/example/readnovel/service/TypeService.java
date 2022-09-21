package com.example.readnovel.service;

import com.example.readnovel.Filter.TypeFilter;
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
import org.springframework.data.domain.Sort;
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

    public Object getAll() {

        return  repository.findAll().stream().map(TypeDto::new);
    }
    public Object findAll(TypeFilter filter) {
        Specification<Type> specification = Specification.where(null);
        if (!filter.getName().isEmpty()) {
            TypeSpecification nameFilter = new TypeSpecification(new SearchCriteria("name", SearchCriteriaOperator.Like, filter.getName()));
            specification = specification.and(nameFilter);
        }
        Sort sort = Sort.by("name");
        if (filter.getSortBy().equals("createdAt")){
            sort = Sort.by("createdAt").descending();
        }
        Pageable pageable = PageRequest.of(filter.getIndex()-1, filter.getSize(), sort );
        return  repository.findAll(specification,pageable).map(TypeDto::new);
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
