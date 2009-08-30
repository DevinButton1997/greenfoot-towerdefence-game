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
 * Preview of Map 1 in the main menu, this class only checks if the preview is pressed or not and changes the level if it's pressed
 * 
 * @author (Kevin Huber) 
 * @version (1.2)
 */
public class World_preview extends Button
{
    /**
     * Check if the preview is pressed...
     */
    public void act() 
    {
        if( Greenfoot.mouseClicked(this) )
        {
            ((myWorld) getWorld()).setLevel(1);
        }
    }
}
