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
		
		//load in budgets and other finacial stats
		try
		{
			int budgetIfPurchase = (int) Constants.format.parse(request.getParameter("budgetIfPurchase"));
			int totalCost = (int) Constants.format.parse(request.getParameter("totalCost"));
			int budget = (int) Constants.format.parse(request.getParameter("budget"));
		}
		catch(Exception e)
		{
			System.out.println("budget parse error");
		}
		
		//Since the players the user selected are already in selectedPFM we just use that
		ArrayList<Player> selectedPFM = league.allTeams.get(Constants.userTeamId).selectedPFM;
		
		//***********************************************************error checking*************************************************************
		String error = " ";
		
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
		//***************************************************************************error Checking end************************************************************************
	}
}
