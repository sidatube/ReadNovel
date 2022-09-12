package com.example.readnovel.service;

import com.example.readnovel.customException.CustomException;
import com.example.readnovel.entity.Account;
import com.example.readnovel.entity.HistoryItem;
import com.example.readnovel.entity.HistoryRead;
import com.example.readnovel.entity.dto.HistoryItemDto;
import com.example.readnovel.repository.AccountRepository;
import com.example.readnovel.repository.HistoryItemRepository;
import com.example.readnovel.repository.HistoryReadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HistoryReadService {
    @Autowired
    private HistoryReadRepository repository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private HistoryItemRepository itemRepository;

    public Object getHistory() throws CustomException {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<Account> optionalAccount = accountRepository.findByUsername(username);
        if (!optionalAccount.isPresent()){
            throw new CustomException("Account not exist!");
        }
        Account account = optionalAccount.get();
        Optional<HistoryRead> optional = repository.findByUsername(username);
        if (!optional.isPresent()){
            HistoryRead historyRead = repository.save(HistoryRead.builder().account(account).historyItems(new ArrayList<>()).build());

            return historyRead.getHistoryItems().stream().map(HistoryItemDto::new).collect(Collectors.toList());
        }
        return optional.get().getHistoryItems().stream().map(HistoryItemDto::new).collect(Collectors.toList());
    }

    public boolean removeItem(String itemId) {
       itemRepository.deleteById(itemId);
       return true;
    }

    public boolean removeAll() throws CustomException {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<HistoryRead> optional = repository.findByUsername(username);
        if (!optional.isPresent()) {
            throw new CustomException("History not found!");
        }
        HistoryRead historyRead = optional.get();
        itemRepository.deleteAll(historyRead.getHistoryItems());
        return true;

    }
}
