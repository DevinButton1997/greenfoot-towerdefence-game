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
 * Buy RocketTower button.
 * 
 * @author (Kevin Huber) 
 * @version (1.1)
 */
public class BuyRocketTowerButton extends Button
{
    /**
     * Check if the button is pressed and handel the result.
     */
    public void act() 
    {
        // Check if the mouse is over the button to do a mouse over effect
        if( getWorld().getObjectsAt(mouseX, mouseY, BuyRocketTowerButton.class).isEmpty() )
        {
            if( mouseOverEffectDisplayed )
            {
                setImage("buy_RocketTower.png");
                mouseOverEffectDisplayed = false;
            }
            
            return;
        }
        else // The mouse is visiting our button ;) .
        {
            if( !mouseOverEffectDisplayed )
            {
                setImage("buy_RocketTower_over.png");
                mouseOverEffectDisplayed = true;
            }
            
            if( Greenfoot.mouseClicked(this) )
            {
                onButtonPressed();
            }
        }
    }
    
    public void onButtonPressed()
    {
        // The button is beeing pressed, now do something!
        createNewTowerPreview(TowerTyp.Rocket);
    }
}
