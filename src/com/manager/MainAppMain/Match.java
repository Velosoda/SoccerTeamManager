package com.manager.MainAppMain;

import java.io.*;
public class Match {

	private Team winner;
	private Team loser;
	
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
			this.setWinner(a); this.setLoser(b);
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
	
	
	public static void main(String[] args) throws IOException
	{
		
		Market market = new Market(Market.makeNames(Constants.namesFilePath));
		League league = new League(market);
		Match match = new Match(league.allTeams.get(1), league.allTeams.get(2));
		System.out.println(match.winner.getId());
	}
}
