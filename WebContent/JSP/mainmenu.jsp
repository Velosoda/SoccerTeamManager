<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="stylesheet.css" type="text/css"/>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Soccer Manager Game</title>
</head>
	<body>
	<br>
	  <div class="mainmenu">
		<a class="clickMe" href="Market"> Market </a>
	 	<a class="clickMe" href="Team"> View Teams </a>
	 	<a class="clickMe" href="ViewFixtures"> View Fixtures </a>
	 	<a class="clickMe" href="NextFixture"> Next Fixture </a>
	  </div>
	  <br>
	  <p>Remember, The optimal setup is 2 Attackers, 4 Midfielders, 4 Defenders and 1 Goalies</p>
	  <p>${error }</p>
	  <form id="form1" method="post" action="EditTeam">
	  <div class = "datagrid">
	  	<table>
		  	<caption>Your Starters</caption>
				<thead>
					<tr>
						<th>Name</th>
						<th>Age</th>
						<th>Attack</th>
						<th>Midfield</th>
						<th>Defense</th>
						<th>Goalie</th>
						<th>Natural Position</th>
						<th>Health</th>
						<th>Remove</th>
						<th>Swap</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="teamStarters"  items= "${teamStarters}" varStatus="position">
						<tr class = "alt">
							<td><c:out value="${teamStarters.name}"/></td>
							<td><c:out value="${teamStarters.age}"/></td>
							<td><c:out value="${teamStarters.attackSkill}"/></td>
							<td><c:out value="${teamStarters.midfieldSkill}"/></td>
							<td><c:out value="${teamStarters.defenseSkill}"/></td>
							<td><c:out value="${teamStarters.goalieSkill}"/></td>
							<td><c:out value="${teamStarters.naturalPosition}"/></td>
							<td><c:out value="${teamStarters.currentHealth }"/></td>
							<td>
								<input type = "radio" name = "playerToRemove" value = "${teamStarters.name}"/>
							</td>
							<td>
								<input type="radio" name="selectedPlayerStarter" value="${teamStarters.name}"/>
								<input type="hidden" name="positionOfStarter" value="${position.index}"/>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div class = "datagrid">
		<table>
		  	<caption>Your Bench</caption>
				<thead>
					<tr>
						<th>Name</th>
						<th>Age</th>
						<th>Attack</th>
						<th>Midfield</th>
						<th>Defense</th>
						<th>Goalie</th>
						<th>Natural Position</th>
						<th>Health</th>
						<th>Add</th>
						<th>Swap</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="teamBench"  items= "${teamBench}" varStatus="position1">
						<tr class = "alt">
							<td><c:out value="${teamBench.name}"/></td>
							<td><c:out value="${teamBench.age}"/></td>
							<td><c:out value="${teamBench.attackSkill}"/></td>
							<td><c:out value="${teamBench.midfieldSkill}"/></td>
							<td><c:out value="${teamBench.defenseSkill}"/></td>
							<td><c:out value="${teamBench.goalieSkill}"/></td>
							<td><c:out value="${teamBench.naturalPosition}"/></td>
							<td><c:out value="${teamBench.currentHealth}"/></td>
							<td>
								<input type = "radio" name = "playerToAdd" value = "${teamBench.name}"/>
							</td>
							<td>
								<input type="radio" name="selectedPlayerBench" value="${teamBench.name}"/>
								<input type="hidden" name="positionOfBench" value="${position1.index}"/>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<input type = "submit" name = "progression" value = "Swap"/>
			<input type = "submit" name = "progression" value = "Add To Starters"/>
			<input type = "submit" name = "progression" value = "Remove From Starters"/>
			<!-- <input type = "submit" name = "progression" value = "Auto Fill Team"/> -->
			<input type = "submit" name = "progression" value = "Auto Sort Team"/>
		</div>
		</form>
		<p>${error}</p>
	</body>
</html>
