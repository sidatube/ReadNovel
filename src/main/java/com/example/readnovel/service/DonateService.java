package com.example.readnovel.service;

import com.example.readnovel.Filter.DonateFilter;
import com.example.readnovel.constant.SearchCriteriaOperator;
import com.example.readnovel.criteriaFilter.DonateSpecification;
import com.example.readnovel.criteriaFilter.SearchCriteria;
import com.example.readnovel.customException.CustomException;
import com.example.readnovel.entity.Account;
import com.example.readnovel.entity.DonateHistory;
import com.example.readnovel.entity.dto.DonateDto;
import com.example.readnovel.entity.group.StatisticalItem;
import com.example.readnovel.repository.AccountRepository;
import com.example.readnovel.repository.ChapterRepository;
import com.example.readnovel.repository.DonateRepository;
import com.example.readnovel.repository.NovelRepository;
import com.example.readnovel.util.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Optional;

@Service
public class DonateService {
    @Autowired
    DonateRepository repository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    NovelRepository novelRepository;
    @Autowired
    ChapterRepository chapterRepository;


    private Account findByUsername(String username) throws CustomException {
        Optional<Account> optionalAccount = accountRepository.findByUsername(username);
        if (!optionalAccount.isPresent()) {
            StringHelper.customException("Not Found Account");
        }
        return optionalAccount.get();
    }

    public Object getDetail(String id) throws CustomException {
        Optional<DonateHistory> opt = repository.findById(id);
        if (!opt.isPresent())
            StringHelper.customException("Not Found donate Detail");
        return new DonateDto(opt.get());
    }

    public Object getList(DonateFilter donateFilter) {
        Specification<DonateHistory> specification = Specification.where(null);
        DonateSpecification notDelete = new DonateSpecification(new SearchCriteria("isDeleted", SearchCriteriaOperator.Equals, false));
        specification = specification.and(notDelete);
        if (!donateFilter.getCode().isEmpty()) {
            DonateSpecification code = new DonateSpecification(new SearchCriteria("code", SearchCriteriaOperator.Equals, donateFilter.getCode()));
            specification = specification.and(code);
        }
        if (!donateFilter.getUsername().isEmpty()) {
            DonateSpecification username = new DonateSpecification(new SearchCriteria("username", SearchCriteriaOperator.Equals, donateFilter.getUsername()));
            specification = specification.and(username);
        }
        Pageable pageable = PageRequest.of(donateFilter.getIndex() - 1, donateFilter.getSize(), Sort.by("createdAt").descending());
        Page<DonateHistory> page = repository.findAll(specification, pageable);
        return page.map(DonateDto::new);
    }

    public Object save(DonateDto donateDto) throws CustomException {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Account account = findByUsername(username);
        DonateHistory donate = DonateHistory.builder()
                .account(account)
                .amount(donateDto.getAmount())
                .username(username)
                .code(donateDto.getCode())
                .build();
        return new DonateDto(repository.save(donate));
    }

    public Object create(DonateDto dto) {
        DonateHistory donateHistory = DonateHistory.builder()
                .code(dto.getCode())
                .username(SecurityContextHolder.getContext().getAuthentication().getName())
                .amount(dto.getAmount())
                .build();
        return new DonateDto(repository.save(donateHistory));
    }

    public boolean delete(String id) throws CustomException {
        Optional<DonateHistory> optional = repository.findById(id);
        if (!optional.isPresent()){
            throw new CustomException("Error!");
        }
        DonateHistory deleted = optional.get();
        deleted.setDeleted(true);
        repository.save(deleted);
        return true;
    }

    public Object getStatistical() {
        Date ts = new java.sql.Date(System.currentTimeMillis());
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(ts);
        StatisticalItem data = new StatisticalItem();
        data.setDonateInMonth(repository.findDonateCount(calendar.get(Calendar.YEAR)));
        data.setNovelCount(novelRepository.count());
        data.setUserCount(accountRepository.count());
        data.setChapterCount(chapterRepository.count());
        return data ;
    }
    public Object getListDonateInMount(DonateFilter filter){
        Pageable pageable = PageRequest.of(filter.getIndex()-1, filter.getSize(),Sort.by("created_at").descending());
        Date ts = new java.sql.Date(System.currentTimeMillis());
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(ts);
        int month = calendar.get(Calendar.MONTH) +1;
        if  (filter.getMonth()>0&&filter.getMonth()<=month){
            month = filter.getMonth();
        }
        return repository.findDonateListByMonth(month, calendar.get(Calendar.YEAR),pageable).map(DonateDto::new);
    }

}
