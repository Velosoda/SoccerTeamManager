package com.manager.MainAppMain;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/EditTeam")
public class ServletEditTeam extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletEditTeam()
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		//load context vars necessary
		ServletContext STM = getServletConfig().getServletContext();
		Market market = (Market)STM.getAttribute("market");
		League league = (League)STM.getAttribute("league");
		ModelLoading ml = (ModelLoading)STM.getAttribute("ModelLoading");
		String error = "";
		
		//Arraylists being manipulated
		ArrayList<Player> teamStarters = league.allTeams.get(Constants.userTeamId).teamStarters;
		ArrayList<Player> teamBench = league.allTeams.get(Constants.userTeamId).teamBench;
		
		//get the players that the user wants to switch
		String starter = request.getParameter("selectedPlayerStarter");
		String bench = request.getParameter("selectedPlayerBench");
		String progression = request.getParameter("progression");
		
		if(progression.equals("Auto Fill Team"))
		{
			//checking for size of 11
			if(teamStarters.size() == 11)
			{
				error = "Your team is already full"; 
				
				//store all the values created above to the context
				STM.setAttribute("market", market);
				STM.setAttribute("league", league); 
				STM.setAttribute("ModelLoading", ml);
				
				request.setAttribute("error", error);
				request.setAttribute("teamStarters", teamStarters);
				request.setAttribute("teamBench", teamBench);
				request.getRequestDispatcher("JSP/mainmenu.jsp").forward(request, response);
				return;
			}
			
			int attCounter = 0;
			int midCounter = 0;
			int defCounter = 0;
			int goaCounter = 0;
			for(int i = 0; i < teamStarters.size(); i++)
			{
				if(teamStarters.get(i).getNaturalPosition().equals(Constants.attacker))
				{
					attCounter++;
				}
				if(teamStarters.get(i).getNaturalPosition().equals(Constants.midfielder))
				{
					midCounter++;
				}
				if(teamStarters.get(i).getNaturalPosition().equals(Constants.defender))
				{
					defCounter++;
				}
				if(teamStarters.get(i).getNaturalPosition().equals(Constants.goalie))
				{
					goaCounter++;
				}
			}
			
			//test count 
			System.out.println("Attacker Counter : " + attCounter);
			System.out.println("Midfielder Counter : " + midCounter);
			System.out.println("Defender Counter : " + defCounter);
			System.out.println("Goalie Counter : " + goaCounter);
			
			//auto fill process 
			for(int i = 0; i < teamBench.size(); i++)
			{
				if(attCounter < Constants.teamAttackerStarterLimit && teamBench.get(i).getNaturalPosition().equals(Constants.attacker))
				{
					teamStarters.add(teamBench.get(i));
					teamBench.remove(i);
				}
				if(midCounter < Constants.teamMidfieldStarterLimit && teamBench.get(i).getNaturalPosition().equals(Constants.midfielder))
				{
					teamStarters.add(teamBench.get(i));
					teamBench.remove(i);
				}
				if(defCounter < Constants.teamDefenderStarterLimit && teamBench.get(i).getNaturalPosition().equals(Constants.defender))
				{
					teamStarters.add(teamBench.get(i));
					teamBench.remove(i);
				}
				if(goaCounter < Constants.teamGoalieStarterLimit && teamBench.get(i).getNaturalPosition().equals(Constants.goalie))
				{
					teamStarters.add(teamBench.get(i));
					teamBench.remove(i);
				}
			}
			
			//store all the values created above to the context
			STM.setAttribute("market", market);
			STM.setAttribute("league", league); 
			STM.setAttribute("ModelLoading", ml);
			
			request.setAttribute("teamStarters", teamStarters);
			request.setAttribute("teamBench", teamBench);
			request.getRequestDispatcher("JSP/mainmenu.jsp").forward(request, response);
			return;
		}
		if(progression.equals("Auto Sort Team"))
		{
			//auto sort
			for(int i = 0; i < teamStarters.size(); i++)
			{
				if(teamStarters.get(i).getNaturalPosition().equals(Constants.attacker))
				{
					Player temp = teamStarters.get(i);
					teamStarters.remove(i);
					teamStarters.add(0, temp);
				}
				if(teamStarters.get(i).getNaturalPosition().equals(Constants.midfielder))
				{
					Player temp = teamStarters.get(i);
					teamStarters.remove(i);
					teamStarters.add(2, temp);
				}
				if(teamStarters.get(i).getNaturalPosition().equals(Constants.defender))
				{
					Player temp = teamStarters.get(i);
					teamStarters.remove(i);
					teamStarters.add(6, temp);
				}
				if(teamStarters.get(i).getNaturalPosition().equals(Constants.goalie))
				{
					Player temp = teamStarters.get(i);
					teamStarters.remove(i);
					teamStarters.add(10, temp);
				}
			}
			for(int i = 0; i < teamStarters.size(); i++)
			{
				ml.updateCurrentPosition(teamStarters.get(i), i, teamStarters);
			}
			
			//store all the values created above to the context
			STM.setAttribute("market", market);
			STM.setAttribute("league", league); 
			STM.setAttribute("ModelLoading", ml);
			
			request.setAttribute("teamStarters", teamStarters);
			request.setAttribute("teamBench", teamBench);
			request.getRequestDispatcher("JSP/mainmenu.jsp").forward(request, response);
			return;
		}
		if(progression.equals("Swap"))
		{
			Integer positionOfStarter = 0;
			Integer positionOfBench = 0; 
			for(int i = 0; i < teamStarters.size(); i++)
			{
				if(teamStarters.get(i).getName().equals(starter))
				{
					positionOfStarter = i;
				}
			}
			for(int i = 0; i < teamBench.size(); i++)
			{
				if(teamBench.get(i).getName().equals(bench))
				{
					positionOfBench = i;
				}
			}
			
			System.out.println("Starter to Swap out: "+starter);
			System.out.println("Bench player to swap in: "+bench);
			System.out.println("Position of Starter in the array: "+positionOfStarter);
			System.out.println("Position of Bench in the array: "+positionOfBench);
			System.out.println();
			
			//Swap
			Player swapOut = teamStarters.get(positionOfStarter); //in the end of the swap this player goes to Bench
			Player swapIn = teamBench.get(positionOfBench); //in the end of the swap this player goes to Starters
			teamBench.remove(swapIn);
			teamStarters.remove(swapOut);
			
			//Add them back to the team 
			teamStarters.add(positionOfStarter, swapIn);
			teamBench.add(positionOfBench, swapOut);
			
			STM.setAttribute("market", market);
			STM.setAttribute("league", league); 
			STM.setAttribute("ModelLoading", ml);
			
			request.setAttribute("teamStarters", teamStarters);
			request.setAttribute("teamBench", teamBench);
			request.getRequestDispatcher("JSP/mainmenu.jsp").forward(request, response);
			return;
		}
	}
}