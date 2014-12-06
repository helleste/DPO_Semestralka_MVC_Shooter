/**
 * 
 */
package cz.fit.dpo.mvcshooter.model.entities;

import java.util.Random;

import cz.fit.dpo.mvcshooter.model.ModelConfig;

/**
 * @author helleste
 *
 */
public abstract class Enemy extends GameObject {
	
	// Maybe in the future implement random generation of positions
	public Enemy(int x, int y) {
		super(x, y);
	}
	
	public Enemy clone(){
		return null;
	}
	
	@Override
	public String toString() {
		return "Enemy x=" + getX() + " y=" +getY();
	}

}
