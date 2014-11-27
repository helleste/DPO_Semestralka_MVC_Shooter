package cz.fit.dpo.mvcshooter.model.factories;

import cz.fit.dpo.mvcshooter.model.entities.Enemy;
import cz.fit.dpo.mvcshooter.model.entities.Missile;

public interface EntitiesFactory {
	public Missile createMissile(int x, int y, int dispersion);
	public Enemy createEnemy(int x, int y);
}
