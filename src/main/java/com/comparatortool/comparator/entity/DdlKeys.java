package com.comparatortool.comparator.entity;

import javax.persistence.*;

@Entity
@Table(name="all_constraints")
@SecondaryTable(name = "all_cons_columns")
@IdClass(DdlKeysId.class)
@PrimaryKeyJoinColumns({
        @PrimaryKeyJoinColumn(name="owner",
                referencedColumnName="owner"),
//        @PrimaryKeyJoinColumn(name="constraint_type",
//                referencedColumnName="constraint_type")
//        @PrimaryKeyJoinColumn(name="table_name", referencedColumnName = "table_name")
})
@NamedNativeQuery(name="keys", query = "SELECT cons.owner, cons.table_name, cons.constraint_name, cols.column_name, cols.position, cons.status, cons.constraint_type,  cons.r_owner, cons.r_constraint_name, \n" +
        "\t\t\t\t case \n" +
        "\t\t\t\t when cons.Constraint_type = 'R' \n" +
        "\t\t\t\t then (select  c.column_name from all_cons_columns c where c.constraint_name = cons.R_Constraint_Name) \n" +
        "\t\t\t\t end as r_column_name \n" +
        "\t\t\t\t FROM all_constraints cons join all_cons_columns cols \n" +
        "\t\t\t\t On cons.constraint_name = cols.constraint_name \n" +
        "\t\t\t\t AND cons.owner = cols.owner \n" +
        "\t\t\t\t WHERE cols.table_name = ?2 and cols.owner = ?1 \n" +
        "\t\t\t\t ORDER BY cols.column_name", resultClass = DdlKeys.class)
public class DdlKeys {

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

    @Column(name = "column_name", table = "all_cons_columns")
    private String column_name;

    @Column(name = "position", table = "all_cons_columns")
    private String position;



//    @Column(name = "search_condition", table = "all_cons_columns")
//    private String search_condition;


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
                ", column_name='" + column_name + '\'' +
                ", position='" + position + '\'' +
                ", status='" + status + '\'' +
                ", constraint_type='" + constraint_type + '\'' +
//                ", search_condition='" + search_condition + '\'' +
                ", r_owner='" + r_owner + '\'' +
                ", r_constraint_name='" + r_constraint_name + '\'' +
                ", r_column_name='" + r_column_name + '\'' +
                '}';
    }
}

