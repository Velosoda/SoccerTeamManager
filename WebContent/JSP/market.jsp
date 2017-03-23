<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>SMG-Player Market</title>
	</head>
	<body>
		<!-- 
			<p>There are ${attacker} attackers </p>
			<p>There are ${midfielder} midfielders</p>
			<p>There are ${defender} defenders</p>
			<p>There are ${goalie} goalies</p>
			<p>There are ${youth} youth players</p> 
		-->
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
				<c:forEach var="totalMarket"  items= "${totalMarket}">
					<tr>
						<td><c:out value="${totalMarket.name}"></c:out>
						<td><c:out value="${totalMarket.age}"/></td>
						<td><c:out value="${totalMarket.ageGroup}"/></td>
						<td><c:out value="${totalMarket.attackSkill}"/></td>
						<td><c:out value="${totalMarket.midfieldSkill}"/></td>
						<td><c:out value="${totalMarket.defenseSkill}"/></td>
						<td><c:out value="${totalMarket.goalieSkill}"/></td>
						<td><c:out value="${totalMarket.naturalPosition}"/></td>
						<td><c:out value="${totalMarket.overall}"/></td>
						<td><c:out value="${totalMarket.cost}"/></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</body>
</html>