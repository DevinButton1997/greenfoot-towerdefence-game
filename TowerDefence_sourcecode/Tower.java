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
import java.util.List; // List

/**
 * Basic methods and Variables needed by the tower.
 * 
 * @author (Kevin Huber) 
 * @version (1.1)
 */
public class Tower extends UI
{
    protected double RADIUS;       // The radius in wich the tower can attack Creeps.
    protected double RELOAD_SPEED; // Calls of act() methode before the tower can shoot again.
    protected double TOWER_DAMAGE; // The Damage that the Tower does to the creeps.
    protected int lastShot;        // Counts the calls of the act methode from the Tower untill it reacheas the value of REALOAD_SPEED.
    protected double BULLET_SPEED; // Bullet speed
    protected boolean attackFirst; // If True the tower will always shoot at the Creep with the lowest id in range = the creep nearst to the exit of the map, else it will alway shoot at the creep with the highest id in range.
    
    protected int level = 1;       // Level from the tower, 1 is the initial level.
    protected int upgradeCosts;    // Upgradecosts to upgrade the tower to the next higher level.
    protected LevelBar levelBar;   // Shows the current upgrade stage of the tower.
    
    protected Creep target;        // Handel in wich the current target is stored.
    
    /**
     * Create a new Tower
     * 
     * @param radius        Radius of the tower.
     * @param reloadSpeed   Calls of Act methode before next shoot.
     * @param damage        Damage the tower does on enemys.
     * @param bulletSpeed   Bulletspeed.
     * @param towerCosts    Ammout of Gold needed to build the tower.
     */
    public Tower(double radius, double reloadSpeed, double damage, double bulletSpeed, int towerCosts, boolean attackFirstCreep)
    {
        RADIUS        = radius;
        RELOAD_SPEED  = reloadSpeed;
        TOWER_DAMAGE  = damage;
        BULLET_SPEED  = bulletSpeed;
        upgradeCosts  = (int) (towerCosts * 1.75);
        lastShot      = (int) RELOAD_SPEED;
        attackFirst   = attackFirstCreep;
    }
    
    /**
     * Looks out for a target.
     * 
     * @return handle of the target
     */
    public Creep getTarget()
    {
        List objectsInRange = getObjectsInRange( (int) RADIUS, Creep.class );
    
        if( objectsInRange.size() > 0 )
        {
            int targetId = 0;
            int targetCreepId;
            
            if( attackFirst )
            {
                targetCreepId = MAX_CREEPS;
                
                for( int i = 0; i < objectsInRange.size(); i++ )
                {
                    if( ((Creep) objectsInRange.get(i)).id < targetCreepId )
                    {
                        targetCreepId = ((Creep) objectsInRange.get(i)).id;
                        targetId      = i;
                    }
                }
            }
            else
            {
                targetCreepId = 0;
                
                for( int i = 0; i < objectsInRange.size(); i++ )
                {
                    if( ((Creep) objectsInRange.get(i)).id > targetCreepId )
                    {
                        targetCreepId = ((Creep) objectsInRange.get(i)).id;
                        targetId = i;
                    }
                }
            }
            
            return (Creep) objectsInRange.get(targetId);
        }
        else return null;
    }
    
    /**
     * Rotate the Tower in the direction of the current target.
     */
    public void turnToTarget()
    {
        // Get a second point on the way of our target, this is needed for angle calculation.
        double angle = Math.toRadians( getRotation() );
        double x     = getX() + Math.cos(angle);
        double y     = getY() + Math.sin(angle);
        
        // Create two new vectors
        Vektor v1 = new Vektor(getX()-x, getY()-y);
        Vektor v2 = new Vektor(getX()-target.getX(), getY()-target.getY());
        
        
        // Let the creep do another step in mind, to look if we have to turn our tower left(-) or right(+).
        double Vangle = Math.toRadians( getRotation() + (int) Math.round(v1.getAngle(v1, v2)) );
        double vX     = x + Math.cos(Vangle);
        double vY     = y + Math.sin(Vangle);
        
        
        Vektor vV1 = new Vektor(x-vX, y-vY);
        Vektor vV2 = new Vektor(x-target.getX(), y-target.getY());
        
        // If the angle is less than 1 we have to turn left(-).
        if( (int) Math.round(vV1.getAngle(vV1, vV2)) > 1 )
        {
            turn( -(int) Math.round(v1.getAngle(v1, v2)) );
        }
        else
        {
            turn( (int) Math.round(v1.getAngle(v1, v2)) );
        }
    }
    
    /**
     * Increases the level of the Tower and adjust the values for speed, upgradeCost, Radius, reloeadSpeed and Damage.
     */
    public void levelUp()
    {
        level++;
        
        Greenfoot.playSound("upgrade.wav");
    }
    
    /**
     * Returns the max. fire radius.
     * 
     * @return   Fire radius
     */
    public int getRadius()
    {
        return (int) RADIUS;
    }
    
    /**
     * Returns the damage, the tower does to creeps.
     * 
     * @return   Tower damage
     */
    public int getDamage()
    {
        return (int) TOWER_DAMAGE;
    }
    
    /**
     * Returns the reaload speed of the tower.
     * 
     * @return   Calls of Act methode before next shoot
     */
    public int getReloadSpeed()
    {
        return (int) RELOAD_SPEED;
    }
    
    /**
     * Returns the ammout of gold, needed to upgrade the tower.
     * 
     * @return   Ammout of gold, needed to upgrade the tower.
     */
    public int getUpgradeCosts()
    {
        return upgradeCosts;
    }
    
    /**
     * Returns the current level of the tower.
     * 
     * @return   Tower level
     */
    public int getLevel()
    {
        return level;
    }
    
    /**
     * Changes the towers priority on wich creep in his range he will open fire.
     */
    public void attackFirstCreep(boolean attackFirstCreep)
    {
        attackFirst = attackFirstCreep;
    }
    
    /**
     * Returns the towers target priority
     * 
     * @return   Target priority.
     */
    public boolean getTargetPriority()
    {
        return attackFirst;
    }
    
    /**
     * Turn 'angle' degrees towards the right (clockwise).
     */
    public void turn(int angle)
    {
        setRotation( getRotation()+angle );
    }
}
