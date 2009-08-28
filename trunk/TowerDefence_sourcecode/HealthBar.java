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
 * Bar that displays the current health of something.
 * 
 * @author (Kevin Huber) 
 * @version (1.1)
 */
public class HealthBar extends Actor
{
    private double maxHP;

    /**
     * Creates a new healthbar with the given valus.
     * 
     * @param currentHP   Current ammout of healthpoints.
     * @param pMaxHP      Maximum ammout of healthpoints.
     */
    public HealthBar(int currentHP, int pMaxHP)
    {
        maxHP = pMaxHP;
        
        setImage(new GreenfootImage(1,1));
    }
    
    /**
     * Updates the healthbar.
     * 
     * @param x           X-position for the healthbar.
     * @param y           Y-position for the healthbar.
     * @param currentHP   Current ammout of healthpoints.
     */
    public void update(int x, int y, double currentHP)
    {
        setImage(new GreenfootImage(1,1));
        
        setLocation(x, y);
        
        GreenfootImage im = new GreenfootImage(44, 8);
        // Create the black border.
        im.fill();
        
        // Create the grey background
        im.setColor(Color.RED);
        im.fillRect(1, 1, 42, 6);
        
        // Calculate the Healthpoints on our Healthbar
        double hpProzent = currentHP/maxHP;
        
        // Draw the Healthpoints on our Healthbar
        im.setColor(Color.GREEN);
        im.fillRect(1, 1, (int) Math.round(42*hpProzent), 6);
        
        setImage(im);
    }
}
