package cz.fit.dpo.mvcshooter.model.entities;

import cz.fit.dpo.mvcshooter.model.ModelConfig;

/**
 *
 * @author Ondrej Stuchlik
 */
public abstract class GameObject implements Visitable {
    private int x;
	private int y;

    public GameObject(int x, int y) {
        this.setX(x);
        this.setY(y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public boolean collidesWith(GameObject anotherObject) {
        return Math.abs(this.getX() - anotherObject.getX()) < ModelConfig.COLLISION_MARGIN
              && Math.abs(this.getY() - anotherObject.getY()) < ModelConfig.COLLISION_MARGIN;
    }

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}
    
}
