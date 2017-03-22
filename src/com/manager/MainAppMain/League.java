package com.manager.MainAppMain;

import java.util.*;

public class League 
{
	private int maxNoCPUTeams = 3;
	private int maxNoTeams = 4; 
	private int maxNoGames = 12;
	private int maxNoHomeGames = 3;
	private int currentTeam = 0;
	ArrayList<Team> allTeams = new ArrayList<Team>();
	League(Market market)
	{
		addTeamsToLeague(market);
		generateMatchUps(allTeams);
//		System.out.println(allTeams.size());
		/*
		for(int i = 0; i < getMaxNoTeams(); i++)
		{
			Match newMatch = new Match();
			newMatch.setHomeTeam(i);
			for(int j = 0; j < getMaxNoHomeGames(); j++)
			{
				if(i != j)
				{
					newMatch.setAwayTeam(j);
					matchUps.add(newMatch);
				}
			}
		}
		*/
	}
	public void generateMatchUps(ArrayList<Team> league)//Each element of the League array gets their own array that includes all OTHER teams
	{
		for(int i = 0; i < Constants.leagueMaxTeams; i++)
		{
			for(int j = 0 ; j < Constants.leagueMaxTeams; j ++)
			{
				Team currentTeam = league.get(i);
				if(currentTeam.getId() == j)
				{
					continue;
				}
				currentTeam.matches.add(league.get(j).getId());
			}
		}
	}
	public void addTeamsToLeague(Market market)
	{
		for(int i = 0; i < Constants.leagueMaxTeams-1; i++)
		{
			Team newTeam = new Team(market);
			newTeam.setId(i);
			allTeams.add(newTeam);	
		}
		Team usersTeam = new Team("user");
		usersTeam.setId(Constants.leagueMaxTeams-1);
		allTeams.add(usersTeam);
		System.out.println(allTeams.size());
	}
	
	/*public static void main(String[] args)
	{
		Market market = new Market();
		League league = new League(market);
		for(int i = 0; i < league.allTeams.size(); i++)
		{
			for(int j = 0; j < league.allTeams.get(i).matches.size(); j++)
			{
				System.out.println("Team " + league.allTeams.get(i).getId() + " VS Team " + league.allTeams.get(i).matches.get(j));
			}
		}
	}
	*/
	
	//getter and setters
	public int getMaxNoHomeGames() {
		return maxNoHomeGames;
	}
	public void setMaxNoHomeGames(int maxNoHomeGames) {
		this.maxNoHomeGames = maxNoHomeGames;
	}
	public int getMaxNoCPUTeams() {
		return maxNoCPUTeams;
	}
	public void setMaxNoCPUTeams(int maxNoCPUTeams) {
		this.maxNoCPUTeams = maxNoCPUTeams;
	}
	public int getMaxNoGames() {
		return maxNoGames;
	}
	public void setMaxNoGames(int maxNoGames) {
		this.maxNoGames = maxNoGames;
	}
	public int getMaxNoTeams() {
		return maxNoTeams;
	}
	public void setMaxNoTeams(int maxNoTeams) {
		this.maxNoTeams = maxNoTeams;
	}
	public int getCurrentTeam() {
		return currentTeam;
	}
	public void setCurrentTeam(int currentTeam) {
		this.currentTeam = currentTeam;
	}
}
