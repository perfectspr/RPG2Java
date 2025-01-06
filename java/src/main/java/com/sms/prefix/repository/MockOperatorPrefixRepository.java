package com.sms.prefix.repository;

import com.sms.prefix.model.OperatorPrefix;
import java.util.*;
import java.util.stream.Collectors;

public class MockOperatorPrefixRepository implements OperatorPrefixRepository {
    private final List<OperatorPrefix> database = new ArrayList<>();
    
    public MockOperatorPrefixRepository() {
        // Initialize with some mock data
        database.add(new OperatorPrefix(1, 1, 1, "MOV01"));
        database.add(new OperatorPrefix(1, 2, 1, "MOV02"));
        database.add(new OperatorPrefix(2, 1, 1, "MOV03"));
        database.add(new OperatorPrefix(2, 2, 1, "MOV04"));
        database.add(new OperatorPrefix(3, 1, 1, "MOV05"));
        database.add(new OperatorPrefix(3, 2, 1, "MOV06"));
        database.add(new OperatorPrefix(4, 1, 1, "MOV07"));
        database.add(new OperatorPrefix(4, 2, 1, "MOV08"));
        database.add(new OperatorPrefix(5, 1, 1, "MOV09"));
        database.add(new OperatorPrefix(5, 2, 1, "MOV10"));
        database.add(new OperatorPrefix(6, 1, 1, "MOV11"));
        database.add(new OperatorPrefix(6, 2, 1, "MOV12"));
        database.add(new OperatorPrefix(7, 1, 1, "MOV13"));
        database.add(new OperatorPrefix(7, 2, 1, "MOV14"));
        database.add(new OperatorPrefix(8, 1, 1, "MOV15"));
    }

    @Override
    public List<OperatorPrefix> findAll() {
        return new ArrayList<>(database);
    }

    @Override
    public List<OperatorPrefix> findAllByPrefix1(Integer prefix1) {
        return database.stream()
                .filter(p -> Objects.equals(p.getPrefix1(), prefix1))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<OperatorPrefix> findByPrefixes(Integer prefix1, Integer prefix2, Integer prefix3) {
        return database.stream()
                .filter(p -> Objects.equals(p.getPrefix1(), prefix1)
                        && Objects.equals(p.getPrefix2(), prefix2)
                        && Objects.equals(p.getPrefix3(), prefix3))
                .findFirst();
    }

    @Override
    public void save(OperatorPrefix operatorPrefix) {
        delete(operatorPrefix.getPrefix1(), operatorPrefix.getPrefix2(), operatorPrefix.getPrefix3());
        database.add(operatorPrefix);
        // Sort the database to maintain order
        database.sort(Comparator
                .comparing(OperatorPrefix::getPrefix1)
                .thenComparing(OperatorPrefix::getPrefix2)
                .thenComparing(OperatorPrefix::getPrefix3));
    }

    @Override
    public void delete(Integer prefix1, Integer prefix2, Integer prefix3) {
        database.removeIf(p -> 
            Objects.equals(p.getPrefix1(), prefix1) &&
            Objects.equals(p.getPrefix2(), prefix2) &&
            Objects.equals(p.getPrefix3(), prefix3)
        );
    }

    @Override
    public List<OperatorPrefix> findByPrefixesGreaterThan(Integer prefix1, Integer prefix2, Integer prefix3) {
        return database.stream()
                .filter(p -> {
                    int compare = comparePrefix(p, prefix1, prefix2, prefix3);
                    return compare > 0;
                })
                .limit(13)
                .collect(Collectors.toList());
    }

    @Override
    public List<OperatorPrefix> findByPrefixesLessThan(Integer prefix1, Integer prefix2, Integer prefix3) {
        List<OperatorPrefix> result = database.stream()
                .filter(p -> {
                    int compare = comparePrefix(p, prefix1, prefix2, prefix3);
                    return compare < 0;
                })
                .collect(Collectors.toList());
        
        // Return last 13 records
        if (result.size() > 13) {
            return result.subList(result.size() - 13, result.size());
        }
        return result;
    }

    private int comparePrefix(OperatorPrefix p, Integer prefix1, Integer prefix2, Integer prefix3) {
        int compare = p.getPrefix1().compareTo(prefix1);
        if (compare != 0) return compare;
        
        compare = p.getPrefix2().compareTo(prefix2);
        if (compare != 0) return compare;
        
        return p.getPrefix3().compareTo(prefix3);
    }
} 