<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Soccer Manager Game</title>
<style type = "text/css">
	.mainmenu
  	{
		 	 
	}
	.clickMe 
	{
	   	-moz-appearance: button;
	    -ms-appearance: button;
	    -o-appearance: button;
	    -webkit-appearance: button;
	    appearance: button;
	    text-decoration: none;
	    color: #000;
	    padding: 0.2em 0.4em;
	}
	table 
	{
		font-family: arial, sans-serif;
		border-collapse: collapse;
	}
	td, th
	{
		border: 1px solid #dddddd;
		text-align: left;
	}
</style>
</head>
	<body>
	  <div class="mainmenu">
		<a class="clickMe" href="Market"> Market </a> 
	 	<a class="clickMe" href="Team"> View Teams </a>
	 	<a class="clickMe" href="ViewFixtures"> View Fixtures </a>
	 	<a class="clickMe" href="NextFixture"> Next Fixture </a>
	  </div>
	  <br>
	  	<table border = "1px">
	  	<caption>Your Starters</caption>
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
				<c:forEach var="teamStarters"  items= "${teamStarters}">
					<tr>
						<td><c:out value="${teamStarters.name}"></c:out>
						<td><c:out value="${teamStarters.age}"/></td>
						<td><c:out value="${teamStarters.ageGroup}"/></td>
						<td><c:out value="${teamStarters.attackSkill}"/></td>
						<td><c:out value="${teamStarters.midfieldSkill}"/></td>
						<td><c:out value="${teamStarters.defenseSkill}"/></td>
						<td><c:out value="${teamStarters.goalieSkill}"/></td>
						<td><c:out value="${teamStarters.naturalPosition}"/></td>
						<td><c:out value="${teamStarters.overall}"/></td>
						<td><c:out value="${teamStarters.cost}"/></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<table border = "1px">
	  	<caption>Your Bench</caption>
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
				<c:forEach var="teamBench"  items= "${teamBench}">
					<tr>
						<td><c:out value="${teamBench.name}"></c:out>
						<td><c:out value="${teamBench.age}"/></td>
						<td><c:out value="${teamBench.ageGroup}"/></td>
						<td><c:out value="${teamBench.attackSkill}"/></td>
						<td><c:out value="${teamBench.midfieldSkill}"/></td>
						<td><c:out value="${teamBench.defenseSkill}"/></td>
						<td><c:out value="${teamBench.goalieSkill}"/></td>
						<td><c:out value="${teamBench.naturalPosition}"/></td>
						<td><c:out value="${teamBench.overall}"/></td>
						<td><c:out value="${teamBench.cost}"/></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</body>
</html>
