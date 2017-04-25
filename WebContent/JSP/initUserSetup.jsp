<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<link rel="stylesheet" href="stylesheet.css" type="text/css"/>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Make User Team</title>
	</head>
	<body>
		<p>The size of the market is ${marketSize}</p>
		<p>Please Select 2 Attackers, 4 Midfielders, 4 Defenders, and 1 Goalie</p>
		<p>${error}</p>
		<div class = "datagrid" style ="float:left; width: 50%;">
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
							<form action = "InitHandler" method = "POST">
								<input type = "hidden" name = "selectedPlayers" value = "${totalMarket.name}"/>
								<input type = "hidden" name = "selectedPlayerPosition" value = "${totalMarket.naturalPosition}"/>
								<input type = "submit" name = "progression" value = "Add Player"/>
							</form>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		</div>
		<div  class = "datagrid" style ="float:right; width: 49%;">
		<table>
		<caption>Selected Items</caption>
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
							<form action = "InitHandler" method = "POST">
								<input type = "hidden" name = "selectedPlayers" value = "${selectedPFM.name}"/>
								<input type = "hidden" name = "selectedPlayerPosition" value = "${selectedPFM.naturalPosition}"/>
								<input type = "submit" name = "progression" value = "Remove"/>
							</form>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<form action="InitAddPlayersToTeam" method="post"> 
			<input type = "hidden" name = "realTotalCost" value = "${realTotalCost}"/>
			<input type = "hidden" name = "realBudget" value = "${realBudget}"/>
			<input type = "hidden" name = "realBudgetIfPurchase" value = "${realBudgetIfPurchase}"/> 
			<input type = "submit" value = "Confirm Team"/>
		</form>
		<p>Total = ${totalCost}</p> <!-- total cost of this (^) selection -->
		<p>Your Budget = ${budget}</p>
		<p>Budget After Purchase = ${budgetIfPurchase}</p> <!-- What will the budget be after purchase -->
		<!-- </div> -->
		</div>
	</body>
</html>