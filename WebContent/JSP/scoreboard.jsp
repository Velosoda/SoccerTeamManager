<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<link rel="stylesheet" href="stylesheet.css" type="text/css"/>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Season Results</title>
	</head>
	<body>
		<p>The Season is Over!</p>
		<div class = "datagrid" style ="width: 25%;">
			<table>
			<caption>Heres How Everyone Did</caption>
				<thead>
					<tr>
						<th>Team 1</th>
						<th>Team 2</th>
						<th>Team 3</th>
						<th>Your Team</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<c:forEach var="leaguePoints"  items= "${leaguePoints}" >
							<td><c:out value="${leaguePoints}"></c:out></td>		
						</c:forEach>
					</tr>	
				</tbody>
			</table>
			</div>
			<br>
			<div class="mainmenu">
				<a class="clickMe" href="MainMenu"> Start New Season </a>
		</div>
	</body>
</html>