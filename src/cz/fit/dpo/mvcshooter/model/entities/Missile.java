package cz.fit.dpo.mvcshooter.model.entities;

import cz.fit.dpo.mvcshooter.model.ModelConfig;

public class Missile extends GameObject {
	
	private long startTime;

	public Missile(int x, int y) {
		super(x, y);
		startTime = System.currentTimeMillis();
		// TODO Auto-generated constructor stub
	}
	
	public void move(int gravity, int force, int angle) {
		
		// TODO t=currenttimestamp - starttimestamp
		long currentTime = System.currentTimeMillis();
		long timeDifference = currentTime - startTime;
		System.out.println(timeDifference);
		x = (int) (x + force * timeDifference * Math.cos(angle));
		y = (int) (y + force * timeDifference * Math.sin(angle) - (gravity * Math.pow(timeDifference, 2))/2);
	}
	
	@Override
	public String toString() {
		return "Missile x=" + x + " y=" + y;
	}

}
