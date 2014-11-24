package cz.fit.dpo.mvcshooter.model.entities;

import java.util.ArrayList;
import java.util.List;

public class DoubleShootingState implements ShootingState {

	@Override
	public List<Missile> shoot(int x, int y, EntitiesFactory factory) {
		List<Missile> missiles = new ArrayList();
		// TODO Dodelat disperzi
		missiles.add(factory.createMissile(x, y));
		missiles.add(factory.createMissile(x, y));
		return missiles;
	}

}
