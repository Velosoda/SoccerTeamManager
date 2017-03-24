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
		//load context vars necessary
		ServletContext STM = getServletConfig().getServletContext();
		Market market = (Market)STM.getAttribute("market");
		League league = (League)STM.getAttribute("league");
		ModelLoading ml = (ModelLoading)STM.getAttribute("ModelLoading");
		
		//get parameters
		String selectedPlayerName = request.getParameter("selectedPlayers");
		String selectedPlayerPosition = request.getParameter("selectedPlayerPosition");
		String progression = request.getParameter("progression");
		
		//test params 
		System.out.println("name : " + selectedPlayerName);
		System.out.println("position : " + selectedPlayerPosition);
		System.out.println("progression : " + progression);
		
		//choose the positional arraylist
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
		
		//determines whether the user hit remove
		if(progression.equals("Remove"))
		{
			ArrayList<Player> selectedPFM = league.allTeams.get(Constants.userTeamId).selectedPFM;
			for(int i = 0; i < selectedPFM.size(); i++)
			{
				if(selectedPFM.get(i).getName().equals(selectedPlayerName))
				{
					System.out.println("index of item to remove : " + i);
					System.out.println("size of SelectedPFM Beginning: " + selectedPFM.size());
					//add removed player to the total market and the positional array
					ml.totalMarket.add(selectedPFM.get(i));
					positionalArray.add(selectedPFM.get(i));
					System.out.println("Added to Total Market and Positional Array : " + selectedPFM.get(i).getName());
					
					//should update cost
					totalCost = totalCost - selectedPFM.get(i).getCost();
					
					//remove from selectedPFM
					selectedPFM.remove(i);
					
					//send new selectedPFM to request
					request.setAttribute("selectedPFM", selectedPFM);
					
					//not sure if necessary but going to keep out of fear
					STM.setAttribute("market", market);
					STM.setAttribute("league", league);
					STM.setAttribute("ModelLoading", ml);
					
					double budgetAfterPurchase = Constants.teamBudget - totalCost;
					
					//send manipulated vars to the request so the view can use it 
					request.setAttribute("totalMarket", ml.totalMarket);
					request.setAttribute("totalCost", Constants.format.format(totalCost));
					request.setAttribute("budget", Constants.format.format(Constants.teamBudget));
					request.setAttribute("marketSize", ml.totalMarket.size());
					request.setAttribute("budgetIfPurchase", Constants.format.format(budgetAfterPurchase));
					
					request.getRequestDispatcher("/JSP/initUserSetup.jsp").forward(request, response);
					
					System.out.println("go back to initusersetup");
					System.out.println("size of SelectedPFM End: " + selectedPFM.size());
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
			request.setAttribute("totalCost", Constants.format.format(totalCost));
			request.setAttribute("budgetIfPurchase", Constants.format.format(budgetAfterPurchase));
			request.setAttribute("budget", Constants.format.format(Constants.teamBudget));
			request.setAttribute("marketSize", ml.totalMarket.size());
			System.out.println();
			request.getRequestDispatcher("/JSP/initUserSetup.jsp").forward(request, response);
		}
	}
}
