package com.example.readnovel.controller;

import com.example.readnovel.Filter.NovelFilter;
import com.example.readnovel.customException.CustomException;
import com.example.readnovel.entity.dto.ChapterDto;
import com.example.readnovel.entity.dto.NovelDto;
import com.example.readnovel.entity.dto.NovelMinDto;
import com.example.readnovel.entity.dto.VolumeDto;
import com.example.readnovel.service.ChapterService;
import com.example.readnovel.service.NovelService;
import com.example.readnovel.service.VolumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/novel")
public class NovelController {
    @Autowired
    private NovelService service;
    @Autowired
    private VolumeService volumeService;
    @Autowired
    private ChapterService chapterService;

    @PostMapping
    public ResponseEntity<Object> getList(@RequestBody NovelFilter novelFilter) {
        return ResponseEntity.ok().body(service.getList(novelFilter).map(NovelMinDto::new));
    }

    @PostMapping("admin")
    @PreAuthorize("hasAnyAuthority('admin','mod')")
    public ResponseEntity<Object> adminGetList(@RequestBody NovelFilter novelFilter) {
        return ResponseEntity.ok().body(service.getList(novelFilter).map(NovelDto::new));
    }

    @GetMapping("hot")
    public ResponseEntity<Object> getHot() {
        return ResponseEntity.ok().body(service.getHot().map(NovelMinDto::new));
    }

    @GetMapping("adminHot")
    @PreAuthorize("hasAnyAuthority('admin','mod')")
    public ResponseEntity<Object> adminGetHot() {
        return ResponseEntity.ok().body(service.getHot().map(NovelDto::new));
    }

    @PostMapping("create")
    public ResponseEntity<Object> save(@RequestBody NovelDto novelDto) throws CustomException {
        return ResponseEntity.ok().body(service.save(novelDto));
    }

    @GetMapping("follow")
    @PreAuthorize("hasAnyAuthority('admin','mod','user')")
    public ResponseEntity<Object> follow(@RequestParam(defaultValue = "") String id) throws CustomException {
        return ResponseEntity.ok().body(service.followNovel(id));
    }

    @GetMapping("followList")
    @PreAuthorize("hasAnyAuthority('admin','mod','user')")
    public ResponseEntity<Object> getFollow(@RequestParam(defaultValue = "1") int index, @RequestParam(defaultValue = "20") int size) throws CustomException {
        return ResponseEntity.ok().body(service.getFollowList(index, size));
    }

    @GetMapping("detail")
    public ResponseEntity<Object> getDetail(@RequestParam(defaultValue = "") String id) throws CustomException {
        return ResponseEntity.ok().body(service.getDetail(id));
    }

    @PutMapping("update")
    public ResponseEntity<Object> update(@RequestBody NovelDto novelDto) throws CustomException {
        return ResponseEntity.ok().body(service.update(novelDto));
    }

    @DeleteMapping("delete")
    public ResponseEntity<Object> delete(@RequestParam(defaultValue = "") String id) throws CustomException {
        return ResponseEntity.ok().body(service.delete(id));
    }

    @DeleteMapping("adminDelete")
    @PreAuthorize("hasAnyAuthority('admin','mod')")
    public ResponseEntity<Object> adminDelete(@RequestParam(defaultValue = "") String id) throws CustomException {
        return ResponseEntity.ok().body(service.adminDelete(id));
    }

    //Volume
    @PostMapping("volume")
    public ResponseEntity<Object> addVol(@RequestBody VolumeDto volumeDto) throws CustomException {
        return ResponseEntity.status(HttpStatus.CREATED).body(volumeService.save(volumeDto));
    }

    @GetMapping("volumesByNovelId")
    public ResponseEntity<Object> getVolsByNovelId(@RequestParam(defaultValue = "") String novelId) throws CustomException {
        return ResponseEntity.status(HttpStatus.OK).body(volumeService.getVolsByNovelId(novelId));
    }

    @GetMapping("volume")
    public ResponseEntity<Object> getVolDetail(@RequestParam(defaultValue = "") String id) throws CustomException {
        return ResponseEntity.status(HttpStatus.OK).body(volumeService.getDetail(id));
    }

    @PutMapping("volume")
    public ResponseEntity<Object> updateVol(@RequestBody VolumeDto volumeDto) throws CustomException {
        return ResponseEntity.status(HttpStatus.OK).body(volumeService.update(volumeDto));
    }

    @DeleteMapping("volume")
    public ResponseEntity<Object> delVol(@RequestParam String id) throws CustomException {
        return ResponseEntity.status(HttpStatus.OK).body(volumeService.delete(id));
    }

    //Chapter
    @GetMapping("chapter")
    public ResponseEntity<Object> getChapterDetail(@RequestParam(defaultValue = "") String id) throws CustomException {
        return ResponseEntity.status(HttpStatus.OK).body(chapterService.findById(id));
    }

    @PostMapping("chapter")
    public ResponseEntity<Object> addChapter(@RequestBody ChapterDto chapterDto) throws CustomException {
        return ResponseEntity.status(HttpStatus.CREATED).body(chapterService.save(chapterDto));
    }

    @PutMapping("chapter")
    public ResponseEntity<Object> updateChapter(@RequestBody ChapterDto chapterDto) throws CustomException {
        return ResponseEntity.status(HttpStatus.OK).body(chapterService.update(chapterDto));
    }

    @DeleteMapping("chapter")
    public ResponseEntity<Object> delChapter(@RequestParam(defaultValue = "") String id) throws CustomException {
        return ResponseEntity.status(HttpStatus.OK).body(chapterService.delete(id));
    }

    @GetMapping("chapter/next")
    public ResponseEntity<Object> getNext(@RequestParam(defaultValue = "") String id) throws CustomException {
        return ResponseEntity.status(HttpStatus.OK).body(chapterService.getNextChapter(id));
    }
    @GetMapping("chapter/previous")
    public ResponseEntity<Object> getPrevious(@RequestParam(defaultValue = "") String id) throws CustomException {
        return ResponseEntity.status(HttpStatus.OK).body(chapterService.getPreviousChapter(id));
    }
}
