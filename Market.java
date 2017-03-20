package com.manager.MainAppMain;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;
import java.io.*;


//the array list has to be passed around until it reaches the player constructor. I tried every other method under the sun but this seems to be the only stable way. 
//at the very least the array is  still only being made once
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
	
	//mostly useless but keep it here if you want to soley test market.java
	public static ArrayList<String> makeNames(String filePath) throws IOException
	{
		
		ArrayList<String> names = new ArrayList<String>();	
		File file = new File(filePath);	
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line = br.readLine();
		while (line != null) 
		{ 
			names.add(line); line = br.readLine(); 
			}
		br.close();
		return names;
	}
	
	public void refreshMarket(Player playerToRemove)
	{
		for(int i = 0; i < marketTotal.size(); i++)
		{
			Player currentPlayer = marketTotal.get(i);
			if(currentPlayer.equals(playerToRemove))
			{
				marketTotal.remove(i);
			}
		}
	}
	
	public void fillMarket(ArrayList<String> names, ArrayList<Player> arrayToFill, String position, int limit) throws IOException
	{
		//Random Index Generation 
		Random firstName = new Random();
		Random lastName =  new Random();
		int rand1 = firstName.nextInt(names.size() -1 );
		int rand2 = lastName.nextInt(names.size()-1);
		
		Player newPlayer = new Player();
		
		//Give a player the randomly chosen name and then remove that name from the names ArrayList
		newPlayer.setName(names.get(rand1).trim() + " " + names.get(rand2).trim());
		names.remove(rand1);
		names.remove(rand2);
		
		if(limit > 0)
		{
			if(newPlayer.getAgeGroup().equals(Constants.youth) && marketYouth.size() < Constants.marketYouthExtra)
			{
				marketYouth.add(newPlayer);
				fillMarket(names,arrayToFill, position, limit);
			}
			else if(newPlayer.getNaturalPosition().equals(position) && !newPlayer.getAgeGroup().equals(Constants.youth))
			{
				arrayToFill.add(newPlayer);
				fillMarket(names,arrayToFill, position, limit-1);
			}
			else
			{
				fillMarket(names,arrayToFill, position, limit);
			}
		}
		else
		{
			return;
		}
	}
	
	Market(ArrayList<String> names) throws IOException
	{	
		
		//FILLS THE MARKET WITH THE MINIUM AMOUNT OF PLAYERS NEEDED TO FORM A TEAM
		fillMarket(names,marketGoalie, Constants.goalie, Constants.marketGoalieMin);
		fillMarket(names,marketDefender, Constants.defender, Constants.marketDefenderMin);
		fillMarket(names,marketMidfielder, Constants.midfielder, Constants.marketMidfielderMin);
		fillMarket(names,marketAttacker, Constants.attacker, Constants.marketAttackerMin);
		//FILLS THE MARKET WITH EXTRA PLAYERS 
		fillMarket(names,marketGoalie, Constants.goalie, random.nextInt(Constants.marketGoalieExtra - Constants.marketGoalieMin) + Constants.marketGoalieMin);
		fillMarket(names,marketDefender, Constants.defender, random.nextInt(Constants.marketDefenderExtra - Constants.marketDefenderMin) + Constants.marketDefenderMin);
		fillMarket(names,marketMidfielder, Constants.midfielder, random.nextInt(Constants.marketMidfielderExtra - Constants.marketMidfielderMin) + Constants.marketMidfielderMin);
		fillMarket(names,marketAttacker, Constants.attacker, random.nextInt(Constants.marketAttackerExtra - Constants.marketAttackerMin) + Constants.marketAttackerMin);
	}	
	
	public static void main(String[] args) throws IOException
	{
		Market market = new Market(makeNames(Constants.namesFilePath));
		for(int i = 0; i < market.marketDefender.size(); i++)
		{
			System.out.println((i+1));
			market.marketDefender.get(i).printStats();
		}
		System.out.println("ATT "+market.marketAttacker.size());
		System.out.println("DEF "+market.marketDefender.size());
		System.out.println("MID "+market.marketMidfielder.size());
		System.out.println("Goalie " +market.marketGoalie.size());
		System.out.println("Youth "+ market.marketYouth.size());
		System.out.println("total "+ (market.marketGoalie.size() + market.marketMidfielder.size() + market.marketDefender.size() + market.marketAttacker.size()));

		File file2 = new File("WebContent");
		for(String fileNames : file2.list()) System.out.println(fileNames);	
	}
}