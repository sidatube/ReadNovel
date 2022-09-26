package com.example.readnovel.service;

import com.example.readnovel.customException.CustomException;
import com.example.readnovel.entity.*;
import com.example.readnovel.entity.dto.ChapterDto;
import com.example.readnovel.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Service
public class ChapterService {
    @Autowired
    private ChapterRepository repository;
    @Autowired
    private VolumeRepository volumeRepository;
    @Autowired
    private NovelRepository novelRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private HistoryItemRepository itemRepository;
    @Autowired
    private HistoryReadRepository historyReadRepository;

    public Object findById(String id) throws CustomException {
        Chapter chapter = repository.findById(id).orElse(null);
        if (chapter == null) {
            throw new CustomException("Chapter not found!");
        }
        chapter.setView(chapter.getView() + 1);
        repository.save(chapter);
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<Account> optionalAccount = accountRepository.findByUsername(username);
        if (optionalAccount.isPresent()) {
            Novel novel = chapter.getVolume().getNovel();
            Optional<HistoryRead> optionalHistoryRead = historyReadRepository.findByUsername(username);
            HistoryRead historyRead = optionalHistoryRead.orElseGet(() -> historyReadRepository.save(HistoryRead.builder().account(optionalAccount.get()).historyItems(new ArrayList<>()).build()));
            HistoryItem historyItem = historyRead.getHistoryItem(novel);
            historyItem.setLastChap(chapter);
            itemRepository.save(historyItem);
        }

        return new ChapterDto(chapter);
    }


    public Object save(ChapterDto chapterDto) throws CustomException {
        Optional<Volume> optionalVolume = volumeRepository.findById(chapterDto.getVolumeId());
        if (!optionalVolume.isPresent()) {
            throw new CustomException("Volume not found!");
        }
        Chapter chapter = Chapter.builder()
                .title(chapterDto.getTitle())
                .content(chapterDto.getContent())
                .number(chapterDto.getNumber())
                .numberTitle(chapterDto.getNumberTitle())
                .isLock(chapterDto.isLock())
                .lastUpdate(new Timestamp(System.currentTimeMillis()))
                .volume(optionalVolume.get())
                .build();
        if (chapter.getTitle().isEmpty() || chapter.getContent().isEmpty() || chapter.getVolume() == null) {
            throw new NullPointerException();
        }
        Chapter saved = repository.save(chapter);
        updateNovel(optionalVolume.get().getNovel().getId(), saved);
        return new ChapterDto(saved);
    }

    public Object update(ChapterDto chapterDto) throws CustomException {
        Optional<Chapter> optionalChapter = repository.findById(chapterDto.getId());
        Optional<Volume> optionalVolume = volumeRepository.findById(chapterDto.getVolumeId());
        if (!optionalChapter.isPresent()) {
            throw new CustomException("Chapter not found!");
        }
        if (!optionalVolume.isPresent()) {
            throw new CustomException("Volume not found!");
        }
        Chapter old = optionalChapter.get();
        old.setTitle(chapterDto.getTitle());
        old.setNumber(chapterDto.getNumber());
        old.setNumberTitle(chapterDto.getNumberTitle());
        old.setContent(chapterDto.getContent());
        old.setLock(chapterDto.isLock());
        old.setLastUpdate(new Timestamp(System.currentTimeMillis()));
        old.setVolume(optionalVolume.get());
        return new ChapterDto(repository.save(old));
    }

    public boolean delete(String id) throws CustomException {
        Chapter chapter=  findChapterById(id);
        chapter.setDeleted(true);
        repository.save(chapter);
        return true;
    }

    public void updateNovel(String novelId, Chapter saved) throws CustomException {
        Optional<Novel> optionalNovel = novelRepository.findById(novelId);
        if (!optionalNovel.isPresent()) {
            throw new CustomException("Novel not Found");
        }
        Novel novel = optionalNovel.get();
        novel.setLastUpdate(new Timestamp(System.currentTimeMillis()));
        novelRepository.save(novel);
    }

    public Object getNextChapter(String id) throws CustomException {
        Chapter chapter = findChapterById(id);
        Novel novel = findNovelById(chapter.getVolume().getNovelId());
        List<Chapter> chapters = new ArrayList<>();
        for (Volume vol : novel.getVolumes()
        ) {
            chapters.addAll(vol.getChapters());
        }
        try {
            return chapters.get(chapters.indexOf(chapter) + 1);
        } catch (Exception ignored) {

        }
        throw new CustomException("Không có chap mới");
    }

    public Object getPreviousChapter(String id) throws CustomException {
        Chapter chapter = findChapterById(id);
        Novel novel = findNovelById(chapter.getVolume().getNovelId());
        List<Chapter> chapters = new ArrayList<>();
        for (Volume vol : novel.getVolumes()
        ) {
            chapters.addAll(vol.getChapters());
        }
        try {
            return chapters.get(chapters.indexOf(chapter) - 1);
        } catch (Exception ignored) {

        }
        throw new CustomException("Không có chap cũ hơn");
    }

    private Chapter findChapterById(String chapId) throws CustomException {
        Optional<Chapter> optionalChapter = repository.findById(chapId);
        if (!optionalChapter.isPresent()) {
            throw new CustomException("Chapter not found!");
        }
        return optionalChapter.get();
    }

    private Volume findVolById(String volId) throws CustomException {
        Optional<Volume> optional = volumeRepository.findById(volId);
        if (!optional.isPresent()) {
            throw new CustomException("Volume not found!");
        }
        return optional.get();
    }

    private Novel findNovelById(String novelId) throws CustomException {
        Optional<Novel> optional = novelRepository.findById(novelId);
        if (!optional.isPresent()) {
            throw new CustomException("Novel not found!");
        }
        return optional.get();
    }
}
