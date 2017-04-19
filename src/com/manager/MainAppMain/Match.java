package com.manager.MainAppMain;

import java.io.*;
import java.util.ArrayList;
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
	public void playerEvaluation(Team a, Team b)
	{
		a.setPoints(0);
		b.setPoints(0);
		System.out.println("A is team " + a.getId() + " B is team " + b.getId());
		//determine winner of atk skill
		for(int i = 0 ;i<2; i++)
		{
			if(a.teamStarters.get(i).getAttackSkill() < b.teamStarters.get(i).getAttackSkill() )
			{
				b.setPoints(b.getPoints()+ 1);
				b.teamStarters.get(i).levelUp();
				a.teamStarters.get(i).reprisal();
			}
			else
				a.setPoints(a.getPoints()+1);
				a.teamStarters.get(i).levelUp();
				b.teamStarters.get(i).reprisal();	
		}
		System.out.println("A points:" + a.getPoints() + " B points: " + b.getPoints());
		//determine winner of mf skill
		for(int i =2; i < 6 ; i++)
		{
			if(a.teamStarters.get(i).getMidfieldSkill() < b.teamStarters.get(i).getMidfieldSkill())
			{
				b.setPoints(b.getPoints()+ 1);
				b.teamStarters.get(i).levelUp();
				a.teamStarters.get(i).reprisal();
			}
			else
				a.setPoints(a.getPoints()+ 1);
				a.teamStarters.get(i).levelUp();
				b.teamStarters.get(i).reprisal();
		}
		System.out.println("A points:" + a.getPoints() + " B points: " + b.getPoints());
		//determine winner of d skill
		for(int i =6; i < 10 ; i++)
		{
			if(a.teamStarters.get(i).getDefenseSkill() < b.teamStarters.get(i).getDefenseSkill())
			{
				b.setPoints(b.getPoints()+ 1);
				b.teamStarters.get(i).levelUp();
				a.teamStarters.get(i).reprisal();
			}
			else
				a.setPoints(a.getPoints()+ 1);
				a.teamStarters.get(i).levelUp();
				b.teamStarters.get(i).reprisal();
		}
		System.out.println("A points:" + a.getPoints() + " B points: " + b.getPoints());
		//determine winner of goalie skill
			if(a.teamStarters.get(10).getGoalieSkill() < b.teamStarters.get(10).getGoalieSkill())
			{
				b.setPoints(b.getPoints()+ 1);
				b.teamStarters.get(10).levelUp();
				a.teamStarters.get(10).reprisal();
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
	public void playMatch(Team a, Team b)
	{
		
		ArrayList<Player> teamb = b.teamStarters;
		ArrayList<Player> teama = a.teamStarters;
		int atkAvgA = (teama.get(0).getAttackSkill() + teama.get(1).getAttackSkill()) / 2;
		int atkAvgB = (teamb.get(0).getAttackSkill() + teamb.get(1).getAttackSkill()) / 2;
		if(atkAvgA > atkAvgB)
		{
			a.setGoals(a.getGoals() + 1);
		}
		else if(atkAvgA == atkAvgB)
		{
			a.setGoals(a.getGoals() + 1);
			b.setGoals(b.getGoals() + 1);
		}
		else 
		{
			b.setGoals(b.getGoals() + 1);
		}
		
		int mfAvgA = (teama.get(2).getMidfieldSkill() + teama.get(3).getMidfieldSkill()  + teama.get(4).getMidfieldSkill() + teama.get(5).getMidfieldSkill()) / 4;
		int mfAvgB = (teamb.get(2).getMidfieldSkill() + teamb.get(3).getMidfieldSkill()  + teamb.get(4).getMidfieldSkill() + teamb.get(5).getMidfieldSkill()) / 4;
		if(mfAvgA > mfAvgB)
		{
			a.setGoals(a.getGoals() + 1);
		}
		else if(mfAvgA == mfAvgB)
		{
			a.setGoals(a.getGoals() + 1);
			b.setGoals(b.getGoals() + 1);
		}
		else 
		{
			b.setGoals(b.getGoals() + 1);
		}
		
		int dAvgA = (teama.get(6).getDefenseSkill() + teama.get(7).getDefenseSkill()  + teama.get(8).getDefenseSkill() + teama.get(9).getDefenseSkill()) / 4;
		int dAvgB = (teamb.get(6).getDefenseSkill() + teamb.get(7).getDefenseSkill()  + teamb.get(8).getDefenseSkill() + teamb.get(9).getDefenseSkill()) / 4;
		if(dAvgA > dAvgB)
		{
			a.setGoals(a.getGoals() + 1);
		}
		else if(dAvgA == dAvgB)
		{
			a.setGoals(a.getGoals() + 1);
			b.setGoals(b.getGoals() + 1);
		}
		else 
		{
			b.setGoals(b.getGoals() + 1);
		}
		
		
		int gAvgA = teama.get(10).getGoalieSkill();
		int gAvgB = (teamb.get(10).getGoalieSkill());
		if(gAvgA > gAvgB)
		{
			a.setGoals(a.getGoals() + 1);
		}
		else if(gAvgA == gAvgB)
		{
			a.setGoals(a.getGoals() + 1);
			b.setGoals(b.getGoals() + 1);
		}
		else 
		{
			b.setGoals(b.getGoals() + 1);
		}
		System.out.println("atk avg A is " + atkAvgA);
		System.out.println("atk avg B is " + atkAvgB);
		System.out.println("mf avg A is " + mfAvgA);
		System.out.println("mf avg B is " + mfAvgB);
		System.out.println("d avg A is " + dAvgA);
		System.out.println("d avg B is " + dAvgB);
		System.out.println("g avg A is " + gAvgA);
		System.out.println("g avg B is " + gAvgB);
		System.out.println("A scored " + a.getGoals() + " goals");
		System.out.println("B scored " + b.getGoals() + " goals");
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

		Market market = new Market(Market.makeNames(Constants.namesFilePath));
		League league = new League(market);
		League.viewSchedule();
		Match match = new Match();
		
		System.out.println("**************Team 1***********************");
		//league.allTeams.get(0).printAllstats();
		System.out.println("*******************Team 2**************");
		//league.allTeams.get(1).printAllstats();
		
		for(int i = 0; i < 1 ; i++)
		{
			match.playerEvaluation(league.allTeams.get(0), league.allTeams.get(1));
			match.playMatch(league.allTeams.get(0), league.allTeams.get(1));
			
		}
		
		System.out.println("******************Team 1***************");
		//league.allTeams.get(0).printAllstats();
		System.out.println("*********************Team 2****************");
		//league.allTeams.get(1).printAllstats();
		
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
