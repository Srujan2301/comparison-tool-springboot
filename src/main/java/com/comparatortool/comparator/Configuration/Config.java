package com.comparatortool.comparator.Configuration;

import com.comparatortool.comparator.entity.DdlKeys;
import com.comparatortool.comparator.entity.DdlKeysId;
import com.comparatortool.comparator.entity.all_tab_columns;
import com.comparatortool.comparator.entity.all_tables;
import com.comparatortool.comparator.model.Comparator;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.context.annotation.Bean;

//@org.springframework.context.annotation.Configuration
public class Config {

    @Bean(name ="entityManagerFactory")
    public static SessionFactory getSessionFactory(){

        Configuration config = new Configuration();
        Comparator dbSettings = new Comparator();

        String url = "jdbc:oracle:thin:@" + dbSettings.getHost() + ":" + dbSettings.getPort() + "/" + dbSettings.getSrvName();

        System.out.println("Config: " + dbSettings.getHost() + dbSettings.getPort() + dbSettings.getSrvName() + dbSettings.getUsername() + dbSettings.getPassword());

        String usrname = dbSettings.getUsername();
        String pwd = dbSettings.getPassword();

        config.setProperty("hibernate.dialect","org.hibernate.dialect.Oracle12cDialect");
        config.setProperty("hibernate.connection.url", url);
        config.setProperty("hibernate.connection.username", usrname);
        config.setProperty("hibernate.connection.password", pwd);

        config.addAnnotatedClass(all_tab_columns.class);
        config.addAnnotatedClass(all_tables.class);
        config.addAnnotatedClass(DdlKeys.class);
        config.addAnnotatedClass(DdlKeysId.class);

        SessionFactory sessionFactory = config.buildSessionFactory();

        return sessionFactory;
    }

}
