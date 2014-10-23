package cz.fit.dpo.mvcshooter.model.entities;

import cz.fit.dpo.mvcshooter.model.ModelConfig;

public class Missile extends GameObject {

	public Missile(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}
	
	public void move(int gravity, int force, int angle) {
		
		// TODO t=currenttimestamp - starttimestamp
		x = (int) (x + force * 1 * Math.cos(angle));
		y = (int) (y + force * 1 * Math.sin(angle) - (gravity * Math.pow(1, 2))/2);
	}
	
	@Override
	public String toString() {
		return "Missile x=" + x + " y=" + y;
	}

}
