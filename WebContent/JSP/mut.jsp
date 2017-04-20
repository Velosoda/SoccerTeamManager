<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Make User Team</title>
</head>

<body>
 The size of totalMarket is ${totalMarket.size()}


<form method = "get" action="MUT">
<table>
		<thead>
			<tr>
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
					<td><c:out value="${totalMarket.age}"/></td>
					<td><c:out value="${totalMarket.ageGroup}"/></td>
					<td><c:out value="${totalMarket.attackSkill}"/></td>
					<td><c:out value="${totalMarket.midfieldSkill}"/></td>
					<td><c:out value="${totalMarket.defenseSkill}"/></td>
					<td><c:out value="${totalMarket.goalieSkill}"/></td>
					<td><c:out value="${totalMarket.naturalPosition}"/></td>
					<td><c:out value="${totalMarket.overall}"/></td>
					<td><c:out value="${totalMarket.cost}"/></td>
					<!-- Player's name is printed and then the cell itself acts as a checkbox form. Then the name gets passed as a string based on what the actual name was to begin with -->
					<td><c:out value="${totalMarket.name}"/><input type="radio" name="playerName" value = "${totalMarket.name }"/></td>
					<td></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

 	
    <button type="submit">Create Team</button>
</form>


</body>
</html>