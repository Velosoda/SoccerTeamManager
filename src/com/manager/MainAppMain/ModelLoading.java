package com.manager.MainAppMain;

import java.util.*;

public class ModelLoading 
{
	
	ArrayList<Player> totalMarket = new ArrayList<Player>();
	
	public void fillTotalMarket(ArrayList<Player> position)
	{
		for(int i = 0; i < position.size(); i++)
		{
			Player playerToCopy = position.get(i);
			totalMarket.add(playerToCopy);
		}
	}
	public void updateCurrentPosition(ArrayList<Player> teamStarters)
	{
		for(int i = 0; i < teamStarters.size(); i++)
		{
			Player playerBeingSwitched = teamStarters.get(i);
			if(i > 0 && i < 2)
			{
				playerBeingSwitched.setCurrentPosition(Constants.attacker);
			}
			if(i >= 2 && i < 6)
			{
				playerBeingSwitched.setCurrentPosition(Constants.midfielder);
			}
			if(i >= 6 && i < 10)
			{
				playerBeingSwitched.setCurrentPosition(Constants.defender);
			}
			if(i == 10)
			{
				playerBeingSwitched.setCurrentPosition(Constants.goalie);
			}
		}
	}
	/*
	public static void main(String[] args)
	{
		Market market = new Market();
		ModelLoading ML = new ModelLoading();
		System.out.println("marketAttacker size " + market.marketAttacker.size());
		for(int i = 0; i < market.marketAttacker.size(); i++)
		{
			market.marketAttacker.get(i).printStats();
		}
		ML.fillTotalMarket(market.marketAttacker);
		System.out.println("TotalMarket size "+ ML.totalMarket.size());
		for(int i = 0; i < ML.totalMarket.size(); i++)
		{
			ML.totalMarket.get(i).printStats();
		}
	}
	*/
}
