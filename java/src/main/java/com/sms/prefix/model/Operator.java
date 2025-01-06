package com.sms.prefix.model;

public class Operator {
    private String code;
    private String description;
    private Integer tableId;

    // Constructors
    public Operator() {}

    public Operator(String code, String description, Integer tableId) {
        this.code = code;
        this.description = description;
        this.tableId = tableId;
    }

    // Getters and Setters
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getTableId() {
        return tableId;
    }

    public void setTableId(Integer tableId) {
        this.tableId = tableId;
    }

    @Override
    public String toString() {
        return String.format("Operator[code='%s', description='%s', tableId=%d]",
                code, description, tableId);
    }
} 