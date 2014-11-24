package cz.fit.dpo.mvcshooter.model.entities;

import java.util.List;

public interface ShootingState {
	public List<Missile> shoot(int x, int y, EntitiesFactory factory);
}
