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
import java.awt.Font;

/**
 * Screen with the end result of the Game(loose or win)
 * 
 * @author (Kevin Huber) 
 * @version (1.0)
 */
public class GameOverScreen extends Actor
{
    public GameOverScreen(int imWidth, int imHeight, boolean won)
    {
        GreenfootImage im = new GreenfootImage(imWidth, imHeight);
        
        im.setColor(new Color(0, 0, 0, 90));
        im.fill();
        
        im.setFont(new Font("Tahoma", Font.BOLD, 32));
        
        if(!won)
        {
            im.setColor(Color.RED);
            im.drawString("Du hast verloren, probiere es erneut.", imWidth/2-295, imHeight/2);
        }
        else
        {
            im.setColor(Color.GREEN);
            im.drawString("Herzlichen Glückwunsch, du hast gewonnen!", imWidth/2-355, imHeight/2);
        }
        setImage(im);
    }
}
