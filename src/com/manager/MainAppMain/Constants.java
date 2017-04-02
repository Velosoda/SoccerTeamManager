package com.manager.MainAppMain;

import java.text.NumberFormat;
import java.util.Locale;

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
	public static int youthInjuryRisk = 1; 
	public static int proInjuryRisk = 2; 
	public static int expertInjuryRisk = 3; 
	public static int maxAge = 35;
	public static int minAge = 16;
	public static int youthSkillMax = 70;
	public static int youthSkillMin = 35;
	public static int proSkillMax = 100;
	public static int proSkillMin = 70;
	public static int expertSkillMax = 90;
	public static int expertSkillMin = 50;
	public static double defaultCostPerPlayer = 100000; //100,000
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
	//***********************************************************************************************************************
	//TEAM VARIABLES
	public static double teamBudget = 1300000; //1,300,000
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
}
