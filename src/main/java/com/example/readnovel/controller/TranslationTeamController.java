package com.example.readnovel.controller;

import com.example.readnovel.customException.CustomException;
import com.example.readnovel.entity.dto.MemberDto;
import com.example.readnovel.entity.dto.TranslationTeamDto;
import com.example.readnovel.service.TranslationTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/translationTeam")
public class TranslationTeamController {
    @Autowired
    private TranslationTeamService service;

    @GetMapping
    @PreAuthorize("hasAuthority('user')")
    public ResponseEntity<Object> getList(@RequestParam(defaultValue = "") String name,
                                          @RequestParam(defaultValue = "1") int index,
                                          @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(service.findAll(name,index,size));
    }

    @GetMapping("getDetail")
    @PreAuthorize("hasAuthority('user')")
    public ResponseEntity<Object> getDetail(@RequestParam(defaultValue = "") String teamId) throws CustomException {
        return ResponseEntity.ok(service.getDetail(teamId));
    }

    @PostMapping("create")
    @PreAuthorize("hasAuthority('user')")
    public ResponseEntity<Object> createTeam(@RequestBody TranslationTeamDto translationTeam) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(translationTeam));
    }

    @PutMapping("update")
    @PreAuthorize("hasAuthority('user')")
    public ResponseEntity<Object> updateTeam(@RequestBody TranslationTeamDto translationTeam) throws CustomException {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.update(translationTeam));
    }

    @DeleteMapping("delete")
    @PreAuthorize("hasAuthority('user')")
    public ResponseEntity<Object> deleteTeam(@RequestParam(defaultValue = "") String teamId) throws CustomException {
        return ResponseEntity.ok(service.delete(teamId));
    }

    @PutMapping("addMember")
    @PreAuthorize("hasAuthority('user')")
    public ResponseEntity<Object> addMember(@RequestBody MemberDto memberDto) throws CustomException {
        return ResponseEntity.ok(service.addMember(memberDto));
    }

    @PutMapping("editMember")
    @PreAuthorize("hasAuthority('user')")
    public ResponseEntity<Object> editMember(@RequestBody MemberDto memberDto) throws CustomException {
        return ResponseEntity.ok(service.editMember(memberDto));
    }

    @DeleteMapping("removeMember")
    @PreAuthorize("hasAuthority('user')")
    public ResponseEntity<Object> removeMember(@RequestBody MemberDto memberDto) throws CustomException {
        return ResponseEntity.ok(service.removeMember(memberDto));
    }
}
