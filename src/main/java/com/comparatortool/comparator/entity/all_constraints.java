package com.comparatortool.comparator.entity;

import javax.persistence.*;

@Entity
@IdClass(all_constraints_id.class)
@Table(name="all_constraints")
public class all_constraints {

    @Id
    @Column(name = "owner")
    private String owner;

    @Id
    @Column(name = "table_name")
    private String table_name;

    @Id
    @Column(name="constraint_name", table = "all_constraints")
    private String constraint_name;

    @Id
    @Column(name = "constraint_type", table = "all_constraints")
    private String constraint_type;

    @Column(name = "status", table = "all_constraints")
    private String status;

    @Column(name = "r_owner", table = "all_constraints")
    private String r_owner;

    @Column(name = "r_constraint_name", table = "all_constraints")
    private String r_constraint_name;

    @Column(name = "r_column_name", table = "all_constraints")
    private String r_column_name;


    public String getR_column_name() {
        return r_column_name;
    }

    public void setR_column_name(String r_column_name) {
        this.r_column_name = r_column_name;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getConstraint_type() {
        return constraint_type;
    }

    public void setConstraint_type(String constraint_type) {
        this.constraint_type = constraint_type;
    }

    public String getConstraint_name() {
        return constraint_name;
    }

    public void setConstraint_name(String constraint_name) {
        this.constraint_name = constraint_name;
    }

    //    public String getSearch_condition() {
//        return search_condition;
//    }
//
//    public void setSearch_condition(String search_condition) {
//        this.search_condition = search_condition;
//    }

    public String getR_owner() {
        return r_owner;
    }

    public void setR_owner(String r_owner) {
        this.r_owner = r_owner;
    }

    public String getR_constraint_name() {
        return r_constraint_name;
    }

    public void setR_constraint_name(String r_constraint_name) {
        this.r_constraint_name = r_constraint_name;
    }

    @Override
    public String toString() {
        System.out.println("Inside ddl keys");
        return "DdlKeys{" +
                "owner='" + owner + '\'' +
                ", table_name='" + table_name + '\'' +
                ", status='" + status + '\'' +
                ", constraint_type='" + constraint_type + '\'' +
//                ", search_condition='" + search_condition + '\'' +
                ", r_owner='" + r_owner + '\'' +
                ", r_constraint_name='" + r_constraint_name + '\'' +
                ", r_column_name='" + r_column_name + '\'' +
                '}';
    }
}

