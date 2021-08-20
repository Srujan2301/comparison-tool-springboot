
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
  <form action="<%= request.getContextPath() %>/schema" method="post">
   <table style="with: 80%">
    <tr>
     <td>Source Schema Name: </td>
     <td><input type="text" name="sourceSchema" /></td>
    </tr>

    <tr>
     <td>Target Schema Name: </td>
     <td><input type="text" name="targetSchema" /></td>
    </tr>

   </table>
   <input type="submit" value="Submit" />
  </form>
 </div>
</body>
</html>