
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
  <form action="<%= request.getContextPath() %>/login" method="post">
   <table style="with: 80%">
    <tr>
     <td>Host : </td>
     <td><input type="text" name="host" /></td>
    </tr>
    <tr>
     <td>Port : </td>
     <td><input type="text" name="port" /></td>
    </tr>
    <tr>
     <td>Service Name/SID : </td>
     <td><input type="text" name="srvName" /></td>
    </tr>
    <tr>
     <td>Username : </td>
     <td><input type="text" name="username" /></td>
    </tr>
    <tr>
      <td>Password : </td>
      <td><input type="password" name="password" /></td>
    </tr>
   </table>

   <h3> ${error} </h3>

   <input type="submit" value="Submit" />
  </form>

 </div>
</body>
</html>