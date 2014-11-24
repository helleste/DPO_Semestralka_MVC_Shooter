package cz.fit.dpo.mvcshooter.model.entities;

import java.util.ArrayList;
import java.util.List;

public class SimpleShootingState implements ShootingState {

	@Override
	public List<Missile> shoot(int x, int y, EntitiesFactory factory) {
		// TODO Auto-generated method stub
		List<Missile> missiles = new ArrayList();
		missiles.add(factory.createMissile(x, y));
		return missiles;
	}

}
