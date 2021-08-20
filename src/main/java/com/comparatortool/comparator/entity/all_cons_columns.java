package com.comparatortool.comparator.entity;

import javax.persistence.*;

@Entity
@Table(name="all_cons_columns")
@IdClass(all_cons_columns_id.class)
public class all_cons_columns {

    @Id
    @Column(name = "owner")
    private String owner;

    @Id
    @Column(name = "table_name")
    private String table_name;

    @Column(name = "column_name", table = "all_cons_columns")
    private String column_name;

    @Column(name = "position", table = "all_cons_columns")
    private String position;

    @Column(name = "constraint_name", table = "all_cons_columns")
    private String constraint_name;

    public String getConstraint_name() {
        return constraint_name;
    }

    public void setConstraint_name(String constraint_name) {
        this.constraint_name = constraint_name;
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

    public String getColumn_name() {
        return column_name;
    }

    public void setColumn_name(String column_name) {
        this.column_name = column_name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        System.out.println("Inside ddl keys");
        return "DdlKeys{" +
                "owner='" + owner + '\'' +
                ", table_name='" + table_name + '\'' +
                ", column_name='" + column_name + '\'' +
                ", position='" + position + '\'' +
                ", constraint_name= '" + constraint_name + '\'' +
                '}';
    }
}

