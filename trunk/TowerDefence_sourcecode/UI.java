/**
 * TowerDefence
 * Copyright (C) 2008-2009 Kevin 'Blackdead' Huber
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 **/
 
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * All variables other classes need access to.
 * 
 * @author (Kevin Huber) 
 * @version (0.9 Alpha)
 */
public class UI extends Actor
{
    protected static Counter goldCounter;
    protected static Label nextWaveLabel;
    protected static Counter levelCounter;
    protected static Counter liveCounter;
    protected static Label selectedTowerLevelLabel;
    protected static Label selectedTowerDamageLabel;
    protected static Label selectedTowerRadiusLabel;
    protected static Label selectedTowerSpeedLabel;
    protected static Label selectedTowerUpgradeCostLabel;
    
    protected static UpgradeButton upgradeButton;
    protected static SellButton sellButton;
    protected static BuyNormalTowerButton buyNormalTowerButton;
    protected static BuyFastFireTowerButton buyFastFireTowerButton;
    protected static BuyRocketTowerButton buyRocketTowerButton;
    protected static NormalSpeedButton normalSpeedButton;
    protected static FastSpeedButton fastSpeedButton;
    protected static SoundOnOffButton soundOnOffButton;
    
    protected static int aliveCreeps;
    protected static final int MAX_CREEPS = 20;  // Number of spawned creeps in one wave
    protected static int spawnedCreeps;
    
    protected static Tower selectedTower;
    protected static int buildTower = 0;  // 0 = No tower; 1 = normal tower;
    protected static final int PRICE_NORMALTOWER = 25;
    protected static final int PRICE_FASTFIRETOWER = 35;
    protected static final int PRICE_ROCKETTOWER = 50;
    
    protected static final int MAX_LEVEL = 50;
    
    protected static boolean soundOn = true;
    
    protected static int gold;
    protected static int level;
    protected static int lives;
    
    /**
     * Set the values of gold, level, lives and aliveCreeps to it's default values
     */
    public void reset()
    {
        aliveCreeps = 0;
        
        gold = 1500;
        level = 0;
        lives = 20;
    }
    
    /**
     * Decreases the total ammout of gold by the given value.
     * 
     * @param ammout ammout of gold to substract from the players ammout of gold.
     */
    public void decreaseGold(int ammout)
    {
        gold -= ammout;
        goldCounter.decrement(ammout);
    }
    
    /**
     * Increases the total ammout of gold by the given value.
     * 
     * @param ammout ammout of gold to put ontop of the players ammout of gold.
     */
    public void increaseGold(int ammout)
    {
        gold += ammout;
        goldCounter.increment(ammout);
    }
    
    /**
     * Increases the Current Level.
     * 
     * @param levelBounty Bounty for reaching the next level, this bounty will be added ontop of the players ammout of gold.
     * 
     * @return false if the MAX_LEVEL is reached ( win ).
     */
    public boolean levelUp(int levelBounty)
    {
        level++;
        
        // Spiel gewonnen
        if(level > MAX_LEVEL)
        {
            getWorld().addObject(new GameOverScreen(getWorld().getWidth(), getWorld().getHeight(), true), getWorld().getWidth()/2, getWorld().getHeight()/2);
            Greenfoot.stop();
            return false;
        }
        
        levelCounter.increment();
        increaseGold(levelBounty);
        
        return true;
    }
    
    /**
     * Decreases the total ammout of lives left.
     */
    public void decreaseLives()
    {
        lives--;
        
        // Spiel verloren
        if(lives <= 0)
        {
            getWorld().addObject(new GameOverScreen(getWorld().getWidth(), getWorld().getHeight(), false), getWorld().getWidth()/2, getWorld().getHeight()/2);
            Greenfoot.stop();
        }
        
        liveCounter.decrement();
    }
    
    /**
     * Returns the current Level.
     * 
     * @return Current level
     */
    public int getLevel()
    {
        return level;
    }
    
    /**
     * Returns the total ammout of gold.
     * 
     * @return Current ammout of Gold
     */
    public int getGold()
    {
        return gold;
    }
    
    /**
     * Returns the total ammout of lives left.
     * 
     * @return Lives left
     */
    public int getLives()
    {
        return lives;
    }
}
