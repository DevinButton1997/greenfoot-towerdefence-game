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
 
import greenfoot.*;  // (World, Actor, GreenfootImage, and Greenfoot)

import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * Counter that displays a number.
 * 
 * @author Michael Kolling / Modified by Kevin Huber
 * @version 1.0.2
 */
public class Counter extends Actor
{
    private int value = 0;
    private String text;
    private int stringLength;

    public Counter()
    {
        text         = "";
        stringLength = (text.length() + 2) * 10;
        updateImage();
    }
    
    
    public Counter(int startValue)
    {
        value        = startValue;
        text         = "";
        stringLength = (text.length() + 2) * 10;
        updateImage();
    }

    public Counter(String prefix)
    {
        text         = prefix;
        stringLength = (text.length() + 2) * 10;

        setImage(new GreenfootImage(stringLength, 16));
        updateImage();
    }
    
    
    public Counter(String prefix, int startValue)
    {
        value        = startValue;
        text         = prefix;
        stringLength = (text.length() + 2) * 10;

        setImage(new GreenfootImage(stringLength, 16));
        updateImage();
    }

    public void increment()
    {
        value++;
        updateImage();
    }
    
    public void increment(int ammout)
    {
        value += ammout;
        updateImage();
    }
    
    public void decrement()
    {
        value--;
        updateImage();
    }
    
    public void decrement(int ammout)
    {
        value -= ammout;
        updateImage();
    }
    
    public void setValue(int newValue)
    {
        value = newValue;
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
        image.drawString(text + value, 1, 12);
    }
}