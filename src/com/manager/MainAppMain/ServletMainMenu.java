package com.manager.MainAppMain;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/MainMenu")
public class ServletMainMenu extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    public ServletMainMenu() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		ServletContext STM = getServletConfig().getServletContext();
		Market market = (Market)STM.getAttribute("market");
		League league = (League)STM.getAttribute("league");
		ModelLoading ml = (ModelLoading)STM.getAttribute("ModelLoading");
		
		ArrayList<Player> teamStarters = league.allTeams.get(Constants.userTeamId).teamStarters;
		ArrayList<Player> teamBench = league.allTeams.get(Constants.userTeamId).teamBench;
		
		STM.setAttribute("market", market);
		STM.setAttribute("league", league); 
		STM.setAttribute("ModelLoading", ml);
		
		request.setAttribute("teamStarters", teamStarters);
		request.setAttribute("teamBench", teamBench);
		request.getRequestDispatcher("/JSP/mainmenu.jsp").forward(request, response);
	}
}