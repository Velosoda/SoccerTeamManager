package com.manager.MainAppMain;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.ArrayList;

public class Constants 
{

	//STRING CONSTANTS		
	public static String goalie = "Goalie"; 
	public static String defender = "Defender"; 
	public static String midfielder = "MidFielder"; 
	public static String attacker = "Attacker";
	public static String experienced = "Experienced";
	public static String pro = "Pro";
	public static String youth = "Youth";
	public static String multiPosition = "Multi Positional";
	
	//**********************************************************************************************************************
	//SKILL VARIABLES
	public static int youthGrowth = 3;
	public static int proGrowth = 2;
	public static int expertGrowth = 1; 
	public static int levelUpRate = 1;
	public static int youthInjuryRisk = 10; 
	public static int proInjuryRisk = 20; 
	public static int expertInjuryRisk = 30; 
	public static int maxAge = 35;
	public static int minAge = 16;
	public static int youthSkillMax = 70;
	public static int youthSkillMin = 35;
	public static int proSkillMax = 100;
	public static int proSkillMin = 70;
	public static int expertSkillMax = 90;
	public static int expertSkillMin = 50;
	public static double defaultCostPerPlayer = 100000; //100,000
	public static int youthHealthMax = 550;
	public static int proHealthMax = 700;
	public static int expertHealthMax = 950;
	public static int youthHealthMin = 50;
	public static int proHealthMin = 100;
	public static int expertHealthMin = 150;
	public static ArrayList<Player> levelUpRecord = new ArrayList<Player>();
	public static ArrayList<Player> deathRecord = new ArrayList<Player>();
	
	//***********************************************************************************************************************
	//MARKET VARIABLES 
	public static int marketGoalieMin = 18;
	public static int marketGoalieExtra = 30;
	public static int marketDefenderMin = 30;
	public static int marketDefenderExtra = 42;
	public static int marketMidfielderMin = 30;
	public static int marketMidfielderExtra = 42;
	public static int marketAttackerMin = 18;
	public static int marketAttackerExtra = 30; 
	public static int marketYouthMin = 24;
	public static int marketYouthExtra = 50;
	public static int marketMultiPositionLimit = 20;
	public static int initMarketSize = 72; // does not count the Youth;  number comes from summation of all market mins
	public static int healthLimit = 1000; //based off of the highest value health can ever be
	public static int healthMultiplier = (int)(healthLimit/10); //multiplier for market
	public static int injuryRiskLimit = 40;//based off of the highest value injury risk can ever be
	public static int injuryCostMultiplier = 300;//multiplier for market
	
	//***********************************************************************************************************************
	//TEAM VARIABLES
	public static double teamBudget = 2000000; //changed to 2000000 to accomadate new addition of risk and health to overall cost
	public static int teamStadiumSize = 10000; //10,000
	public static int teamGoalieStarterLimit = 1;
	public static int teamDefenderStarterLimit = 4; 
	public static int teamMidfieldStarterLimit = 4;
	public static int teamAttackerStarterLimit = 2; 
	public static int teamBenchLimit = 6;
	public static int userTeamId = 3;
	public static int teamSize = 11;
	//public static int teamReservesLimit = 9;
	
	//***********************************************************************************************************************
	//LEAGUE VARIABLES
	public static int leagueMaxTeams = 4; // does count the users team 
	
	//***********************************************************************************************************************
	public static NumberFormat format = NumberFormat.getInstance(Locale.US);
	
	//***********************************************************************************************************************
	//GAME VARS	
	public static String namesFilePath = "WebContent/names.txt";
	public static int userTeamAutoFill = 0; // auto fill the team for test mode
	
}
