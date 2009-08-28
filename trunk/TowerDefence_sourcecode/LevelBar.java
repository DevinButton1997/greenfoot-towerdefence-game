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
import java.awt.Color;

/**
 * Shows the current upgrade level of a tower.
 * 
 * @author (Kevin Huber) 
 * @version (1.1)
 */
public class LevelBar extends Actor
{
    public LevelBar()
    {
        GreenfootImage im = new GreenfootImage(21, 8);
        im.fill();
        im.setColor(new Color(20, 255, 0, 255));
        im.fillRect(1, 1, 3, 6);
        
        setImage(im);
    }
    
    public void setLevel(int level)
    {
        GreenfootImage im = new GreenfootImage(21, 8);
        im.fill();
        im.setColor(new Color(20, 255, 0, 255));
        
        switch(level)
        {
            case 5: 
                im.fillRect(17, 1, 3, 6);
            case 4:
                im.fillRect(13, 1, 3, 6);
            case 3:
                im.fillRect(9, 1, 3, 6);
            case 2:
                im.fillRect(5, 1, 3, 6);
            case 1:
                im.fillRect(1, 1, 3, 6);
                break;
        }
        
        setImage(im);
    }
}
