package com.comparatortool.comparator.controller;

import com.comparatortool.comparator.dao.ComparatorDao_Data;
import com.comparatortool.comparator.entity.*;
import com.comparatortool.comparator.model.Comparator;
import com.comparatortool.comparator.service.DdlKeyService;
import com.comparatortool.comparator.service.DdlService;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.util.*;

@RestController
@CrossOrigin(origins = "*")
//@Controller
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableJpaRepositories("com.comparatortool.comparator.dao")
public class ComparatorController {

    @RequestMapping("/logins")
    public String hello() {
        return "Hello S";
    }

    public ComparatorController() {
        super();
        // TODO Auto-generated constructor stub
    }

    @RequestMapping(value = "/login", method =  RequestMethod.POST)
    public Comparator postConnection(@RequestBody Comparator comparator){


        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            //Get a connection
            String url = "jdbc:oracle:thin:@" + comparator.getHost() + ":" + comparator.getPort() + "/" + comparator.getSrvName();
            System.out.println(url);
            String user = comparator.getUsername();
            String pwd = comparator.getPassword();
            Connection myCon = DriverManager.getConnection(url, user, pwd);
            System.out.println("Connection passed");

            SessionFactory sessionFactory = getSessionFactory();
            Comparator.setSessionFactory(sessionFactory);


            return comparator;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("Connection failed");
            return null;
        }

    }

    @Autowired
    com.comparatortool.comparator.service.schemaService schemaService;

    @RequestMapping(value = "/schema", method = RequestMethod.POST)
    public Comparator postSchema(@RequestBody Comparator comparator){

        try {
            schemaValidation(comparator);

            return comparator;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }

    }



    @Autowired
    DdlService ddlService;

    @Autowired
    DdlKeyService ddlKeyService;

    @RequestMapping(value = "/ddl", method = RequestMethod.POST)
    public Comparator postDdl(@RequestBody Comparator comparator){

        try {
            ddlDiff(comparator);
            return comparator;
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }

    }

    @RequestMapping(value = "/dml", method = RequestMethod.POST)
    public Comparator postDml(@RequestBody Comparator comparator){
        ComparatorDao_Data comparatorDaoData = new ComparatorDao_Data();

        try {
            comparatorDaoData.dbConnection(comparator);

            return comparator;
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }

    }

    public static SessionFactory getSessionFactory(){
        Configuration config = new Configuration();
        Comparator dbSettings = new Comparator();


        String url = "jdbc:oracle:thin:@" + dbSettings.getHost() + ":" + dbSettings.getPort() + "/" + dbSettings.getSrvName();

        System.out.println("Config: " + dbSettings.getHost() + dbSettings.getPort() + dbSettings.getSrvName() + dbSettings.getUsername() + dbSettings.getPassword());

        String usrname = dbSettings.getUsername();
        String pwd = dbSettings.getPassword();
        config.setProperty("hibernate.dialect", "org.hibernate.dialect.Oracle12cDialect");

        if (usrname != null && pwd != null) {
            config.setProperty("hibernate.connection.url", url);
            config.setProperty("hibernate.connection.username", usrname);
            config.setProperty("hibernate.connection.password", pwd);


        } else {
            config.setProperty("hibernate.connection.url", "jdbc:oracle:thin:@ip-10-12-120-218.us-east-2.compute.internal:1521/DB12C");
            config.setProperty("hibernate.connection.username", "hedba");
            config.setProperty("hibernate.connection.password", "n0hedge");
        }

        config.addAnnotatedClass(all_tab_columns.class);
        config.addAnnotatedClass(all_tables.class);
        config.addAnnotatedClass(DdlKeys.class);
        config.addAnnotatedClass(DdlKeysId.class);
        config.addAnnotatedClass(all_constraints.class);
        config.addAnnotatedClass(all_cons_columns.class);
        SessionFactory sessionFactory = config.buildSessionFactory();

        return sessionFactory;

    }

    public void schemaValidation(Comparator comparator) throws ClassNotFoundException {

        System.out.println("host in main" + comparator.getHost());

        List<all_tables> srcTables = schemaService.findByOwner(comparator.getSourceSchema());

        srcTables.forEach(System.out::println);
        System.out.println("Source Tables: " + srcTables);
        if (!srcTables.iterator().hasNext()) {
            System.out.println("No source tables");
        } else {
            System.out.println("Source Schema Tables");
            System.out.println("--------------------");

            StringBuilder x = new StringBuilder(" ");
            ArrayList<Map<String, String>> srcList = new ArrayList<>();


            for (all_tables srcTable : srcTables) {
                x.append("<br><br/>").append(srcTable.getTable_name());
                System.out.println(srcTable.getTable_name());

                Map<String, String> map = new HashMap<>();
                map.put("table_name", srcTable.getTable_name());

                //Store key-value pairs in map sets
                System.out.println(map);
                srcList.add(map);//Save the map collection object into the list collection
                System.out.println("Put in collection");
                for (Map<String, String> map_1 : srcList) {
                    System.out.println(map_1);
                }

            }

            comparator.setSchemaDiff(x.toString());

            comparator.setSrclist(srcList);

            System.out.println("========================");

            System.out.println("\n");

            System.out.println(comparator.getSchemaDiff());

        }
        List<all_tables> tarTables = schemaService.findByOwner(comparator.getTargetSchema());

        if (!tarTables.iterator().hasNext()) {
            System.out.println("No target tables");
        } else {
            System.out.println("Target Schema Tables");
            System.out.println("--------------------");

            StringBuilder y = new StringBuilder(" ");
            ArrayList<Map<String, String>> tarList = new ArrayList<>();


            for (all_tables tarTable : tarTables) {
                y.append("<br><br/>").append(tarTable.getTable_name());
                System.out.println(tarTable.getTable_name());

                Map<String, String> map = new HashMap<>();
                map.put("table_name", tarTable.getTable_name());

                //Store key-value pairs in map sets
                System.out.println(map);
                tarList.add(map);//Save the map collection object into the list collection
                System.out.println("Put in collection");
                for (Map<String, String> map_1 : tarList) {
                    System.out.println(map_1);
                }

            }

            comparator.setSchemaDiff(y.toString());
            comparator.setTarList(tarList);

            System.out.println("========================");

            System.out.println("\n");

            System.out.println(comparator.getSchemaDiff());


        }
    }

    public void ddlDiff(Comparator comparator) throws ClassNotFoundException {

        List<all_tab_columns> ddlDiffColumns = (List<all_tab_columns>) ddlService.findDdl(comparator.getSourceTable(), comparator.getSourceSchema(), comparator.getTargetTable(), comparator.getTargetSchema());
//        ddlDiffColumns.forEach(System.out::println);

        String srcCount = schemaService.findRows(comparator.getSourceSchema(), comparator.getSourceTable());
        System.out.println("Source No.of Rows" + srcCount);

        List<DdlKeys> ddlSrcKeys = ddlKeyService.findKeys(comparator.getSourceSchema(), comparator.getSourceTable());
//        ddlSrcKeys.forEach(System.out::println);

        String tarCount = schemaService.findRows(comparator.getTargetSchema(), comparator.getTargetTable());
        System.out.println("Source No.of Rows" + tarCount);

        List<DdlKeys> ddlTarKeys = ddlKeyService.findKeys(comparator.getTargetSchema(), comparator.getTargetTable());
//        ddlTarKeys.forEach(System.out::println);

        long sourceRows = 0;
        long targetRows = 0;

        System.out.println("Table schema differences");
        System.out.println("========================");

        StringBuilder x = new StringBuilder(" ");

        ArrayList<Map<String,String>> list = new ArrayList<>() ;

        for(all_tab_columns ddlDiffColumn: ddlDiffColumns) {

            x.append("<br><br/>").append(ddlDiffColumn.getOwner()).append(", ").append(ddlDiffColumn.getTable_name()).append(", ").append(ddlDiffColumn.getColumn_name()).append(", ").append(ddlDiffColumn.getColumn_id()).append(", ").append(ddlDiffColumn.getNullable()).append(", ").append(ddlDiffColumn.getData_type()).append(", ").append(ddlDiffColumn.getData_length());

            Map<String, String> map = new HashMap<>();
            map.put("owner", ddlDiffColumn.getOwner());
            map.put("table_name", ddlDiffColumn.getTable_name());
            map.put("column_name", ddlDiffColumn.getColumn_name());
            map.put("column_id", ddlDiffColumn.getColumn_id());
            map.put("nullable", ddlDiffColumn.getNullable());
            map.put("data_type", ddlDiffColumn.getData_type());
            map.put("data_length", ddlDiffColumn.getData_length());
            //Store key-value pairs in map sets
            System.out.println(map);
            list.add(map);//Save the map collection object into the list collection
            System.out.println("Put in collection");


            comparator.setTableDiff(x.toString());
            comparator.setList(list);

            System.out.println("========================");

            System.out.println("\n");

        }

        ArrayList<Map<String,String>> keyList = new ArrayList<>() ;

        Iterator itr = ddlSrcKeys.iterator();
            while(itr.hasNext()) {
                Object[] obj = (Object[]) itr.next();

                Map<String,String> map = new HashMap<>();
                map.put("owner", String.valueOf(obj[0]));
                map.put("table_name", String.valueOf(obj[1]));
                map.put("column_name", String.valueOf(obj[3]));
                map.put("status", String.valueOf(obj[5]));
                map.put("constraint_type", String.valueOf(obj[6]));
                map.put("position", String.valueOf(obj[4]));
                map.put("r_column_name", String.valueOf(obj[9]));
                //Store key-value pairs in map sets
                System.out.println(map);
                keyList.add(map);

            }


        Iterator itrTar = ddlSrcKeys.iterator();
        while(itrTar.hasNext()) {
            Object[] obj = (Object[]) itrTar.next();

            Map<String,String> map = new HashMap<>();
            map.put("owner", String.valueOf(obj[0]));
            map.put("table_name", String.valueOf(obj[1]));
            map.put("column_name", String.valueOf(obj[3]));
            map.put("status", String.valueOf(obj[5]));
            map.put("constraint_type", String.valueOf(obj[6]));
            map.put("position", String.valueOf(obj[4]));
            map.put("r_column_name", String.valueOf(obj[9]));
            //Store key-value pairs in map sets
            System.out.println(map);
            keyList.add(map);

        }

        comparator.setKeyList(keyList);

            String y = " ";

                y = srcCount;
                System.out.println("Source Table No.of Rows: " + srcCount );
                if( srcCount != null)
                    sourceRows = Long.parseLong(srcCount);

        comparator.setSourceRows(y);
            //Retrieve targetTable no.of rows

            String z = " ";
                z =  tarCount;

                System.out.println("Target Table No.of Rows: " + tarCount );
                if(tarCount != null)
                    targetRows = Long.parseLong(tarCount);

        comparator.setTargetRows(z);

            String w = "Difference in No.of Rows : " + Math.abs(sourceRows - targetRows);
            comparator.setDiffRows(w);

            System.out.println("Difference in No.of Rows : " + Math.abs(sourceRows - targetRows));


    }

}

