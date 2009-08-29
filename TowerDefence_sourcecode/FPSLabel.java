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
 * Write a description of class FPSLabel2 here.
 * 
 * @author (Kevin Huber) 
 * @version (1.1)
 */
public class FPSLabel extends Label
{
    private Label fpsLabel;
    private long lastCallOfAct;
    private long lastLabelRefresh;
    
    public FPSLabel()
    {
        super("FPS: n.a");
        
        GreenfootImage im = new GreenfootImage(75, 25);
        im.setColor(new Color(0, 0, 0, 150));
        im.fill();
        setImage(im);
        
        lastCallOfAct = System.currentTimeMillis();
    }
    
    /**
     * Act - do whatever the FPSLabel2 wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if( System.currentTimeMillis() >= lastLabelRefresh + 500 )
        {
            updateImage();
            lastLabelRefresh = System.currentTimeMillis();
        }
        
        lastCallOfAct = System.currentTimeMillis();
    }   
    
    private void updateImage()
    {
        GreenfootImage im = new GreenfootImage(80, 25);
        im.setColor(new Color(0, 0, 0, 150));
        im.fill();
        
        im.drawImage( getStringAsImage("FPS: " + ((int) (1000 / (System.currentTimeMillis() - lastCallOfAct + 1)))), 22, 8);
        
        setImage(im);
    } 
}
