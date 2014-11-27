package cz.fit.dpo.mvcshooter.model.factories;

import cz.fit.dpo.mvcshooter.model.entities.Missile;
import cz.fit.dpo.mvcshooter.model.entities.StaticEnemy;
import cz.fit.dpo.mvcshooter.model.strategies.MovementStrategy;
import cz.fit.dpo.mvcshooter.model.strategies.SimpleStrategy;

public class SimpleEntitiesFactory implements EntitiesFactory {

	@Override
	public Missile createMissile(int x, int y, int dispersion) {
		MovementStrategy strategy = new SimpleStrategy();
		System.out.println("from factory x= " + x + " y = " +y);
		return new Missile(x, y, strategy, dispersion);
	}

	@Override
	public StaticEnemy createEnemy(int x, int y) {
		
		return new StaticEnemy(x, y);
	}

}
