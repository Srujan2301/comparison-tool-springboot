package com.comparatortool.comparator.entity;

import java.io.Serializable;

public class all_tables_id implements Serializable {
    private String owner;
    private String table_name;

    protected all_tables_id(){

    }

    public all_tables_id(String owner, String table_name) {
        this.owner = owner;
        this.table_name = table_name;
    }
}
