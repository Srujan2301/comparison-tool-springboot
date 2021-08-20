package com.comparatortool.comparator.dao;

import com.comparatortool.comparator.entity.DdlKeys;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Lazy
@Repository
@ComponentScan(basePackages = "com.cli.jdbc.datasource")
public interface DdlKeysRepo extends CrudRepository<DdlKeys, String> {
    @Query(nativeQuery = true, name= "keys")
    Iterable<DdlKeys> findKeys(String owner, String table);
}
