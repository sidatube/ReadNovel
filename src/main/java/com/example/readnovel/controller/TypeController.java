package com.example.readnovel.controller;

import com.example.readnovel.Filter.TypeFilter;
import com.example.readnovel.customException.CustomException;
import com.example.readnovel.entity.dto.TypeDto;
import com.example.readnovel.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/type")
public class TypeController {
    @Autowired
    private TypeService service;

    @GetMapping
    public ResponseEntity<Object> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping("admin")
    @PreAuthorize("hasAnyAuthority('admin','mod')")
    public ResponseEntity<Object> adminGetAll(@RequestBody TypeFilter filter) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.findAll(filter));
    }

    @PostMapping("create")
    @PreAuthorize("hasAnyAuthority('admin','mod')")

    public ResponseEntity<Object> addType(@RequestBody TypeDto type) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.addType(type));
    }

    @GetMapping("detail")
    public ResponseEntity<Object> getDetail(@RequestParam(defaultValue = "") String id) throws CustomException {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @PutMapping("update")
    @PreAuthorize("hasAnyAuthority('admin','mod')")
    public ResponseEntity<Object> update(@RequestBody TypeDto update) throws CustomException {
        return ResponseEntity.ok().body(service.update(update));
    }

    @DeleteMapping("delete")
    @PreAuthorize("hasAnyAuthority('admin','mod')")
    public ResponseEntity<Object> delete(@RequestParam(defaultValue = "") String id) throws CustomException {
        return ResponseEntity.ok().body(service.delete(id));
    }

}
