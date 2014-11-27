package cz.fit.dpo.mvcshooter.model.factories;

import cz.fit.dpo.mvcshooter.model.entities.Enemy;
import cz.fit.dpo.mvcshooter.model.entities.Missile;
import cz.fit.dpo.mvcshooter.model.entities.MoustacheEnemy;
import cz.fit.dpo.mvcshooter.model.strategies.MovementStrategy;
import cz.fit.dpo.mvcshooter.model.strategies.RealisticStrategy;

public class RealisticEntitiesFactory implements EntitiesFactory {

	@Override
	public Missile createMissile(int x, int y, int dispersion) {
		MovementStrategy strategy = new RealisticStrategy();
		return new Missile(x, y, strategy, dispersion);
	}

	@Override
	public Enemy createEnemy(int x, int y) {
		return new MoustacheEnemy(x, y);
	}

}
