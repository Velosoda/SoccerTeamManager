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
				<c:forEach var="userTeam"  items= "${userTeam}">
					<tr>
						<td><c:out value="${userTeam.name}"></c:out>
						<td><c:out value="${userTeam.age}"/></td>
						<td><c:out value="${userTeam.ageGroup}"/></td>
						<td><c:out value="${userTeam.attackSkill}"/></td>
						<td><c:out value="${userTeam.midfieldSkill}"/></td>
						<td><c:out value="${userTeam.defenseSkill}"/></td>
						<td><c:out value="${userTeam.goalieSkill}"/></td>
						<td><c:out value="${userTeam.naturalPosition}"/></td>
						<td><c:out value="${userTeam.overall}"/></td>
						<td><c:out value="${userTeam.cost}"/></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</body>
</html>
