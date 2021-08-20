package com.comparatortool.comparator.model;

import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.Map;

public class Comparator {

	public static String sourceTable;
	public static String targetTable;
	public static String sourceSchema;
	public static String targetSchema;
	private String tableDiff;
	private String sourceRows;
	private String targetRows;
	private String diffRows;
	private String compOption;
	private String schemaDiff;
	public static String host;
	public static String port;
	public static String srvName;
	public static String username;
	public static String password;
	public static String ddldml;
	public static String sourcePk;
	public static String targetPk;
	public  ArrayList<Map<String,String>> list;
	public  ArrayList<Map<String, String>> srcList;
	public  ArrayList<Map<String, String>> tarList;
	public  ArrayList<Map<String,String>> keyList;
	public  ArrayList<ArrayList> dataSrcTarList;
	public  ArrayList<ArrayList> dataTarSrcList;
	public  ArrayList<ArrayList> diffData;
	public  ArrayList<ArrayList> diffColumn;
	public  ArrayList<String> tarColumns;
	public  ArrayList<String> srcColumns;
	public static ArrayList<String> tarPk;
	public static ArrayList<String> srcPk;
	public static SessionFactory sessionFactory;

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static void setSessionFactory(SessionFactory sessionFactory) {
		Comparator.sessionFactory = sessionFactory;
	}

	public  ArrayList<ArrayList> getDiffData() {
		return this.diffData;
	}

	public  void setDiffData(ArrayList<ArrayList> diffData) {
		this.diffData = diffData;
	}

	public  ArrayList<ArrayList> getDiffColumn() {
		return this.diffColumn;
	}

	public  void setDiffColumn(ArrayList<ArrayList> diffColumn) {
		this.diffColumn = diffColumn;
	}

	public  static ArrayList<String> getTarPk() {
		return tarPk;
	}

	public static void setTarPk(ArrayList<String> tarPk) {
		Comparator.tarPk = tarPk;
	}

	public static ArrayList<String> getSrcPk() {
		return srcPk;
	}

	public static void setSrcPk(ArrayList<String> srcPk) {
		Comparator.srcPk = srcPk;
	}

	public static String getSourcePk() {
		return sourcePk;
	}

	public static void setSourcePk(String sourcePk) {
		Comparator.sourcePk = sourcePk;
	}

	public static String getTargetPk() {
		return targetPk;
	}

	public static void setTargetPk(String targetPk) {
		Comparator.targetPk = targetPk;
	}

	public static String getDdldml() {
		return ddldml;
	}

	public static void setDdldml(String ddldml) {
		Comparator.ddldml = ddldml;
	}

	public  ArrayList<String> getTarColumns() {
		return this.tarColumns;
	}

	public  void setTarColumns(ArrayList<String> tarColumns) {
		this.tarColumns = tarColumns;
	}

	public  ArrayList<String> getSrcColumns() {
		return this.srcColumns;
	}

	public  void setSrcColumns(ArrayList<String> srcColumns) {
		this.srcColumns = srcColumns;
	}

	public  ArrayList<ArrayList> getDataTarSrcList() {
		return this.dataTarSrcList;
	}

	public  void setDataTarSrcList(ArrayList<ArrayList> dataTarSrcList) {
		this.dataTarSrcList = dataTarSrcList;
	}

	public  ArrayList<ArrayList> getDataSrcTarList() {
		return this.dataSrcTarList;
	}

	public  void setDataSrcTarList(ArrayList<ArrayList> dataSrcTarList) {
		this.dataSrcTarList = dataSrcTarList;
	}

	public  ArrayList<Map<String,String>> getKeyList() {
		return this.keyList;
	}

	public  void setKeyList(ArrayList<Map<String,String>> keyList) {
		this.keyList = keyList;
	}

	public  ArrayList<Map<String, String>> getSrcList() {
		return srcList;
	}

	public  void setSrclist(ArrayList<Map<String, String >> srclist) {
		this.srcList = srclist;
	}

	public  ArrayList<Map<String ,String >> getTarList() {
		return tarList;
	}

	public  void setTarList(ArrayList<Map<String,String>> tarList) {
		this.tarList = tarList;
	}

	public ArrayList<Map<String,String>> getList() {
		return list;
	}

	public void setList(ArrayList<Map<String,String>> list) {
		this.list = list;
	}

	public String getPort() {
		System.out.println("Port value: " + port);
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getSrvName() {
		return srvName;
	}

	public  void setSrvName(String srvName) {
		this.srvName = srvName;
	}

	public String getUsername() {

		System.out.println("User value: " + username);
		return username;
	}

	public  void setUsername(String username) {
		this.username = username;
	}

	public  String getPassword() {

		System.out.println("Password value: " + password);
		return password;
	}

	public  void setPassword(String password) {
		this.password = password;
	}

	public String getHost() {
		System.out.println( "host in get" + host);
		return host;
	}

	public void setHost(String host) {
		System.out.println( "host in set" + this.host);
		this.host = host;
		System.out.println( "host in set" + this.host);

	}

	public String getSchemaDiff() {
		return schemaDiff;
	}

	public void setSchemaDiff(String schemaDiff) {
		this.schemaDiff = schemaDiff;
	}

	public String getCompOption() {
		return compOption;
	}

	public void setCompOption(String compOption) {
		this.compOption = compOption;
	}

	public String getSourceTable() {
		return sourceTable;
	}
	public void setSourceTable(String sourceTable) {
		this.sourceTable = sourceTable;
	}
	public String getTargetTable() {
		return targetTable;
	}
	public void setTargetTable(String targetTable) {
		this.targetTable = targetTable;
	}
	public String getDiffRows() {
		return diffRows;
	}
	public void setDiffRows(String diffRows) {
		this.diffRows = diffRows;
	}
	public String getSourceRows() {
		return sourceRows;
	}
	public void setSourceRows(String sourceRows) {
		this.sourceRows = sourceRows;
	}
	public String getTargetRows() {
		return targetRows;
	}
	public void setTargetRows(String targetRows) {
		this.targetRows = targetRows;
	}
	public String getTableDiff() {
		return tableDiff;
	}
	public void setTableDiff(String tableDiff) {
		this.tableDiff = tableDiff;
	}
	public String getSourceSchema() {
		return sourceSchema;
	}
	public void setSourceSchema(String sourceSchema) {
		Comparator.sourceSchema = sourceSchema;
	}
	public String getTargetSchema() {
		return targetSchema;
	}
	public void setTargetSchema(String targetSchema) {
		Comparator.targetSchema = targetSchema;
	}

	
	
	
}
