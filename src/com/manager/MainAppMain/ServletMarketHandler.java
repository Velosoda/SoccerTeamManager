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
	String error = "";
	
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
		Team userTeam = league.allTeams.get(Constants.userTeamId);
		
		//all the arrays that will be manipulated
		ArrayList<Player> selectedPFM = league.allTeams.get(Constants.userTeamId).selectedPFM;
		ArrayList<Player> teamStarters = league.allTeams.get(Constants.userTeamId).teamStarters;
		ArrayList<Player> teamBench = league.allTeams.get(Constants.userTeamId).teamBench;
		
		if(progression.equals("Confirm Purchase"))
		{
			for(int i = 0; i < selectedPFM.size(); i++)
			{
				teamBench.add(selectedPFM.get(i));
				selectedPFM.remove(i);
			}
			
			//cant Afford clause
			if(userTeam.getCostOfPFM() > userTeam.getBudget())
			{
				error = "You Can not afford these players";
				
				//return to the market with everything intact
				request.setAttribute("teamStarters", teamStarters);
				request.setAttribute("teamBench", teamBench);
				request.setAttribute("selectedPFM", selectedPFM);
				request.setAttribute("totalMarket", ml.totalMarket);
				request.setAttribute("error", error);
				//budgets
				request.setAttribute("budget", Constants.format.format(userTeam.getBudget()));
				request.setAttribute("totalCostOfPurchase", Constants.format.format(userTeam.getCostOfPFM()));
				request.setAttribute("budgetIfPurchased", Constants.format.format(userTeam.getBudget() - userTeam.getCostOfPFM()));
				
				STM.setAttribute("market", market);
				STM.setAttribute("league", league); 
				STM.setAttribute("ModelLoading", ml);
				request.getRequestDispatcher("/JSP/market.jsp").forward(request, response);
				return;
			}
			//can afford clause
			else
			{
				//subtract the cost of the player
				userTeam.setBudget(userTeam.getBudget()-userTeam.getCostOfPFM());
				
				//reset the CostofPFM var
				userTeam.setCostOfPFM(0);
				
				request.setAttribute("budget", Constants.format.format(userTeam.getBudget()));
				request.setAttribute("totalCostOfPurchase", Constants.format.format(userTeam.getCostOfPFM()));
				request.setAttribute("budgetIfPurchased", Constants.format.format(userTeam.getBudget() - userTeam.getCostOfPFM()));
				request.setAttribute("teamStarters", teamStarters);
				request.setAttribute("teamBench", teamBench);
				STM.setAttribute("market", market);
				STM.setAttribute("league", league); 
				STM.setAttribute("ModelLoading", ml);
				request.getRequestDispatcher("/JSP/mainmenu.jsp").forward(request, response);
				return;
			}

		}
		if(progression.equals("Add to Cart"))
		{
			for(int i = 0; i < ml.totalMarket.size(); i++)
			{
				if(ml.totalMarket.get(i).getName().equals(selectedPlayerName))
				{
					Player selectedPlayer = ml.totalMarket.get(i); 
					
					//add to selectedPFM and take it out of the market
					selectedPFM.add(selectedPlayer);
					ml.totalMarket.remove(selectedPlayer);
					
					//budget control here
					userTeam.setCostOfPFM(userTeam.getCostOfPFM()+selectedPlayer.getCost());
					
					//Server commands
					//arraylists
					request.setAttribute("teamStarters", teamStarters);
					request.setAttribute("teamBench", teamBench);
					request.setAttribute("selectedPFM", selectedPFM);
					request.setAttribute("totalMarket", ml.totalMarket);
					//budgets
					request.setAttribute("budget", Constants.format.format(userTeam.getBudget()));
					request.setAttribute("totalCostOfPurchase", Constants.format.format(userTeam.getCostOfPFM()));
					request.setAttribute("budgetIfPurchased", Constants.format.format(userTeam.getBudget() - userTeam.getCostOfPFM()));
					
					//context
					STM.setAttribute("market", market);
					STM.setAttribute("league", league); 
					STM.setAttribute("ModelLoading", ml);
					request.getRequestDispatcher("/JSP/market.jsp").forward(request, response);
					return;
				}
			}
		}
		//Removing from SelectedPFM
		if(progression.equals("Remove"))
		{
			for(int i = 0; i < selectedPFM.size(); i++)
			{
				if(selectedPFM.get(i).getName().equals(selectedPlayerName))
				{
					Player selectedPlayer = selectedPFM.get(i); 
					//remove from cart
					ml.totalMarket.add(selectedPlayer);
					selectedPFM.remove(i);
					
					//budget control here
					userTeam.setCostOfPFM(userTeam.getCostOfPFM()-selectedPlayer.getCost());
					
					//Server commands
					//arraylists
					request.setAttribute("teamStarters", teamStarters);
					request.setAttribute("teamBench", teamBench);
					request.setAttribute("selectedPFM", selectedPFM);
					request.setAttribute("totalMarket", ml.totalMarket);
					//budgets
					request.setAttribute("budget", Constants.format.format(userTeam.getBudget()));
					request.setAttribute("totalCostOfPurchase", Constants.format.format(userTeam.getCostOfPFM()));
					request.setAttribute("budgetIfPurchased", Constants.format.format(userTeam.getBudget() - userTeam.getCostOfPFM()));
					
					//context
					STM.setAttribute("market", market);
					STM.setAttribute("league", league); 
					STM.setAttribute("ModelLoading", ml);
					request.getRequestDispatcher("/JSP/market.jsp").forward(request, response);
					return;				
				}
			}
		}
		//Sell from Bench or Starters 
		if(progression.equals("Sell"))
		{
			for(int i = 0; i < teamStarters.size(); i++)
			{
				if(teamStarters.get(i).getName().equals(selectedPlayerName))
				{
					Player selectedPlayer = teamStarters.get(i); 
					
					//remove from Starters
					ml.totalMarket.add(selectedPlayer);
					teamStarters.remove(i);
					
					//budget control here
					userTeam.setBudget(userTeam.getBudget()+selectedPlayer.getCost());
					
					//Server commands
					request.setAttribute("teamStarters", teamStarters);
					request.setAttribute("teamBench", teamBench);
					request.setAttribute("selectedPFM", selectedPFM);
					request.setAttribute("totalMarket", ml.totalMarket);
					//budgets
					request.setAttribute("budget", Constants.format.format(userTeam.getBudget()));
					request.setAttribute("totalCostOfPurchase", Constants.format.format(userTeam.getCostOfPFM()));
					request.setAttribute("budgetIfPurchased", Constants.format.format(userTeam.getBudget() - userTeam.getCostOfPFM()));
					
					STM.setAttribute("market", market);
					STM.setAttribute("league", league); 
					STM.setAttribute("ModelLoading", ml);
					request.getRequestDispatcher("/JSP/market.jsp").forward(request, response);
					return;					
				}
			}
			for(int i = 0; i < teamBench.size(); i++)
			{
				if(teamBench.get(i).getName().equals(selectedPlayerName))
				{
					Player selectedPlayer = teamBench.get(i); 
					
					//remove from Starters
					ml.totalMarket.add(selectedPlayer);
					teamStarters.remove(i);
					
					//budget control here
					userTeam.setBudget(userTeam.getBudget()+selectedPlayer.getCost());
					
					//Server commands
					request.setAttribute("teamStarters", teamStarters);
					request.setAttribute("teamBench", teamBench);
					request.setAttribute("selectedPFM", selectedPFM);
					request.setAttribute("totalMarket", ml.totalMarket);
					//budgets
					request.setAttribute("budget", userTeam.getBudget());
					STM.setAttribute("market", market);
					STM.setAttribute("league", league); 
					STM.setAttribute("ModelLoading", ml);
					request.getRequestDispatcher("/JSP/market.jsp").forward(request, response);
					return;					
				}
			}
		}
	}
}
