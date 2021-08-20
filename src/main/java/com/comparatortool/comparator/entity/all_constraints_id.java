package com.comparatortool.comparator.entity;

import java.io.Serializable;

public class all_constraints_id implements Serializable {
    private String owner;
    private String table_name;
    private String constraint_name;
    private String constraint_type;

    protected all_constraints_id(){

    }

    public all_constraints_id(String owner, String table_name, String constraint_name, String constraint_type) {
        this.owner = owner;
        this.table_name = table_name;
        this.constraint_name = constraint_name;
        this.constraint_type = constraint_type;
    }
}
