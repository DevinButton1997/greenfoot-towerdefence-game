import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;

/**
 * Write a description of class NormalBullet here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class NormalBullet extends Bullet
{
    public NormalBullet(int pX, int pY, int speed, int damage, String explosionSound)
    {
        super(pX, pY, speed, damage, explosionSound);
        
        GreenfootImage bulletImage = new GreenfootImage(13,13);
        bulletImage.fillOval(1, 1, 12, 12);
        
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
