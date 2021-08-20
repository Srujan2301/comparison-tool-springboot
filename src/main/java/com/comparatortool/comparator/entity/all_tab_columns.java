package com.comparatortool.comparator.entity;

import javax.persistence.*;

@Entity
@IdClass(all_tab_columns_id.class)
@Table(name="all_tab_columns")
@NamedNativeQuery(name ="ddlDiffColumns", query = "select owner,table_name,column_name,column_id,data_type,data_length, data_precision, data_scale, nullable  \n" +
        "            from all_tab_columns a where  \n" +
        "            (a.column_name, a.data_type, a.data_length, a.data_precision, a.data_scale, a.nullable ) not in \n" +
        "            (select column_name, data_type, data_length, data_precision, data_scale, nullable from all_tab_columns b \n" +
        "            where b.table_name = ?1  and b.owner = ?2) and a.table_name = ?3 and a.owner = ?4 \n" +
        "\n" +
        "            union all \n" +
        "\n" +
        "            select owner,table_name,column_name,column_id,data_type,data_length, data_precision, data_scale, nullable \n" +
        "            from all_tab_columns a where \n" +
        "            (a.column_name, a.data_type, a.data_length, a.data_precision, a.data_scale, a.nullable ) not in \n" +
        "            (select column_name, data_type, data_length, data_precision, data_scale, nullable from all_tab_columns b \n" +
        "            where b.table_name = ?3 and b.owner = ?4) and a.table_name = ?1 and a.owner = ?2 \n" +
        "            Order by column_name, owner, table_name", resultClass = all_tab_columns.class)
public class all_tab_columns {

    @Id
    @Column(name = "owner")
    private String owner;

    @Id
    @Column(name = "table_name")
    private String table_name;

    @Id
    @Column(name = "column_name")
    private String column_name;

    @Column(name ="column_id")
    private String column_id;

    @Column(name = "data_type")
    private String data_type;

    @Column(name = "data_length")
    private String data_length;

    @Column(name = "data_precision")
    private String data_precision;

    @Column(name = "data_scale")
    private String data_scale;

    @Column(name = "nullable")
    private String nullable;

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

    public String getColumn_id() {
        return column_id;
    }

    public void setColumn_id(String column_id) {
        this.column_id = column_id;
    }

    public String getData_type() {
        return data_type;
    }

    public void setData_type(String data_type) {
        this.data_type = data_type;
    }

    public String getData_length() {
        return data_length;
    }

    public void setData_length(String data_length) {
        this.data_length = data_length;
    }

    public String getData_precision() {
        return data_precision;
    }

    public void setData_precision(String data_precision) {
        this.data_precision = data_precision;
    }

    public String getData_scale() {
        return data_scale;
    }

    public void setData_scale(String data_scale) {
        this.data_scale = data_scale;
    }

    public String getNullable() {
        return nullable;
    }

    public void setNullable(String nullable) {
        this.nullable = nullable;
    }

    @Override
    public String toString() {
        return "all_tab_columns{" +
                "owner='" + owner + '\'' +
                ", table_name='" + table_name + '\'' +
                ", column_name='" + column_name + '\'' +
                ", column_id='" + column_id + '\'' +
                ", data_type='" + data_type + '\'' +
                ", data_length='" + data_length + '\'' +
                ", data_precision='" + data_precision + '\'' +
                ", data_scale='" + data_scale + '\'' +
                ", nullable='" + nullable + '\'' +
                '}';
    }
}
