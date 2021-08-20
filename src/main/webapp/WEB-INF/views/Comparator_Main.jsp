
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
 <div align="center">
  <h1>Comparator Tool</h1>
  <h3> Click one of the following options </h3>

  <form action="<%= request.getContextPath() %>/schema" method="get">
   <input type="submit" value="Schema Comparator" name = "compOption"/>
  </form>

  <br> </br>

  <form action="<%= request.getContextPath() %>/table" method="get">
     <input type="submit" value="Table Comparator" name = "compOption"/>
  </form>

 </div>
</body>
</html>