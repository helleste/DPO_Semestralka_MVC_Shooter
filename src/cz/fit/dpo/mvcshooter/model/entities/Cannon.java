package cz.fit.dpo.mvcshooter.model.entities;

import cz.fit.dpo.mvcshooter.model.ModelConfig;
import cz.fit.dpo.mvcshooter.view.Visitor;
import java.awt.Graphics;

/**
 *
 * @author Ondrej Stuchlik
 */
public class Cannon extends GameObject {

	private int angle = ModelConfig.CANNON_DEFAULT_ANGLE;
    private int force = ModelConfig.CANNON_DEFAULT_FORCE;

    public Cannon() {
        super(ModelConfig.CANNON_X, ModelConfig.CANNON_DEFAULT_Y);
    }
    
    public int getAngle() {
		return angle;
	}

	public void setAngle(int angle) {
		this.angle = angle;
	}

	public int getForce() {
		return force;
	}

	public void setForce(int force) {
		this.force = force;
	}
   
    public void moveUp() {
        if (y >= ModelConfig.CANNON_TOP_MARGIN) {
            y -= ModelConfig.CANNON_MOVE_STEP;
        }
    }
    
    public void moveDown() {
        if (y <= ModelConfig.PLAYGROUND_HEIGHT - ModelConfig.CANNON_BOTTOM_MARGIN) {
            y +=  ModelConfig.CANNON_MOVE_STEP;
        }
    }

	public void accept(Graphics g, Visitor visitor) {
		visitor.visit(g, this);
	}
    
}
