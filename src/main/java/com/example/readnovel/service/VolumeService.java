package com.example.readnovel.service;

import com.example.readnovel.customException.CustomException;
import com.example.readnovel.entity.Chapter;
import com.example.readnovel.entity.Novel;
import com.example.readnovel.entity.Volume;
import com.example.readnovel.entity.dto.VolumeDto;
import com.example.readnovel.repository.ChapterRepository;
import com.example.readnovel.repository.NovelRepository;
import com.example.readnovel.repository.VolumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class VolumeService {
    @Autowired
    private VolumeRepository repository;
    @Autowired
    private NovelRepository novelRepository;

    public VolumeDto save(VolumeDto dto) throws CustomException {
        if (dto.getTitle().isEmpty()) {
            throw new NullPointerException();
        }
        Novel novel = findNovel(dto.getNovelId());
        Volume volume = Volume.builder().title(dto.getTitle()).thumbnail(dto.getThumbnail()).novel(novel).build();
        return new VolumeDto(repository.save(volume));
    }

    private Novel findNovel(String novelId) throws CustomException {
        Novel novel = novelRepository.findById(novelId).orElse(null);
        if (novel == null) {
            throw new CustomException("Novel not found!");
        }
        return novel;
    }

    private Volume findById(String id) throws CustomException {
        Volume vol = repository.findById(id).orElse(null);
        if (vol == null) {
            throw new CustomException("Novel not found!");
        }
        return vol;
    }

    public Object update(VolumeDto dto) throws CustomException {
        Volume old = findById(dto.getId());
        if (dto.getTitle().isEmpty()) {
            throw new CustomException("Title is empty!");
        }
        old.setTitle(dto.getTitle());
        old.setNumber(dto.getNumber());
        old.setThumbnail(dto.getThumbnail());
        return new VolumeDto(repository.save(old));

    }

    public Boolean delete(String id) throws CustomException {
        Volume del = findById(id);
        String creator = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!del.getCreatedBy().contains(creator))
            throw new CustomException("Not the Creator");
        repository.deleteById(id);
        return true;
    }

}
