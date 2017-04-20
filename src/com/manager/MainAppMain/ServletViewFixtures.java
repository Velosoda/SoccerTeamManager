package com.manager.MainAppMain;

import java.io.IOException;
import java.util.ArrayList;

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
		
		Match newMatchDay = new Match();
		
		//set up match day schedule
		newMatchDay.matchDayMatchups(league);
		
		//create leagePoints array
		int[] leaguePoints = new int[4];
		
		//11 players 
		//player Evaluations
		try
		{
			newMatchDay.playerEvaluation(league.allTeams.get(newMatchDay.getMatch1_1()), league.allTeams.get(newMatchDay.getMatch1_2()));
			newMatchDay.playerEvaluation(league.allTeams.get(newMatchDay.getMatch2_1()), league.allTeams.get(newMatchDay.getMatch2_2()));
			
			//determine who wins
			newMatchDay.playMatch(league.allTeams.get(newMatchDay.getMatch1_1()), league.allTeams.get(newMatchDay.getMatch1_2()));
			newMatchDay.playMatch(league.allTeams.get(newMatchDay.getMatch2_1()), league.allTeams.get(newMatchDay.getMatch2_2()));
			
			//determine who has gotten injured
			league.allTeams.get(Constants.userTeamId).injuryCheck();
			
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
			
			//send  results tables
			request.setAttribute("levelUpRecord", Constants.levelUpRecord);
			request.setAttribute("deathRecord", Constants.deathRecord);
			
			//reset goals
			league.allTeams.get(newMatchDay.getMatch1_1()).setGoals(0);
			league.allTeams.get(newMatchDay.getMatch1_2()).setGoals(0);
			league.allTeams.get(newMatchDay.getMatch2_1()).setGoals(0);
			league.allTeams.get(newMatchDay.getMatch2_2()).setGoals(0);
			
			STM.setAttribute("market", market);
			STM.setAttribute("league", league);
			STM.setAttribute("ModelLoading", ml);
			
			//populate scoreboard between every match
			for (int i = 0; i < league.allTeams.size() ; i++)
			{
				leaguePoints[i] = league.allTeams.get(i).getLeaguePoints();
				//System.out.println("Team " + i + "points: " + league.allTeams.get(i).getLeaguePoints());
			}
			request.setAttribute("leaguePoints", leaguePoints);
			request.getRequestDispatcher("/JSP/fixtureresults.jsp").forward(request, response);
			return;
		}
		catch(NullPointerException e)
		{
			for (int i = 0; i < league.allTeams.size() ; i++)
			{
				leaguePoints[i] = league.allTeams.get(i).getLeaguePoints();
				System.out.println("Team " + i + "points: " + league.allTeams.get(i).getLeaguePoints());
			}
			request.setAttribute("leaguePoints", leaguePoints);
			request.getRequestDispatcher("JSP/scoreboard.jsp").forward(request,response );;
			return;	
		}
	}
}
