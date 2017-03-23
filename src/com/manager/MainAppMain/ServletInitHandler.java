package com.manager.MainAppMain;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/InitHandler")
public class ServletInitHandler extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
	ArrayList<Player> positionalArray; 
	double totalCost = 0.0;
	public ServletInitHandler() 
    {
        super();      
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		ServletContext STM = getServletConfig().getServletContext();
		Market market = (Market)STM.getAttribute("market");
		League league = (League)STM.getAttribute("league");
		ModelLoading ml = (ModelLoading)STM.getAttribute("ModelLoading");
		
		//get parameters
		String selectedPlayerName = request.getParameter("selectedPlayer");
		String selectedPlayerPosition = request.getParameter("selectedPlayerPosition");
		System.out.println("name : " + selectedPlayerName);
		System.out.println("position : " + selectedPlayerPosition);
		
		
		//choose the positional arraylist
		if(selectedPlayerPosition.equals(Constants.attacker))
		{
			positionalArray = market.marketAttacker;
			System.out.println("Attack array used");
		}
		if(selectedPlayerPosition.equals(Constants.midfielder))
		{
			positionalArray = market.marketMidfielder;
			System.out.println("midfielder array used");
		}
		if(selectedPlayerPosition.equals(Constants.defender))
		{
			positionalArray = market.marketDefender;
			System.out.println("defender array used");
		}
		if(selectedPlayerPosition.equals(Constants.goalie))
		{
			positionalArray = market.marketGoalie;
			System.out.println("goalie array used");
		}
		
		//search for player in total arraylist
		for(int i = 0; i < ml.totalMarket.size(); i++)
		{
			if(ml.totalMarket.get(i).getName().equals(selectedPlayerName))
			{
				ml.totalMarket.remove(i);
			}
		}
		
		//search for player in positional arraylist
		for(int i = 0; i < positionalArray.size(); i++)
		{
			if(positionalArray.get(i).getName().equals(selectedPlayerName))
			{
				league.allTeams.get(3).selectedPFM.add(positionalArray.get(i));
				request.setAttribute("selectedPFM", league.allTeams.get(3).selectedPFM);
				totalCost = totalCost +  positionalArray.get(i).getCost();
			}
		}
		
		//calculate budgetAfterPurchase 
		double budgetAfterPurchase = Constants.teamBudget - totalCost;
		
		STM.setAttribute("market", market);
		STM.setAttribute("league", league);
		STM.setAttribute("ModelLoading", ml);
		request.setAttribute("totalMarket", ml.totalMarket);
		request.setAttribute("totalCost", Constants.format.format(totalCost));
		request.setAttribute("budgetIfPurchase", Constants.format.format(budgetAfterPurchase));
		request.setAttribute("budget", Constants.format.format(Constants.teamBudget));
		request.getRequestDispatcher("/JSP/initialUserSetup.jsp").forward(request, response);
	}
}
