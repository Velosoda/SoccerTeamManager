package com.manager.MainAppMain;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Market")
public class ServletMarket extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletMarket() 
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doPost(request,response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		ServletContext STM = getServletConfig().getServletContext();
		Market market = (Market)STM.getAttribute("market");
		League league = (League)STM.getAttribute("league");
		ModelLoading ml = (ModelLoading)STM.getAttribute("ModelLoading");
		
		//misc
		Team userTeam = league.allTeams.get(Constants.userTeamId);
		
		request.setAttribute("totalMarket", ml.totalMarket);
		request.setAttribute("marketSize", ml.totalMarket.size());
		request.setAttribute("teamStarters", league.allTeams.get(Constants.userTeamId).teamStarters);
		request.setAttribute("teamBench", league.allTeams.get(Constants.userTeamId).teamBench);
		request.setAttribute("budget", Constants.format.format(userTeam.getBudget()));
		request.setAttribute("totalCostOfPurchase", Constants.format.format(userTeam.getCostOfPFM()));
		request.setAttribute("budgetIfPurchased", Constants.format.format(userTeam.getBudget() - userTeam.getCostOfPFM()));
		
		STM.setAttribute("market", market);
		STM.setAttribute("league", league); 
		STM.setAttribute("ModelLoading", ml);
		
		request.getRequestDispatcher("/JSP/market.jsp").forward(request, response);
	}
}
