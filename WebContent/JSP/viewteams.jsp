<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="stylesheet.css" type="text/css"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>View Teams</title>
</head>
<body>
	<table>
		<thead>
			<tr>
				<th>ID</th>
			<tr>
		</thead>
		<tbody>
			<c:forEach var="allTeams"  items= "${allTeams}">
				<tr>
					<td><c:out value="${allTeams.id}"/></td>
					<td>
						<form action="Team" method = "Post">
							<input type = "hidden" name = "bipass" value = "${allTeams.id}"> 
							<input class = "clickMe" type = "submit" value = "view">
						</form>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<p>Team ${bipass}<p>
	<div class = "datagrid" style =" width: 49%;">
	<table>
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
			<c:forEach var="sts"  items= "${selectedTeamStarters}">
				<tr>
					<td><c:out value="${sts.name}"></c:out>
					<td><c:out value="${sts.age}"/></td>
					<td><c:out value="${sts.ageGroup}"/></td>
					<td><c:out value="${sts.attackSkill}"/></td>
					<td><c:out value="${sts.midfieldSkill}"/></td>
					<td><c:out value="${sts.defenseSkill}"/></td>
					<td><c:out value="${sts.goalieSkill}"/></td>
					<td><c:out value="${sts.naturalPosition}"/></td>
					<td><c:out value="${sts.overall}"/></td>
					<td><c:out value="${sts.cost}"/></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	</div>
	<p>Team ${bipass} Bench</p>
	<div class = "datagrid" style ="width: 49%;">
		<table>
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
				<c:forEach var="stb"  items= "${selectedTeamBench}">
					<tr>
						<td><c:out value="${stb.name}"></c:out>
						<td><c:out value="${stb.age}"/></td>
						<td><c:out value="${stb.ageGroup}"/></td>
						<td><c:out value="${stb.attackSkill}"/></td>
						<td><c:out value="${stb.midfieldSkill}"/></td>
						<td><c:out value="${stb.defenseSkill}"/></td>
						<td><c:out value="${stb.goalieSkill}"/></td>
						<td><c:out value="${stb.naturalPosition}"/></td>
						<td><c:out value="${stb.overall}"/></td>
						<td><c:out value="${stb.cost}"/></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<p>${linkBack } <a class="clickMe" href="MainMenu"> Back To Main Menu</a></p>
</body>
</html>