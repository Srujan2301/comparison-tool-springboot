package com.comparatortool.comparator.dao;

import java.sql.*;
import java.util.*;

import com.comparatortool.comparator.model.Comparator;

public class ComparatorDao_Data {


    public void dbConnection(Comparator comparator) throws ClassNotFoundException {


        String src_Columns = "select * from all_tab_columns where table_name = '" + comparator.getSourceTable() + "' and owner = '" + comparator.getSourceSchema() + "' ";
        String tar_Columns = "select * from all_tab_columns where table_name = '" + comparator.getTargetTable() + "' and owner = '" + comparator.getTargetSchema() + "' ";

        String src_Pk = "SELECT cols.table_name, cols.column_name, cols.position, cons.status, cons.owner, cons.constraint_type, cons.search_condition, cons.r_owner, cons.r_constraint_name " +
                "FROM all_constraints cons, all_cons_columns cols " +
                "WHERE cols.table_name = '" + comparator.getSourceTable() + "' and cols.owner = '" + comparator.getSourceSchema() + "' AND cons.constraint_type = 'P' " +
                "AND cons.constraint_name = cols.constraint_name " +
                "AND cons.owner = cols.owner " +
                "ORDER BY cols.table_name, cols.position ";

        String tar_Pk = "SELECT cols.table_name, cols.column_name, cols.position, cons.status, cons.owner, cons.constraint_type, cons.search_condition, cons.r_owner, cons.r_constraint_name " +
                "FROM all_constraints cons, all_cons_columns cols " +
                "WHERE cols.table_name = '" + comparator.getTargetTable() + "' and cols.owner = '" + comparator.getTargetSchema() + "' AND cons.constraint_type = 'P' " +
                "AND cons.constraint_name = cols.constraint_name " +
                "AND cons.owner = cols.owner " +
                "ORDER BY cols.table_name, cols.position ";

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            //Get a connection
            String url = "jdbc:oracle:thin:@" + comparator.getHost() + ":" + comparator.getPort() + "/" + comparator.getSrvName();
            String user = comparator.getUsername();
            String pwd = comparator.getPassword();
            Connection myCon = DriverManager.getConnection(url,user,pwd);

            //create a statement
            Statement myStmt = myCon.createStatement();
            Statement myStmt1 = myCon.createStatement();

            ArrayList<String> srcPkList = new ArrayList<String>() ;
            ArrayList<String> tarPkList = new ArrayList<String>() ;

            ResultSet rs = myStmt.executeQuery(src_Pk);
            String sourcePk = "";
            Boolean firstFlag = true;
            while(rs.next()){
                if(!firstFlag) {
                    sourcePk = sourcePk + ",";
                }
                sourcePk = sourcePk + rs.getString("column_name");
                firstFlag = false;
                srcPkList.add(0,rs.getString("column_name"));

            }

            rs = myStmt.executeQuery(tar_Pk);
            String targetPk = "";
            firstFlag = true;
            while(rs.next()){
                if(!firstFlag) {
                    targetPk = targetPk + ",";
                }
                targetPk = targetPk + rs.getString("column_name");
                firstFlag = false;
                tarPkList.add(0,rs.getString("column_name"));

            }

            comparator.setSourcePk(sourcePk);
            comparator.setTargetPk(targetPk);
            comparator.setTarPk(tarPkList);
            comparator.setSrcPk(srcPkList);

            String src_tar_data = "select * from " + comparator.getSourceSchema() + "."  + comparator.getSourceTable() +
                    " where (" + comparator.getSourcePk() + ") in " +
                    "(Select " + comparator.getSourcePk() + " from " + comparator.getSourceSchema() + "."  + comparator.getSourceTable() +
                    " minus " +
                    "Select " + comparator.getTargetPk() + " from " + comparator.getTargetSchema() + "." + comparator.getTargetTable() + ")";

            String tar_src_data = "select * from " + comparator.getTargetSchema() + "."  + comparator.getTargetTable() +
                    " where (" + comparator.getTargetPk() + ") in " +
                    "(Select " + comparator.getTargetPk() + " from " + comparator.getTargetSchema() + "."  + comparator.getTargetTable() +
                    " minus " +
                    "Select " + comparator.getSourcePk() + " from " + comparator.getSourceSchema() + "." + comparator.getSourceTable() + ")";


            rs = myStmt.executeQuery(src_tar_data);

            ArrayList<ArrayList> dataSrcTarList = new ArrayList<ArrayList>() ;

            while(rs.next()) {

                ArrayList<String> list = new ArrayList<String>();

                list.add(0,comparator.getSourceSchema());
                list.add(0,comparator.getSourceTable());

                ResultSet rs1 = myStmt1.executeQuery(src_Columns);

                while (rs1.next()){
                    list.add(0,rs.getString(rs1.getString("column_name")));
                }

                dataSrcTarList.add(list);//Save the map collection object into the list collection

            }

            ArrayList<ArrayList> dataTarSrcList = new ArrayList<ArrayList>() ;

            rs = myStmt.executeQuery(tar_src_data);

            while(rs.next()) {

                ArrayList<String> list = new ArrayList<String>();

                list.add(0,comparator.getTargetSchema());
                list.add(0,comparator.getTargetTable());

                ResultSet rs1 = myStmt1.executeQuery(tar_Columns);

                while (rs1.next()){
                    list.add(0,rs.getString(rs1.getString("column_name")));
                }

                dataTarSrcList.add(list);//Save the map collection object into the list collection

            }

            comparator.setDataSrcTarList(dataSrcTarList);
            comparator.setDataTarSrcList(dataTarSrcList);

            System.out.println("========================");

            System.out.println("\n");

            ArrayList<String> srcColumnsList = new ArrayList<String>() ;
            ArrayList<String> tarColumnsList = new ArrayList<String>() ;

            ResultSet rs_column = myStmt.executeQuery(src_Columns);

            while(rs_column.next()) {
                srcColumnsList.add(0,rs_column.getString("column_name"));
                }

            rs_column = myStmt.executeQuery(tar_Columns);

            while(rs_column.next()){
                tarColumnsList.add(0,rs_column.getString("column_name"));
            }

            comparator.setSrcColumns(srcColumnsList);
            comparator.setTarColumns(tarColumnsList);

            dataDiff_SrcTar(comparator);


        } catch (SQLException throwables) {
            System.out.println("Invalid Database Entered, please try again");
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }

    }

    public void dataDiff_SrcTar(Comparator comparator) {

        ArrayList<String> srcColumns = new ArrayList<String>();
        ArrayList<String> tarColumns = new ArrayList<String>();
        ArrayList<String> srcPk = new ArrayList<String>();
        ArrayList<String> tarPk = new ArrayList<String>();
        ArrayList<ArrayList> diffColumn = new ArrayList<ArrayList>();
        ArrayList<ArrayList> diffData = new ArrayList<ArrayList>();
        srcColumns = comparator.getSrcColumns();
        tarColumns = comparator.getTarColumns();
        srcPk = Comparator.getSrcPk();
        tarPk = Comparator.getTarPk();

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            //Get a connection
            String url = "jdbc:oracle:thin:@" + comparator.getHost() + ":" + comparator.getPort() + "/" + comparator.getSrvName();
            String user = comparator.getUsername();
            String pwd = comparator.getPassword();
            Connection myCon = DriverManager.getConnection(url, user, pwd);

            Statement myStmt = myCon.createStatement();
            //create a statement



            for (String srcColumn : srcColumns) {

                String selectSrcString = " ";
                String selectTarString = " ";
                String onString = " ";
                String querySrc = " ";
                String queryTar = " ";
                boolean firstFlag = true;
                ArrayList<String> columns = new ArrayList<String>();


                for (String sPk : srcPk) {
                    if (!firstFlag) {
                        selectSrcString = selectSrcString + ",";
                        selectTarString = selectTarString + ",";
                        onString = onString + " and ";
                    }
                    columns.add(sPk);
                    selectSrcString = selectSrcString + "a." + sPk + " a" + sPk + " , b." + sPk + " b" + sPk;
//                    selectTarString = selectTarString + "b." + sPk;
                    firstFlag = false;

                    onString = onString + "a." + sPk + " = " + "b." + sPk;
                }

                if (!firstFlag) {
                    selectSrcString = selectSrcString + ",";
//                    selectTarString = selectTarString + ",";
                    onString = onString + " and ";
                }
                selectSrcString = selectSrcString + "a." + srcColumn + " a" + srcColumn + " , b." + srcColumn + " b" + srcColumn;
//                selectTarString = selectTarString + "b." + srcColumn;
                firstFlag = false;
                onString = onString + "a." + srcColumn + " != " + "b." + srcColumn;
                columns.add(srcColumn);

                querySrc = "Select " + selectSrcString + " from " + comparator.getSourceSchema() + "." + comparator.getSourceTable() + " a join " +
                        comparator.getTargetSchema() + "." + comparator.getTargetTable() + " b on " + onString;

//                queryTar = "Select " + selectTarString + " from " + comparator.getSourceSchema() + "." + comparator.getSourceTable() + " a join " +
//                        comparator.getTargetSchema() + "." + comparator.getTargetTable() + " b on " + onString;

                ResultSet rsSrc = myStmt.executeQuery(querySrc);
//                ResultSet rsTar = myStmt.executeQuery(queryTar);

                ArrayList<ArrayList> dataList = new ArrayList<ArrayList>();

                boolean dataEntry = false;
                dataList.clear();
                while (rsSrc.next()){
                    ArrayList<String> data1 = new ArrayList<String>();

                    for(String column:columns){
                    data1.add(rsSrc.getString("a" + column));
                    }
                    data1.add(comparator.getSourceTable());
                    data1.add(comparator.getSourceSchema());
                    dataList.add(data1);

                    ArrayList<String> data2 = new ArrayList<String>();
                    for(String column:columns){
                        data2.add(rsSrc.getString("b" + column));
                    }
                    data2.add(comparator.getTargetTable());
                    data2.add(comparator.getTargetSchema());
                    dataList.add(data2);


                    dataEntry = true;
                }

                if(dataEntry) {
                    diffColumn.add(columns);
                    diffData.add(dataList);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        comparator.setDiffData(diffData);
        comparator.setDiffColumn(diffColumn);
    }
}

