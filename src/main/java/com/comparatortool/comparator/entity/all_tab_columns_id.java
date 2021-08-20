package com.comparatortool.comparator.entity;

import java.io.Serializable;

public class all_tab_columns_id implements Serializable {
    private String owner;
    private String table_name;
    private String column_name;

    protected all_tab_columns_id(){

    }

    public all_tab_columns_id(String owner, String table_name, String column_name) {
        this.owner = owner;
        this.table_name = table_name;
        this.column_name = column_name;
    }
}
