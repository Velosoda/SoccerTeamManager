package com.manager.MainAppMain;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ViewFixtures")
public class ServletViewFixtures extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

    public ServletViewFixtures()
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		League league = (League) getServletContext().getAttribute("league");
		request.setAttribute("team0Matches",league.allTeams.get(0).matches);
		request.setAttribute("team1Matches",league.allTeams.get(1).matches);
		request.setAttribute("team2Matches",league.allTeams.get(2).matches);
		request.setAttribute("team3Matches",league.allTeams.get(3).matches);
		request.getRequestDispatcher("/JSP/viewfixtures.jsp").forward(request, response);
	}

}
