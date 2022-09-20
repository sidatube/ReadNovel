package com.example.readnovel.controller;

import com.example.readnovel.Filter.DonateFilter;
import com.example.readnovel.customException.CustomException;
import com.example.readnovel.service.DonateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/donate")
public class DonateController {
    @Autowired
    DonateService service;

    @PostMapping("getListAdmin")
    @PreAuthorize("hasAnyAuthority('admin','mod')")
    public ResponseEntity<Object> getListByAdmin(DonateFilter filter) {
        return ResponseEntity.ok().body(service.getList(filter));
    }
    @PostMapping("getHistory")
    @PreAuthorize("hasAnyAuthority('admin','mod','user')")
    public ResponseEntity<Object> getHistory(DonateFilter filter) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        filter.setUsername(username);
        return ResponseEntity.ok().body(service.getList(filter));
    }
    @GetMapping("getDetailAdmin")
    @PreAuthorize("hasAnyAuthority('admin','mod')")
    public ResponseEntity<Object> getDetail(@RequestParam(defaultValue = "")String  id) throws CustomException {
        return ResponseEntity.ok().body(service.getDetail(id));
    }
}
