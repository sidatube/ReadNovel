package com.example.readnovel.controller;

import com.example.readnovel.Filter.DonateFilter;
import com.example.readnovel.customException.CustomException;
import com.example.readnovel.entity.dto.DonateDto;
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
    public ResponseEntity<Object> getListByAdmin(@RequestBody DonateFilter filter) {
        return ResponseEntity.ok().body(service.getList(filter));
    }
    @PostMapping("create")
    @PreAuthorize("hasAnyAuthority('admin','mod','user')")
    public ResponseEntity<Object> create(@RequestBody DonateDto dto) {
        return ResponseEntity.ok().body(service.create(dto));
    }
    @DeleteMapping("delete")
    @PreAuthorize("hasAnyAuthority('admin','mod')")
    public ResponseEntity<Object> delete(@RequestParam(defaultValue = "")String  id) throws CustomException {
        return ResponseEntity.ok().body(service.delete(id));
    }
    @PostMapping("getHistory")
    @PreAuthorize("hasAnyAuthority('admin','mod','user')")
    public ResponseEntity<Object> getHistory(@RequestBody DonateFilter filter) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        filter.setUsername(username);
        return ResponseEntity.ok().body(service.getList(filter));
    }
    @GetMapping("getDetailAdmin")
    @PreAuthorize("hasAnyAuthority('admin','mod')")
    public ResponseEntity<Object> getDetail(@RequestParam(defaultValue = "")String  id) throws CustomException {
        return ResponseEntity.ok().body(service.getDetail(id));
    }
    @GetMapping("getStatistical")
    @PreAuthorize("hasAnyAuthority('admin','mod')")
    public ResponseEntity<Object> getStatistical() throws CustomException {
        return ResponseEntity.ok().body(service.getStatistical());
    }
    @PostMapping("getDonateInMonth")
    @PreAuthorize("hasAnyAuthority('admin','mod')")
    public ResponseEntity<Object> getDonateInMonth(@RequestBody DonateFilter filter) throws CustomException {
        return ResponseEntity.ok().body(service.getListDonateInMount(filter));
    }
}
