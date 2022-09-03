package com.example.readnovel.controller;


import com.example.readnovel.customException.CustomException;
import com.example.readnovel.entity.Role;
import com.example.readnovel.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/role")
@PreAuthorize("hasAnyAuthority('admin','mod')")
public class RoleController {
    @Autowired
    private RoleService service;
    @GetMapping
    public ResponseEntity<Object> getList(@RequestParam(defaultValue = "") String roleName){
        return ResponseEntity.ok(service.getList(roleName));
    }
    @GetMapping("detail")
    public ResponseEntity<Object> getDetail(@RequestParam(defaultValue = "") String roleId){
        Role role = service.findById(roleId);
        if (role!=null){
            return ResponseEntity.ok(role);
        }
        return ResponseEntity.notFound().build();
    }
    @PutMapping("update")
    public ResponseEntity<Object> update(@RequestBody Role role) throws CustomException {
        return ResponseEntity.ok(service.update(role));
    }
}
