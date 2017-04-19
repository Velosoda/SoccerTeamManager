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
	public void updateCurrentPosition(Player playerBeingSwitched, int positionInArray, ArrayList<Player> teamStarters)
	{
		if(positionInArray > 0 && positionInArray < 2)
		{
			playerBeingSwitched.setCurrentPosition(Constants.attacker);
		}
		if(positionInArray >= 2 && positionInArray < 6)
		{
			playerBeingSwitched.setCurrentPosition(Constants.midfielder);
		}
		if(positionInArray >= 6 && positionInArray < 10)
		{
			playerBeingSwitched.setCurrentPosition(Constants.defender);
		}
		if(positionInArray == 10)
		{
			playerBeingSwitched.setCurrentPosition(Constants.goalie);
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
