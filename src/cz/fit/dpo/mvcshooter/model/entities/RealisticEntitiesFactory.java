package cz.fit.dpo.mvcshooter.model.entities;

public class RealisticEntitiesFactory implements EntitiesFactory {

	@Override
	public Missile createMissile(int x, int y) {
		MovementStrategy strategy = new RealisticStrategy();
		return new Missile(x, y, strategy);
	}

	@Override
	public Enemy createEnemy(int x, int y) {
		return new MovingEnemy(x, y);
	}

}
