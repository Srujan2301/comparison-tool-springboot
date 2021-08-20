package com.comparatortool.comparator.service;

import com.comparatortool.comparator.Configuration.Config;
import com.comparatortool.comparator.dao.DdlDiffReadOnly;
import com.comparatortool.comparator.entity.all_tab_columns;
import com.comparatortool.comparator.model.Comparator;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DdlService {

//    @Autowired(required = false)
//    DdlDiffReadOnly ddlDiffReadOnly;

    public List<all_tab_columns> findDdl(String srcTable, String srcOwner, String tarTable, String tarOwner){
        SessionFactory sessionFactory = Comparator.getSessionFactory();
        Session session = sessionFactory.openSession();

        String hql1 =
        "            from all_tab_columns a where  \n" +
        "            (a.column_name, a.data_type, a.data_length, a.data_precision, a.data_scale, a.nullable ) not in \n" +
        "            (select column_name, data_type, data_length, data_precision, data_scale, nullable from all_tab_columns b \n" +
        "            where b.table_name = :srcTable  and b.owner = :srcOwner) and a.table_name = :tarTable and a.owner = :tarOwner \n" +
        "            Order by column_name, owner, table_name";

        String hql2 =
        "            from all_tab_columns a where \n" +
        "            (a.column_name, a.data_type, a.data_length, a.data_precision, a.data_scale, a.nullable ) not in \n" +
        "            (select column_name, data_type, data_length, data_precision, data_scale, nullable from all_tab_columns b \n" +
        "            where b.table_name = :tarTable and b.owner = :tarOwner) and a.table_name = :srcTable and a.owner = :srcOwner \n" +
        "            Order by column_name, owner, table_name";


//        Iterable<all_tab_columns>  res = ddlDiffReadOnly.findDdl(srcTable, srcOwner, tarTable, tarOwner);

        Query<all_tab_columns> query1 = session.createQuery(hql1,all_tab_columns.class).setParameter("srcTable", srcTable).setParameter("srcOwner", srcOwner).setParameter("tarTable", tarTable).setParameter("tarOwner", tarOwner);
        Query<all_tab_columns> query2 = session.createQuery(hql2,all_tab_columns.class).setParameter("srcTable", srcTable).setParameter("srcOwner", srcOwner).setParameter("tarTable", tarTable).setParameter("tarOwner", tarOwner);
        List<all_tab_columns> list1 = query1.list();
        List<all_tab_columns> list2 = query2.list();
        list1.addAll(list2);
        List<all_tab_columns> list = list1;

        session.close();
//        sessionFactory.close();
        System.out.println("Inside DdlKeyService");
        return list;
    }

}
