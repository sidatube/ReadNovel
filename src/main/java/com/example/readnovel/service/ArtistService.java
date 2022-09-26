package com.example.readnovel.service;

import com.example.readnovel.constant.SearchCriteriaOperator;
import com.example.readnovel.criteriaFilter.ArtistSpecification;
import com.example.readnovel.criteriaFilter.SearchCriteria;
import com.example.readnovel.customException.CustomException;
import com.example.readnovel.entity.Artist;
import com.example.readnovel.entity.Author;
import com.example.readnovel.entity.dto.AFirstDto;
import com.example.readnovel.repository.ArtistRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service
public class ArtistService {
    @Autowired
    private ArtistRepository repository;
    @Autowired
    private ModelMapper modelMapper;
    public Object add(AFirstDto dto) {
        Artist artist = modelMapper.map(dto,Artist.class);
        return repository.save(artist);
    }

    public Object findAll(String name) {
        Specification<Artist> specification = Specification.where(null);
        if (!name.isEmpty()) {
            ArtistSpecification nameFilter = new ArtistSpecification(new SearchCriteria("name", SearchCriteriaOperator.Like, name));
            specification = specification.and(nameFilter);
        }
        return  repository.findAll(specification);
    }

    public Artist findById(String id) throws CustomException {
        if (id.isEmpty()){
            throw new CustomException("Id is empty!");
        }
        Artist artist=repository.findById(id).orElse(null);
        if (artist==null)
            throw new CustomException("Artist not Found");
        return artist;
    }

    public Object update(AFirstDto update) throws CustomException {
        Artist old = findById(update.getId());
        old.setOtherName(update.getOtherName());
        old.setName(update.getName());
        return repository.save(old);
    }

    public Object delete(String id) throws CustomException {
        Artist old = findById(id);
        old.setDeleted(true);
        repository.save(old);
        return true;
    }
}
