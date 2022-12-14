package com.example.readnovel.controller;

import com.example.readnovel.Filter.CommentFilter;
import com.example.readnovel.customException.CustomException;
import com.example.readnovel.entity.dto.CommentDto;
import com.example.readnovel.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/comment")
public class CommentController {
    @Autowired
    private CommentService service;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('admin','mod')")
    public ResponseEntity<Object> getList(@RequestBody CommentFilter commentFilter) {
        return ResponseEntity.ok().body(service.getList(commentFilter));
    }
    @PostMapping("getAll")
    public ResponseEntity<Object> getListCMTNoParent(@RequestBody CommentFilter commentFilter) throws CustomException {
        return ResponseEntity.ok().body(service.getListByAreaId(commentFilter));
    }

    @GetMapping("detail")
    @PreAuthorize("hasAnyAuthority('admin','mod','user')")
    public ResponseEntity<Object> detail(@RequestParam String id) throws CustomException {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.getDetail(id));
    }

    @PostMapping("write")
    @PreAuthorize("hasAnyAuthority('admin','mod','user')")
    public ResponseEntity<Object> write(@RequestBody CommentDto dto) throws CustomException {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(dto));
    }

    @PutMapping("update")
    @PreAuthorize("hasAnyAuthority('admin','mod','user')")
    public ResponseEntity<Object> update(@RequestBody CommentDto dto) throws CustomException {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.update(dto));
    }

    @DeleteMapping("delete")
    @PreAuthorize("hasAnyAuthority('admin','mod','user')")
    public ResponseEntity<Object> delete(@RequestParam String id) throws CustomException {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
