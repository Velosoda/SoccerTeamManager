package com.manager.MainAppMain;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

@WebServlet("/Loading")
public class ServletLoading extends HttpServlet 
{
	private static final long serialVersionUID = 1L; 
	
    public ServletLoading() 
    {
        super();      
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{	
		//load in all the names from the names.txt to arraylist "namesList" 
		ArrayList<String> namesList = new ArrayList<String>();
		BufferedReader br = new BufferedReader(new FileReader(getServletContext().getRealPath("/names.txt")));
		String line = br.readLine();
		while (line != null) 
		{ 
			namesList.add(line); line = br.readLine(); 
		}
		br.close();
		
		//setup context
		ServletContext STM = getServletConfig().getServletContext();
		
		//create market and league
		Market market = new Market(namesList);
		League league = new League(market); 
		
		//misc
		Team userTeam = league.allTeams.get(Constants.userTeamId);
		
		//get players and add them to the total market
		ModelLoading ml = new ModelLoading();
		ml.fillTotalMarket(market.marketAttacker);
		ml.fillTotalMarket(market.marketMidfielder);
		ml.fillTotalMarket(market.marketDefender);
		ml.fillTotalMarket(market.marketGoalie);
		ml.fillTotalMarket(market.marketYouth);
		
		//store all the values created above to the context
		STM.setAttribute("market", market);
		STM.setAttribute("league", league); 
		STM.setAttribute("ModelLoading", ml);
		
		//send market to request
		request.setAttribute("totalMarket", ml.totalMarket);
		request.setAttribute("marketSize", ml.totalMarket.size());
		request.setAttribute("teamStarters", league.allTeams.get(Constants.userTeamId).teamStarters);
		
		//budget 
		request.setAttribute("budget", Constants.format.format(userTeam.getBudget()));
		request.setAttribute("totalCostOfPurchase", Constants.format.format(userTeam.getCostOfPFM()));
		request.setAttribute("budgetIfPurchased", Constants.format.format(userTeam.getBudget() - userTeam.getCostOfPFM()));
		
		//go to main menu
		if(Constants.userTeamAutoFill == 0)
		{
			request.getRequestDispatcher("/JSP/mainmenu.jsp").forward(request, response);
			return;
		}
		else
		{
			request.getRequestDispatcher("/JSP/splashmainmenu.jsp").forward(request, response);
			return;
		}
	}
}
