package com.example.readnovel.controller;

import com.example.readnovel.customException.CustomException;
import com.example.readnovel.service.HistoryReadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/history")
public class HistoryController {
    @Autowired
    private HistoryReadService service;
    @GetMapping
    public ResponseEntity<Object> getHistory() throws CustomException {
        return ResponseEntity.ok().body(service.getHistory());
    }
    @DeleteMapping("remove")
    public ResponseEntity<Object> deleteHistory(@RequestParam(defaultValue = "") String itemId) throws CustomException {
        service.removeItem(itemId);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("removeAll")
    public ResponseEntity<Object> deleteAll() throws CustomException {
        service.removeAll();
        return ResponseEntity.ok().build();
    }
}
