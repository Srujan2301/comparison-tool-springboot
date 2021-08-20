package com.comparatortool.comparator.service;

import com.comparatortool.comparator.Configuration.Config;
import com.comparatortool.comparator.dao.SchemaReadOnly;
import com.comparatortool.comparator.entity.DdlKeys;
import com.comparatortool.comparator.entity.DdlKeysId;
import com.comparatortool.comparator.entity.all_tab_columns;
import com.comparatortool.comparator.entity.all_tables;
import com.comparatortool.comparator.model.Comparator;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class schemaService {

//    @Autowired
//    SchemaReadOnly schemaReadOnly;

    public List<all_tables> findByOwner(String owner){
        Comparator com = new Comparator();
        System.out.println("Port in SS: " + com.getPort());
        SessionFactory sessionFactory = Comparator.getSessionFactory();
        Session session = sessionFactory.openSession();

        String hql = "from all_tables where owner = :owner";
        Query<all_tables> query = session.createQuery(hql).setParameter("owner", owner);
        List<all_tables> listTables = query.list();

//        Iterable<all_tables>  res = schemaReadOnly.findByOwner(owner);
        session.close();
//        sessionFactory.close();
        return listTables;
    }

    public String findRows(String owner, String table){
        SessionFactory sessionFactory = Comparator.getSessionFactory();
        Session session = sessionFactory.openSession();
//        String res =  schemaReadOnly.findRows(owner,table);

        String hql = "select num_rows from all_tables where owner= :owner and table_name = :table";
        Query<String> query = session.createQuery(hql).setParameter("owner", owner).setParameter("table",table);
        String rows = query.getSingleResult();
        session.close();
//        sessionFactory.close();
        return rows;
    }

    @Bean(name ="entityManagerFactory")
    public static void getSessionFactory(){
//            Configuration config = new Configuration();
//            Comparator dbSettings = new Comparator();
//
//
//            String url = "jdbc:oracle:thin:@" + dbSettings.getHost() + ":" + dbSettings.getPort() + "/" + dbSettings.getSrvName();
//
//            System.out.println("Config: " + dbSettings.getHost() + dbSettings.getPort() + dbSettings.getSrvName() + dbSettings.getUsername() + dbSettings.getPassword());
//
//            String usrname = dbSettings.getUsername();
//            String pwd = dbSettings.getPassword();
//            config.setProperty("hibernate.dialect", "org.hibernate.dialect.Oracle12cDialect");
//
//            if (usrname != null && pwd != null) {
//                config.setProperty("hibernate.connection.url", url);
//                config.setProperty("hibernate.connection.username", usrname);
//                config.setProperty("hibernate.connection.password", pwd);
//
//
//            } else {
//                config.setProperty("hibernate.connection.url", "jdbc:oracle:thin:@ip-10-12-120-218.us-east-2.compute.internal:1521/DB12C");
//                config.setProperty("hibernate.connection.username", "hedba");
//                config.setProperty("hibernate.connection.password", "n0hedge");
//            }
//
//            config.addAnnotatedClass(all_tab_columns.class);
//            config.addAnnotatedClass(all_tables.class);
//            config.addAnnotatedClass(DdlKeys.class);
//            config.addAnnotatedClass(DdlKeysId.class);
//            SessionFactory sessionFactory = config.buildSessionFactory();
//
//            return sessionFactory;

    }
}
