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
public class ServletLoading extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//namesList has to be made here. This was ultimatly the only way to create the array using a file since the path to names doesnt exist outside of this instance of the servlet. Don't quote me on that 
	public ArrayList<String> makeNames() throws IOException
	{
		ArrayList<String> namesList = new ArrayList<String>();
		BufferedReader br = new BufferedReader(new FileReader(getServletContext().getRealPath("/names.txt")));
		String line = br.readLine();
		while (line != null) 
		{ 
			namesList.add(line); line = br.readLine(); 
			}
		br.close();
		return namesList;
	
	}
	
	
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
		
			ArrayList<String> namesList = new ArrayList<String>();
			BufferedReader br = new BufferedReader(new FileReader(getServletContext().getRealPath("/names.txt")));
			String line = br.readLine();
			while (line != null) 
			{ 
				namesList.add(line); line = br.readLine(); 
				}
			br.close();
		
		
		ServletContext STM = getServletConfig().getServletContext();
		//the market takes in an ArrayList<String> so that it can then eventually pass it on to the player constructor
		Market market = new Market(makeNames());
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
