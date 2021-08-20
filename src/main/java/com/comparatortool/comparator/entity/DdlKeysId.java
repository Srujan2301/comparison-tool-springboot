package com.comparatortool.comparator.entity;

import java.io.Serializable;

public class DdlKeysId implements Serializable {
    private String owner;
    private String table_name;
//    private String column_name;
    private String constraint_name;
    private String constraint_type;

    protected DdlKeysId(){

    }

    public DdlKeysId(String owner, String table_name, String constraint_name, String constraint_type) {
        this.owner = owner;
        this.table_name = table_name;
//        this.column_name = column_name;
        this.constraint_name = constraint_name;
        this.constraint_type = constraint_type;
    }
}
