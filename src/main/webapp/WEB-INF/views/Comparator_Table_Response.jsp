

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
table, th, td{
border: 1px solid black;
}

</style>
</head>
<body>
	<h1> Data Received and Responded</h1>
	<form action="/table" method="get">

        <table style = "width:80%">
        		<tr>
        			<th>OWNER</th>
       			    <th>TABLE NAME</th>
        			<th>COLUMN NAME</th>
        			<th>COLUMN ID</th>
                    <th>NULLABLE</th>
          			<th>DATA TYPE</th>
                    <th>DATA LENGTH</th>
        		</tr>

        		<c:forEach items="${list}" var="usr">
                    <tr>
        				<td>${usr.owner}</td>
        				<td>${usr.table_name}</td>
        				<td>${usr.column_name}</td>
        				<td>${usr.column_id}</td>
        				<td>${usr.nullable}</td>
        				<td>${usr.data_type}</td>
        				<td>${usr.data_length}</td>
        			</tr>

        		</c:forEach>

        </table>

        <br></br>

        <table style = "width:80%">
        		<tr>
        			<th>OWNER</th>
       			    <th>TABLE NAME</th>
        			<th>COLUMN NAME</th>
        			<th>STATUS</th>
                    <th>CONSTRAINT TYPE</th>
          			<th>SEARCH CONDITION</th>
                    <th>REF COLUMN NAME</th>
        		</tr>

        		<c:forEach items="${keyList}" var="usr">
                    <tr>
        				<td>${usr.owner}</td>
        				<td>${usr.table_name}</td>
        				<td>${usr.column_name}</td>
        				<td>${usr.status}</td>
        				<td>${usr.constraint_type}</td>
        				<td>${usr.search_condition}</td>
                        <td>${usr.r_column_name}</td>
        			</tr>

        		</c:forEach>

        </table>

        <br></br>

        <table style="width:50%">
            <tr>
            	<th>OWNER</th>
               	<th>TABLE NAME</th>
                <th>No. of ROWS</th>
            </tr>

            <tr>
            	<td>${srcOwner}</td>
                <td>${srcTable}</td>
                <td>${sourceRows}</td>
   			</tr>
   			<tr>
                 <td>${tarOwner}</td>
                 <td>${tarTable}</td>
                 <td>${targetRows}</td>
            </tr>

        </table>

</body>
</html>