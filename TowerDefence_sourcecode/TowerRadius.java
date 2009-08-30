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
 * Displays the radius of a tower.
 * 
 * @author (Kevin Huber) 
 * @version (1.0)
 */
public class TowerRadius extends Actor
{
    public TowerRadius(Tower tower)
    {
        GreenfootImage im = new GreenfootImage((int) (tower.getRadius()*1.8), (int) (tower.getRadius()*1.8));
        setImage(im);
        
        // Fill the circle
        im.setColor(new Color (46, 46, 46, 180));
        im.fillOval(0, 0, (int) (tower.getRadius()*1.8), (int) (tower.getRadius()*1.8));
        
        setImage(im);
    }
}
