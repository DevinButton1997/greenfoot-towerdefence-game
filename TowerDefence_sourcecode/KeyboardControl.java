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
 * This class handels all actions done by the keyboard
 * 
 * @author (Kevin Huber) 
 * @version (1.0)
 */
public class KeyboardControl  extends Controls
{
    protected static final int timeOut = 250; // in ms
    private long lastKeystroke;
    
    public KeyboardControl()
    {
       setImage(new GreenfootImage(1, 1));
    }
    
    /**
     * Act - do whatever the KeyboardControl wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if( System.currentTimeMillis() < (lastKeystroke + timeOut) )
            return;
        
        if( (Greenfoot.isKeyDown("u"))&&(selectedTower != null)&&(getGold() >= selectedTower.getUpgradeCosts()) ) // Upgrade selected tower
        {
            decreaseGold(selectedTower.getUpgradeCosts());
               
            selectedTower.levelUp();
            updateTowerDetails();
            
            lastKeystroke = System.currentTimeMillis();
        }
        else if( (Greenfoot.isKeyDown("s"))&&(selectedTower != null) ) // Sell Tower
        {
            increaseGold((int) Math.round((selectedTower.getUpgradeCosts()/2)*0.75));
            
            getWorld().removeObject(selectedTower.levelBar);
            getWorld().removeObject(selectedTower);
            deselectTower();
            
            lastKeystroke = System.currentTimeMillis();
        }
        else if( Greenfoot.isKeyDown("1") ) // Build a new Normal Tower
        {
            createNewTowerPreview(TowerTyp.Normal);
            
            lastKeystroke = System.currentTimeMillis();
        }
        else if( Greenfoot.isKeyDown("2") ) // Build a new FastFire Tower
        {
            createNewTowerPreview(TowerTyp.FastFire);
            
            lastKeystroke = System.currentTimeMillis();
        }
        else if( Greenfoot.isKeyDown("3") ) // Build a new Rocket Tower
        {
            createNewTowerPreview(TowerTyp.Rocket);
            
            lastKeystroke = System.currentTimeMillis();
        }
        /*else if( Greenfoot.isKeyDown("4") ) // Build a new Ice Tower
        {
            createNewTowerPreview(TowerTyp.Ice);
            
            lastKeystroke = System.currentTimeMillis();
        }*/
    }    
}
