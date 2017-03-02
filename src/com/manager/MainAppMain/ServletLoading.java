package com.manager.MainAppMain;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Loading")
public class ServletLoading extends HttpServlet {
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
		ServletContext STM = getServletConfig().getServletContext();
		Market market = new Market(); 
		League league = new League(market); 
		ModelLoading ml = new ModelLoading();
		ml.fillTotalMarket(market.marketAttacker);
		ml.fillTotalMarket(market.marketMidfielder);
		ml.fillTotalMarket(market.marketDefender);
		ml.fillTotalMarket(market.marketGoalie);
		ml.fillTotalMarket(market.marketYouth);
		STM.setAttribute("market", market);
		STM.setAttribute("league", league);
		STM.setAttribute("ModelLoading", ml);
		request.getRequestDispatcher("/JSP/mainmenu.jsp").forward(request, response);
	}
}
