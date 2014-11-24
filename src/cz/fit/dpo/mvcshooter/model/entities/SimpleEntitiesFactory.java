package cz.fit.dpo.mvcshooter.model.entities;

public class SimpleEntitiesFactory implements EntitiesFactory {

	@Override
	public Missile createMissile(int x, int y) {
		MovementStrategy strategy = new SimpleStrategy();
		return new Missile(x, y, strategy);
	}

	@Override
	public StaticEnemy createEnemy(int x, int y) {
		
		return new StaticEnemy(x, y);
	}

}
