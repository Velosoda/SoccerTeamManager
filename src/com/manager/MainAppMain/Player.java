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
	public double cost;
	public int overall;
	public int age;
	public String ageGroup;
	Player()
	{
		this.age = random.nextInt(Constants.maxAge - Constants.minAge) + Constants.minAge;
		if(age <= 20)
		{
			this.attackSkill = random.nextInt(Constants.youthSkillMax - Constants.youthSkillMin) + Constants.youthSkillMin;
			this.defenceSkill = random.nextInt(Constants.youthSkillMax - Constants.youthSkillMin) + Constants.youthSkillMin;
			this.midfieldSkill = random.nextInt(Constants.youthSkillMax - Constants.youthSkillMin) + Constants.youthSkillMin;
			this.ageGroup = "Youth";
		}
		else if(age > 20 && age <=30)
		{			
			this.attackSkill = random.nextInt(Constants.proSkillMax - Constants.proSkillMin) + Constants.proSkillMin;
			this.defenceSkill = random.nextInt(Constants.proSkillMax - Constants.proSkillMin) + Constants.proSkillMin;
			this.midfieldSkill = random.nextInt(Constants.proSkillMax - Constants.proSkillMin) + Constants.proSkillMin;
			this.ageGroup = "Pro";
		}
		else if(age > 30)
		{
			this.attackSkill = random.nextInt(Constants.expertSkillMax - Constants.expertSkillMin) + Constants.expertSkillMin;
			this.defenceSkill = random.nextInt(Constants.expertSkillMax - Constants.expertSkillMin) + Constants.expertSkillMin;
			this.midfieldSkill = random.nextInt(Constants.expertSkillMax - Constants.expertSkillMin) + Constants.expertSkillMin;
			this.ageGroup = "Experienced";
		}
		this.overall = (this.attackSkill + this.midfieldSkill + this.defenceSkill)/3;
		this.cost = overall/100;
	}
	public static void main(String[] args)
	{
		Player player = new Player();
		System.out.println("Age: " + player.age);
		System.out.println("Age Group: " + player.ageGroup);
		System.out.println("Attack: " + player.attackSkill);
		System.out.println("MidFeild: "+ player.midfieldSkill);
		System.out.println("Defender: " + player.defenceSkill);
		System.out.println("Overall: " + player.overall);
		System.out.println("Cost: " + player.cost);
	}
}
