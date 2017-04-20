package com.manager.MainAppMain;

import java.util.Random;



import java.io.*;

public class Player 
{
	Random random = new Random();
	private String name;
	private int currentTeam;
	private String naturalPosition = "";
	private String currentPosition = ""; 
	private int attackSkill;
	private int midfieldSkill;
	private int defenseSkill;
	private int goalieSkill;
	private int currentSkillValue;
	private Double cost = Constants.defaultCostPerPlayer;
	private int overall;
	private int age;
	private int growth;
	private int injuryRisk ; 
	private String ageGroup;
	private int exp;
	private int injuryPool;
	private int health;
	private int death = 0; 

	public void injuryCheck()
	{
		//the chance of an injury occuring is seperate from the actual risk,this further diversify's player quality
		int injuryChance = (random.nextInt(20)) + (this.injuryRisk); 
		if(injuryChance < random.nextInt(100))
		{
			this.injury();
		}
		
	}
	
	public void injury()
	{
		this.setInjuryPool((int)(random.nextInt(5) * 2) * this.getInjuryRisk());
		this.setHealth(this.getHealth() - this.getInjuryPool());
		if(health <= 0)
		{
			this.death();
		}
	}

	public void death() //temporarily setting up death like this in case facilitation in other methods is easier
	{
		//this will remove a player permenantly 
		this.setDeath(1);
		Constants.deathRecord.add(this);
	}
	
	Player() throws IOException
	{	
		setAge(random.nextInt(Constants.maxAge - Constants.minAge) + Constants.minAge);
		
		if(getAge() <= 20)
		{
			setGoalieSkill(random.nextInt(Constants.youthSkillMax - Constants.youthSkillMin) + Constants.youthSkillMin);
			setAttackSkill(random.nextInt(Constants.youthSkillMax - Constants.youthSkillMin) + Constants.youthSkillMin);
			setDefenseSkill(random.nextInt(Constants.youthSkillMax - Constants.youthSkillMin) + Constants.youthSkillMin);
			setMidfieldSkill(random.nextInt(Constants.youthSkillMax - Constants.youthSkillMin) + Constants.youthSkillMin);
			setInjuryRisk(random.nextInt(Constants.youthInjuryRisk) + (int)(.3 * Constants.youthInjuryRisk));
			setHealth(random.nextInt(Constants.youthHealthMax) + Constants.youthHealthMin);
			positionDeterminator();
			setAgeGroup(Constants.youth);
		}
		else if(getAge() > 20 && getAge() <= 30)
		{
			setGoalieSkill(random.nextInt(Constants.proSkillMax - Constants.proSkillMin) + Constants.proSkillMin);
			setAttackSkill(random.nextInt(Constants.proSkillMax - Constants.proSkillMin) + Constants.proSkillMin);
			setDefenseSkill(random.nextInt(Constants.proSkillMax - Constants.proSkillMin) + Constants.proSkillMin);
			setMidfieldSkill(random.nextInt(Constants.proSkillMax - Constants.proSkillMin) + Constants.proSkillMin);
			setInjuryRisk(random.nextInt(Constants.proInjuryRisk ) + (int)(.3 * Constants.proInjuryRisk));
			setHealth(random.nextInt(Constants.proHealthMax) + Constants.proHealthMin);
			positionDeterminator();
			setAgeGroup(Constants.pro);
		}
		else if(getAge() > 30)
		{
			setGoalieSkill(random.nextInt(Constants.expertSkillMax - Constants.expertSkillMin) + Constants.expertSkillMin);
			setAttackSkill(random.nextInt(Constants.expertSkillMax - Constants.expertSkillMin) + Constants.expertSkillMin);
			setDefenseSkill(random.nextInt(Constants.expertSkillMax - Constants.expertSkillMin) + Constants.expertSkillMin);
			setMidfieldSkill(random.nextInt(Constants.expertSkillMax - Constants.expertSkillMin) + Constants.expertSkillMin);
			setInjuryRisk(random.nextInt(Constants.expertInjuryRisk) + (int)(.3 * Constants.expertInjuryRisk));
			setHealth(random.nextInt(Constants.expertHealthMax) + Constants.expertHealthMin);
			positionDeterminator();
			setAgeGroup(Constants.experienced);
		}
		this.setCost(this.getCost() * this.getOverall()/100);
		this.setCost(this.getCost() + (10000 -(Constants.healthLimit - (this.getHealth() * 10))));
		this.setCost(this.getCost() + (Constants.injuryRiskLimit - this.getInjuryRisk()) * Constants.injuryCostMultiplier);
		
	}
	
	public void positionDeterminator()
	{
		if(getAttackSkill() > getDefenseSkill() && getAttackSkill() > getMidfieldSkill() && getAttackSkill() > getGoalieSkill())
		{
			setNaturalPosition(Constants.attacker);
			setGoalieSkill(random.nextInt(Constants.youthSkillMin));
			setOverall((getAttackSkill() + getMidfieldSkill()  + getDefenseSkill())/3);
			setCurrentSkillValue(this.getAttackSkill());//***********
			return;
		}
		else if(getDefenseSkill() > getMidfieldSkill() && getDefenseSkill() > getAttackSkill() && getDefenseSkill() > getGoalieSkill())
		{
			setNaturalPosition(Constants.defender);
			setGoalieSkill(random.nextInt(Constants.youthSkillMin));
			setOverall((getAttackSkill() + getMidfieldSkill()  + getDefenseSkill())/3);
			setCurrentSkillValue(this.getDefenseSkill());//********************************
			return;
		}
		else if(getMidfieldSkill() > getAttackSkill() && getMidfieldSkill() > getDefenseSkill() && getMidfieldSkill() > getGoalieSkill())
		{
			setNaturalPosition(Constants.midfielder);
			setGoalieSkill(random.nextInt(Constants.youthSkillMin));
			setOverall((getAttackSkill() + getMidfieldSkill()  + getDefenseSkill())/3);
			setCurrentSkillValue(this.getMidfieldSkill());//*********************
			return;
		}
		else if(getGoalieSkill() > getAttackSkill() && getGoalieSkill() > getMidfieldSkill() && getGoalieSkill() > getDefenseSkill())
		{
			setNaturalPosition(Constants.goalie);
			setAttackSkill(random.nextInt(Constants.youthSkillMin));
			setDefenseSkill(random.nextInt(Constants.youthSkillMin));
			setMidfieldSkill(random.nextInt(Constants.youthSkillMin));
			setOverall(getGoalieSkill());
			setCurrentSkillValue(this.getGoalieSkill());
			return;
		}
		else
		{
			if(getAttackSkill() == getMidfieldSkill() || getAttackSkill() == getDefenseSkill())
			{
				setNaturalPosition(Constants.attacker);
				setOverall((getAttackSkill() + getMidfieldSkill()  + getDefenseSkill())/3);
				setCost(getCost()+50000);
			}
			else if(getMidfieldSkill() == getDefenseSkill())
			{
				setNaturalPosition(Constants.midfielder);
				setOverall((getAttackSkill() + getMidfieldSkill()  + getDefenseSkill())/3);
				setCost(getCost()+50000);
			}
			else if(getAttackSkill() == getGoalieSkill() || getMidfieldSkill() == getGoalieSkill() || getDefenseSkill() == getGoalieSkill())
			{
				setNaturalPosition(Constants.goalie);
				setOverall(getGoalieSkill());
				setCost(getCost()+50000);
			}
			return;
		}
	}
	
	public void levelUp()
	{
		if(this.currentPosition == Constants.attacker)
		{
			setAttackSkill(getAttackSkill() + Constants.levelUpRate);
		}
		if(this.currentPosition == Constants.defender)
		{
			setDefenseSkill(getDefenseSkill() + Constants.levelUpRate);
		}
		if(this.currentPosition == Constants.midfielder)
		{
			setMidfieldSkill(getMidfieldSkill() + Constants.levelUpRate);
		}
		if(this.currentPosition == Constants.goalie)
		{
			setGoalieSkill(getGoalieSkill() + Constants.levelUpRate);
		}
		System.out.println(this.getName() + " has leveled up");
		
		if(this.getCurrentTeam() == Constants.userTeamId)
		{
		Constants.levelUpRecord.add(this);
		}
		this.setExp(0);
	}
	
	public void reprisal()//chance to level up even if they lose an evaluation
	{
		Random r = new Random();
		int chance = r.nextInt(100);
		System.out.println("Random in reprisal is " + chance);
		if (chance < 20)
		{
			
			this.setExp(this.getExp()+1);
			if(this.getExp() == 3)
			{
				this.levelUp();
			}
		}
	}
	
	public void printStats()
	{
		System.out.println("Age: " + getAge());
		System.out.println("Age Group: " + getAgeGroup());
		System.out.println("Attack: " + getAttackSkill());
		System.out.println("MidField: "+ getMidfieldSkill());
		System.out.println("Defence: " + getDefenseSkill());
		System.out.println("Goaltending: " + getGoalieSkill());
		System.out.println("Position: " + getNaturalPosition());
		System.out.println("Overall: " + getOverall());
		System.out.println("Cost: $" + Constants.format.format(getCost()));
		System.out.println("Name is " + getName());
		System.out.println("Health is " + getHealth());
		System.out.println();
	}
	
	public int getCurrentSkillValue() {
		return currentSkillValue;
	}

	public void setCurrentSkillValue(int currentSkillValue) {
		this.currentSkillValue = currentSkillValue;
	}
		
	public int getDeath() {
		return death;
	}

	public void setDeath(int death) {
		this.death = death;
	}

	public int getInjuryPool() {
		return injuryPool;
	}

	public void setInjuryPool(int injuryPool) {
		this.injuryPool = injuryPool;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}
	public int getCurrentTeam() {
		return currentTeam;
	}
	public void setCurrentTeam(int currentTeam) {
		this.currentTeam = currentTeam;
	}
	public String getNaturalPosition() {
		return naturalPosition;
	}
	public void setNaturalPosition(String naturalPosition) {
		this.naturalPosition = naturalPosition;
	}
	public String getCurrentPosition() {
		return currentPosition;
	}
	public void setCurrentPosition(String currentPosition) {
		this.currentPosition = currentPosition;
	}
	public int getAttackSkill() {
		return attackSkill;
	}
	public void setAttackSkill(int attackSkill) {
		this.attackSkill = attackSkill;
	}
	public int getMidfieldSkill() {
		return midfieldSkill;
	}
	public void setMidfieldSkill(int midfieldSkill) {
		this.midfieldSkill = midfieldSkill;
	}
	public int getDefenseSkill() {
		return defenseSkill;
	}
	public void setDefenseSkill(int defenseSkill) {
		this.defenseSkill = defenseSkill;
	}
	public int getGoalieSkill() {
		return goalieSkill;
	}
	public void setGoalieSkill(int goalieSkill) {
		this.goalieSkill = goalieSkill;
	}
	public Double getCost() {
		return cost;
	}
	public void setCost(Double cost) {
		this.cost = cost;
	}
	public int getOverall() {
		return overall;
	}
	public void setOverall(int overall) {
		this.overall = overall;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getGrowth() {
		return growth;
	}
	public void setGrowth(int growth) {
		this.growth = growth;
	}
	public int getInjuryRisk() {
		return injuryRisk;
	}
	public void setInjuryRisk(int injuryRisk) {
		this.injuryRisk = injuryRisk;
	}
	public String getAgeGroup() {
		return ageGroup;
	}
	public void setAgeGroup(String ageGroup) {
		this.ageGroup = ageGroup;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getExp() {
		return exp;
	}
	public void setExp(int exp) {
		this.exp = exp;
	}
	
	public static void main(String[] args) throws IOException
	{
	//	ArrayList<Player> league = new ArrayList<Player>();
		for(int i = 0; i < 50; i++)
		{
			Player player = new Player();
			if(player.naturalPosition.equals(Constants.goalie))
			{
			}
			System.out.println("Age: " + player.getAge());
			System.out.println("Age Group: " + player.getAgeGroup());
			System.out.println("Attack: " + player.getAttackSkill());
			System.out.println("MidField: "+ player.getMidfieldSkill());
			System.out.println("Defense: " + player.getDefenseSkill());
			System.out.println("Goalie: " + player.getGoalieSkill());
			System.out.println("Position: " + player.getNaturalPosition());
			System.out.println("Overall: " + player.getOverall());
			System.out.println("Cost: $" + Constants.format.format(player.getCost()));
			System.out.println();
			System.out.println(player.getName());
		}	
	}
}