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
 * Blocks objects from class Tower.
 * 
 * @author (Kevin Huber) 
 * @version (1.1.2)
 */
public class Blocker extends Actor
{
    /**
     * @width    width of the blocker.
     * @height   height of the blocker.
     */
    public Blocker(int width, int height)
    {
        setImage(new GreenfootImage(width, height));
        /* The following line should only be uncommented if you plan to make a new map!*/
        //setSize(width, height);
    }
    
    public void setSize(int width, int height)
    {
        GreenfootImage im = new GreenfootImage(width, height);
        im.fill();
        setImage(im);
    }
}
