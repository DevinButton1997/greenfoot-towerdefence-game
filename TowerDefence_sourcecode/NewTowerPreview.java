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
 * Displays a preview image of the new tower at the cursors position.
 * 
 * @author (Kevin Huber) 
 * @version (1.0)
 */
public class NewTowerPreview extends Actor
{
    public NewTowerPreview(String imageFile)
    {
        setImage(imageFile);
    }
    /**
     * Act - do whatever the NewTowerPreview wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        MouseInfo mouseInfo = Greenfoot.getMouseInfo();
        
        if( mouseInfo == null )
            return;
        
        setLocation(mouseInfo.getX(), mouseInfo.getY());
    }    
}
