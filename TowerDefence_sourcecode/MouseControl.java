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
 * Handels all actions done by the mouse.
 * 
 * @author (Kevin Huber) 
 * @version (1.0)
 */
public class MouseControl extends Controls
{
    public MouseControl()
    {
       setImage(new GreenfootImage(1, 1));
    }
    
    /**
     * Act - do whatever the MouseControl wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        MouseInfo mouseInfo = Greenfoot.getMouseInfo();
        
        if( mouseInfo == null )
            return;
        
        mouseX = mouseInfo.getX();
        mouseY = mouseInfo.getY();
        
        if( mouseInfo.getClickCount() <= 0 )
            return;
    
        if( mouseInfo.getButton() == 3 ) // Right mousebutton is clicked, deselect the current tower and cancel all build processes.
        {
            deselectTower();
            removeNewTowerPreview();
        }
        else if( buildTowerTyp != TowerTyp.None )
        {
            if( mouseInfo.getActor() == null )
                return;
            if( atWorldEdge() )
                return;
            
            switch( buildTowerTyp )
            {
                case Normal:
                    if( getGold() >= PRICE_NORMALTOWER )
                    {
                        decreaseGold(PRICE_NORMALTOWER);
                        selectedTower = new NormalTower();
                        selectedTower.levelBar = new LevelBar();
                        
                        getWorld().addObject(selectedTower, mouseX, mouseY);
                        getWorld().addObject(selectedTower.levelBar, mouseX, mouseY);
                        
                        updateTowerDetails();
                        
                        buildTowerTyp = TowerTyp.None;
                    }
                    break;
                case FastFire:
                    if( getGold() >= PRICE_FASTFIRETOWER )
                    {
                        decreaseGold(PRICE_FASTFIRETOWER);
                        selectedTower = new FastFireTower();
                        selectedTower.levelBar = new LevelBar();
                        
                        getWorld().addObject(selectedTower, mouseX, mouseY);
                        getWorld().addObject(selectedTower.levelBar, mouseX, mouseY);
                        
                        updateTowerDetails();
                        
                        buildTowerTyp = TowerTyp.None;
                    }
                    break;
                case Rocket:
                    if( getGold() >= PRICE_ROCKETTOWER )
                    {
                        decreaseGold(PRICE_ROCKETTOWER);
                        selectedTower = new RocketTower();
                        selectedTower.levelBar = new LevelBar();
                        
                        getWorld().addObject(selectedTower, mouseX, mouseY);
                        getWorld().addObject(selectedTower.levelBar, mouseX, mouseY);
                        
                        updateTowerDetails();
                        
                        buildTowerTyp = TowerTyp.None;
                    }
                    break;
                case Ice:
                    if( getGold() >= PRICE_ICETOWER )
                    {
                        decreaseGold(PRICE_ICETOWER);
                        selectedTower = new IceTower();
                        selectedTower.levelBar = new LevelBar();
                        
                        getWorld().addObject(selectedTower, mouseX, mouseY);
                        getWorld().addObject(selectedTower.levelBar, mouseX, mouseY);
                        
                        updateTowerDetails();
                        
                        buildTowerTyp = TowerTyp.None;
                    }
                    break;
            }
            
            removeNewTowerPreview();
        }
        else if( getWorld().getObjectsAt(mouseX, mouseY, Tower.class).isEmpty() == false ) // select the tower under the players mouse and bring up it's details.
        {
            selectedTower = (Tower) getWorld().getObjectsAt(mouseX, mouseY, Tower.class).get(0);
            updateTowerDetails();
        }
        else if( getWorld().getObjectsAt(mouseX, mouseY, Button.class).isEmpty() ) // Deselect all
        {
            deselectTower();
        }
    }
}
