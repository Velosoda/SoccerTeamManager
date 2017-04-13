<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ScoreBoard</title>
<style type = "text/css">
table, th, td {
    border: 1px solid black;
}

</style>
</head>
<body>



<table>
<thead>
	<tr>
		<th>Team 1</th>
		<th>Team 2</th>
		<th>Team 3</th>
		<th>Team 4</th>
	</tr>
</thead>
<tbody>
<tr>
	<c:forEach var="leaguePoints"  items= "${leaguePoints}" >
			
			<td><c:out value="${leaguePoints}" > </c:out></td>		
		
</c:forEach>
</tr>	
</tbody>
</table>


</body>
</html>