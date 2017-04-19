package com.manager.MainAppMain;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/InitAddPlayersToTeam")
public class SerlvetInitAddPlayersToTeam extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
    public SerlvetInitAddPlayersToTeam() 
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		//load context vars necessary
		ServletContext STM = getServletConfig().getServletContext();
		Market market = (Market)STM.getAttribute("market");
		League league = (League)STM.getAttribute("league");
		ModelLoading ml = (ModelLoading)STM.getAttribute("ModelLoading");
		
		//Since the players the user selected are already in selectedPFM we just use that
		ArrayList<Player> selectedPFM = league.allTeams.get(Constants.userTeamId).selectedPFM;
		
		//reset error message
		String error = " ";
		
		//load in budgets and other financial stats and check for negative balances
		Double budgetIfPurchase = Double.parseDouble(request.getParameter("realBudgetIfPurchase"));
		Double totalCost = Double.parseDouble(request.getParameter("realTotalCost"));
		Double budget = Double.parseDouble(request.getParameter("realBudget"));
		System.out.println("budgetIfPurchase : " + budgetIfPurchase);
		System.out.println("totalCost : " + totalCost);
		System.out.println("budget : " + budget);
		
		if(budgetIfPurchase < 0)
		{
			error = "Your balance cannot be negative!";
			request.setAttribute("error", error);
			request.setAttribute("totalMarket", ml.totalMarket);
			request.setAttribute("selectedPFM", selectedPFM);
			
			//send non formated budgets
			request.setAttribute("realBudgetIfPurchase", budgetIfPurchase);
			request.setAttribute("realTotalCost", totalCost);
			request.setAttribute("realBudget", Constants.teamBudget);
			
			//send formated budgets
			request.setAttribute("totalCost", Constants.format.format(totalCost));
			request.setAttribute("budget", Constants.format.format(Constants.teamBudget));					
			request.setAttribute("budgetIfPurchase", Constants.format.format(budgetIfPurchase));
			
			request.getRequestDispatcher("/JSP/initUserSetup.jsp").forward(request, response);
			return;
		}
		//***************************************************************************error checking*************************************************************
		
		//checking for size of 11
		if(selectedPFM.size() < 11)
		{
			error = "You are missing some players make sure you have 11 (2 ATT, 4 MID, 4 DEF, 1 GOA)"; 
			request.setAttribute("error", error);
			request.setAttribute("totalMarket", ml.totalMarket);
			request.setAttribute("selectedPFM", selectedPFM);
			request.getRequestDispatcher("/JSP/initUserSetup.jsp").forward(request, response);
			return;
		}
		
		
		//checking for (2 ATT, 4 MID, 4 DEF, 1 GOA) *Move to MODEL* **EVENTUALLY**
		int attCounter = 0;
		int midCounter = 0;
		int defCounter = 0;
		int goaCounter = 0;
		for(int i = 0; i < selectedPFM.size(); i++)
		{
			if(selectedPFM.get(i).getNaturalPosition().equals(Constants.attacker))
			{
				attCounter++;
			}
			if(selectedPFM.get(i).getNaturalPosition().equals(Constants.midfielder))
			{
				midCounter++;
			}
			if(selectedPFM.get(i).getNaturalPosition().equals(Constants.defender))
			{
				defCounter++;
			}
			if(selectedPFM.get(i).getNaturalPosition().equals(Constants.goalie))
			{
				goaCounter++;
			}
		}
		//test counter...count 
		System.out.println("Attacker Counter : " + attCounter);
		System.out.println("Midfielder Counter : " + midCounter);
		System.out.println("Defender Counter : " + defCounter);
		System.out.println("Goalie Counter : " + goaCounter);
		
		if(attCounter > Constants.teamAttackerStarterLimit || attCounter < Constants.teamAttackerStarterLimit)
		{
			error = error + "Too many Attackers please make sure you have only 2 ";
			request.setAttribute("error", error);
			request.setAttribute("totalMarket", ml.totalMarket);
			request.setAttribute("selectedPFM", selectedPFM);
			request.getRequestDispatcher("/JSP/initUserSetup.jsp").forward(request, response);
			return;
		}
		if(midCounter > Constants.teamMidfieldStarterLimit || midCounter < Constants.teamMidfieldStarterLimit)
		{
			error = error + "Too many Midfielders please make sure you have only 4 ";
			request.setAttribute("error", error);
			request.setAttribute("totalMarket", ml.totalMarket);
			request.setAttribute("selectedPFM", selectedPFM);
			request.getRequestDispatcher("/JSP/initUserSetup.jsp").forward(request, response);
			return;
		}
		if(defCounter > Constants.teamDefenderStarterLimit || defCounter < Constants.teamDefenderStarterLimit)
		{
			error = error + "Too many Defenders please make sure you have only 4 ";
			request.setAttribute("error", error);
			request.setAttribute("totalMarket", ml.totalMarket);
			request.setAttribute("selectedPFM", selectedPFM);
			request.getRequestDispatcher("/JSP/initUserSetup.jsp").forward(request, response);
			return;
		}
		if(goaCounter > Constants.teamGoalieStarterLimit || goaCounter < Constants.teamGoalieStarterLimit)
		{
			error = error + "Too many Goalie please make sure you have only 1 ";
			request.setAttribute("error", error);
			request.setAttribute("totalMarket", ml.totalMarket);
			request.setAttribute("selectedPFM", selectedPFM);
			request.getRequestDispatcher("/JSP/initUserSetup.jsp").forward(request, response);
			return;
		}
		//***************************************************************************error checking end**********************************************************
		//***********************************************************************User Team Manipulation**********************************************************
		Team userTeam = league.allTeams.get(Constants.userTeamId);
		
		//change budget
		userTeam.setBudget(budgetIfPurchase);
		
		for(int i = 0; i < selectedPFM.size(); i++)
		{
			if(selectedPFM.get(i).getNaturalPosition().equals(Constants.attacker))
			{
				Player temp = new Player();
				temp = selectedPFM.get(i);
				selectedPFM.remove(i);
				selectedPFM.add(0, temp);
			}
			if(selectedPFM.get(i).getNaturalPosition().equals(Constants.midfielder))
			{
				Player temp = new Player();
				temp = selectedPFM.get(i);
				selectedPFM.remove(i);
				selectedPFM.add(2, temp);
			}
			if(selectedPFM.get(i).getNaturalPosition().equals(Constants.defender))
			{
				Player temp = new Player();
				temp = selectedPFM.get(i);
				selectedPFM.remove(i);
				selectedPFM.add(6, temp);
			}
			if(selectedPFM.get(i).getNaturalPosition().equals(Constants.goalie))
			{
				Player temp = new Player();
				temp = selectedPFM.get(i);
				selectedPFM.remove(i);
				selectedPFM.add(10, temp);
			}
		}
		for(int i = 0; i < selectedPFM.size(); i++)
		{
			userTeam.teamStarters.add(selectedPFM.get(i));
		}
		
		for(int i = 0; i < userTeam.teamStarters.size(); i++)
		{
			ml.updateCurrentPosition(userTeam.teamStarters.get(i), i, userTeam.teamStarters);
		}
		//clear selectedPFM
		selectedPFM.clear();
		
		//again not sure if necessary 
		STM.setAttribute("market", market);
		STM.setAttribute("league", league);
		STM.setAttribute("ModelLoading", ml);
		
		//send array to test page
		request.setAttribute("userTeam", userTeam.teamStarters);
		
		request.getRequestDispatcher("/JSP/mainmenu.jsp").forward(request, response);
	}
}
