package com.comparatortool.comparator.dao;

import com.comparatortool.comparator.entity.all_tables;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Lazy
@Repository
@ComponentScan(basePackages = "com.cli.jdbc.datasource")
public interface SchemaReadOnly extends CrudRepository<all_tables, String> {

//    @Query(value = "select table_name from all_tables where owner = ?1 order by table_name")
    Iterable<all_tables> findByOwner(String owner);

    @Query(value = "select num_rows from all_tables where owner= ?1 and table_name = ?2")
    String findRows(String owner, String table);

}
