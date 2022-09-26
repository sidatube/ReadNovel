package com.example.readnovel.service;

import com.example.readnovel.Filter.AFirstFilter;
import com.example.readnovel.constant.SearchCriteriaOperator;
import com.example.readnovel.criteriaFilter.AuthorSpecification;
import com.example.readnovel.criteriaFilter.AuthorSpecification;
import com.example.readnovel.criteriaFilter.SearchCriteria;
import com.example.readnovel.customException.CustomException;
import com.example.readnovel.entity.Author;
import com.example.readnovel.entity.Author;
import com.example.readnovel.entity.dto.AFirstDto;
import com.example.readnovel.repository.AuthorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service
public class AuthorService {
    @Autowired
    private AuthorRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    public Object add(AFirstDto dto) {
        Author author =modelMapper.map(dto,Author.class);
        return repository.save(author);
    }

    public Object findAll(String name) {
        Specification<Author> specification = Specification.where(null);
        AuthorSpecification notDelete = new AuthorSpecification(new SearchCriteria("isDeleted", SearchCriteriaOperator.Equals, false));
        specification = specification.and(notDelete);
        if (!name.isEmpty()) {
            AuthorSpecification nameFilter = new AuthorSpecification(new SearchCriteria("name", SearchCriteriaOperator.Like, name));
            specification = specification.and(nameFilter);
        }
        return  repository.findAll(specification);
    }
    public Object findAll(AFirstFilter filter) {
        Specification<Author> specification = Specification.where(null);
        AuthorSpecification notDelete = new AuthorSpecification(new SearchCriteria("isDeleted", SearchCriteriaOperator.Equals, false));
        specification = specification.and(notDelete);
        if (!filter.getName().isEmpty()) {
            AuthorSpecification nameFilter = new AuthorSpecification(new SearchCriteria("name", SearchCriteriaOperator.Like, filter.getName()));
            specification = specification.and(nameFilter);
        }
        Sort sort = Sort.by("name");
        if(filter.getSortBy()!=null){
            switch (filter.getSortBy()) {
                case "nameDesc":
                    sort = Sort.by("name").descending();
                    break;
                case "createdAt":
                    sort = Sort.by("createdAt");
                    break;
                case "createdAtDesc":
                    sort = Sort.by("createdAt").descending();
                    break;
                default:
                    break;
            }
        }
        Pageable pageable = PageRequest.of(filter.getIndex() - 1, filter.getSize(),sort );
        return  repository.findAll(specification,pageable).map(AFirstDto::new);
    }


    public Author findById(String id) throws CustomException {
        if (id.isEmpty()){
            throw new CustomException("Id is empty!");
        }
        Author author=repository.findById(id).orElse(null);
        if (author==null)
            throw new CustomException("Author not Found");
        return author;
    }

    public Object update(AFirstDto update) throws CustomException {
        Author old = findById(update.getId());
        old.setName(update.getName());
        return repository.save(old);
    }

    public Object delete(String id) throws CustomException {
        Author old = findById(id);
        old.setDeleted(true);
        repository.save(old);
        return true;
    }
}
