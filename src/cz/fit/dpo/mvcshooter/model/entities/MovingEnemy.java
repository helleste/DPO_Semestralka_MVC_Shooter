package cz.fit.dpo.mvcshooter.model.entities;

import java.awt.Graphics;
import java.util.Random;

import cz.fit.dpo.mvcshooter.model.ModelConfig;
import cz.fit.dpo.mvcshooter.view.Visitor;

public class MovingEnemy extends Enemy {

	public MovingEnemy(int x, int y) {
		super(x, y);
		// Tady asi dodělat to hejbání
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
	public void accept(Graphics g, Visitor visitor) {
		visitor.visit(g, this);
	}

}
