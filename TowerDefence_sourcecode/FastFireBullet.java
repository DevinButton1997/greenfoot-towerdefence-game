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
 * Write a description of class FastFireBullet here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FastFireBullet extends Bullet
{
    public FastFireBullet(int pX, int pY, int speed, int damage, String explosionSound)
    {
        super(pX, pY, speed, damage, explosionSound);
        
        GreenfootImage bulletImage = new GreenfootImage(7,7);
        bulletImage.fillOval(1, 1, 6, 6);
        
        setImage(bulletImage);
    }
    
    /**
     * Act - do whatever the NormalBullet wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        super.act();
    }   
}
