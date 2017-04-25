<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>View Fixture</title>
</head>
<body>
	<table border= "1">
		<thead>
			<tr>
				<th>team 1</th>
				<th>team 2</th>
				<th>team 3</th>
				<th>Your Team</th>
			<tr>
		</thead>
		<tbody>
			<c:forEach begin="0" end="${fn:length(team0Matches) - 1}" var="index">
   				<tr>
      				<td><c:out value="${team0Matches[index] + 1}"/></td>
      				<td><c:out value="${team1Matches[index] + 1}"/><td>
      				<td><c:out value="${team2Matches[index] + 1}"/><td>
      				<td><c:out value="${team3Matches[index] + 1}"/><td>
   				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>