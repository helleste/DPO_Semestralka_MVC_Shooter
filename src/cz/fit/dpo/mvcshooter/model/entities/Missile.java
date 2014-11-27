package cz.fit.dpo.mvcshooter.model.entities;

import java.awt.Graphics;

import cz.fit.dpo.mvcshooter.model.ModelConfig;
import cz.fit.dpo.mvcshooter.model.strategies.MovementStrategy;
import cz.fit.dpo.mvcshooter.view.Visitor;

public class Missile extends GameObject {
	
	public long startTime;
	private MovementStrategy strategy;
	private int dispersion;

	public Missile(int x, int y) {
		super(x, y);
		startTime = System.currentTimeMillis();
	}
	
	public Missile(int x, int y, MovementStrategy strategy, int dispersion) {
		super(x, y);
		this.strategy = strategy;
		this.startTime = System.currentTimeMillis();
		this.dispersion = dispersion;
	}
	
	public int getDispersion() {
		return dispersion;
	}
	
	public void move(Cannon cannon) {
		strategy.moveMissile(this, cannon);
	}
	
	@Override
	public String toString() {
		return "Missile x=" + getX() + " y=" + getY();
	}

	@Override
	public void accept(Graphics g, Visitor visitor) {
		visitor.visit(g, this);
	}

}
