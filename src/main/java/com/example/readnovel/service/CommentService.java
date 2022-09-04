package com.example.readnovel.service;

import com.example.readnovel.Filter.CommentFilter;
import com.example.readnovel.constant.SearchCriteriaOperator;
import com.example.readnovel.criteriaFilter.CommentSpecification;
import com.example.readnovel.criteriaFilter.NovelSpecification;
import com.example.readnovel.criteriaFilter.SearchCriteria;
import com.example.readnovel.customException.CustomException;
import com.example.readnovel.entity.*;
import com.example.readnovel.entity.dto.CommentDto;
import com.example.readnovel.entity.dto.NovelDto;
import com.example.readnovel.repository.*;
import com.example.readnovel.util.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    @Autowired
    private CommentRepository repository;
    @Autowired
    private NovelRepository novelRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ChapterRepository chapterRepository;
    @Autowired
    private AccountRepository accountRepository;

    public CommentDto save(CommentDto dto) throws CustomException {
        if (dto.getContent().isEmpty()) {
            throw new CustomException("Content is empty!");
        }
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<Account> optionalAccount = accountRepository.findByUsername(username);
        if (!optionalAccount.isPresent())
            throw  new CustomException("Account not exit!");
        Comment comment = Comment.builder().account(optionalAccount.get()).build();
        setValue(comment, dto);

        return new CommentDto(repository.save(comment));
    }

    private Comment findById(String id) throws CustomException {
        Optional<Comment> opt = repository.findById(id);
        if (!opt.isPresent()) {
            throw new CustomException("CMT is not exist");
        }
        return opt.get();
    }
    public CommentDto getDetail(String id) throws CustomException {
        return new CommentDto(findById(id));
    }
    public CommentDto update(CommentDto dto) throws CustomException {
        Comment old = findById(dto.getId());
       setValue(old,dto);
        return new CommentDto(repository.save(old));
    }

    public void delete(String id) throws CustomException {
        Comment comment = findById(id);
        if (comment.isDeleted())
            throw new CustomException("CMT had been delete!");
        comment.setDeleted(true);
        repository.save(comment);

    }

    private void setValue(Comment cmt, CommentDto dto) throws CustomException {
        cmt.setContent(dto.getContent());
        cmt.setDeleted(dto.isDeleted());
        if (!dto.getParentId().isEmpty()) {
            Comment parent = findById(dto.getParentId());
            cmt.setParent(parent);
        }
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!cmt.getAccount().getUsername().equalsIgnoreCase(username)){
            throw new CustomException("User is not writer!");
        }
        switch (dto.getAreaEnum()) {
            case POST:
                Optional<Post> postOptional = postRepository.findById(dto.getAreaId());
                if (!postOptional.isPresent()) {
                    throw new CustomException("Post is not exist!");
                }
                cmt.setPost(postOptional.get());
            case NOVEL:
                Optional<Novel> novelOptional = novelRepository.findById(dto.getAreaId());
                if (!novelOptional.isPresent()) {
                    throw new CustomException("Novel is not exist!");
                }
                cmt.setNovel(novelOptional.get());
            case CHAPTER:
                Optional<Chapter> chapterOptional = chapterRepository.findById(dto.getAreaId());
                if (!chapterOptional.isPresent()) {
                    throw new CustomException("Chapter is not exist!");
                }
                cmt.setChapter(chapterOptional.get());
        }
    }

    public Object getList(CommentFilter commentFilter) {
        Specification<Comment> specification = Specification.where(null);
        if (!(commentFilter.getUsername() == null || commentFilter.getUsername().isEmpty())) {
            CommentSpecification nameFilter = new CommentSpecification(new SearchCriteria("createdBy", SearchCriteriaOperator.Like, commentFilter.getUsername()));
            specification = specification.and(nameFilter);
        }
        if (!(commentFilter.getContent() == null || commentFilter.getContent().isEmpty())) {
            CommentSpecification nameFilter = new CommentSpecification(new SearchCriteria("content", SearchCriteriaOperator.Like, commentFilter.getContent()));
            specification = specification.and(nameFilter);
        }
        switch (commentFilter.getAreaEnum()) {
            case POST:
                CommentSpecification postFilter = new CommentSpecification(new SearchCriteria("post", SearchCriteriaOperator.Join, null));
                specification = specification.and(postFilter);
                break;
            case CHAPTER:
                CommentSpecification chapter = new CommentSpecification(new SearchCriteria("chapter", SearchCriteriaOperator.Join, null));
                specification = specification.and(chapter);
                break;
            case NOVEL:
                CommentSpecification novel = new CommentSpecification(new SearchCriteria("novel", SearchCriteriaOperator.Join, null));
                specification = specification.and(novel);
                break;
            default:
                break;

        }
        Pageable pageable = PageRequest.of(commentFilter.getIndex() - 1, commentFilter.getSize());
        Page<Comment> comments = repository.findAll(specification, pageable);
        return comments.map(CommentDto::new);
    }
}
