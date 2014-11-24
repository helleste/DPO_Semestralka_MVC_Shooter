package cz.fit.dpo.mvcshooter.model.entities;

public interface EntitiesFactory {
	public Missile createMissile(int x, int y);
	public Enemy createEnemy(int x, int y);
}
