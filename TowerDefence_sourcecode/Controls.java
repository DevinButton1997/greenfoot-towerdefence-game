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
 * Controls Mouse and Keyboard actions.
 * 
 * @author (Kevin Huber) 
 * @version (1.1)
 */
public class Controls extends UI
{
     private MouseInfo mouseInfo;
     private int keyDelay = 10;
     
     private int mouseX = 10;
     private int mouseY = 10;
     
     private boolean onButton = false;
     private boolean onNewTower = false;
     
     private boolean onUpgradeButton = false;
     private boolean onSellButton = false;
     private boolean onBuyNormalTowerButton = false;
     private boolean onBuyFastFireTowerButton = false;
     private boolean onBuyRocketTowerButton = false;
     
     private boolean buttonDown = false;
     
     public Controls()
     {
        setImage(new GreenfootImage(1, 1));
     }
     
    /**
     * Act - do whatever the Steuerung wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        mouseInfo = Greenfoot.getMouseInfo();
        
        
        if( mouseInfo != null )
        {
            mouseX = mouseInfo.getX();
            mouseY = mouseInfo.getY();
            
            if( buttonDown )
                buttonDown = false;
            
            // Check overlays
            if( !getWorld().getObjectsAt(mouseX, mouseY, UpgradeButton.class).isEmpty() )
            {
                if( !onUpgradeButton )
                    upgradeButton.setImage("button_Upgrade_over.png");
                
                if( onButton )
                {
                    buyFastFireTowerButton.setImage("buy_FastFireTower.png");
                    buyRocketTowerButton.setImage("buy_RocketTower.png");
                    sellButton.setImage("button_sell.png");
                    buyNormalTowerButton.setImage("buy_NormalTower.png");
                }
                
                onButton                 = true;
                onUpgradeButton          = true;
                onSellButton             = false;
                onBuyNormalTowerButton   = false;
                onBuyFastFireTowerButton = false;
                onBuyRocketTowerButton   = false;
            }
            else if( !getWorld().getObjectsAt(mouseX, mouseY, SellButton.class).isEmpty() )
            {
                if( !onSellButton )
                    sellButton.setImage("button_sell_over.png");
                
                if( onButton )
                {
                    buyFastFireTowerButton.setImage("buy_FastFireTower.png");
                    buyRocketTowerButton.setImage("buy_RocketTower.png");
                    upgradeButton.setImage("button_Upgrade.png");
                    buyNormalTowerButton.setImage("buy_NormalTower.png");
                }
                
                onButton                 = true;
                onUpgradeButton          = false;
                onSellButton             = true;
                onBuyNormalTowerButton   = false;
                onBuyFastFireTowerButton = false;
                onBuyRocketTowerButton   = false;
            }
            else if( !getWorld().getObjectsAt(mouseX, mouseY, BuyNormalTowerButton.class).isEmpty() )
            {
                if( !onBuyNormalTowerButton )
                    buyNormalTowerButton.setImage("buy_NormalTower_over.png");
                
                if( onButton )
                {
                    buyFastFireTowerButton.setImage("buy_FastFireTower.png");
                    buyRocketTowerButton.setImage("buy_RocketTower.png");
                    upgradeButton.setImage("button_Upgrade.png");
                    sellButton.setImage("button_sell.png");
                }
                
                onButton                 = true;
                onUpgradeButton          = false;
                onSellButton             = false;
                onBuyNormalTowerButton   = true;
                onBuyFastFireTowerButton = false;
                onBuyRocketTowerButton   = false;
            }
            else if( !getWorld().getObjectsAt(mouseX, mouseY, BuyFastFireTowerButton.class).isEmpty() )
            {   
                if( !onBuyFastFireTowerButton )
                    buyFastFireTowerButton.setImage("buy_FastFireTower_over.png");
                
                if( onButton )
                {
                    buyNormalTowerButton.setImage("buy_NormalTower.png");
                    buyRocketTowerButton.setImage("buy_RocketTower.png");
                    upgradeButton.setImage("button_Upgrade.png");
                    sellButton.setImage("button_sell.png");
                }
                
                onButton                 = true;
                onUpgradeButton          = false;
                onSellButton             = false;
                onBuyNormalTowerButton   = false;
                onBuyFastFireTowerButton = true;
                onBuyRocketTowerButton   = false;
            }
            else if( !getWorld().getObjectsAt(mouseX, mouseY, BuyRocketTowerButton.class).isEmpty() )
            {
                if( !onBuyRocketTowerButton )
                    buyRocketTowerButton.setImage("buy_RocketTower_over.png");
                
                if( onButton )
                {
                    buyNormalTowerButton.setImage("buy_NormalTower.png");
                    buyFastFireTowerButton.setImage("buy_FastFireTower.png");
                    upgradeButton.setImage("button_Upgrade.png");
                    sellButton.setImage("button_sell.png");
                }
                
                onButton                 = true;
                onUpgradeButton          = false;
                onSellButton             = false;
                onBuyNormalTowerButton   = false;
                onBuyFastFireTowerButton = false;
                onBuyRocketTowerButton   = true;
            }
            else if( onButton )
            {        
                buyNormalTowerButton.setImage("buy_NormalTower.png");
                buyFastFireTowerButton.setImage("buy_FastFireTower.png");
                buyRocketTowerButton.setImage("buy_RocketTower.png");
                upgradeButton.setImage("button_Upgrade.png");
                sellButton.setImage("button_sell.png");
                
                onButton                 = false;
                onUpgradeButton          = false;
                onSellButton             = false;
                onBuyNormalTowerButton   = false;
                onBuyFastFireTowerButton = false;
                onBuyRocketTowerButton   = true;
            }
            
            
            // Buttons überprüfen
            if( Greenfoot.mouseClicked(buyNormalTowerButton) )
            {
                buildTower = 1;  // 1 = Normal tower
                onNewTower = false;
                buttonDown = true;
            }
            else if( Greenfoot.mouseClicked(buyFastFireTowerButton) )
            {
                buildTower = 2;  // 2 = FastFire Tower
                onNewTower = false;
                buttonDown = true;
            }
            else if( Greenfoot.mouseClicked(buyRocketTowerButton) )
            {
                buildTower = 3;  // 3 = Rocket Tower
                onNewTower = false;
                buttonDown = true;
            }
            else if( (selectedTower != null)&&(Greenfoot.mouseClicked(upgradeButton)) )
            {
                if( getGold() >= selectedTower.getUpgradeCosts() )
                {
                    decreaseGold(selectedTower.getUpgradeCosts());
                    
                    selectedTower.levelUp();
                    updateTowerDetails();
                }
                buttonDown = true;
            }
            else if( (selectedTower != null)&&(Greenfoot.mouseClicked(sellButton)) )
            {
                increaseGold((int) Math.round((selectedTower.getUpgradeCosts()/2)*0.75));
                
                getWorld().removeObject(selectedTower);
                getWorld().removeObject(selectedTower.levelBar);
                deselectTower();
                buttonDown = true;
            }
            else if( Greenfoot.mouseClicked(normalSpeedButton) )
            {
                Greenfoot.setSpeed(50);
                buttonDown = true;
            }
            else if( Greenfoot.mouseClicked(fastSpeedButton) )
            {
                Greenfoot.setSpeed(65);
                buttonDown = true;
            }
            else if( Greenfoot.mouseClicked(soundOnOffButton) )
            {
                if( soundOn )
                {
                    soundOnOffButton.setImage("button_sound_off.png");
                }
                else
                {
                    soundOnOffButton.setImage("button_sound_on.png");
                }
                
                soundOn = !soundOn;
            }
            
            
            // If the player wants to build a new tower we give him a preview of the tower under his mouse.
            if( buildTower > 0 )
            {
                switch(buildTower)
                {
                    case 1:
                        if( !onNewTower )
                        {
                            setImage("new_NormalTower.png");
                            onNewTower = true;
                        }
                        setLocation(mouseX, mouseY);
                        break;
                    case 2:
                        if( !onNewTower )
                        {
                            setImage("new_FastFireTower.png");
                            onNewTower = true;
                        }
                        setLocation(mouseX, mouseY);
                        onNewTower = true;
                        break;
                    case 3:
                        if( !onNewTower )
                        {
                            setImage("new_RocketTower.png");
                            onNewTower = true;
                        }
                        setLocation(mouseX, mouseY);
                        onNewTower = true;
                        break;
                }
            }
            
            
            if( mouseInfo.getButton() == 3 ) // Right mouse is clicked, deselect the current tower and cancel all build progresses.
            {
                deselectTower();
                
                buildTower = 0;
                onNewTower = false;
            }
            else if( ((mouseInfo.getActor() == null)|| // The player wants to build a new tower, let's check his financials first ;) .
                     ((!getWorld().getObjectsAt(mouseX, mouseY, Controls.class).isEmpty())&&(getWorld().getObjectsAt(mouseX, mouseY, Tower.class).isEmpty())))&&
                     (mouseInfo.getClickCount() > 0)&&(!atWorldEdge())&&(getWorld().getObjectsAt(mouseX, mouseY, Blocker.class).isEmpty())&&(buildTower > 0) )
            {
                switch(buildTower)
                {
                    case 1:
                        if( getGold() >= PRICE_NORMALTOWER )
                        {
                            decreaseGold(PRICE_NORMALTOWER);
                            selectedTower = new NormalTower();
                            selectedTower.levelBar = new LevelBar();
                            
                            getWorld().addObject(selectedTower, mouseX, mouseY);
                            getWorld().addObject(selectedTower.levelBar, mouseX, mouseY);
                            
                            updateTowerDetails();
                            
                            buildTower = 0; // 0 = No Tower
                            onNewTower = false;
                        }
                        break;
                    case 2:
                        if( getGold() >= PRICE_FASTFIRETOWER )
                        {
                            decreaseGold(PRICE_FASTFIRETOWER);
                            selectedTower = new FastFireTower();
                            selectedTower.levelBar = new LevelBar();
                            
                            getWorld().addObject(selectedTower, mouseX, mouseY);
                            getWorld().addObject(selectedTower.levelBar, mouseX, mouseY);
                            
                            updateTowerDetails();
                            
                            buildTower = 0; // 0 = No Tower
                            onNewTower = false;
                        }
                        break;
                    case 3:
                        if(getGold() >= PRICE_ROCKETTOWER)
                        {
                            decreaseGold(PRICE_ROCKETTOWER);
                            selectedTower = new RocketTower();
                            selectedTower.levelBar = new LevelBar();
                            
                            getWorld().addObject(selectedTower, mouseX, mouseY);
                            getWorld().addObject(selectedTower.levelBar, mouseX, mouseY);
                            
                            updateTowerDetails();
                            
                            buildTower = 0; // 0 = No Tower
                            onNewTower = false;
                        }
                        break;
                }
            }
            else if( (getWorld().getObjectsAt(mouseX, mouseY, Tower.class).isEmpty() == false)&&(mouseInfo.getClickCount() > 0)&&(buildTower <= 0) ) // select the tower under the players mouse and bring up it's specifications.
            {
                selectedTower = (Tower) getWorld().getObjectsAt(mouseX, mouseY, Tower.class).get(0);
                updateTowerDetails();
                
                buildTower = 0;
            }
            else if( (mouseInfo.getClickCount() > 0)&&(buildTower <= 0)&&(!buttonDown) )
            {
                deselectTower();
                buttonDown = false;
            }
        }
        
        
        if( keyDelay < 25 )
            keyDelay++;
        
        if( (Greenfoot.isKeyDown("u"))&&(selectedTower != null)&&(getGold() >= selectedTower.getUpgradeCosts())&&(keyDelay >= 25) ) // Upgrade selected tower
        {
            decreaseGold(selectedTower.getUpgradeCosts());
                
            selectedTower.levelUp();
            updateTowerDetails();
            
            keyDelay = 0;
        }
        else if( (Greenfoot.isKeyDown("s"))&&(selectedTower != null)&&(keyDelay >= 25) ) // Sell Tower
        {
            increaseGold((int) Math.round((selectedTower.getUpgradeCosts()/2)*0.75));
            
            getWorld().removeObject(selectedTower);
            getWorld().removeObject(selectedTower.levelBar);
            deselectTower();
        }
        else if( (Greenfoot.isKeyDown("1"))&&(keyDelay >= 25) ) // Build a new Normal Tower
        {
            buildTower = 1; // Normal Tower
            onNewTower = false;
            
            setImage("new_NormalTower.png");
            setLocation(mouseX, mouseY);
        }
        else if((Greenfoot.isKeyDown("2"))&&(keyDelay >= 25)) // Build a new FastFire Tower
        {
            buildTower = 2; // FastFire Tower
            onNewTower = false;
            
            setImage("new_FastFireTower.png");
            setLocation(mouseX, mouseY);
        }
        else if((Greenfoot.isKeyDown("3"))&&(keyDelay >= 25)) // Build a new Rocket Tower
        {
            buildTower = 3; // Rocket Tower
            onNewTower = false;
            
            
            setImage("new_RocketTower.png");
            setLocation(mouseX, mouseY);
        }
    }    
    
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
            selectedTowerLevelLabel.setCaption("n.a");
            selectedTowerDamageLabel.setCaption("n.a");
            selectedTowerRadiusLabel.setCaption("n.a");
            selectedTowerSpeedLabel.setCaption("n.a");
            selectedTowerUpgradeCostLabel.setCaption("n.a");
        }
        
        setImage(new GreenfootImage(1, 1));
        setLocation(0, 0);
        
        selectedTower = null;
    }
    
    /**
     * Draws a cycle arround the tower.
     */
    public void drawTowerRadius()
    {
        GreenfootImage im = new GreenfootImage((int) (selectedTower.getRadius()*1.8), (int) (selectedTower.getRadius()*1.8));
        setImage(im);
                
        // Fill the circle
        im.setColor(new Color (46, 46, 46, 180));
        im.fillOval(0, 0, (int) (selectedTower.getRadius()*1.8), (int) (selectedTower.getRadius()*1.8));
                
        setLocation(selectedTower.getX(), selectedTower.getY());
        setImage(im);
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
