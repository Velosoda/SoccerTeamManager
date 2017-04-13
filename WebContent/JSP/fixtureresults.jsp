<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>	
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Fixture Results</title>
	<style type = "text/css">
table, th, td {
    border: 1px solid black;
}
</style>
</head>
<body>
	<p> Match 1: ${team1_1} vs ${team1_2}</p>
	<p> Match 2: ${team2_1} vs ${team2_2}</p>
	<p> Result of Match 1: ${team1_1} [${team1_1Goals}] | ${team1_2} [${team1_2Goals}]</p>
	<p> Result of Match 2: ${team2_1} [${team2_1Goals}] | ${team2_2} [${team2_2Goals}]</p>
	
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
		<div class="mainmenu">
			<a class="clickMe" href="Market"> Market </a> 
	 		<a class="clickMe" href="Team"> View Teams </a>
	 		<a class="clickMe" href="ViewFixtures"> View Fixtures </a>
	 		<a class="clickMe" href="NextFixture"> Next Fixture </a>
	  </div>
</body>
</html>