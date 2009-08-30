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
 * Basic control class
 * 
 * @author (Kevin Huber) 
 * @version (1.2)
 */
public class Controls extends UI
{
    public static enum TowerTyp
    {
        None,
        Normal,
        FastFire,
        Rocket,
        Ice
    }
    
    protected static TowerTyp buildTowerTyp = TowerTyp.None;
    protected static TowerRadius selectedTowerRadius;
    protected static NewTowerPreview newTowerPreview;
    protected static MouseInfo mouseInfo;
    
    protected static int mouseX = 10;
    protected static int mouseY = 10;
    
    /**
     * Updates the tower informations in the Menu and redraws the radius of the Tower.
     */
    public void updateTowerDetails()
    {
        if( selectedTower != null )
        {
            selectedTowerLevelLabel.setCaption(""+selectedTower.getLevel());
            selectedTowerDamageLabel.setCaption(""+selectedTower.getDamage());
            selectedTowerRadiusLabel.setCaption(""+selectedTower.getRadius());
            selectedTowerSpeedLabel.setCaption(""+selectedTower.getReloadSpeed());
            selectedTowerUpgradeCostLabel.setCaption(""+selectedTower.getUpgradeCosts());
        }
        
        drawTowerRadius();
    }
    
    /**
     * Set all information in the tower details box to 0, set no tower as selected and remove the drawn tower radius.
     */
    public void deselectTower()
    {
        if( selectedTower != null )
        {
            if( selectedTowerRadius != null )
            {
                getWorld().removeObject(selectedTowerRadius);
                selectedTowerRadius = null;
            }
            
            selectedTowerLevelLabel.setCaption("n.a");
            selectedTowerDamageLabel.setCaption("n.a");
            selectedTowerRadiusLabel.setCaption("n.a");
            selectedTowerSpeedLabel.setCaption("n.a");
            selectedTowerUpgradeCostLabel.setCaption("n.a");
        }
        
        selectedTower = null;
    }
    
    /**
     * Draws a cycle arround the tower.
     */
    public void drawTowerRadius()
    {
        if( selectedTowerRadius != null )
        {
            getWorld().removeObject(selectedTowerRadius);
            selectedTowerRadius = null;
        }
        
        selectedTowerRadius = new TowerRadius(selectedTower);
        getWorld().addObject(selectedTowerRadius, selectedTower.getX(), selectedTower.getY());
    }
    
    /**
     * Displays a tower preview at the cursors position.
     * 
     * @param towerTyp Towertyp that should be displayed
     */
    public void createNewTowerPreview(TowerTyp towerTyp)
    {
        if( towerTyp == TowerTyp.None )
            return;
        
        if( newTowerPreview != null )
        {
            getWorld().removeObject(newTowerPreview);
            newTowerPreview = null;
        }
        
        switch(towerTyp)
        {
            case Normal:
                newTowerPreview = new NewTowerPreview("new_NormalTower.png");
                break;
            case FastFire:
                newTowerPreview = new NewTowerPreview("new_FastFireTower.png");
                break;
            case Rocket:
                newTowerPreview = new NewTowerPreview("new_RocketTower.png");
                break;
            case Ice:
                newTowerPreview = new NewTowerPreview("new_IceTower.png");
                break;
        }
        
        buildTowerTyp = towerTyp;
        getWorld().addObject(newTowerPreview, mouseX, mouseY);
    }
    
    /**
     * Removes the tower preview
     */
    public void removeNewTowerPreview()
    {
        if( newTowerPreview != null )
        {
            getWorld().removeObject(newTowerPreview);
            newTowerPreview = null;
        }
        
        buildTowerTyp = TowerTyp.None;
    }
    
    /**
     * Test if we are close to one of the edges of the world. Return true if we are.
     */
    public boolean atWorldEdge()
    {
        if(mouseX < 10 || mouseX > getWorld().getWidth() - 215)
            return true;
        if(mouseY < 10 || mouseY > getWorld().getHeight() - 10)
            return true;
        else
            return false;
    }
}
