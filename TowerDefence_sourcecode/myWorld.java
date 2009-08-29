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
 * Write a description of class ChooseMap here.
 * 
 * @author (Kevin Huber) 
 * @version (1.1)
 */
public class myWorld extends World
{
    public static World_preview map1 = new World_preview();
    public static World2_preview map2 = new World2_preview();
    public static World3_preview map3 = new World3_preview();
    public static MenuControl ms = new MenuControl();

    /**
     * Constructor for objects of class ChooseMap.
     * 
     */
    public myWorld()
    {    
        super(760, 560, 1);
        
        setBackground("mainMenuBG.png");
        
        setPaintOrder(Explosion.class, GameOverScreen.class, HealthBar.class, DamageReceived.class,
                      Creep.class, LevelBar.class, NormalTower.class, Label.class, Counter.class, Bullet.class);
        setActOrder(Label.class, Creep.class, Tower.class, Bullet.class, Spawn.class, Controls.class);
        
        addObject(map1, 320, 165);
        addObject(map2, 565, 245);
        addObject(map3, 320, 390);
        addObject(ms, 0, 0);
        
        //setLevel(3);
    }
    
    
    public void setLevel(int mapId)
    {
        removeObject(map1);
        removeObject(map2);
        removeObject(map3);
        removeObject(ms);
        
        UI ui = new UI();
        
        //Menu interface entfernen
        removeObject(map1);
        removeObject(map2);
        removeObject(ms);
        
        switch(mapId)
        {
            case 1:
                setBackground("world.png");
                // reset UI
                ui.reset();
        
                // Create controls and spawns
                addObject(new Controls(), 0, 0);
                addObject(new Spawn(0), 0, 70);
                addObject(ui, 0, 0);
                
                
                // Remove the image of the ui
                ui.setImage(new GreenfootImage(1, 1));
                
                // Create FPS label
                ui.fpsLabel = new FPSLabel();
                addObject(ui.fpsLabel, 20, 6);
                
                
                // Create menu
                addObject(new Menu(), 660, 280);
                
                // Create gold counter
                ui.goldCounter = new Counter(ui.getGold());
                addObject(ui.goldCounter, 688, 73);
                
                // Create wave counter
                ui.nextWaveLabel = new Label("n.a");
                addObject(ui.nextWaveLabel, 688, 79);
                
                // Create live counter
                ui.liveCounter = new Counter(ui.getLives());
                addObject(ui.liveCounter, 688, 121);
                
                // Create level counter
                ui.levelCounter = new Counter(ui.getLevel());
                addObject(ui.levelCounter, 688, 145);
                
                
                // Buy normal tower button
                ui.buyNormalTowerButton = new BuyNormalTowerButton();
                addObject(ui.buyNormalTowerButton, 597, 218);
                
                // Buy fastfire tower button
                ui.buyFastFireTowerButton = new BuyFastFireTowerButton();
                addObject(ui.buyFastFireTowerButton, 659, 218);
                
                // Buy rocket tower button
                ui.buyRocketTowerButton = new BuyRocketTowerButton();
                addObject(ui.buyRocketTowerButton, 721, 218);
                
                
                // Create tower level counter
                ui.selectedTowerLevelLabel = new Label("n.a");
                addObject(ui.selectedTowerLevelLabel, 688, 386);
                
                // Create Tower damage counter
                ui.selectedTowerDamageLabel = new Label("n.a");
                addObject(ui.selectedTowerDamageLabel, 688, 410);
                
                // Create tower radius counter
                ui.selectedTowerRadiusLabel = new Label("n.a");
                addObject(ui.selectedTowerRadiusLabel, 688, 434);
                
                // Create tower speed counter
                ui.selectedTowerSpeedLabel = new Label("n.a");
                addObject(ui.selectedTowerSpeedLabel, 688, 458);
                
                // Create tower upgrade costs counter
                ui.selectedTowerUpgradeCostLabel = new Label("n.a");
                addObject(ui.selectedTowerUpgradeCostLabel, 688, 510);
                
                // Create tower upgrade button
                ui.upgradeButton = new UpgradeButton();
                addObject(ui.upgradeButton, 616, 540);
                
                // Create tower sell button
                ui.sellButton = new SellButton();
                addObject(ui.sellButton, 706, 540);
                
                // Create normal speed button
                ui.normalSpeedButton = new NormalSpeedButton();
                addObject(ui.normalSpeedButton, 10, 550);
                
                // Create fast speed button
                ui.fastSpeedButton = new FastSpeedButton();
                addObject(ui.fastSpeedButton, 30, 550);
                
                // Create sound on/off button
                ui.soundOnOffButton = new SoundOnOffButton();
                addObject(ui.soundOnOffButton, 55, 550);
                
                
                
                // Create Blocker
                addObject(new Blocker(534, 70), 250, 71);
                addObject(new Blocker(70, 375), 482, 227);
                addObject(new Blocker(364, 70), 335, 392);
                addObject(new Blocker(70, 184), 180, 335);
                addObject(new Blocker(200, 70), 245, 268);
                addObject(new Blocker(70, 160), 318, 223);
                addObject(new Blocker(300, 70), 203, 170);
                addObject(new Blocker(70, 380), 78, 325);
                addObject(new Blocker(725, 70), 405, 492);
                
                
                
                // Create WayPoints
                addObject(new WayPoint(), 480, 70);
                addObject(new WayPoint(), 480, 390);
                addObject(new WayPoint(), 178, 390);
                addObject(new WayPoint(), 178, 270);
                addObject(new WayPoint(), 320, 270);
                addObject(new WayPoint(), 320, 170);
                addObject(new WayPoint(), 78, 170);
                addObject(new WayPoint(), 78, 490);
                addObject(new WayPoint(), 540, 490);
                break;
            case 2:
                setBackground("world2.png");
                // reset UI
                ui.reset();
        
                // Create controls and spawns
                addObject(new Controls(), 0, 0);
                addObject(new Spawn(0), 0, 80);
                addObject(ui, 0, 0);
                
                
                // Remove the image of the ui
                ui.setImage(new GreenfootImage(1, 1));
                
                // Create FPS label
                ui.fpsLabel = new FPSLabel();
                addObject(ui.fpsLabel, 20, 5);
                
                
                // Create menu
                addObject(new Menu(), 660, 280);
                
                // Create gold counter
                ui.goldCounter = new Counter(ui.getGold());
                addObject(ui.goldCounter, 688, 73);
                
                // Create wave counter
                ui.nextWaveLabel = new Label("n.a");
                addObject(ui.nextWaveLabel, 688, 79);
                
                // Create live counter
                ui.liveCounter = new Counter(ui.getLives());
                addObject(ui.liveCounter, 688, 121);
                
                // Create level counter
                ui.levelCounter = new Counter(ui.getLevel());
                addObject(ui.levelCounter, 688, 145);
                
                
                // Buy normal tower button
                ui.buyNormalTowerButton = new BuyNormalTowerButton();
                addObject(ui.buyNormalTowerButton, 597, 218);
                
                // Buy fastfire tower button
                ui.buyFastFireTowerButton = new BuyFastFireTowerButton();
                addObject(ui.buyFastFireTowerButton, 659, 218);
                
                // Buy rocket tower button
                ui.buyRocketTowerButton = new BuyRocketTowerButton();
                addObject(ui.buyRocketTowerButton, 721, 218);
                
                
                // Create tower level counter
                ui.selectedTowerLevelLabel = new Label("n.a");
                addObject(ui.selectedTowerLevelLabel, 688, 386);
                
                // Create Tower damage counter
                ui.selectedTowerDamageLabel = new Label("n.a");
                addObject(ui.selectedTowerDamageLabel, 688, 410);
                
                // Create tower radius counter
                ui.selectedTowerRadiusLabel = new Label("n.a");
                addObject(ui.selectedTowerRadiusLabel, 688, 434);
                
                // Create tower speed counter
                ui.selectedTowerSpeedLabel = new Label("n.a");
                addObject(ui.selectedTowerSpeedLabel, 688, 458);
                
                // Create tower upgrade costs counter
                ui.selectedTowerUpgradeCostLabel = new Label("n.a");
                addObject(ui.selectedTowerUpgradeCostLabel, 688, 510);
                
                // Create tower upgrade button
                ui.upgradeButton = new UpgradeButton();
                addObject(ui.upgradeButton, 616, 540);
                
                // Create tower sell button
                ui.sellButton = new SellButton();
                addObject(ui.sellButton, 706, 540);
                
                // Create normal speed button
                ui.normalSpeedButton = new NormalSpeedButton();
                addObject(ui.normalSpeedButton, 10, 550);
                
                // Create fast speed button
                ui.fastSpeedButton = new FastSpeedButton();
                addObject(ui.fastSpeedButton, 30, 550);
                
                // Create sound on/off button
                ui.soundOnOffButton = new SoundOnOffButton();
                addObject(ui.soundOnOffButton, 55, 550);
                
                
                // Blocker erstellen
                addObject(new Blocker(600, 70), 252, 80);
                addObject(new Blocker(70, 110), 522, 128);
                addObject(new Blocker(600, 70), 252, 180);
                addObject(new Blocker(70, 110), 20, 228);
                addObject(new Blocker(600, 70), 252, 280);
                addObject(new Blocker(70, 110), 522, 328);
                addObject(new Blocker(600, 70), 252, 380);
                addObject(new Blocker(70, 110), 20, 428);
                addObject(new Blocker(600, 70), 252, 480);
                
                
                
                // Wegpunkte erzeugen.
                addObject(new WayPoint(), 522, 80);
                addObject(new WayPoint(), 522, 180);
                addObject(new WayPoint(), 20, 180);
                addObject(new WayPoint(), 20, 280);
                addObject(new WayPoint(), 522, 280);
                addObject(new WayPoint(), 522, 380);
                addObject(new WayPoint(), 20, 380);
                addObject(new WayPoint(), 20, 480);
                addObject(new WayPoint(), 522, 480);
                break;
            case 3:
                setBackground("world3.png");
                // reset UI
                ui.reset();
        
                // Create controls and spawns
                addObject(new Controls(), 0, 0);
                addObject(new Spawn(90), 508, 0);
                addObject(ui, 0, 0);
                
                
                // Remove the image of the ui
                ui.setImage(new GreenfootImage(1, 1));
                
                // Create FPS label
                ui.fpsLabel = new FPSLabel();
                addObject(ui.fpsLabel, 20, 5);
                
                
                // Create menu
                addObject(new Menu(), 660, 280);
                
                // Create gold counter
                ui.goldCounter = new Counter(ui.getGold());
                addObject(ui.goldCounter, 688, 73);
                
                // Create wave counter
                ui.nextWaveLabel = new Label("n.a");
                addObject(ui.nextWaveLabel, 688, 79);
                
                // Create live counter
                ui.liveCounter = new Counter(ui.getLives());
                addObject(ui.liveCounter, 688, 121);
                
                // Create level counter
                ui.levelCounter = new Counter(ui.getLevel());
                addObject(ui.levelCounter, 688, 145);
                
                
                // Buy normal tower button
                ui.buyNormalTowerButton = new BuyNormalTowerButton();
                addObject(ui.buyNormalTowerButton, 597, 218);
                
                // Buy fastfire tower button
                ui.buyFastFireTowerButton = new BuyFastFireTowerButton();
                addObject(ui.buyFastFireTowerButton, 659, 218);
                
                // Buy rocket tower button
                ui.buyRocketTowerButton = new BuyRocketTowerButton();
                addObject(ui.buyRocketTowerButton, 721, 218);
                
                
                // Create tower level counter
                ui.selectedTowerLevelLabel = new Label("n.a");
                addObject(ui.selectedTowerLevelLabel, 688, 386);
                
                // Create Tower damage counter
                ui.selectedTowerDamageLabel = new Label("n.a");
                addObject(ui.selectedTowerDamageLabel, 688, 410);
                
                // Create tower radius counter
                ui.selectedTowerRadiusLabel = new Label("n.a");
                addObject(ui.selectedTowerRadiusLabel, 688, 434);
                
                // Create tower speed counter
                ui.selectedTowerSpeedLabel = new Label("n.a");
                addObject(ui.selectedTowerSpeedLabel, 688, 458);
                
                // Create tower upgrade costs counter
                ui.selectedTowerUpgradeCostLabel = new Label("n.a");
                addObject(ui.selectedTowerUpgradeCostLabel, 688, 510);
                
                // Create tower upgrade button
                ui.upgradeButton = new UpgradeButton();
                addObject(ui.upgradeButton, 616, 540);
                
                // Create tower sell button
                ui.sellButton = new SellButton();
                addObject(ui.sellButton, 706, 540);
                
                // Create normal speed button
                ui.normalSpeedButton = new NormalSpeedButton();
                addObject(ui.normalSpeedButton, 10, 550);
                
                // Create fast speed button
                ui.fastSpeedButton = new FastSpeedButton();
                addObject(ui.fastSpeedButton, 30, 550);
                
                // Create sound on/off button
                ui.soundOnOffButton = new SoundOnOffButton();
                addObject(ui.soundOnOffButton, 55, 550);
                
                
                // Blocker erstellen
                addObject(new Blocker(60, 100), 508, 42);
                addObject(new Blocker(480, 60), 280, 62);
                addObject(new Blocker(60, 240), 70, 174);
                addObject(new Blocker(120, 60), 124, 264);
                addObject(new Blocker(60, 174), 154, 207);
                addObject(new Blocker(120, 60), 194, 150);
                addObject(new Blocker(60, 174), 238, 207);
                addObject(new Blocker(120, 60), 278, 264);
                addObject(new Blocker(60, 174), 321, 207);
                addObject(new Blocker(120, 60), 364, 150);
                addObject(new Blocker(60, 174), 404, 207);
                addObject(new Blocker(120, 60), 446, 264);
                addObject(new Blocker(60, 135), 490, 301);
                addObject(new Blocker(480, 60), 280, 346);
                addObject(new Blocker(60, 193), 62, 412);
                addObject(new Blocker(120, 60), 110, 479);
                addObject(new Blocker(60, 118), 146, 450);
                addObject(new Blocker(120, 60), 184, 421);
                addObject(new Blocker(60, 118), 226, 450);
                addObject(new Blocker(120, 60), 264, 479);
                addObject(new Blocker(60, 118), 304, 450);
                addObject(new Blocker(120, 60), 342, 421);
                addObject(new Blocker(60, 118), 382, 450);
                addObject(new Blocker(200, 60), 460, 479);
                
                
                
                // Wegpunkte erzeugen.
                addObject(new WayPoint(), 508, 61);
                addObject(new WayPoint(), 66, 61);
                addObject(new WayPoint(), 66, 265);
                addObject(new WayPoint(), 153, 265);
                addObject(new WayPoint(), 153, 149);
                addObject(new WayPoint(), 236, 149);
                addObject(new WayPoint(), 236, 265);
                addObject(new WayPoint(), 320, 265);
                addObject(new WayPoint(), 320, 149);
                addObject(new WayPoint(), 405, 149);
                addObject(new WayPoint(), 405, 265);
                
                addObject(new WayPoint(), 488, 265);
                addObject(new WayPoint(), 488, 344);
                addObject(new WayPoint(), 65, 344);
                addObject(new WayPoint(), 65, 480);
                addObject(new WayPoint(), 144, 480);
                addObject(new WayPoint(), 144, 420);
                addObject(new WayPoint(), 224, 420);
                addObject(new WayPoint(), 224, 480);
                addObject(new WayPoint(), 304, 480);
                addObject(new WayPoint(), 304, 420);
                addObject(new WayPoint(), 380, 420);
                addObject(new WayPoint(), 380, 480);
                addObject(new WayPoint(), 525, 480);
                break;
        }
        Greenfoot.setSpeed(100);
        Greenfoot.setSpeed(50);
    }
}
