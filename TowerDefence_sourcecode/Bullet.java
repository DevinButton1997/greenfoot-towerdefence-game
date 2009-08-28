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
import java.lang.IllegalStateException;
import java.util.List; // List

/**
 * Bullet.
 * 
 * @author (Kevin Huber) 
 * @version (1.1)
 */
public class Bullet extends UI
{
    protected double BULLETSPEED;
    protected int DAMAGE;
    
    protected Creep target;
    protected int targetX;
    protected int targetY;
    protected boolean fired = false;
    protected String sound;
    
    protected long lastCallOfAct;

    
    /**
     * Create a new bullet.
     * 
     * @param pX             Targets x position
     * @param pY             Targets y position
     * @param speed          bullet speed
     * @param damage         bullet damage
     * @param explosionSound bullet explosion sound
     */
    public Bullet(int pX, int pY, double speed, int damage, String explosionSound)
    {
        targetX       = pX;
        targetY       = pY;
        BULLETSPEED   = speed;
        DAMAGE        = damage;
        sound         = explosionSound;
        lastCallOfAct = System.currentTimeMillis();
    }
    
    /**
     * Create a new bullet.
     * 
     * @param pTarget        bullet target
     * @param speed          bullet speed
     * @param damage         bullet damage
     * @param explosionSound bullet explosion sound
     */
    public Bullet(Creep pTarget, int speed, int damage, String explosionSound)
    {
        target        = pTarget;
        BULLETSPEED   = speed;
        DAMAGE        = damage;
        sound         = explosionSound;
        lastCallOfAct = System.currentTimeMillis();
    }
    
    /**
     * Act - do whatever the Geschoss wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        long actCallTimeDiff = System.currentTimeMillis() - lastCallOfAct;
        lastCallOfAct        = System.currentTimeMillis();
        
        // If the bullet is fired and not self aiming to target we don't have to correct the fire angle.
        if( !fired )
        {
            if( target != null )
            {
                try
                {
                    targetX = target.getX();
                    targetY = target.getY();
                }
                catch( IllegalStateException e )
                {
                    List objectsInRange = getObjectsInRange(125, Creep.class);
    
                    if( objectsInRange.size() > 0 )
                    {
                        target = (Creep) objectsInRange.get(0);
                    }
                }
            }
            
            // Get a second point on the way of our target, this is needed for angle calculation.
            double angle = Math.toRadians( getRotation() );
            double x     = getX() + Math.cos(angle);
            double y     = getY() + Math.sin(angle);
            Vektor v1    = new Vektor(getX()-x, getY()-y);
            
            Vektor v2;
            
            // Calculate shot coordinates
            if( target != null )
            {
                angle = Math.toRadians( target.getRotation() );
                v2    = new Vektor(getX()-(target.getX() + Math.cos(angle) * target.getSpeed()), getY()-(target.getY() + Math.sin(angle) * target.getSpeed()));
            }
            else 
            {
                v2 = new Vektor(getX()-targetX, getY()-targetY);
            }
            
            
            // Let the creep do another step in mind, to look if we have to turn our tower left(-) or right(+).
            double Vangle = Math.toRadians( getRotation()+(int) Math.round(v1.getAngle(v1, v2)) );
            double vX     = x + Math.cos(Vangle);
            double vY     = y + Math.sin(Vangle);
            
            
            Vektor vV1 = new Vektor(x-vX, y-vY);
            Vektor vV2 = new Vektor(x-targetX, y-targetY);
            
            // If the angle is less than 1 we have to turn left(-).
            if( (int) Math.round(vV1.getAngle(vV1, vV2)) > 1 )
            {
                turn( -(int) Math.round(v1.getAngle(v1, v2)) );
            }
            else
            {
                turn( (int) Math.round(v1.getAngle(v1, v2)) );
            }
            
            fired = true;
        }
        
        if( atWorldEdge() )
        {
            getWorld().removeObject(this);
            return;
        }
        else if( canSee(Creep.class) )
        {
            Creep victim = (Creep) getWorld().getObjectsAt(getX(), getY(), Creep.class).get(0);
            
            victim.decHealth(DAMAGE);
            
            if( soundOn )
                Greenfoot.playSound(sound);
            
            getWorld().removeObject(this);
            
            return;
        }
        
        move(actCallTimeDiff);
    }   
    
    /**
     * Move forward in the current direction.
     */
    public void move(long numSecsPassed)
    {
        double angle = Math.toRadians( getRotation() );
        int x        = (int) Math.round((getX() + Math.cos(angle) * ((numSecsPassed / getWorld().getWidth()) + BULLETSPEED)));
        int y        = (int) Math.round((getY() + Math.sin(angle) * ((numSecsPassed / getWorld().getHeight()) + BULLETSPEED)));
        
        setLocation(x, y);
    }
    
    /**
     * Test if we are close to one of the edges of the world. Return true is we are.
     */
    public boolean atWorldEdge()
    {
        if(getX() < 10 || getX() > getWorld().getWidth() - 210)
            return true;
        if(getY() < 10 || getY() > getWorld().getHeight() - 10)
            return true;
        else
            return false;
    }
    
    /**
     * Return true if we can see an object of class 'clss' right where we are. 
     * False if there is no such object here.
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
        if( rotation > 0 )
        {
            while( rotation >= 360 )
            {
                rotation -= 360;
            }
        }
        else
        {
            while( rotation <= -360 )
            {
                rotation += 360;
            }
        }
        
        setRotation(rotation);
    }
}
