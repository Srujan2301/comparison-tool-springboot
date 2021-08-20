<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
* {
  box-sizing: border-box;
}
.column {
  float: left;
  width: 50%;
  padding: 10px;
  /* height: 300px; */ /* Should be removed. Only for demonstration */
}

.row:after {
  content: "";
  display: table;
  clear: both;
}
</style>
</head>

<body>
	<h1> Data Received and Responded</h1>
	<form action="<%= request.getContextPath() %>/table" method="post">

    <h4> Select one of the table in each schema to compare and click submit...!!! </h4>
    <input type = "radio" name = "ddl_dml" value = "DDL"> DDL
    <input type = "radio" name = "ddl_dml" value = "DML"> DML
       <div class = "row">
        <div class = "column">
        	<h4>Source Schema Tables</h4>

        		<c:forEach items="${srcList}" var="usr">
                <input type = "radio" name = "srcTable" value = ${usr.table_name}> ${usr.table_name}
                <br></br>
        		</c:forEach>
        </div>
        <div class = "column">
            <h4> Target Schema Tables </h4>
        		<c:forEach items="${tarList}" var="usr">
        	    <input type = "radio" name = "tarTable" value = ${usr.table_name}> ${usr.table_name}
        	    <br></br>
                </c:forEach>
        </div>
       </div>

        <input type="submit" value="Submit" />


   </form>


</body>
</html>