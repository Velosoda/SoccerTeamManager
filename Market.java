package com.manager.MainAppMain;

import java.util.ArrayList;
import java.util.Random;


public class Market
{
	Random random = new Random();
	
	ArrayList<Player> marketGoalie = new ArrayList<Player>();
	ArrayList<Player> marketDefender = new ArrayList<Player>();
	ArrayList<Player> marketMidfielder = new ArrayList<Player>();
	ArrayList<Player> marketAttacker = new ArrayList<Player>();
	ArrayList<Player> marketYouth = new ArrayList<Player>();
	ArrayList<Player> marketTotal = new ArrayList<Player>();
	public int totalMarketSize = (marketGoalie.size() + marketMidfielder.size() + marketDefender.size() + marketAttacker.size());
	public void fillMarket(ArrayList<Player> arrayToFill, String position, int limit)
	{
		Player newPlayer = new Player();
		if(limit > 0)
		{
			if(newPlayer.getAgeGroup().equals(Constants.youth) && marketYouth.size() < Constants.marketYouthExtra)
			{
				marketYouth.add(newPlayer);
				fillMarket(arrayToFill, position, limit);
			}
			else if(newPlayer.getNaturalPosition().equals(position) && !newPlayer.getAgeGroup().equals(Constants.youth))
			{
				arrayToFill.add(newPlayer);
				fillMarket(arrayToFill, position, limit-1);
			}
			else
			{
				fillMarket(arrayToFill, position, limit);
			}
		}
		else
		{
			return;
		}
	}
	Market()
	{	
		//FILLS THE MARKET WITH THE MINIUM AMOUNT OF PLAYERS NEEDED TO FORM A TEAM
		fillMarket(marketGoalie, Constants.goalie, Constants.marketGoalieMin);
		fillMarket(marketDefender, Constants.defender, Constants.marketDefenderMin);
		fillMarket(marketMidfielder, Constants.midfielder, Constants.marketMidfielderMin);
		fillMarket(marketAttacker, Constants.attacker, Constants.marketAttackerMin);
		//FILLS THE MARKET WITH EXTRA PLAYERS 
		fillMarket(marketGoalie, Constants.goalie, random.nextInt(Constants.marketGoalieExtra - Constants.marketGoalieMin) + Constants.marketGoalieMin);
		fillMarket(marketDefender, Constants.defender, random.nextInt(Constants.marketDefenderExtra - Constants.marketDefenderMin) + Constants.marketDefenderMin);
		fillMarket(marketMidfielder, Constants.midfielder, random.nextInt(Constants.marketMidfielderExtra - Constants.marketMidfielderMin) + Constants.marketMidfielderMin);
		fillMarket(marketAttacker, Constants.attacker, random.nextInt(Constants.marketAttackerExtra - Constants.marketAttackerMin) + Constants.marketAttackerMin);
	}	
	
	public static void main(String[] args)
	{
		Market market = new Market();
		for(int i = 0; i < market.marketAttacker.size(); i++)
		{
			System.out.println((i+1));
			market.marketAttacker.get(i).printStats();
		}
		System.out.println("ATT "+market.marketAttacker.size());
		System.out.println("DEF "+market.marketDefender.size());
		System.out.println("MID "+market.marketMidfielder.size());
		System.out.println("Goalie " +market.marketGoalie.size());
		System.out.println("Youth "+ market.marketYouth.size());
		System.out.println("total "+ (market.marketGoalie.size() + market.marketMidfielder.size() + market.marketDefender.size() + market.marketAttacker.size()));
	}
	
}

