package cz.fit.dpo.mvcshooter.model.strategies;

import cz.fit.dpo.mvcshooter.model.ModelConfig;
import cz.fit.dpo.mvcshooter.model.entities.Cannon;
import cz.fit.dpo.mvcshooter.model.entities.Missile;

public class SimpleStrategy implements MovementStrategy {

	@Override
	public void moveMissile(Missile missile, Cannon cannon) {
		int gravity = ModelConfig.DEFAULT_GRAVITY;
		long currentTime = System.currentTimeMillis();
		long timeDifference = (currentTime - missile.startTime)/100;
		int angle = cannon.getAngle() + missile.getDispersion();
		missile.setX((int) (cannon.getX() + cannon.getForce() * timeDifference * Math.cos(cannon.getAngle())));
		missile.setY((int) (cannon.getY() - (cannon.getForce() * timeDifference *
				Math.sin(angle)) + (gravity * Math.pow(timeDifference, 2))/2));

	}

}
