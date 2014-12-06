package cz.fit.dpo.mvcshooter.model.entities;

import java.awt.Graphics;

import cz.fit.dpo.mvcshooter.view.Visitor;

public class Collision extends GameObject {
	
	private int ticksLeft = 75;

	public Collision(int x, int y) {
		super(x, y);
	}
	
	public int getTicksLeft() {
		return ticksLeft;
	}
	
	public void setTicksLeft(int ticksLeft) {
		this.ticksLeft = ticksLeft;
	}
	
	public void decreaseTicksLeft() {
		ticksLeft -= 1;
	}
	
	@Override
	public Collision clone() {
		Collision c = new Collision(getX(), getY());
		c.setTicksLeft(ticksLeft);
		return c;
	}

	@Override
	public void accept(Graphics g, Visitor visitor) {
		visitor.visit(g, this);
	}

}
