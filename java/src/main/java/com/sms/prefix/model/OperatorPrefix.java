package com.sms.prefix.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class OperatorPrefix {
    private Integer prefix1;
    private Integer prefix2;
    private Integer prefix3;
    private String operatorCode;
    private LocalDate createdDate;
    private LocalTime createdTime;
    private String createdUser;
    private LocalDate modifiedDate;
    private LocalTime modifiedTime;
    private String modifiedUser;

    // Constructors
    public OperatorPrefix() {}

    public OperatorPrefix(Integer prefix1, Integer prefix2, Integer prefix3, String operatorCode) {
        this.prefix1 = prefix1;
        this.prefix2 = prefix2;
        this.prefix3 = prefix3;
        this.operatorCode = operatorCode;
    }

    // Getters and Setters
    public Integer getPrefix1() {
        return prefix1;
    }

    public void setPrefix1(Integer prefix1) {
        this.prefix1 = prefix1;
    }

    public Integer getPrefix2() {
        return prefix2;
    }

    public void setPrefix2(Integer prefix2) {
        this.prefix2 = prefix2;
    }

    public Integer getPrefix3() {
        return prefix3;
    }

    public void setPrefix3(Integer prefix3) {
        this.prefix3 = prefix3;
    }

    public String getOperatorCode() {
        return operatorCode;
    }

    public void setOperatorCode(String operatorCode) {
        this.operatorCode = operatorCode;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public LocalTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalTime createdTime) {
        this.createdTime = createdTime;
    }

    public String getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }

    public LocalDate getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(LocalDate modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public LocalTime getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(LocalTime modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public String getModifiedUser() {
        return modifiedUser;
    }

    public void setModifiedUser(String modifiedUser) {
        this.modifiedUser = modifiedUser;
    }

    @Override
    public String toString() {
        return String.format("OperatorPrefix[prefix1=%d, prefix2=%d, prefix3=%d, operatorCode='%s']",
                prefix1, prefix2, prefix3, operatorCode);
    }
} 