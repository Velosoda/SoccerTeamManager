package com.manager.MainAppMain;

import java.util.ArrayList;
import java.util.Random;

public class Team 
{
	Random random = new Random();
	public String name = "";
	public int overall;
	public double budget = Constants.teamBudget; 
	public int stadiumSize = Constants.teamStadiumSize;
	public int fixtureId;
	ArrayList<Player> teamMain = new ArrayList<Player>();
	ArrayList<Player> teamReserves = new ArrayList<Player>();
	
	//
	public void initPlayerPurchase(ArrayList<Player> marketArray, ArrayList<Player> team, int minPositionReq, int minIndex)
	{
		for(int i = 0; i < minPositionReq; i++)
		{
			Player player = marketArray.get(random.nextInt(minIndex));
			if(this.budget >= player.getCost())
			{
				this.budget = this.budget - player.getCost();
				team.add(player);
				marketArray.remove(player); 
			}
			else
			{
				return;
			}
		}
		return;
	}
	Team(Market market)
	{
		//Buys the starters plus 5 more players
		initPlayerPurchase(market.marketGoalie, this.teamMain, Constants.teamGoalieStarterLimit, Constants.marketGoalieMin);
		initPlayerPurchase(market.marketDefender, this.teamMain, Constants.teamDefenderStarterLimit, Constants.marketDefenderMin);
		initPlayerPurchase(market.marketMidfielder, this.teamMain, Constants.teamMidfieldStarterLimit, Constants.marketMidfielderMin);
		initPlayerPurchase(market.marketAttacker, this.teamMain, Constants.teamAttackerStarterLimit, Constants.marketAttackerMin);
		//Buys Reserves
		initPlayerPurchase(market.marketYouth, this.teamReserves, Constants.teamReservesLimit, Constants.marketYouthMin);
	}
	/*
	public static void main(String[] args)
	{
		Market market = new Market();
		Team team = new Team(market);
		for(int i = 0; i < team.teamMain.size(); i++)
		{
			Player player = team.teamMain.get(i);
			player.printStats();
		}
		System.out.println("Main Team " +team.teamMain.size());
		System.out.println("Reserves "+ team.teamReserves.size());
		System.out.println("Budget " + Constants.format.format(team.budget));

	}
*/
}