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
 * @version (1.4.2)
 */
public class Creep extends UI
{
    private double NORMALMOVEMENTSPEED = 1.0;
    private double MOVEMENTSPEED = NORMALMOVEMENTSPEED;
    private long slowdownEffectTime = 0;
    
    private int GOLD_AMMOUT;
    private int HEALTH_POINTS;
    private int MAX_HEALTH_POINTS;
    
    private long lastCallOfAct;
    
    private WayPoint nextWayPoint;
    public int id;
    private boolean onWaypoint = false;
    
    private HealthBar healthBar;
    
    /**
     * @param pId            Spawn id.
     * @param healthPaoints  Max. healthpoints for the creep.
     * @param goldAmmout     Ammout of gold the player gets after killing this creep.
     * @param direction      Direction to move to.
     */
    public Creep(int pId, int healthPoints, int goldAmmout, int direction)
    {
        MAX_HEALTH_POINTS = healthPoints;
        HEALTH_POINTS     = MAX_HEALTH_POINTS;
        GOLD_AMMOUT       = goldAmmout;
        id                = pId;
        lastCallOfAct     = System.currentTimeMillis();
        
        turn(direction);
    }
    
    /**
     * Act - do whatever the Creep wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        long actCallTimeDiff = System.currentTimeMillis() - lastCallOfAct;
        lastCallOfAct        = System.currentTimeMillis();
        
        if( healthBar == null )
        {
            healthBar = new HealthBar(HEALTH_POINTS, MAX_HEALTH_POINTS);
            getWorld().addObject(healthBar, getX(), getY()-38);
        }
        
        healthBar.update(getX(), getY()-38, HEALTH_POINTS);
        
        if( System.currentTimeMillis() >= slowdownEffectTime )
            MOVEMENTSPEED = NORMALMOVEMENTSPEED;
        
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
        
        move(actCallTimeDiff);
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
        return MOVEMENTSPEED;
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
     * 
     * @param numSecsPassed This should be a time diff between the last calls of act().
     */
    public void move(long numSecsPassed)
    {
        double angle = Math.toRadians( getRotation() );
        int x        = (int) Math.round((getX() + Math.cos(angle) * ((numSecsPassed / getWorld().getWidth()) + MOVEMENTSPEED)));
        int y        = (int) Math.round((getY() + Math.sin(angle) * ((numSecsPassed / getWorld().getHeight()) + MOVEMENTSPEED)));
        
        setLocation(x, y);
    }
    
    /**
     * Slows down the unit over a speciefed time.
     * 
     * @param newMovementSpeed percentage of actual movement speed, e.g actuall movementspeed 10, newMovementSpeed = 0.10 === movementspeed = 1
     * @param effectTime       Time that the unit is slower.
     */
    public void slowDownUnit(double newMovementSpeed, int effectTime)
    {
        slowdownEffectTime = System.currentTimeMillis() + (effectTime * 1000);
        MOVEMENTSPEED = NORMALMOVEMENTSPEED * newMovementSpeed;
    }
}
