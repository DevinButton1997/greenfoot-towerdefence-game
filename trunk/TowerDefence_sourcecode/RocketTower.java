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
 * Rocket Tower (good against bosses and Single units).
 * 
 * @author (Kevin Huber) 
 * @version (0.5)
 */
public class RocketTower extends Tower
{
    private boolean rocketFired = false;
    
    
    public RocketTower()
    {
        super(100, 500, 50, 2, PRICE_ROCKETTOWER, true);
    }
    
    /**
     * Act - do whatever the Tower wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        target = getTarget();
        
        if( lastShot < RELOAD_SPEED )
        {
            lastShot++;
        }
        
        if( target != null )
        {
            turnToTarget();
            levelBar.setRotation(getRotation());
            
            if( (lastShot >= RELOAD_SPEED)||((lastShot >= RELOAD_SPEED - 60)&&(!rocketFired)) )
            {
                if( soundOn )
                    Greenfoot.playSound("rocket-fire.wav");
                
                getWorld().addObject(new RocketBullet(target, (int) BULLET_SPEED, (int) TOWER_DAMAGE, "rocket-explosion.wav"), getX(), getY());
                
                if( !rocketFired )
                {
                    rocketFired = true;
                }
                else
                {
                    lastShot     = 0;
                    rocketFired = false;
                }
            }
        }
    }     
    
    /**
     * Increases the level of the Tower and adjust the values for speed, upgradeCost, Radius, reloeadSpeed and Damage.
     */
    public void levelUp()
    {
        level++;
        
        if( level > 5 )
        {
            level = 5;
            return;
        }
        else if( level == 5 )
        {
            BULLET_SPEED = 3.0;
            upgradeCosts = 0;
            RADIUS       = 300;
            RELOAD_SPEED = 250;
            TOWER_DAMAGE = 1800;
            level        = 5;
        }
        else
        {
            upgradeCosts += (int) (upgradeCosts * 1.75);
            RADIUS       += RADIUS  *0.25;
            RELOAD_SPEED -= RELOAD_SPEED  *0.05;
            TOWER_DAMAGE += TOWER_DAMAGE*0.75;
        }
        
        switch(level)
        {
            case 2:
                setImage("tower_RocketTower_small_full_green.png");
                break;
            case 3:
                setImage("tower_RocketTower_small_full_yellow.png");
                break;
            case 4:
                setImage("tower_RocketTower_small_full_orange.png");
                break;
            case 5:
                setImage("tower_RocketTower_small_full_red.png");
                break;
        }
        
        levelBar.setLevel(level);
        if(soundOn) Greenfoot.playSound("upgrade.wav");
    }
}
