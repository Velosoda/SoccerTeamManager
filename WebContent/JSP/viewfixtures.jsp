<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="stylesheet.css" type="text/css"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>View Fixture</title>
</head>
<body>
	<div class = "datagrid" style ="float:left; width: 24%;">
	<table>
		<caption>Team 1</caption>
			<thead>
				<tr>
					<th>Teams To Verse at Home</th>
				<tr>
			</thead>
			<tbody>
				<c:forEach var="team0Matches"  items= "${team0Matches}">
					<tr>
						<td><c:out value="${team0Matches + 1}"></c:out>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div class = "datagrid" style ="float:left; width: 24%;">
	<table>
		<caption>Team 2</caption>
			<thead>
				<tr>
					<th>Teams To Verse at Home</th>
				<tr>
			</thead>
			<tbody>
				<c:forEach var="team1Matches"  items= "${team1Matches}">
					<tr>
						<td><c:out value="${team1Matches + 1}"></c:out>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div class = "datagrid" style ="float:left; width: 24%;">
	<table>
		<caption>Team 3</caption>
			<thead>
				<tr>
					<th>Teams Left To Verse at Home</th>
				<tr>
			</thead>
			<tbody>
				<c:forEach var="team2Matches"  items= "${team2Matches}">
					<tr>
						<td><c:out value="${team2Matches + 1}"></c:out>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div class = "datagrid" style ="float:left; width: 24%;">
	<table>
		<caption>Your Team</caption>
			<thead>
				<tr>
					<th>Teams To Verse at Home</th>
				<tr>
			</thead>
			<tbody>
				<c:forEach var="team3Matches"  items= "${team3Matches}">
					<tr>
						<td><c:out value="${team3Matches + 1}"></c:out>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<br>
	<a class="clickMe" href="MainMenu" style="float:left;"> Back To Main Menu</a>
</body>
</html>