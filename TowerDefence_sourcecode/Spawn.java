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
 * @version (1.2)
 */
public class Spawn extends UI
{
    private double healthPoints;               // Creep Healthpoints
    private double goldAmmout;                 // Bounty for killing one creep
    private final int actsBetweenSpawns = 100; // Calls of act() between spawns.
    private final int actsBetweenWaves = 500;  // Calls of act() between each wave.
    
    private int spawnedCreepMoveDirection;
    
    private int lastSpawn;
    private int lastWave;
    private boolean roundEnded;
    
    public Spawn(int creepSpawnDirection)
    {
        setImage(new GreenfootImage(1, 1));
        
        
        // Set default values
        healthPoints  = 250;
        goldAmmout    = 5;
        spawnedCreeps = MAX_CREEPS;
        lastSpawn     = actsBetweenSpawns;
        lastWave      = 0;
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
            lastWave   = 0;
            roundEnded = true;
            
            increaseGold((int) ((MAX_CREEPS / 2) * goldAmmout)); // Let's give the player a small extra bonus for passing the last level ;) .
        }
        
        if( roundEnded )
        {
            lastWave++;
            
            nextWaveLabel.setCaption(""+(actsBetweenWaves-lastWave));
        }
        else
        {
            nextWaveLabel.setCaption("n.a");
        }
        
        if( (aliveCreeps <= 0)&&(roundEnded)&&(lastWave >= actsBetweenWaves) )
        {
            if( !playerLevelUp() )
                return;
            
            healthPoints += (int) healthPoints*0.10;
            
            if( soundOn )
                Greenfoot.playSound("come_on.wav");
            
            if( getLevel()%5 == 0 ) // Spawn one boss creep
            {
                spawnedCreeps = MAX_CREEPS;
                // Spawn boss
                getWorld().addObject(new Creep(0, (int) healthPoints*5, (int) goldAmmout*6, spawnedCreepMoveDirection), getX(), getY());
                // increase bounty for killing creeps on 30%
                goldAmmout += goldAmmout*0.30;
                
                aliveCreeps = 1;
            }
            else
            {
                spawnedCreeps = 0;                
                aliveCreeps   = MAX_CREEPS;
            }
            
            lastWave   = actsBetweenSpawns;
            roundEnded = false;
        }
        
        if( lastSpawn > actsBetweenSpawns )
        {
            if( spawnedCreeps < MAX_CREEPS )
            {
                getWorld().addObject(new Creep(spawnedCreeps, (int) healthPoints, (int) goldAmmout, spawnedCreepMoveDirection), getX(), getY());
                
                spawnedCreeps++;
                lastSpawn = 0;
            }
        }
        else lastSpawn++;
    }
}
