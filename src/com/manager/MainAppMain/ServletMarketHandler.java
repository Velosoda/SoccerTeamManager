package com.manager.MainAppMain;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/MarketHandler")//declaring the name of the servlet
public class ServletMarketHandler extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
	ArrayList<Player> positionalArray; 
	double totalCost = 0.0;
	
    public ServletMarketHandler()
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
		
		//get parameters
		String selectedPlayerName = request.getParameter("selectedPlayers");
		String selectedPlayerPosition = request.getParameter("selectedPlayerPosition");
		String progression = request.getParameter("progression");
		
		if(progression.equals("Confirm Purchase"))
		{
			//load in budgets and other financial stats and check for negative balances
			Double budgetIfPurchase = Double.parseDouble(request.getParameter("realBudgetIfPurchase"));
			Double totalCost = Double.parseDouble(request.getParameter("realTotalCost"));
			Double budget = Double.parseDouble(request.getParameter("realBudget"));
			System.out.println("budgetIfPurchase : " + budgetIfPurchase);
			System.out.println("totalCost : " + totalCost);
			System.out.println("budget : " + budget);
			ArrayList<Player> selectedPFM = league.allTeams.get(Constants.userTeamId).selectedPFM;
			Team userTeam = league.allTeams.get(Constants.userTeamId);
			
			if(budgetIfPurchase < 0)
			{
				String error = "Your balance cannot be negative!";
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
				
				request.getRequestDispatcher("/JSP/market.jsp").forward(request, response);
				return;
			}
			for(int i = 0; i < selectedPFM.size(); i++)
			{
				userTeam.teamBench.add(selectedPFM.get(i));
			}
			userTeam.selectedPFM.clear();
			request.setAttribute("teamBench", userTeam.teamBench);
			request.getRequestDispatcher("/JSP/mainmenu.jsp").forward(request, response);
			return;
		}
		
		//test params 
		System.out.println("name : " + selectedPlayerName);
		System.out.println("position : " + selectedPlayerPosition);
		System.out.println("progression : " + progression);
		
		//choose the positional arraylist
		try
		{
			if(selectedPlayerPosition.equals(Constants.attacker))
			{
				positionalArray = market.marketAttacker;
				System.out.println("Positional array : Attack");
			}
			if(selectedPlayerPosition.equals(Constants.midfielder))
			{
				positionalArray = market.marketMidfielder;
				System.out.println("Positional array : Midfielder");
			}
			if(selectedPlayerPosition.equals(Constants.defender))
			{
				positionalArray = market.marketDefender;
				System.out.println("Positional array : Defender");
			}
			if(selectedPlayerPosition.equals(Constants.goalie))
			{
				positionalArray = market.marketGoalie;
				System.out.println("Positional array : Goalie");
			}	
		}
		catch(Exception e)
		{

		}
		
		//determines whether the user hit remove
		if(progression.equals("Sell"))
		{
			ArrayList<Player> teamStarters = league.allTeams.get(Constants.userTeamId).teamStarters;
			for(int i = 0; i < teamStarters.size(); i++)
			{
				if(teamStarters.get(i).getName().equals(selectedPlayerName))
				{
					System.out.println("index of item to remove : " + i);
					System.out.println("size of SelectedPFM Beginning: " + teamStarters.size());
					
					//add removed player to the total market and the positional array
					ml.totalMarket.add(teamStarters.get(i));
					positionalArray.add(teamStarters.get(i));
					System.out.println("Added to Total Market and Positional Array : " + teamStarters.get(i).getName());
					
					//should update cost
					totalCost = totalCost - teamStarters.get(i).getCost();
					double budget = Constants.teamBudget;
					double budgetAfterPurchase = budget - totalCost; 
					//remove from selectedPFM
					teamStarters.remove(i);
					
					//send new selectedPFM to request
					request.setAttribute("selectedPFM", teamStarters);
					
					//not sure if necessary but going to keep out of fear
					STM.setAttribute("market", market);
					STM.setAttribute("league", league);
					STM.setAttribute("ModelLoading", ml);
					
					//send market
					request.setAttribute("totalMarket", ml.totalMarket);
					request.setAttribute("marketSize", ml.totalMarket.size());
					
					//send non formated budgets
					request.setAttribute("realBudgetIfPurchase", budgetAfterPurchase);
					request.setAttribute("realTotalCost", totalCost);
					request.setAttribute("realBudget", Constants.teamBudget);
					
					//send formated budgets
					request.setAttribute("totalCost", Constants.format.format(totalCost));
					request.setAttribute("budget", Constants.format.format(Constants.teamBudget));					
					request.setAttribute("budgetIfPurchase", Constants.format.format(budgetAfterPurchase));
					
					request.getRequestDispatcher("/JSP/market.jsp").forward(request, response);
					System.out.println("go back to initusersetup");
					System.out.println("size of SelectedPFM End: " + teamStarters.size());
					return;
				}
				else
				{
					System.out.println("name not found");
				}
			}
		}
		
		if(progression.equals("Add Player"))
		{
			//search for player in total arraylist to remove them
			for(int i = 0; i < ml.totalMarket.size(); i++)
			{
				if(ml.totalMarket.get(i).getName().equals(selectedPlayerName))
				{
					ml.totalMarket.remove(i);
				}
			}
			
			//search for player in positional arraylist to add them to the selection array
			for(int i = 0; i < positionalArray.size(); i++)
			{
				if(positionalArray.get(i).getName().equals(selectedPlayerName))
				{
					league.allTeams.get(3).selectedPFM.add(positionalArray.get(i));
					request.setAttribute("selectedPFM", league.allTeams.get(3).selectedPFM);
					totalCost = totalCost +  positionalArray.get(i).getCost();
					positionalArray.remove(i);
				}
			}
			
			//calculate budgetAfterPurchase
			double budgetAfterPurchase = Constants.teamBudget - totalCost;
			
			//not sure if necessary but going to keep out of fear
			STM.setAttribute("market", market);
			STM.setAttribute("league", league);
			STM.setAttribute("ModelLoading", ml);
			
			//send manipulated vars to the request so the view can use it 
			request.setAttribute("totalMarket", ml.totalMarket);
			request.setAttribute("realBudgetIfPurchase", budgetAfterPurchase);
			request.setAttribute("realTotalCost", totalCost);
			request.setAttribute("realBudget", Constants.teamBudget);
			request.setAttribute("totalCost", Constants.format.format(totalCost));
			request.setAttribute("budgetIfPurchase", Constants.format.format(budgetAfterPurchase));
			request.setAttribute("budget", Constants.format.format(Constants.teamBudget));
			request.setAttribute("marketSize", ml.totalMarket.size());
			request.setAttribute("userTeam", league.allTeams.get(Constants.userTeamId).teamStarters);
			request.setAttribute("teamBench", league.allTeams.get(Constants.userTeamId).teamBench);
			System.out.println();
			request.getRequestDispatcher("/JSP/market.jsp").forward(request, response);
			return;
		}
	}
}
