package com.manager.MainAppMain;

public class Tools 
{
	public static int initialGoalieTotal = 12; 
	public static int initialDefenderTotal = 24; 
	public static int initialMidFielderTotal = 24;
	public static int initialAttackerTotal = 12;
	public static int initialRandomPlayerTotal = 64; 
	Market market = new Market();
	public void populateMarket(int initialGoalieTotal, int initialDefenderTotal, int initialMidFielderTotal, int initialAttackerTotal, int initialRandomPlayerTotal)
	{
		if(market.marketGoalie.size() < initialGoalieTotal)
		{
			Player newGoalie = new Player();
			newGoalie.naturalPosition = Constants.goalie;
			populateMarket(initialGoalieTotal, initialDefenderTotal, initialMidFielderTotal, initialAttackerTotal, initialRandomPlayerTotal);
		}
		else
		{
			return;
		}
		if(market.marketDefenders.size() < initialDefenderTotal)
		{
			Player newDefender = new Player();
			newDefender.naturalPosition = Constants.defender;
			populateMarket(initialGoalieTotal, initialDefenderTotal, initialMidFielderTotal, initialAttackerTotal, initialRandomPlayerTotal);
		}
		else
		{
			return;
		}
		if(market.marketMidFielders.size() < initialMidFielderTotal)
		{
			Player newMidFielder = new Player();
			newMidFielder.naturalPosition = Constants.midFielder;
			populateMarket(initialGoalieTotal, initialDefenderTotal, initialMidFielderTotal, initialAttackerTotal, initialRandomPlayerTotal);
		}
		else
		{
			return;
		}
		if(market.marketAttackers.size() < initialMidFielderTotal)
		{
			Player newMidFielder = new Player();
			newMidFielder.naturalPosition = Constants.attacker;
			populateMarket(initialGoalieTotal, initialDefenderTotal, initialMidFielderTotal, initialAttackerTotal, initialRandomPlayerTotal);
		}
		else
		{
			return;
		}
	}
	public static void main(String[] args)
	{
		Tools tools = new Tools();
		tools.populateMarket(initialGoalieTotal, initialDefenderTotal, initialMidFielderTotal, initialAttackerTotal, initialRandomPlayerTotal);
	}
}
