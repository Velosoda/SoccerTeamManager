package com.manager.MainAppMain;

import java.util.ArrayList;
import java.util.Random;

public class Team 
{
	private  double Budget = Constants.teamBudget; //1,300,000
	private  int StadiumSize = Constants.teamStadiumSize; //10,000
	private  int GoalieStarterLimit = Constants.teamGoalieStarterLimit;
	private  int DefenderStarterLimit = Constants.teamDefenderStarterLimit; 
	private  int MidfieldStarterLimit = Constants.teamMidfieldStarterLimit;
	private  int AttackerStarterLimit = Constants.teamAttackerStarterLimit; 
	private  int goalieCount;
	private  int defenderCount;
	private  int midfieldCount;
	private  int attackerCount;
	private  int benchMax = Constants.teamBenchLimit;
	//ID*************************
	private int id;
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}
	ArrayList<Player> teamStarters = new ArrayList<Player> ();
	ArrayList<Player> teamBench = new ArrayList<Player> (benchMax);
	static ArrayList<Team> matches = new ArrayList<Team>();
	
	
	Team(Market market)
	{
		for(int positionInTeamStarters = 0; positionInTeamStarters < 11; positionInTeamStarters++)
		{
			//System.out.println("for loop:" + positionInTeamStarters);
			if( positionInTeamStarters <= 1)
			{
				//System.out.println("attacker add");
				addPlayer(market.marketAttacker, positionInTeamStarters, market);
			}
			if( positionInTeamStarters  > 1 &&   positionInTeamStarters  <= 5)
			{
				//System.out.println("midfielder add");
				addPlayer(market.marketMidfielder, positionInTeamStarters, market);
			}

			if( positionInTeamStarters  > 5 &&   positionInTeamStarters  <= 9)
			{
				//System.out.println("defender add");
				addPlayer(market.marketDefender, positionInTeamStarters, market);
			}
			
			if( positionInTeamStarters  == 10)
			{
				//System.out.println("goalie add");
				addPlayer(market.marketGoalie, positionInTeamStarters, market);
			}
		}		
	}
	
	
	public void addPlayer(ArrayList<Player> origin, int positionInTeamStarters, Market market)
	{
		for(int i = 0; i < origin.size(); i++)
		{
			Player potentialBuy = origin.get(i);
			if (potentialBuy.getCost() < getBudget())
			{
				//System.out.println(  origin);
				teamStarters.add(positionInTeamStarters, potentialBuy);
				setBudget(getBudget() - potentialBuy.getCost());
				origin.remove(potentialBuy);
				market.refreshMarket(potentialBuy);
				break;
			}
		}
	}	

	//Begin Edit	*****************************************************
	
	public static ArrayList<Team> makeLeague(Market market)//makes league by taking in a market as an argument, when the League gets made so too do the teams themselves
	{
		ArrayList<Team> league = new ArrayList<Team>();
		for(int i = 0; i < Constants.leagueMaxTeams; i++)
		{
			league.add(new Team(market));
			league.get(i).setId(i);//IDs are created for the team based on their index. After this they have a set ID thats easy to reference
		}		
		return league;
	}
	
	/*  //*****Broken, This is what I salvaged from trying to make the actual match with regards to what team is going to play against what other team******
	public static Team[] makeMatch(ArrayList<Team> league)
	{
		
		Team[] match = new Team[2];
		
	    match[0] = homeTeam;
		match[1] = awayTeam;
		System.out.println("home team: " + homeTeam.getId());
		System.out.println("away team: " + awayTeam.getId());
		league.get(homeTeam.getId()).matches.remove(awayTeam);
		System.out.println(homeTeam.getId() + " versus " + awayTeam.getId());
		return match;
	}
	*/
	
	public static void matchup(ArrayList<Team> league)//Each element of the League array gets their own array that includes all OTHER teams
	{
		
		for(int i = 0; i < league.size(); i++)
		{
			for(int j = 0 ; j < league.size(); j ++)
			{
			if(j == league.get(i).getId())
					continue;
				else
					league.get(i).matches.add(league.get(j));
				
				System.out.println("Matchups Testing: Team " + i + " will face " + j);
			}
		}
	}

	//End Edit
	
	Team(String x)
	{
		
	}
	
	
	public  static void main(String[] args)
	{
		Market market = new Market();
		Team team = new Team(market);
		ArrayList<Team> league = makeLeague(market); //League is made here using the market made in line 139
		matchup(league);//League is sent to matchup and the console should show who will play against who
		
		
		/* **********************Prints stats of all players for a TEST team
		for(int i = 0; i < team.teamStarters.size(); i++)
		{
			System.out.println("player number " + i);
			Player x = team.teamStarters.get(i);
			x.printStats();
		}
		*/
	}
	
	
	
	
	
	public double getBudget() {
		return Budget;
	}
	public void setBudget(double budget) {
		Budget = budget;
	}
	public int getStadiumSize() {
		return StadiumSize;
	}
	public void setStadiumSize(int stadiumSize) {
		StadiumSize = stadiumSize;
	}
	public int getGoalieStarterLimit() {
		return GoalieStarterLimit;
	}
	public void setGoalieStarterLimit(int goalieStarterLimit) {
		GoalieStarterLimit = goalieStarterLimit;
	}
	public int getDefenderStarterLimit() {
		return DefenderStarterLimit;
	}
	public void setDefenderStarterLimit(int defenderStarterLimit) {
		DefenderStarterLimit = defenderStarterLimit;
	}
	public int getMidfieldStarterLimit() {
		return MidfieldStarterLimit;
	}
	public void setMidfieldStarterLimit(int midfieldStarterLimit) {
		MidfieldStarterLimit = midfieldStarterLimit;
	}
	public int getAttackerStarterLimit() {
		return AttackerStarterLimit;
	}
	public void setAttackerStarterLimit(int attackerStarterLimit) {
		AttackerStarterLimit = attackerStarterLimit;
	}
	public int getGoalieCount() {
		return goalieCount;
	}
	public void setGoalieCount(int goalieCount) {
		this.goalieCount = goalieCount;
	}
	public int getDefenderCount() {
		return defenderCount;
	}
	public void setDefenderCount(int defenderCount) {
		this.defenderCount = defenderCount;
	}
	public int getMidfieldCount() {
		return midfieldCount;
	}
	public void setMidfieldCount(int midfieldCount) {
		this.midfieldCount = midfieldCount;
	}
	public int getAttackerCount() {
		return attackerCount;
	}
	public void setAttackerCount(int attackerCount) {
		this.attackerCount = attackerCount;
	}
	public int getBenchMax() {
		return benchMax;
	}
	public void setBenchMax(int benchMax) {
		this.benchMax = benchMax;
	}
}
/*{
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

}
*/
