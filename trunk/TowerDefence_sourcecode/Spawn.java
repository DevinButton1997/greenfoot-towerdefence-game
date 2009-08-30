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
 * Spawns the Creeps and manage the levels.
 * 
 * @author (Kevin Huber) 
 * @version (1.2.2)
 */
public class Spawn extends UI
{
    private static final int timeBetweenSpawns   = 1500;    // Time between spawns in ms.
    private static final int timeBetweenWaves    = 10000;   // Time between waves in ms.
    private static final double GOLD_MULTIPLER   = 0.30;    // Gold multipler ( Default: 0.30 )
    private static final double HEALTH_MULTIPLER = 0.15;    // Health multipler ( Default: 0.15 )
    
    private double healthPoints;               // Creep Healthpoints
    private double goldAmmout;                 // Bounty for killing one creep
    private int spawnedCreepMoveDirection;
    
    private long lastSpawn;
    private long lastWave;
    private boolean roundEnded;
    
    public Spawn(int creepSpawnDirection)
    {
        setImage(new GreenfootImage(1, 1));
        
        // Set default values
        healthPoints  = 180;
        goldAmmout    = 5;
        spawnedCreeps = MAX_CREEPS;
        lastSpawn     = 0;
        lastWave      = System.currentTimeMillis();
        roundEnded    = true;
        
        spawnedCreepMoveDirection = creepSpawnDirection;
    }
    
    /**
     * Act - do whatever the Spawn wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if( (aliveCreeps <= 0)&&(!roundEnded) )
        {
            lastWave   = System.currentTimeMillis();
            roundEnded = true;
            
            increaseGold((int) ((MAX_CREEPS / 2) * goldAmmout)); // Let's give the player a small extra bonus for passing the last level ;) .
        }
        
        if( roundEnded )
        {
            nextWaveLabel.setCaption(""+( (timeBetweenWaves - (System.currentTimeMillis() - lastWave)) / 1000));
        }
        else
        {
            nextWaveLabel.setCaption("n.a");
        }
        
        if( (aliveCreeps <= 0)&&(roundEnded)&&(System.currentTimeMillis() >= (lastWave + timeBetweenWaves)) )
        {
            if( !playerLevelUp() )
                return;
            // Increase creeps healtpoints
            healthPoints += (int) healthPoints * HEALTH_MULTIPLER;
            
            if( soundOn )
                Greenfoot.playSound("come_on.wav");
            
            if( getLevel()%5 == 0 ) // Spawn one boss creep
            {
                spawnedCreeps = MAX_CREEPS;
                // Spawn boss
                getWorld().addObject(new Creep(0, (int) healthPoints * 5, (int) goldAmmout * 6, spawnedCreepMoveDirection), getX(), getY());
                // increase bounty for killing creeps
                goldAmmout += goldAmmout * GOLD_MULTIPLER;
                
                aliveCreeps = 1;
            }
            else
            {
                spawnedCreeps = 0;                
                aliveCreeps   = MAX_CREEPS;
            }
            
            roundEnded = false;
        }
        
        if( System.currentTimeMillis() >= (lastSpawn + timeBetweenSpawns) )
        {
            if( spawnedCreeps < MAX_CREEPS )
            {
                getWorld().addObject(new Creep(spawnedCreeps, (int) healthPoints, (int) goldAmmout, spawnedCreepMoveDirection), getX(), getY());
                
                spawnedCreeps++;
                lastSpawn = System.currentTimeMillis();
            }
        }
    }
}
