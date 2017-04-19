package com.manager.MainAppMain;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/NextFixture")
public class ServletNextFixture extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ServletNextFixture() 
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
		Market market = (Market)STM.getAttribute("market");
		League league = (League)STM.getAttribute("league");
		ModelLoading ml = (ModelLoading)STM.getAttribute("ModelLoading");
		
		try
		{
			Match newMatchDay = new Match();
			
			//set up match day schedule
			newMatchDay.matchDayMatchups(league);
			
			//player Evaluations 
			newMatchDay.playerEvaluation(league.allTeams.get(newMatchDay.getMatch1_1()), league.allTeams.get(newMatchDay.getMatch1_2()));
			newMatchDay.playerEvaluation(league.allTeams.get(newMatchDay.getMatch2_1()), league.allTeams.get(newMatchDay.getMatch2_2()));
			
			//determine who wins
			newMatchDay.playMatch(league.allTeams.get(newMatchDay.getMatch1_1()), league.allTeams.get(newMatchDay.getMatch1_2()));
			newMatchDay.playMatch(league.allTeams.get(newMatchDay.getMatch2_1()), league.allTeams.get(newMatchDay.getMatch2_2()));
			
			//send goals to request
			request.setAttribute("team1_1Goals", league.allTeams.get(newMatchDay.getMatch1_1()).getGoals());
			request.setAttribute("team1_2Goals", league.allTeams.get(newMatchDay.getMatch1_2()).getGoals());
			request.setAttribute("team2_1Goals", league.allTeams.get(newMatchDay.getMatch2_1()).getGoals());
			request.setAttribute("team2_2Goals", league.allTeams.get(newMatchDay.getMatch2_2()).getGoals());
			
			//set Teams to request
			request.setAttribute("team1_1", newMatchDay.getMatch1_1());
			request.setAttribute("team1_2", newMatchDay.getMatch1_2());
			request.setAttribute("team2_1", newMatchDay.getMatch2_1());
			request.setAttribute("team2_2", newMatchDay.getMatch2_2());
			
			//reset goals
			league.allTeams.get(newMatchDay.getMatch1_1()).setGoals(0);
			league.allTeams.get(newMatchDay.getMatch1_2()).setGoals(0);
			league.allTeams.get(newMatchDay.getMatch2_1()).setGoals(0);
			league.allTeams.get(newMatchDay.getMatch2_2()).setGoals(0);
			
			
			STM.setAttribute("market", market);
			STM.setAttribute("league", league);
			STM.setAttribute("ModelLoading", ml);
			
			request.getRequestDispatcher("/JSP/fixtureresults.jsp").forward(request, response);
		}
		catch(NullPointerException e)
		{
			String error = "The season has ended";
			league.generateMatchUps(league.allTeams);
			request.setAttribute("error", error);
			request.getRequestDispatcher("/JSP/mainmenu.jsp").forward(request, response);
		}
	}
}
