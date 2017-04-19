package com.manager.MainAppMain;

import java.util.ArrayList;

public class Tools 
{
	public static void main(String[] args)
	{
		try
		{
			ArrayList<Player> placeholderlist = new ArrayList<Player>();
			ArrayList<Player> userTeam = new ArrayList<Player>();
			
			//fill lists
			System.out.println("Placeholder team ");
			for(int i = 0; i < 11; i++)
			{
				Player placeholder = new Player();
				placeholder.setName("placeholder");
				placeholder.printStats();
				placeholderlist.add(placeholder);
			}
			
			System.out.println("userTeam team");
			for(int i = 0; i < 11; i++)
			{
				Player actualPlayer = new Player();
				actualPlayer.setName("actualPlayer");
				actualPlayer.printStats();
				userTeam.add(actualPlayer);
			}
			
			//add userteam elements to the placeholder list
			for(int i = 0; i < userTeam.size(); i++)
			{
				if(userTeam.get(i).getNaturalPosition().equals(Constants.attacker))
				{
					placeholderlist.add(0, userTeam.get(i));
				}
			}
			
			System.out.println("new placeholderlist");
			for(int i = 0; i < placeholderlist.size(); i++)
			{
				placeholderlist.get(i).printStats();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
