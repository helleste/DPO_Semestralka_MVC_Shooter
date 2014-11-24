package cz.fit.dpo.mvcshooter.model.entities;

import java.awt.Graphics;

import cz.fit.dpo.mvcshooter.model.ModelConfig;
import cz.fit.dpo.mvcshooter.view.Visitor;

public class Missile extends GameObject {
	
	public long startTime;
	private MovementStrategy strategy;

	public Missile(int x, int y) {
		super(x, y);
		startTime = System.currentTimeMillis();
		// TODO Auto-generated constructor stub
	}
	
	public Missile(int x, int y, MovementStrategy strategy) {
		super(x, y);
		this.strategy = strategy;
		this.startTime = System.currentTimeMillis();
	}
	
	public void move(Cannon cannon) {
		strategy.moveMissile(this, cannon);
	}
	
	@Override
	public String toString() {
		return "Missile x=" + x + " y=" + y;
	}

	@Override
	public void accept(Graphics g, Visitor visitor) {
		visitor.visit(g, this);
	}

}
