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
import java.awt.Font;
import java.awt.Color;

/**
 * Label that Displays a String.
 * 
 * @author (Kevin Huber) 
 * @version (1.1)
 */
public class Label extends Actor
{   
    private String text;

    public Label(String prefix)
    {
        text = prefix;
        int stringLength = (text.length() + 2) * 10;

        setImage(new GreenfootImage(stringLength, 16));
        updateImage();
    }
    
    /**
     * Change the Caption of the Label
     */
    public void setCaption(String caption)
    {
        text = caption;
        updateImage();
    }
    
    /**
     * Make the image
     */
    private void updateImage()
    {
        GreenfootImage image = getImage();
        image.clear();
        image.setFont(new Font("Tahoma", Font.BOLD, 12));
        image.setColor(Color.WHITE);
        image.drawString(text, 1, 12);
    }
    
    /**
     * Returns a handle to a GreenfootImage with a copy of the label text
     */
    protected GreenfootImage getStringAsImage(String string)
    {
        GreenfootImage image = new GreenfootImage((string.length() + 2) * 10, 16);
        image.setFont(new Font("Tahoma", Font.BOLD, 12));
        image.setColor(Color.WHITE);
        image.drawString(string, 1, 12);
        
        return image;
    }
}
