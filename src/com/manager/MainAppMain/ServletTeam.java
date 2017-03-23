package com.manager.MainAppMain;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Team")
public class ServletTeam extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ServletTeam() 
    {
        super();      
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try
		{
			Integer bipass = Integer.parseInt(request.getParameter("bipass"));
			League league = (League) getServletContext().getAttribute("league");
			Team selectedTeam = league.allTeams.get(bipass);
			request.setAttribute("selectedTeamStarters", selectedTeam.teamStarters);
			request.setAttribute("selectedTeamBench", selectedTeam.teamBench);
			request.setAttribute("bipass", bipass);
			request.getRequestDispatcher("/JSP/viewteams.jsp").forward(request, response);
		}
		catch(NumberFormatException e)
		{
			request.setAttribute("e", e);
		}
		League league = (League) getServletContext().getAttribute("league");
		request.setAttribute("allTeams", league.allTeams);
		request.getRequestDispatcher("/JSP/viewteams.jsp").forward(request, response);
	}

}
