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
	User Team Results
		<thead>
			<tr>
				<th>Name</th>
				<th>Position</th>
				<th>Old Stat</th>
				<th>New Stat</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="levelUpRecord"  items= "${levelUpRecord}" >
			<tr>
				<td><c:out value="${levelUpRecord.name}" > </c:out></td>
				<td><c:out value ="${levelUpRecord.currentPosition}"></c:out></td>
				<td><c:out value ="${levelUpRecord.currentSkillValue - 1}"></c:out></td>
				<td><c:out value ="${levelUpRecord.currentSkillValue}"></c:out></td>
			</tr>	
			</c:forEach>
		</tbody>
	</table>
		<br>
		<br>
		<br>
		<table>
	Death Record
		<thead>
			<tr>
				<th>Name</th>
				<th>Position</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="deathRecord"  items ="${deathRecord}" >
			<tr>
				<td><c:out value="${deathRecord.name}" > </c:out></td>
				<td><c:out value ="${deathRecord.naturalPosition}"></c:out></td>
			</tr>	
			</c:forEach>
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