<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Cart</title>
	</head>
	<body>
	
		<p>Total Cost of Team : ${totalCost}</p>
		<p>Budget : ${usersBudget}</p>
		
		<table border = "1px">
			<thead>
				<tr>
					<th>Name</th>
					<th>Age</th>
					<th>AgeGroup</th>
					<th>Attack</th>
					<th>Midfield</th>
					<th>Defense</th>
					<th>Goalie</th>
					<th>Position</th>
					<th>Overall</th>
					<th>Cost</th>
				<tr>
			</thead>
			<tbody>
				<c:forEach var="selectedPlayers"  items= "${selectedPlayers}">
					<tr>
						<td><c:out value="${selectedPlayers.name}"></c:out>
						<td><c:out value="${selectedPlayers.age}"/></td>
						<td><c:out value="${selectedPlayers.ageGroup}"/></td>
						<td><c:out value="${selectedPlayers.attackSkill}"/></td>
						<td><c:out value="${selectedPlayers.midfieldSkill}"/></td>
						<td><c:out value="${selectedPlayers.defenseSkill}"/></td>
						<td><c:out value="${selectedPlayers.goalieSkill}"/></td>
						<td><c:out value="${selectedPlayers.naturalPosition}"/></td>
						<td><c:out value="${selectedPlayers.overall}"/></td>
						<td><c:out value="${selectedPlayers.cost}"/></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<form method="post" action="UserSetup">
			<input type="hidden" value="${selectedPlayers}"/>
			<input type="submit" value="Buy Selected Players"/>
		</form>
		
	</body>
</html>