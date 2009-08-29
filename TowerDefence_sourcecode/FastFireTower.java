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
 * Fast shooting Tower.
 * 
 * @author (Kevin Huber) 
 * @version (0.5)
 */
public class FastFireTower extends Tower
{
    private boolean firedOne = false;
    
    
    public FastFireTower()
    {
        super(75, 55, 7, 4.0, PRICE_FASTFIRETOWER, true);
    }
    
    /**
     * Act - do whatever the Tower wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        target = getTarget();
        
        if( lastShot < RELOAD_SPEED)
            lastShot++;
        
        if( target != null )
        {
            turnToTarget();
            
            if( lastShot >= RELOAD_SPEED )
            {
                // Calculate shot coordinates
                double angle = Math.toRadians( target.getRotation() );
                double x     = target.getX() + Math.cos(angle) * target.getSpeed();
                double y     = target.getY() + Math.sin(angle) * target.getSpeed();
                
                getWorld().addObject(new FastFireBullet((int) x, (int) y, (int) BULLET_SPEED, (int) TOWER_DAMAGE, "hit.wav"), getX(), getY());
                getWorld().addObject(new FastFireBullet((int) x, (int) y, (int) BULLET_SPEED, (int) TOWER_DAMAGE, "hit.wav"), getX(), getY());
                
                lastShot = 0;
                firedOne      = false;
            }
            else if( (lastShot >= RELOAD_SPEED - 15)&&(!firedOne) ) // Cause this tower can fire two bullets, we check if we can shoot the next one.
            {
                // Calculate shot coordinates
                double angle = Math.toRadians( target.getRotation() );
                double x = target.getX() + Math.cos(angle) * target.getSpeed();
                double y = target.getY() + Math.sin(angle) * target.getSpeed();
                getWorld().addObject(new FastFireBullet((int) x, (int) y, (int) BULLET_SPEED, (int) TOWER_DAMAGE, "hit.wav"), getX(), getY());
                getWorld().addObject(new FastFireBullet((int) x, (int) y, (int) BULLET_SPEED, (int) TOWER_DAMAGE, "hit.wav"), getX(), getY());
                
                firedOne = true;
            }
        }
    }     
    
    /**
     * Increases the level of the Tower and adjust the values for speed, upgradeCost, Radius, reloeadSpeed and Damage.
     */
    public void levelUp()
    {
        level++;
        
        if(level > 5)
        {
            level = 5;
            return;
        }
        else if(level >= 5)
        {
            BULLET_SPEED  = 6.0;
            upgradeCosts  = 0;
            RADIUS        = 110;
            RELOAD_SPEED  = 20;
            TOWER_DAMAGE  = 78;
            level         = 5;
        }
        else
        {
            upgradeCosts  += (int) (upgradeCosts * 1.75);
            RADIUS        += RADIUS * 0.1;
            RELOAD_SPEED  -= RELOAD_SPEED * 0.1;
            TOWER_DAMAGE  += TOWER_DAMAGE;
        }
        
        switch(level)
        {
            case 2:
                setImage("tower_FastFireTower_small_green.png");
                break;
            case 3:
                setImage("tower_FastFireTower_small_yellow.png");
                break;
            case 4:
                setImage("tower_FastFireTower_small_orange.png");
                break;
            case 5:
                setImage("tower_FastFireTower_small_red.png");
                break;
        }
        
        levelBar.setLevel(level);
        if(soundOn) Greenfoot.playSound("upgrade.wav");
    }
}
