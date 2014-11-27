package cz.fit.dpo.mvcshooter.model.strategies;

import cz.fit.dpo.mvcshooter.model.entities.Cannon;
import cz.fit.dpo.mvcshooter.model.entities.Missile;

public interface MovementStrategy {
	public void moveMissile(Missile missile, Cannon cannon);

}
