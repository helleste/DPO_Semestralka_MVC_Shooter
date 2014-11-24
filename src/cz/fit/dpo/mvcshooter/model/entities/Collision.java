package cz.fit.dpo.mvcshooter.model.entities;

import java.awt.Graphics;

import cz.fit.dpo.mvcshooter.view.Visitor;

public class Collision extends GameObject {

	public Collision(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void accept(Graphics g, Visitor visitor) {
		visitor.visit(g, this);
	}

}
