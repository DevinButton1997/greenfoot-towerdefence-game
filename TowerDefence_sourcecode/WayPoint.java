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
 * Chain of Waypoints.
 * One way connected.
 * 
 * Waypoints are essential, they are the orientation spots for the Creeps.
 * 
 * @author (Kevin Huber) 
 * @version (1.1)
 */
public class WayPoint extends Actor
{
    public static WayPoint start;
    private WayPoint next;
    
    public WayPoint()
    {
        setImage(new GreenfootImage(1, 1));
        
        // Connect Waypoints with each other...
        if(start != null)
        {
            start.next = this;
        }
        
        start = this;
    }
    
    /**
     * Returns the Handle of the next Waypoint.
     */
    public WayPoint getNextWayPoint()
    {
        return next;
    }
}
