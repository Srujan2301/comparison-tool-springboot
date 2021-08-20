package com.comparatortool.comparator.service;

import com.comparatortool.comparator.Configuration.Config;
import com.comparatortool.comparator.dao.DdlKeysRepo;
import com.comparatortool.comparator.entity.DdlKeys;
import com.comparatortool.comparator.model.Comparator;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DdlKeyService {

//    @Autowired(required = false)
//    DdlKeysRepo ddlKeysRepo;

    public List<DdlKeys> findKeys(String owner, String table){
        SessionFactory sessionFactory = Comparator.getSessionFactory();
        Session session = sessionFactory.openSession();
//        Iterable<DdlKeys>  res = ddlKeysRepo.findKeys(owner,table);

        String hql = "SELECT cons.owner, cons.table_name, cons.constraint_name, cols.column_name, cols.position, cons.status, cons.constraint_type,  cons.r_owner, cons.r_constraint_name, \n" +
        "\t\t\t\t case \n" +
        "\t\t\t\t when cons.constraint_type = 'R' \n" +
        "\t\t\t\t then (select  c.column_name from all_cons_columns c where c.constraint_name = cons.r_constraint_name) \n" +
        "\t\t\t\t end as r_column_name \n" +
        "\t\t\t\t FROM all_constraints cons join all_cons_columns cols \n" +
        "\t\t\t\t On cons.constraint_name = cols.constraint_name \n" +
        "\t\t\t\t AND cons.owner = cols.owner \n" +
        "\t\t\t\t WHERE cols.table_name = :table and cols.owner = :owner \n" +
        "\t\t\t\t ORDER BY cols.column_name";

        Query<DdlKeys> query = session.createQuery(hql).setParameter("owner", owner).setParameter("table", table);
        List<DdlKeys> list = query.list();

        session.close();

//        sessionFactory.close();
        System.out.println("Inside DdlKeyService");
        return list;

    }

}
