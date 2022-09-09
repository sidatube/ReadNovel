package com.example.readnovel.controller;

import com.example.readnovel.customException.CustomException;
import com.example.readnovel.entity.Type;
import com.example.readnovel.entity.dto.TypeDto;
import com.example.readnovel.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/type")
@PreAuthorize("hasAnyAuthority('admin','mod')")
public class TypeController {
    @Autowired
    private TypeService service;

    @GetMapping
    public ResponseEntity<Object> getAll(@RequestParam(defaultValue = "") String name) {
        return ResponseEntity.ok(service.findAll(name));
    }

    @PostMapping("create")
    public ResponseEntity<Object> addType(@RequestBody TypeDto type) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.addType(type));
    }

    @GetMapping("detail")
    public ResponseEntity<Object> getDetail(@RequestParam(defaultValue = "") String id) throws CustomException {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @PutMapping("update")
    public ResponseEntity<Object> update(@RequestBody TypeDto update) throws CustomException {
        return ResponseEntity.ok().body(service.update(update));
    }

    @DeleteMapping("delete")
    public ResponseEntity<Object> delete(@RequestParam(defaultValue = "") String id) throws CustomException {
        return ResponseEntity.ok().body(service.delete(id));
    }

}
