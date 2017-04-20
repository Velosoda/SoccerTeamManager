package com.manager.MainAppMain;

import java.util.ArrayList;

public class Team 
{
	private double Budget = Constants.teamBudget; //1,300,000
	private int StadiumSize = Constants.teamStadiumSize; //10,000
	private int GoalieStarterLimit = Constants.teamGoalieStarterLimit;
	private int DefenderStarterLimit = Constants.teamDefenderStarterLimit; 
	private int AttackerStarterLimit = Constants.teamAttackerStarterLimit; 
	private int goalieCount;
	private int defenderCount;
	private int MidfieldStarterLimit = Constants.teamMidfieldStarterLimit;
	private int midfieldCount;
	private int attackerCount;
	private int benchMax = Constants.teamBenchLimit;
	private int points = 0;
	private int goals = 0;
	private double costOfPFM = 0;
	private int id;
	private  int leaguePoints = 0;
	ArrayList<Player> teamStarters = new ArrayList<Player> ();
	ArrayList<Player> teamBench = new ArrayList<Player> (benchMax);
	ArrayList<Integer> matches = new ArrayList<Integer>();
	ArrayList<Player> selectedPFM = new ArrayList<Player>(); //Players From Market : PFM
	
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
	
	public void setPlayerId()
	{
		for(int i = 0 ; i < this.teamStarters.size() ; i++)
		{
			this.teamStarters.get(i).setCurrentTeam(this.getId());
			System.out.println(this.teamStarters.get(i).getName() + " has team ID of " + this.getId());
		}
	}
	
	//check if any players in the team had an injury in the match
	public void injuryCheck()
	{
		for(int i = 0; i < this.teamStarters.size(); i++)
		{
			this.teamStarters.get(i).injuryCheck();
			if(this.teamStarters.get(i).getDeath() == 1)
			{
				//teamStarters.remove(i);  this should be turned on when moving players around is safe
				System.out.println(this.teamStarters.get(i).getName() + " has died");
				this.teamBench.add(this.teamStarters.get(i));
				this.teamStarters.remove(this.teamStarters.get(i));
				
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
		//setCurrentPositions(teamStarters);
	}	
	
	public void setCurrentPositions()
	{
		
		for (int i = 0 ; i < 2 ; i++)
		{
			this.teamStarters.get(i).setCurrentPosition(Constants.attacker);
		}
		for (int i = 0 ; i < 6 ; i++)
		{
			this.teamStarters.get(i).setCurrentPosition(Constants.midfielder);
		}
		for (int i = 0 ; i < 11 ; i++)
		{
			this.teamStarters.get(i).setCurrentPosition(Constants.defender);
		}
			this.teamStarters.get(10).setCurrentPosition(Constants.goalie);
	}
	
	public void setCurrentSkills()
	{
		for (int i = 0 ; i < 2 ; i++)
		{
			this.teamStarters.get(i).setCurrentSkillValue(this.teamStarters.get(i).getAttackSkill());
		}
		for (int i = 0 ; i < 6 ; i++)
		{
			this.teamStarters.get(i).setCurrentSkillValue(this.teamStarters.get(i).getMidfieldSkill());
		}
		for (int i = 0 ; i < 11 ; i++)
		{
			this.teamStarters.get(i).setCurrentSkillValue(this.teamStarters.get(i).getDefenseSkill());
		}
		this.teamStarters.get(10).setCurrentSkillValue(this.teamStarters.get(10).getDefenseSkill());
	}
	
	
	
	Team(String x)
	{
		
	}
	
	public  static void main(String[] args)
	{
		//Market market = new Market();
		//Team team = new Team(market);
		
		
		/* **********************Prints stats of all players for a TEST team
		for(int i = 0; i < team.teamStarters.size(); i++)
		{
			System.out.println("player number " + i);
			Player x = team.teamStarters.get(i);
			x.printStats();
		}
		*/
	}

	public int getLeaguePoints() {
		return leaguePoints;
	}

	public void setLeaguePoints(int leaguePoints) {
		this.leaguePoints = leaguePoints;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
		public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public int getGoals() {
		return goals;
	}

	public void setGoals(int goals) {
		this.goals = goals;
	}

	public double getCostOfPFM() {
		return costOfPFM;
	}

	public void setCostOfPFM(double costOfPFM) {
		this.costOfPFM = costOfPFM;
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
