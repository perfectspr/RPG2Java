package com.sms.prefix.service;

import com.sms.prefix.model.OperatorPrefix;
import com.sms.prefix.model.Operator;
import com.sms.prefix.repository.OperatorPrefixRepository;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public class OperatorPrefixService {
    private final OperatorPrefixRepository repository;
    private static final int PAGE_SIZE = 13; // Same as SFLPAG in RPG

    public OperatorPrefixService(OperatorPrefixRepository repository) {
        this.repository = repository;
    }

    public List<OperatorPrefix> findAll() {
        return repository.findAll();
    }

    public List<OperatorPrefix> getNextPage(Integer prefix1, Integer prefix2, Integer prefix3) {
        return repository.findByPrefixesGreaterThan(prefix1, prefix2, prefix3);
    }

    public List<OperatorPrefix> getPreviousPage(Integer prefix1, Integer prefix2, Integer prefix3) {
        return repository.findByPrefixesLessThan(prefix1, prefix2, prefix3);
    }

    public Optional<OperatorPrefix> findByPrefixes(Integer prefix1, Integer prefix2, Integer prefix3) {
        return repository.findByPrefixes(prefix1, prefix2, prefix3);
    }

    public void create(OperatorPrefix prefix, String username) {
        prefix.setCreatedDate(LocalDate.now());
        prefix.setCreatedTime(LocalTime.now());
        prefix.setCreatedUser(username);
        prefix.setModifiedDate(LocalDate.now());
        prefix.setModifiedTime(LocalTime.now());
        prefix.setModifiedUser(username);
        repository.save(prefix);
    }

    public void update(OperatorPrefix prefix, String username) {
        prefix.setModifiedDate(LocalDate.now());
        prefix.setModifiedTime(LocalTime.now());
        prefix.setModifiedUser(username);
        repository.save(prefix);
    }

    public void delete(Integer prefix1, Integer prefix2, Integer prefix3) {
        repository.delete(prefix1, prefix2, prefix3);
    }

    public List<OperatorPrefix> findByPosition(Integer prefix1) {
        return repository.findAllByPrefix1(prefix1);
    }
} 