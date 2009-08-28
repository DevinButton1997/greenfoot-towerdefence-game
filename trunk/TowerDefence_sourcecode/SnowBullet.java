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
 * Write a description of class SnowBullet here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SnowBullet extends Bullet
{
    public SnowBullet(Creep pTarget, int speed, int damage, String explosionSound)
    {
        super(pTarget, speed, damage, explosionSound);
    }
    
    /**
     * Act - do whatever the RocketBullet wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        long actCallTimeDiff = System.currentTimeMillis() - lastCallOfAct;
        lastCallOfAct        = System.currentTimeMillis();
        
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
        
        if( atWorldEdge() )
        {
            getWorld().removeObject(this);
            return;
        }
        else if( canSee(Creep.class) )
        {
            Creep victim = (Creep) getWorld().getObjectsAt(getX(), getY(), Creep.class).get(0);
            
            getWorld().addObject(new Explosion(), victim.getX(), victim.getY());
            
            List CreepsInRange = getObjectsInRange(90, Creep.class);
            
            for( int i = 0; i < CreepsInRange.size(); i++ )
            {
                ((Creep) CreepsInRange.get(i)).decHealth(DAMAGE-(i*2));
                ((Creep) CreepsInRange.get(i)).slowDownUnit(0.30, 3);
            }
        
            if( soundOn )
                Greenfoot.playSound(sound);
        
            getWorld().removeObject(this);
        
            return;
        }
        
        move(actCallTimeDiff);
    }    
}
