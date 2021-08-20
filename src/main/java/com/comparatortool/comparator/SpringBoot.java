//package com.comparatortool.comparator;
//
//import com.comparatortool.comparator.dao.SchemaReadOnly;
//import com.comparatortool.comparator.entity.all_tables;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.Bean;
//
//import java.util.List;
//
//@SpringBootApplication
//public class SpringBoot {
//
//    public static void main(String[] args){
//        SpringApplication.run(SpringBoot.class, args);
//    }
////
////    @Bean
////    public static Iterable<all_tables> schemaTabs(SchemaReadOnly schemaReadOnly){
////          Iterable<all_tables> all_tabs = schemaReadOnly.findByOwner("OT");
////          for(all_tables allTables:all_tabs){
////              System.out.println(allTables);
////          }
////          return all_tabs;
////    }
//}
