package com.manager.MainAppMain;

import java.io.*;
public class Match {

	private Team winner;
	private Team loser;
	private Integer match1_1;
	private Integer match1_2;
	private Integer match2_1;
	private Integer match2_2; 
	
	Match()
	{
		
	}
	Match(Team a, Team b)
	{
		System.out.println("A is team " + a.getId() + " B is team " + b.getId());
		//determine winner of atk skill
		for(int i = 0 ;i<2; i++)
		{
			if(a.teamStarters.get(i).getAttackSkill() < b.teamStarters.get(i).getAttackSkill() )
			{
				b.setPoints(b.getPoints()+ 1);
			}
			else
				a.setPoints(a.getPoints()+1);
		}
		System.out.println("A points:" + a.getPoints() + " B points: " + b.getPoints());
		//determine winner of mf skill
		for(int i =2; i < 6 ; i++)
		{
			if(a.teamStarters.get(i).getMidfieldSkill() < b.teamStarters.get(i).getMidfieldSkill())
			{
				b.setPoints(b.getPoints()+ 1);
			}
			else
				a.setPoints(a.getPoints()+ 1);
		}
		System.out.println("A points:" + a.getPoints() + " B points: " + b.getPoints());
		//determine winner of d skill
		for(int i =6; i < 10 ; i++)
		{
			if(a.teamStarters.get(i).getDefenseSkill() < b.teamStarters.get(i).getDefenseSkill())
			{
				b.setPoints(b.getPoints()+ 1);
			}
			else
				a.setPoints(a.getPoints()+ 1);
		}
		System.out.println("A points:" + a.getPoints() + " B points: " + b.getPoints());
		//determine winner of goalie skill
			if(a.teamStarters.get(10).getGoalieSkill() < b.teamStarters.get(10).getGoalieSkill())
			{
				b.setPoints(b.getPoints()+ 1);
			}
			else
				a.setPoints(a.getPoints()+ 1);
			System.out.println("A points:" + a.getPoints() + " B points: " + b.getPoints());
		//judge who is the winner based on who has the most points
		if(a.getPoints() < b.getPoints())
		{
			this.setWinner(b); this.setLoser(a);
		}
		else
		{
			this.setWinner(a); this.setLoser(b);
		}
	}
	public void firstMatch(League league)
	{
		for(int i = 0; i < league.allTeams.size(); i++)
		{
			for(int j = 0; j < league.allTeams.get(i).matches.size(); j++)
			{
				if(getMatch1_1() == null)
				{
					setMatch1_1(league.allTeams.get(i).getId());
					setMatch1_2(league.allTeams.get(i).matches.get(0));
					league.allTeams.get(i).matches.remove(0);
					return;
				}
			}
		}
	}
	public void secondMatch(League league)
	{
		for(int i = 0; i < league.allTeams.size(); i++)
		{
			for(int j = 0; j < league.allTeams.get(i).matches.size(); j++)
			{
				if(i == getMatch1_1() || i == getMatch1_2())
				{
					continue;
				}
				else
				{
					setMatch2_1(league.allTeams.get(i).getId());
					if(league.allTeams.get(i).matches.get(j) == getMatch1_1() || league.allTeams.get(i).matches.get(j) == getMatch1_2())
					{
						continue;
					}
					else
					{
						setMatch2_2(league.allTeams.get(i).matches.get(j));
						league.allTeams.get(i).matches.remove(j);
						return;
					}
				}
			}
		}
	}
	public void matchDayMatchups(League league)
	{
		setMatch1_1(null);
		setMatch1_2(null);
		setMatch2_1(null);
		setMatch2_2(null);
		firstMatch(league);
		secondMatch(league);
		System.out.println("match1_1 : " + getMatch1_1());
		System.out.println("match1_2 : " + getMatch1_2());
		System.out.println("match2_1 : " + getMatch2_1());
		System.out.println("match2_2 : " + getMatch2_2());
	}
	public static void main(String[] args) throws IOException
	{
//		Match match = new Match(league.allTeams.get(1), league.allTeams.get(2));
//		System.out.println(match.winner.getId());
		Market market = new Market(Market.makeNames(Constants.namesFilePath));
		League league = new League(market);
		League.viewSchedule();
		Match match = new Match();
		for(int i = 0; i < 6; i++)
		{
			System.out.println("Match day " + (i+1));
			match.matchDayMatchups(league);
		}
	}
	
	public Team getLoser() {
		return loser;
	}
	public void setLoser(Team loser) {
		this.loser = loser;
	}
	public Team getWinner() {
		return winner;
	}
	public void setWinner(Team winner) {
		this.winner = winner;
	}
	public Integer getMatch1_1() {
		return match1_1;
	}
	public void setMatch1_1(Integer match1_1) {
		this.match1_1 = match1_1;
	}
	public Integer getMatch1_2() {
		return match1_2;
	}
	public void setMatch1_2(Integer match1_2) {
		this.match1_2 = match1_2;
	}
	public Integer getMatch2_1() {
		return match2_1;
	}
	public void setMatch2_1(Integer match2_1) {
		this.match2_1 = match2_1;
	}
	public Integer getMatch2_2() {
		return match2_2;
	}
	public void setMatch2_2(Integer match2_2) {
		this.match2_2 = match2_2;
	}
}
