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
public class Enemy extends GameObject {
	
	// Maybe in the future implement random generation of positions
	public Enemy(int x, int y) {
		super(x, y);
	}
	
	// Moves the enemy to random position on the field
	public void move() {
		x = generateX();
		y = generateY();
	}
	
	// Generates random coordinate x
	private int generateX() {
		Random rand = new Random();
		return rand.nextInt(ModelConfig.PLAYGROUND_WIDTH);
	}
	
	// Generates random coordinate x
	private int generateY() {
		Random rand = new Random();
		return rand.nextInt(ModelConfig.PLAYGROUND_HEIGHT);
	}
	
	@Override
	public String toString() {
		return "Enemy x=" + x + " y=" +y;
	}

}
