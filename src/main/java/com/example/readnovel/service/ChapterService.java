package com.example.readnovel.service;

import com.example.readnovel.customException.CustomException;
import com.example.readnovel.entity.Chapter;
import com.example.readnovel.entity.Novel;
import com.example.readnovel.entity.Volume;
import com.example.readnovel.entity.dto.ChapterDto;
import com.example.readnovel.repository.ChapterRepository;
import com.example.readnovel.repository.NovelRepository;
import com.example.readnovel.repository.VolumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChapterService {
    @Autowired
    private ChapterRepository repository;
    @Autowired
    private VolumeRepository volumeRepository;
    @Autowired
    private NovelRepository novelRepository;
    public Object findById(String id) throws CustomException {
        Chapter chapter= repository.findById(id).orElse(null);
        if (chapter==null){
            throw new CustomException("Chapter not found!");
        }
        chapter.setView(chapter.getView()+1);
        repository.save(chapter);
        return new ChapterDto(chapter);
    }


    public Object save(ChapterDto chapterDto) throws CustomException {
        Optional<Volume> optionalVolume = volumeRepository.findById(chapterDto.getVolumeId());
        if (!optionalVolume.isPresent()){
            throw new CustomException("Volume not found!");
        }
        Chapter chapter = Chapter.builder()
                .title(chapterDto.getTitle())
                .content(chapterDto.getContent())
                .isLock(chapterDto.isLock())
                .volume(optionalVolume.get())
                .build();
        if (chapter.getTitle().isEmpty()|| chapter.getContent().isEmpty()||chapter.getVolume()==null){
            throw new NullPointerException();
        }
        updateNovel(optionalVolume.get().getNovel().getId());
        return new ChapterDto(repository.save(chapter));
    }
    public Object update(ChapterDto chapterDto) throws CustomException{
        Optional<Chapter> optionalChapter = repository.findById(chapterDto.getId());
        Optional<Volume> optionalVolume = volumeRepository.findById(chapterDto.getVolumeId());
        if (!optionalChapter.isPresent()){
            throw new CustomException("Chapter not found!");
        }
        if (!optionalVolume.isPresent()){
            throw new CustomException("Volume not found!");
        }
        Chapter old = optionalChapter.get();
        old.setTitle(chapterDto.getTitle());
        old.setContent(chapterDto.getContent());
        old.setLock(chapterDto.isLock());
        old.setVolume(optionalVolume.get());
        return new ChapterDto(repository.save(old));
    }
    public boolean delete(String id){
        repository.deleteById(id);
        return true;
    }
    public void updateNovel(String novelId) throws CustomException {
        Optional<Novel> optionalNovel = novelRepository.findById(novelId);
        if (!optionalNovel.isPresent()){
            throw  new CustomException("Novel not Found");
        }
        Novel novel = optionalNovel.get();
        novelRepository.save(novel);
    }
}
