package cz.fit.dpo.mvcshooter.model.entities;

import java.awt.Graphics;

import cz.fit.dpo.mvcshooter.view.Visitor;

public class StaticEnemy extends Enemy {

	public StaticEnemy(int x, int y) {
		super(x, y);
	}	
	
	@Override
	public StaticEnemy clone() {
		return new StaticEnemy(getX(), getY());
	}

	@Override
	public void accept(Graphics g, Visitor visitor) {
		visitor.visit(g, this);
	}

}
