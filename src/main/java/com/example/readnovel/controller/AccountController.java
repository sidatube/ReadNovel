package com.example.readnovel.controller;

import com.example.readnovel.Filter.AccountFilter;
import com.example.readnovel.customException.CustomException;
import com.example.readnovel.entity.Account;
import com.example.readnovel.entity.dto.AccountDTO;
import com.example.readnovel.entity.dto.ChangePassword;
import com.example.readnovel.entity.dto.ForgotPassword;
import com.example.readnovel.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('admin','mod')")
    public ResponseEntity<Object> getListAccount(@RequestBody AccountFilter filter) {
        return ResponseEntity.ok(accountService.getList(filter));
    }
    @GetMapping("detail")
    @PreAuthorize("hasAnyAuthority('admin','mod')")
    public ResponseEntity<Object> getAccountDetail(@RequestParam(defaultValue = "") String  username) throws CustomException {
        return ResponseEntity.ok(accountService.findByUsername(username));
    }
    @GetMapping("getDetail")
    @PreAuthorize("hasAuthority('user')")
    public ResponseEntity<Object> getMyDetail() throws CustomException {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity.ok(accountService.findByUsername(username));
    }
    @PostMapping("forgotPassword")
    public ResponseEntity<Object> forgotPassword(@RequestBody ForgotPassword forgotPassword) {
        return ResponseEntity.ok(accountService.forgotPassword(forgotPassword));
    }

    @PutMapping("setNewPass")
    public ResponseEntity<Object> setNewPass(@RequestBody ChangePassword changePassword) throws CustomException {
        return ResponseEntity.ok(accountService.setNewPass(changePassword));
    }

    @PreAuthorize("hasAuthority('user')")
    @PutMapping("changePassword")
    public ResponseEntity<Object> changePassword(@RequestBody ChangePassword changePassword) throws CustomException {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity.ok(accountService.changePassword(username, changePassword));
    }
    @PreAuthorize("hasAuthority('user')")
    @PutMapping("changeInfo")
    public ResponseEntity<Object> changeInfo(@RequestBody AccountDTO accountDTO) throws CustomException {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity.ok(accountService.changeInfo(username, accountDTO));
    }

    @PreAuthorize("hasAnyAuthority('admin','mod')")
    @PutMapping("adminChangePassword")
    public ResponseEntity<Object> adminChangePassword(@RequestBody ChangePassword changePassword) throws CustomException {
        return ResponseEntity.ok(accountService.adminChangePassword(changePassword));
    }

    @PutMapping("adminChangeInfo")
    @PreAuthorize("hasAnyAuthority('admin','mod')")
    public ResponseEntity<Object> adminChangeInfo(@RequestBody AccountDTO accountDTO) throws CustomException {
        return ResponseEntity.ok(accountService.adminChangeInfo(accountDTO));
    }
    @DeleteMapping("adminDelete")
    @PreAuthorize("hasAnyAuthority('admin','mod')")
    public ResponseEntity<Object> adminDelete(@RequestParam(defaultValue = "") String id) throws CustomException {
        return ResponseEntity.ok(accountService.deleteAccount(id));
    }
}
