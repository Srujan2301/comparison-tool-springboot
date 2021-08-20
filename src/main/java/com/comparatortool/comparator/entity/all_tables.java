package com.comparatortool.comparator.entity;

import javax.persistence.*;

@Entity
@IdClass(all_tables_id.class)
@Table(name = "all_tables")
public class all_tables {
    @Id
    @Column(name = "owner")
    private String owner;

    @Id
    @Column(name = "table_name")
    private String table_name;

    @Column(name = "num_rows")
    private String num_rows;

    public String getNum_rows() {
        return num_rows;
    }

    public void setNum_rows(String num_rows) {
        this.num_rows = num_rows;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getTable_name() {
        return table_name;
    }

    public void setTable_name(String table_name) {
        this.table_name = table_name;
    }

    @Override
    public String toString() {
        return "all_tables{" +
                "owner='" + owner + '\'' +
                ", table_name='" + table_name + '\'' +
                '}';
    }
}
