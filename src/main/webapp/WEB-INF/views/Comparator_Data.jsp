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
	<form action="/data" method="get">

        <table style = "width:100%">
                <h3> Available in Source but missing in Target: </h3>
        		<tr>
        		    <c:forEach items="${srcColumns}" var ="col" >
        			<th>${col}</th>
        			</c:forEach>
        			<th>TABLE NAME</th>
                    <th>OWNER</th>

        		</tr>

        		<c:forEach items="${dataSrcTarList}" var="usr">

                    <tr>
        				<c:forEach items ="${usr}" var = "col">
        				<td>${col}</td>
        				</c:forEach>
        			</tr>

        		</c:forEach>

        </table>

        <br></br>

        <table style = "width:100%">
                <h3> Available in Target but missing in Source: </h3>
                <tr>
                    <c:forEach items="${tarColumns}" var ="col" >
                    <th>${col}</th>
                    </c:forEach>
                    <th>TABLE NAME</th>
                    <th>OWNER</th>

                </tr>

                <c:forEach items="${dataTarSrcList}" var="usr">

                    <tr>
                        <c:forEach items ="${usr}" var = "col">
                        <td>${col}</td>
                        </c:forEach>
                    </tr>

                </c:forEach>

        </table>
        <br></br>

        <h3> Data change in Source and Target tables:</h3>

        <c:forEach items = "${diffColumn}" var = "diffCol" varStatus = "status">

        <table style = "width:50%">
                <tr>
                    <c:forEach items="${diffCol}" var ="col" >
                    <th>${col}</th>
                    </c:forEach>
                    <th>TABLE NAME</th>
                    <th>OWNER</th>

                </tr>

                <tr>
                    <c:forEach items ="${diffData[status.index]}" var = "data">

                        <tr>
                        <c:forEach items = "${data}" var = "x" >
                            <c:forEach items ="${x}" var = "d" >
                                <td>${d}</td>
                            </c:forEach>
                        </c:forEach>
                        </tr>

                    </c:forEach>
                </tr>

        </table>
        <br></br>
        </c:forEach>





</body>
</html>