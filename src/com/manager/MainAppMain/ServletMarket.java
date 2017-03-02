package com.manager.MainAppMain;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Market")
public class ServletMarket extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

    public ServletMarket()
    {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doPost(request, response); 
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Market market = (Market) getServletContext().getAttribute("market");
		League league = (League) getServletContext().getAttribute("league");
		ModelLoading ml = (ModelLoading) getServletContext().getAttribute("ModelLoading");
		int attacker = market.marketAttacker.size();
		int midfielder = market.marketMidfielder.size();
		int defender = market.marketDefender.size();
		int goalie = market.marketGoalie.size();
		int youth = market.marketYouth.size();
		
		System.out.println("TotalMarket size "+ ml.totalMarket.size());
		for(int i = 0; i < ml.totalMarket.size(); i++)
		{
			ml.totalMarket.get(i).printStats();
		}
		request.setAttribute("totalMarket", ml.totalMarket);
		request.setAttribute("attacker", attacker);
		request.setAttribute("midfielder", midfielder);
		request.setAttribute("defender", defender);
		request.setAttribute("goalie", goalie);
		request.setAttribute("youth", youth);
		request.getRequestDispatcher("JSP/market.jsp").forward(request, response);
	}

}
