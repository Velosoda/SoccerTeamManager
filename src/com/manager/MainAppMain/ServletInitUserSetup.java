package com.manager.MainAppMain;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UserSetup")
public class ServletInitUserSetup extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    public ServletInitUserSetup() 
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		//load in all context vars
		ServletContext STM = getServletConfig().getServletContext();
		Market market = (Market)getServletContext().getAttribute("market");
		League league = (League)getServletContext().getAttribute("league");
		ModelLoading ml = (ModelLoading) getServletContext().getAttribute("ModelLoading");
		
		Team userTeam = league.allTeams.get(Constants.userTeamId);
		
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
		request.getRequestDispatcher("/JSP/initUserSetup.jsp").forward(request, response);
	}

}
