package com.manager.MainAppMain;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Cart")
public class ServletCart extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
	//needs to be global to class
	double totalCost = 0.0;
    
	public ServletCart() 
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		//Load in Context vars
		Market market = (Market)getServletContext().getAttribute("market");
		League league = (League)getServletContext().getAttribute("league");
		ModelLoading ml = (ModelLoading) getServletContext().getAttribute("ModelLoading");
		
		String[] selectedPlayer = request.getParameterValues("selectedPlayers");
		ArrayList<Player> detailedList = new ArrayList<Player>();
		
		for(int i = 0; i < selectedPlayer.length; i++)
		{
			for(int j = 0; j <  market.marketTotal.size(); j++)
			{
				if( market.marketTotal.get(j).getName().equals(selectedPlayer[i]))
				{
					detailedList.add( market.marketTotal.get(j));
					totalCost = totalCost +  market.marketTotal.get(j).getCost();
				}
			}
		}
		
		if(totalCost > Constants.teamBudget)
		{
			String error = "Youre over your budget";
			request.setAttribute("error", error);
			request.getRequestDispatcher("/JSP/initialUserSetup.jsp").forward(request, response);
			return;
		}
		
		request.setAttribute("usersBudget", Constants.format.format(Constants.teamBudget));
		request.setAttribute("totalCost", Constants.format.format(totalCost));
		request.setAttribute("selectedPlayers", detailedList);
		request.getRequestDispatcher("/JSP/cart.jsp").forward(request, response);
	}
}
