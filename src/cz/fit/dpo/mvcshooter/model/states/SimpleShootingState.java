package cz.fit.dpo.mvcshooter.model.states;

import java.util.ArrayList;
import java.util.List;

import cz.fit.dpo.mvcshooter.model.entities.Missile;
import cz.fit.dpo.mvcshooter.model.factories.EntitiesFactory;

public class SimpleShootingState implements ShootingState {

	@Override
	public List<Missile> shoot(int x, int y, EntitiesFactory factory) {
		List<Missile> missiles = new ArrayList();
		missiles.add(factory.createMissile(x, y, 0));
		return missiles;
	}

}
