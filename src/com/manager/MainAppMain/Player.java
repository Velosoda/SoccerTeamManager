package com.manager.MainAppMain;

import java.util.Random;

public class Player 
{
	Random random = new Random();
	public String currentTeam = "";
	public String naturalPosition = "";
	public String currentPosition = ""; 
	public int attackSkill;
	public int midfieldSkill;
	public int defenceSkill;
	public int goalieSkill;
	public Double cost = Constants.defaultCostPerPlayer;
	public int overall;
	public int age;
	public int growth;
	public int injuryRisk ; 
	public String ageGroup;
	Player()
	{
		this.age = random.nextInt(Constants.maxAge - Constants.minAge) + Constants.minAge;
		if(age <= 20)
		{
			this.goalieSkill = random.nextInt(Constants.youthSkillMax - Constants.youthSkillMin) + Constants.youthSkillMin;
			this.attackSkill = random.nextInt(Constants.youthSkillMax - Constants.youthSkillMin) + Constants.youthSkillMin;
			this.defenceSkill = random.nextInt(Constants.youthSkillMax - Constants.youthSkillMin) + Constants.youthSkillMin;
			this.midfieldSkill = random.nextInt(Constants.youthSkillMax - Constants.youthSkillMin) + Constants.youthSkillMin;
			this.growth = Constants.youthGrowth;
			this.injuryRisk = Constants.youthInjuryRisk;
			positionDeterminator();
			this.ageGroup = Constants.youth;
		}
		else if(age > 20 && age <= 30)
		{			
			this.goalieSkill = random.nextInt(Constants.proSkillMax - Constants.proSkillMin) + Constants.proSkillMin;
			this.attackSkill = random.nextInt(Constants.proSkillMax - Constants.proSkillMin) + Constants.proSkillMin;
			this.defenceSkill = random.nextInt(Constants.proSkillMax - Constants.proSkillMin) + Constants.proSkillMin;
			this.midfieldSkill = random.nextInt(Constants.proSkillMax - Constants.proSkillMin) + Constants.proSkillMin;
			this.growth = Constants.proGrowth;
			this.injuryRisk = Constants.proInjuryRisk;
			positionDeterminator();
			this.ageGroup = Constants.pro;
		}
		else if(age > 30)
		{
			this.goalieSkill = random.nextInt(Constants.expertSkillMax - Constants.expertSkillMin) + Constants.expertSkillMin;
			this.attackSkill = random.nextInt(Constants.expertSkillMax - Constants.expertSkillMin) + Constants.expertSkillMin;
			this.defenceSkill = random.nextInt(Constants.expertSkillMax - Constants.expertSkillMin) + Constants.expertSkillMin;
			this.midfieldSkill = random.nextInt(Constants.expertSkillMax - Constants.expertSkillMin) + Constants.expertSkillMin;
			this.growth = Constants.expertGrowth;
			this.injuryRisk = Constants.expertInjuryRisk;
			positionDeterminator();
			this.ageGroup = Constants.experienced;
		}
		this.cost = (this.cost * this.overall)/100;
		
	}
	public void positionDeterminator()
	{
		if(this.attackSkill > this.defenceSkill && this.attackSkill > this.midfieldSkill && this.attackSkill > this.goalieSkill)
		{
			this.naturalPosition = Constants.attacker;
			this.goalieSkill = random.nextInt(Constants.youthSkillMin);
			this.overall = (this.attackSkill + this.midfieldSkill + this.defenceSkill)/3;
			return;
		}
		else if(this.defenceSkill > this.midfieldSkill && this.defenceSkill > this.attackSkill && this.defenceSkill > this.goalieSkill)
		{
			this.naturalPosition = Constants.defender;
			this.goalieSkill = random.nextInt(Constants.youthSkillMin);
			this.overall = (this.attackSkill + this.midfieldSkill + this.defenceSkill)/3;
			return;
		}
		else if(this.midfieldSkill > this.attackSkill && this.midfieldSkill > this.defenceSkill && this.midfieldSkill > this.goalieSkill)
		{
			this.naturalPosition = Constants.midfielder;
			this.goalieSkill = random.nextInt(Constants.youthSkillMin);
			this.overall = (this.attackSkill + this.midfieldSkill + this.defenceSkill)/3;
			return;
		}
		else if(this.goalieSkill > this.attackSkill && this.goalieSkill > this.midfieldSkill && this.goalieSkill > this.defenceSkill)
		{
			this.naturalPosition = Constants.goalie;
			this.attackSkill = random.nextInt(Constants.youthSkillMin);
			this.defenceSkill = random.nextInt(Constants.youthSkillMin);
			this.midfieldSkill = random.nextInt(Constants.youthSkillMin);
			this.overall = this.goalieSkill;
			return;
		}
		else
		{
			if(this.attackSkill == this.midfieldSkill || this.attackSkill == this.defenceSkill)
			{
				this.naturalPosition = Constants.attacker;
				this.overall = (this.attackSkill + this.midfieldSkill + this.defenceSkill)/3;
				this.cost = (this.cost * this.overall)/100;
			}
			else if(this.midfieldSkill == this.defenceSkill)
			{
				this.naturalPosition = Constants.midfielder;
				this.overall = (this.attackSkill + this.midfieldSkill + this.defenceSkill)/3;
				this.cost = (this.cost * this.overall)/100;
			}
			else if(this.attackSkill == this.goalieSkill || this.midfieldSkill == this.goalieSkill || this.defenceSkill == this.goalieSkill)
			{
				this.naturalPosition = Constants.goalie;
				this.overall = this.goalieSkill;
				this.cost = (this.cost * this.overall)/100;
			}
			return;
		}
	}
	public void printStats()
	{
		System.out.println("Age: " + this.age);
		System.out.println("Age Group: " + this.ageGroup);
		System.out.println("Attack: " + this.attackSkill);
		System.out.println("MidField: "+ this.midfieldSkill);
		System.out.println("Defence: " + this.defenceSkill);
		System.out.println("Goaltending: " + this.goalieSkill);
		System.out.println("Position: " + this.naturalPosition);
		System.out.println("Overall: " + this.overall);
		System.out.println("Cost: $" + Constants.format.format(this.cost));
		System.out.println();
	}
	/*
	public static void main(String[] args)
	{
		//ArrayList<Player> league = new ArrayList<Player>();
		for(int i = 0; i < 50; i++)
		{
			Player player = new Player();
			if(player.naturalPosition.equals(Constants.goalie))
			{
			}
			System.out.println("Age: " + player.age);
			System.out.println("Age Group: " + player.ageGroup);
			System.out.println("Attack: " + player.attackSkill);
			System.out.println("MidField: "+ player.midfieldSkill);
			System.out.println("Defence: " + player.defenceSkill);
			System.out.println("Goaltending: " + player.goalieSkill);
			System.out.println("Position: " + player.naturalPosition);
			System.out.println("Overall: " + player.overall);
			System.out.println("Cost: $" + Constants.format.format(player.cost));
			System.out.println();
		}
	}
	*/
}