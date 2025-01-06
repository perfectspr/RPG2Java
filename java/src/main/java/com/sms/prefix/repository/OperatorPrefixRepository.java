package com.sms.prefix.repository;

import com.sms.prefix.model.OperatorPrefix;
import java.util.List;
import java.util.Optional;

public interface OperatorPrefixRepository {
    List<OperatorPrefix> findAll();
    
    List<OperatorPrefix> findAllByPrefix1(Integer prefix1);
    
    Optional<OperatorPrefix> findByPrefixes(Integer prefix1, Integer prefix2, Integer prefix3);
    
    void save(OperatorPrefix operatorPrefix);
    
    void delete(Integer prefix1, Integer prefix2, Integer prefix3);
    
    List<OperatorPrefix> findByPrefixesGreaterThan(Integer prefix1, Integer prefix2, Integer prefix3);
    
    List<OperatorPrefix> findByPrefixesLessThan(Integer prefix1, Integer prefix2, Integer prefix3);
} 