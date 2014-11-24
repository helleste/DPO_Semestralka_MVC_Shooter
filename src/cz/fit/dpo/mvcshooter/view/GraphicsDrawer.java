package cz.fit.dpo.mvcshooter.view;

import cz.fit.dpo.mvcshooter.model.entities.Cannon;
import cz.fit.dpo.mvcshooter.model.entities.Collision;
import cz.fit.dpo.mvcshooter.model.entities.Enemy;
import cz.fit.dpo.mvcshooter.model.entities.Missile;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 *
 * @author Ondrej Stuchlik
 */
public class GraphicsDrawer implements Visitor {
    private static final int INFO_X = 5;
    private static final int INFO_Y = 15;
    
    private BufferedImage cannonImage;
    private BufferedImage enemyImage1;
    private BufferedImage enemyImage2;
    private BufferedImage missileImage;
    private BufferedImage collisionImage;
 

    public GraphicsDrawer() {
        try {
            cannonImage = ImageIO.read(getClass().getResourceAsStream("/resources/images/cannon.png"));
            enemyImage1 = ImageIO.read(getClass().getResourceAsStream("/resources/images/enemy1.png"));
            enemyImage2 = ImageIO.read(getClass().getResourceAsStream("/resources/images/enemy2.png"));
            missileImage = ImageIO.read(getClass().getResourceAsStream("/resources/images/missile.png"));
            collisionImage = ImageIO.read(getClass().getResourceAsStream("/resources/images/collision.png"));
        } catch (IOException ex) {
            ex.printStackTrace(System.err);
        }
    }
        
    
    public void visit(Graphics g, Cannon cannon) {
        g.drawImage(cannonImage, 
              cannon.getX() - cannonImage.getWidth()/2, 
              cannon.getY() - cannonImage.getHeight()/2, null);
    }
    
    public void visit(Graphics g, Missile missile) {
        g.drawImage(missileImage, 
        		missile.getX() - missileImage.getWidth()/2, 
        		missile.getY() - missileImage.getHeight()/2, null);
    }
    
    public void visit(Graphics g, Enemy enemy) {
    	g.drawImage(missileImage, 
        		enemy.getX() - missileImage.getWidth()/2, 
        		enemy.getY() - missileImage.getHeight()/2, null);
    }
    
    public void visit(Graphics g, Collision collision) {        
    	g.drawImage(missileImage, 
        		collision.getX() - missileImage.getWidth()/2, 
        		collision.getY() - missileImage.getHeight()/2, null);
    }
    
    public void drawInfo(Graphics g, ModelInfo info) {
        //
    }
    
    
    // fake classes just to satisfy compilator
    class ModelInfo {}

}
