<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<link rel="stylesheet" href="stylesheet.css" type="text/css"/>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Player Market</title>
	</head>
	<body>
		<div class="mainmenu">
		 	<a class="clickMe" href="MainMenu"> Back To Main Menu</a>
	  	</div>
		<p>The size of the market is ${marketSize}</p>
		<p>Please Select 2 Attackers, 4 Midfielders, 4 Defenders, and 1 Goalie</p>
		<p style= "color:red">${error}</p>
		
		<div class = "datagrid" style ="float:left; width: 49%;">
		<table>
		<caption>Market</caption>
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
					<th>Select</th>
				<tr>
			</thead>
			<tbody>
				<c:forEach var="totalMarket"  items= "${totalMarket}">
					<tr class = "alt">
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
						<td>
							<form action = "MarketHandler" method = "POST">
								<input type = "hidden" name = "selectedPlayers" value = "${totalMarket.name}"/>
								<input type = "hidden" name = "selectedPlayerPosition" value = "${totalMarket.naturalPosition}"/>
								<input type = "submit" name = "progression" value = "Add to Cart"/>
							</form>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		</div>
		<div class = "datagrid" style ="float:right; width: 49%;">
		<table>
		<caption>Cart</caption>
			<thead>
				<tr>
					<th>Name</th>
					<th>Position</th>
					<th>Overall</th>
					<th>Cost</th>
					<th>Remove</th>
				<tr>
			</thead>
			<tbody>
				<c:forEach var="selectedPFM"  items= "${selectedPFM}">
					<tr class = "alt">
						<td><c:out value="${selectedPFM.name}"></c:out>
						<td><c:out value="${selectedPFM.naturalPosition}"/></td>
						<td><c:out value="${selectedPFM.overall}"/></td>
						<td><c:out value="${selectedPFM.cost}"/></td>
						<td>	
							<form action = "MarketHandler" method = "POST">
								<input type = "hidden" name = "selectedPlayers" value = "${selectedPFM.name}"/>
								<input type = "hidden" name = "selectedPlayerPosition" value = "${selectedPFM.naturalPosition}"/>
								<input type = "submit" name = "progression" value = "Remove"/>
							</form>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<form action="MarketHandler" method="post"> 
			<input type = "submit" name = "progression" value = "Confirm Purchase"/>
		</form>
		</div>
		<div class = "datagrid" style ="float:right; width: 49%;">
		<table>
		<caption>Team Starters</caption>
			<thead>
				<tr>
					<th>Name</th>
					<th>Position</th>
					<th>Overall</th>
					<th>Cost</th>
					<th>Remove</th>
				<tr>
			</thead>
			<tbody>
				<c:forEach var="teamStarters"  items= "${teamStarters}">
					<tr class = "alt">
						<td><c:out value="${teamStarters.name}"></c:out>
						<td><c:out value="${teamStarters.naturalPosition}"/></td>
						<td><c:out value="${teamStarters.overall}"/></td>
						<td><c:out value="${teamStarters.cost}"/></td>
						<td>	
							<form action = "MarketHandler" method = "POST">
								<input type = "hidden" name = "selectedPlayers" value = "${teamStarters.name}"/>
								<input type = "hidden" name = "selectedPlayerPosition" value = "${teamStarters.naturalPosition}"/> 
								<input type = "submit" name = "progression" value = "Sell"/>
							</form>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		</div>
		<div class = "datagrid" style ="float:right; width: 49%;">
		<table>
		<caption>Team Bench</caption>
			<thead>
				<tr>
					<th>Name</th>
					<th>Position</th>
					<th>Overall</th>
					<th>Cost</th>
					<th>Remove</th>
				<tr>
			</thead>
			<tbody>
				<c:forEach var="teamBench"  items= "${teamBench}">
					<tr class = "alt">
						<td><c:out value="${teamBench.name}"></c:out>
						<td><c:out value="${teamBench.naturalPosition}"/></td>
						<td><c:out value="${teamBench.overall}"/></td>
						<td><c:out value="${teamBench.cost}"/></td>
						<td>	
							<form action = "MarketHandler" method = "POST">
								<input type = "hidden" name = "selectedPlayers" value = "${teamBench.name}"/>
								<input type = "hidden" name = "selectedPlayerPosition" value = "${teamBench.naturalPosition}"/>
								<input type = "submit" name = "progression" value = "Sell"/>
							</form>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<p>Total = ${totalCostOfPurchase}</p> <!-- total cost of this (^) selection -->
		<p>Your Budget = ${budget}</p>
		<p>Budget After Purchase = ${budgetIfPurchased}</p> <!-- What will the budget be after purchase -->
		</div>
	</body>
</html>