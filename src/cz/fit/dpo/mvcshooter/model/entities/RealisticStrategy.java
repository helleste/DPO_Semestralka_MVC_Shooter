package cz.fit.dpo.mvcshooter.model.entities;

import cz.fit.dpo.mvcshooter.model.ModelConfig;

public class RealisticStrategy implements MovementStrategy {
	public static final int WINDRESISTANCE = 10;

	@Override
	public void moveMissile(Missile missile, Cannon cannon) {
		// TODO Auto-generated method stub
		// TODO t=currenttimestamp - starttimestamp
		int gravity = ModelConfig.DEFAULT_GRAVITY;
		long currentTime = System.currentTimeMillis();
		long timeDifference = currentTime - missile.startTime;
		missile.x = (int) (cannon.x + cannon.getForce() * 
				timeDifference * Math.cos(cannon.getAngle())) - WINDRESISTANCE;
		missile.y = (int) (cannon.y + cannon.getForce() * timeDifference * 
				Math.sin(cannon.getAngle()) - (gravity * Math.pow(timeDifference, 2))/2);
		
	}

}
