package cz.fit.dpo.mvcshooter.model.entities;

import cz.fit.dpo.mvcshooter.model.ModelConfig;
import cz.fit.dpo.mvcshooter.model.factories.EntitiesFactory;
import cz.fit.dpo.mvcshooter.model.states.DoubleShootingState;
import cz.fit.dpo.mvcshooter.model.states.ShootingState;
import cz.fit.dpo.mvcshooter.model.states.SimpleShootingState;
import cz.fit.dpo.mvcshooter.view.Visitor;

import java.awt.Graphics;
import java.util.List;

/**
 *
 * @author Ondrej Stuchlik
 */
public class Cannon extends GameObject {

	private int angle = ModelConfig.CANNON_DEFAULT_ANGLE;
    private int force = ModelConfig.CANNON_DEFAULT_FORCE;
    
    private ShootingState shootingState;

    public Cannon() {
        super(ModelConfig.CANNON_X, ModelConfig.CANNON_DEFAULT_Y);
        shootingState = new SimpleShootingState();
    }
    
    public Cannon(Cannon cannon) {
    	super(cannon.getX(), cannon.getY());
    	shootingState = cannon.getShootingState();
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
	
	public ShootingState getShootingState() {
		return shootingState;
	}
   
    public void moveUp() {
        if (getY() >= ModelConfig.CANNON_TOP_MARGIN) {
            setY(getY() - ModelConfig.CANNON_MOVE_STEP);
        }
    }
    
    public void moveDown() {
        if (getY() <= ModelConfig.PLAYGROUND_HEIGHT - ModelConfig.CANNON_BOTTOM_MARGIN) {
            setY(getY() + ModelConfig.CANNON_MOVE_STEP);
        }
    }
    
    public void switchShootingState() {
    	if (shootingState instanceof SimpleShootingState)
    		shootingState = new DoubleShootingState();
    	else
    		shootingState = new SimpleShootingState();
    }
    
    public List<Missile> shoot(EntitiesFactory factory) {
    	return shootingState.shoot(getX(), getY(), factory);
    }

	public void accept(Graphics g, Visitor visitor) {
		visitor.visit(g, this);
	}
    
}
