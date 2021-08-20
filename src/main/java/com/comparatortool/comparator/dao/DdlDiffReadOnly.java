package com.comparatortool.comparator.dao;

import com.comparatortool.comparator.entity.all_tab_columns;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Lazy
@Repository
@ComponentScan(basePackages = "com.cli.jdbc.datasource")
public interface DdlDiffReadOnly extends CrudRepository<all_tab_columns, String> {
    @Query(nativeQuery = true, name = "ddlDiffColumns")
    Iterable<all_tab_columns> findDdl(String srcTable, String srcOwner, String tarTable, String tarOwner);
}
