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
 * Creeps.
 * 
 * @author (Kevin Huber) 
 * @version (1.3)
 */
public class Creep extends UI
{
    private static final double WALKING_SPEED = 1.0;
    
    private int GOLD_AMMOUT;
    private int HEALTH_POINTS;
    private int MAX_HEALTH_POINTS;
    
    private WayPoint nextWayPoint;
    public int id;
    private boolean onWaypoint = false;
    
    private HealthBar healthBar;
    
    /**
     * @param pId            Spawn id.
     * @param healthPaoints  Max. healthpoints for the creep.
     * @param goldAmmout     Ammout of gold the player gets after killing this creep.
     */
    public Creep(int pId, int healthPoints, int goldAmmout)
    {
        MAX_HEALTH_POINTS = healthPoints;
        HEALTH_POINTS     = MAX_HEALTH_POINTS;
        GOLD_AMMOUT       = goldAmmout;
        id                = pId;
    }
    
    /**
     * Act - do whatever the Creep wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if( healthBar == null )
        {
            healthBar = new HealthBar(HEALTH_POINTS, MAX_HEALTH_POINTS);
            getWorld().addObject(healthBar, getX(), getY()-38);
        }
        
        healthBar.update(getX(), getY()-38, HEALTH_POINTS);
        
        if( canSee(WayPoint.class) )
        {
            WayPoint currentWayPoint = (WayPoint) getWorld().getObjectsAt(getX(), getY(), WayPoint.class).get(0);
            
            if(nextWayPoint == null) nextWayPoint = currentWayPoint;
            
            // Check if the waypoint in sight is a new one, to correct the angle if neccessary
            if( currentWayPoint == nextWayPoint )
            {
                if( nextWayPoint.getNextWayPoint() != null )
                {
                    nextWayPoint = nextWayPoint.getNextWayPoint();
                    
                    // Get a second point on the way of our target, this is needed for angle calculation.
                    double angle = Math.toRadians( getRotation() );
                    double x     = getX() + Math.cos(angle);
                    double y     = getY() + Math.sin(angle);
                    
                    // Create two new vectors
                    Vektor v1 = new Vektor(getX()-x, getY()-y);
                    Vektor v2 = new Vektor(getX()-nextWayPoint.getX(), getY()-nextWayPoint.getY());
                    
                    // Let the creep do another step in mind, to look if we have to turn our tower left(-) or right(+).
                    double Vangle = Math.toRadians( getRotation()+(int) Math.round(v1.getAngle(v1, v2)) );
                    double vX = x + Math.cos(Vangle);
                    double vY = y + Math.sin(Vangle);
                    
                    
                    Vektor vV1 = new Vektor(x-vX, y-vY);
                    Vektor vV2 = new Vektor(x-nextWayPoint.getX(), y-nextWayPoint.getY());
                    
                    // If the angle is less than 1 we have to turn left(-).
                    if( (int) Math.round(vV1.getAngle(vV1, vV2)) > 1 )
                    {
                        turn( -(int) Math.round(v1.getAngle(v1, v2)) );
                    }
                    else
                    {
                        turn( (int) Math.round(v1.getAngle(v1, v2)) );
                    }
                    
                    onWaypoint = true;
                }
                else // The creep has done it's way from his spawn to the end of the map... So the Player will lose a live and the Creep will die.
                {
                    decreaseLives();
                    aliveCreeps--;
                    
                    if(healthBar != null)
                        getWorld().removeObject(healthBar);
                    
                    getWorld().removeObject(this);
                    
                    return;
                }
            }
            
            if( onWaypoint == true )
            {
                setLocation(currentWayPoint.getX(), currentWayPoint.getY());
                onWaypoint = false;
                return;
            }
        }
        
        move();
    }
    
    /**
     * Decrase health points by the given value.
     * 
     * @damage   Health to decrase.
     */
    public void decHealth(int damage)
    {
        getWorld().addObject(new DamageReceived(""+damage), getX(), getY()-20);
        HEALTH_POINTS -= damage;
        
        if(HEALTH_POINTS <= 0)
        {
            increaseGold(GOLD_AMMOUT);
            aliveCreeps--;
            
            if( healthBar != null )
                getWorld().removeObject(healthBar);
            
            getWorld().removeObject(this);
            
            return;
        }
    }
    
    /**
     * Returns value of WALKING_SPEED.
     * 
     * @return  WALKING_SPEED
     */
    public double getSpeed()
    {
        return WALKING_SPEED;
    }
    
    /**
     * @return true if we can see an object of class 'clss' right where we are. False if there is no such object here.
     */
    public boolean canSee(Class clss)
    {
        return (getOneObjectAtOffset(0, 0, clss) != null);
    }
    
    /**
     * Turn 'angle' degrees towards the right (clockwise).
     */
    public void turn(int angle)
    {
        int rotation = getRotation()+angle;
        if(rotation > 0)
        {
            while(rotation >= 360)
            {
                rotation -= 360;
            }
        }
        else
        {
            while(rotation <= -360)
            {
                rotation += 360;
            }
        }
        
        setRotation(rotation);
    }
    
    /**
     * Move forward in the current direction.
     */
    public void move()
    {
        double angle = Math.toRadians( getRotation() );
        int x = (int) Math.round(getX() + Math.cos(angle) * WALKING_SPEED);
        int y = (int) Math.round(getY() + Math.sin(angle) * WALKING_SPEED);
        
        setLocation(x, y);
    }
}
