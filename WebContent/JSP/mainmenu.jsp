<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Soccer Manager Game</title>
<style type = "text/css">
	.clickMe 
	{
		  display: inline-block;
		  -webkit-box-sizing: content-box;
		  -moz-box-sizing: content-box;
		  box-sizing: content-box;
		  cursor: pointer;
		  padding: 10px 20px;
		  border: 1px double #000000;
		  -webkit-border-radius: 34px;
		  border-radius: 34px;
		  font: normal 16px/normal "Trebuchet MS", Helvetica, sans-serif;
		  color: rgba(255,255,255,1);
		  -o-text-overflow: clip;
		  text-overflow: clip;
		  background: rgba(0,191,0,1);
		  -webkit-transition: all 300ms cubic-bezier(0.42, 0, 0.58, 1);
		  -moz-transition: all 300ms cubic-bezier(0.42, 0, 0.58, 1);
		  -o-transition: all 300ms cubic-bezier(0.42, 0, 0.58, 1);
		  transition: all 300ms cubic-bezier(0.42, 0, 0.58, 1);
	}
	.datagrid table 
	{
		border-collapse: collapse;
	 	text-align: left;
	  	width: 100%;
	}
	.datagrid 
	{
		font: normal 12px/150% Arial, Helvetica, sans-serif;
		background: #fff; 
		overflow: hidden; 
		border: 1px solid #000000; 
		-webkit-border-radius: 3px; 
		-moz-border-radius: 3px; 
		border-radius: 3px; 
	}
	.datagrid table td, .datagrid table th 
	{ 
		padding: 3px 10px; 
	}
	.datagrid table thead th 
	{
		background:-webkit-gradient( linear, left top, left bottom, color-stop(0.05, #029907), color-stop(1, #029907) );
		background:-moz-linear-gradient( center top, #029907 5%, #029907 100% );
		filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#029907', endColorstr='#029907');
		background-color:#029907;
		color:#FFFFFF; 
		font-size: 15px; 
		font-weight: bold; 
		border-left: 1px solid #000000;
	} 
	.datagrid table thead th:first-child 
	{ 
		border: none;
	}
	.datagrid table tbody td 
	{
		color: #7D7D7D; 
		border-left: 1px solid #000000;
		font-size: 12px;
		font-weight: normal;
	}
	.datagrid table tbody td:first-child 
	{
		border-left: none;
	}
	.datagrid table tbody tr:last-child td 
	{ 
		border-bottom: none;
	}
	.datagrid tr:hover td
	{
		background-color: grey;
		color: #FFFFFF;
	}
</style>
<script type ="text/javascript">
	function submitBothForms ()
	{
    	var x = document.getElementByName("selectedPlayerStarter").value;
    	
	}
</script>
</head>
	<body>
	  <div class="mainmenu">
		<a class="clickMe" href="Market"> Market </a>
	 	<a class="clickMe" href="Team"> View Teams </a>
	 	<a class="clickMe" href="ViewFixtures"> View Fixtures </a>
	 	<a class="clickMe" href="NextFixture"> Next Fixture </a>
	  </div>
	  <br>
	  <form id="form1" method="post" action="EditTeam">
	  <div class = "datagrid">
	  	<table>
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
						<th>Health</th>
						<th>Injury Risk</th>
						<th>Cost</th>
						<th>Swap</th>
					<tr>
				</thead>
				<tbody>
					<c:forEach var="teamStarters"  items= "${teamStarters}" varStatus="position">
						<tr class = "alt">
							<td><c:out value="${teamStarters.name}"></c:out>
							<td><c:out value="${teamStarters.age}"/></td>
							<td><c:out value="${teamStarters.ageGroup}"/></td>
							<td><c:out value="${teamStarters.attackSkill}"/></td>
							<td><c:out value="${teamStarters.midfieldSkill}"/></td>
							<td><c:out value="${teamStarters.defenseSkill}"/></td>
							<td><c:out value="${teamStarters.goalieSkill}"/></td>
							<td><c:out value="${teamStarters.naturalPosition}"/></td>
							<td><c:out value="${teamStarters.overall}"/></td>
							<td><c:out value="${teamStarters.currentHealth }"/></td>
							<td><c:out value="${teamStarters.injuryRisk}"/></td>
							<td><c:out value="${teamStarters.cost}"/></td>
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
						<th>AgeGroup</th>
						<th>Attack</th>
						<th>Midfield</th>
						<th>Defense</th>
						<th>Goalie</th>
						<th>Position</th>
						<th>Overall</th>
						<th>Health</th>
						<th>Swap</th>
					<tr>
				</thead>
				<tbody>
					<c:forEach var="teamBench"  items= "${teamBench}" varStatus="position1">
						<tr class = "alt">
							<td><c:out value="${teamBench.name}"></c:out>
							<td><c:out value="${teamBench.age}"/></td>
							<td><c:out value="${teamBench.ageGroup}"/></td>
							<td><c:out value="${teamBench.attackSkill}"/></td>
							<td><c:out value="${teamBench.midfieldSkill}"/></td>
							<td><c:out value="${teamBench.defenseSkill}"/></td>
							<td><c:out value="${teamBench.goalieSkill}"/></td>
							<td><c:out value="${teamBench.naturalPosition}"/></td>
							<td><c:out value="${teamBench.overall}"/></td>
							<td><c:out value="${teamBench.currentHealth}"/></td>
							<td>
								<input type="radio" name="selectedPlayerBench" value="${teamBench.name}"/>
								<input type="hidden" name="positionOfBench" value="${position1.index}"/>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<input type = "submit" name = "progression" value = "Swap"/>
			<input type = "submit" name = "progression" value = "Auto Fill Team"/>
			<input type = "submit" name = "progression" value = "Auto Sort Team"/>
		</div>
		</form>
		<p>${error}</p>
	</body>
</html>
