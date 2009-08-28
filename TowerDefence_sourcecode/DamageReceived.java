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
 * Displays the ammout of damage given to a creep.
 * 
 * @author (Kevin Huber) 
 * @version (1.1)
 */
public class DamageReceived extends Actor
{
    private static GreenfootImage char_minus = new GreenfootImage("char_-.png");
    private static GreenfootImage char_0 = new GreenfootImage("char_0.png");
    private static GreenfootImage char_1 = new GreenfootImage("char_1.png");
    private static GreenfootImage char_2 = new GreenfootImage("char_2.png");
    private static GreenfootImage char_3 = new GreenfootImage("char_3.png");
    private static GreenfootImage char_4 = new GreenfootImage("char_4.png");
    private static GreenfootImage char_5 = new GreenfootImage("char_5.png");
    private static GreenfootImage char_6 = new GreenfootImage("char_6.png");
    private static GreenfootImage char_7 = new GreenfootImage("char_7.png");
    private static GreenfootImage char_8 = new GreenfootImage("char_8.png");
    private static GreenfootImage char_9 = new GreenfootImage("char_9.png");
    
    private int atl = 50; //Acts to live

    public DamageReceived(String damage)
    {
        GreenfootImage im = new GreenfootImage(10*damage.length()+10, char_minus.getHeight());
        
        int pX = 0;
        
        im.drawImage(char_minus, pX, 0);
        
        
        for(int i = 0; i < damage.length(); i++)
        {
            pX += char_minus.getWidth();
            
            switch(Integer.parseInt(String.valueOf(damage.charAt(i))))
            {
                case 0:
                    im.drawImage(char_0, pX, 0);
                    break;
                case 1:
                    im.drawImage(char_1, pX, 0);
                    break;
                case 2:
                    im.drawImage(char_2, pX, 0);
                    break;
                case 3:
                    im.drawImage(char_3, pX, 0);
                    break;
                case 4:
                    im.drawImage(char_4, pX, 0);
                    break;
                case 5:
                    im.drawImage(char_5, pX, 0);
                    break;
                case 6:
                    im.drawImage(char_6, pX, 0);
                    break;
                case 7:
                    im.drawImage(char_7, pX, 0);
                    break;
                case 8:
                    im.drawImage(char_8, pX, 0);
                    break;
                case 9:
                    im.drawImage(char_9, pX, 0);
                    break;
            }
        }
        
        setImage(im);
    }
    
    /**
     * Act - do whatever the DamageReceived wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        atl--;
        
        if(atl > 0)
        {
            setLocation(getX()+1, getY()-1);
        }
        else
        {
            getWorld().removeObject(this);
            return;
        }
    }    
}
