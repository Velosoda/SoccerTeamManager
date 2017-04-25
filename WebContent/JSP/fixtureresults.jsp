<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>	
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" href="stylesheet.css" type="text/css"/>
		<title>Fixture Results</title>
	</head>
	<body>
		<p> Match 1: ${team1_1 + 1} vs ${team1_2 + 1}</p>
		<p> Match 2: ${team2_1 + 1} vs ${team2_2 + 1}</p>
		<p> Result of Match 1: ${team1_1 + 1} [${team1_1Goals}] | ${team1_2 + 1} [${team1_2Goals}]</p>
		<p> Result of Match 2: ${team2_1 + 1} [${team2_1Goals}] | ${team2_2 + 1} [${team2_2Goals}]</p>
		<div class = "datagrid" style ="width: 49%;">
		<table>
		<caption>Players that Leveled Up</caption>
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
						<td><c:out value= "${levelUpRecord.name}" > </c:out></td>
						<td><c:out value ="${levelUpRecord.currentPosition}"></c:out></td>
						<td><c:out value ="${levelUpRecord.currentSkillValue - 1}"></c:out></td>
						<td><c:out value ="${levelUpRecord.currentSkillValue}"></c:out></td>
					</tr>	
				</c:forEach>
			</tbody>
		</table>
		</div>
		<div class = "datagrid" style ="width: 49%;">
		<table>
			<caption>Players With Zero Health</caption>
			<thead>
				<tr>
					<th>Name</th>
					<th>Position</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="deathRecord"  items ="${deathRecord}">
					<tr>
						<td><c:out value="${deathRecord.name}" > </c:out></td>
						<td><c:out value ="${deathRecord.naturalPosition}"></c:out></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		</div>
		<div class="mainmenu">
		 	<a class="clickMe" href="MainMenu"> Back To Main Menu</a>
	  	</div>
	</body>
</html>