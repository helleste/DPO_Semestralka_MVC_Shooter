package cz.fit.dpo.mvcshooter.model.states;

import java.util.List;

import cz.fit.dpo.mvcshooter.model.entities.Missile;
import cz.fit.dpo.mvcshooter.model.factories.EntitiesFactory;

public interface ShootingState {
	public List<Missile> shoot(int x, int y, EntitiesFactory factory);
}
