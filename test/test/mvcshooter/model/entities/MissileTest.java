package test.mvcshooter.model.entities;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.IOException;

import org.junit.Test;

import cz.fit.dpo.mvcshooter.model.ModelConfig;
import cz.fit.dpo.mvcshooter.model.entities.Cannon;
import cz.fit.dpo.mvcshooter.model.entities.Missile;
import cz.fit.dpo.mvcshooter.model.strategies.RealisticStrategy;
import cz.fit.dpo.mvcshooter.model.strategies.SimpleStrategy;

public class MissileTest {
	
	@Test
	public void testIfMissileMovedWithSimpleStrategy() {
		Missile missile = new Missile(20, 250, new SimpleStrategy(), 0);
		Cannon cannon = mock(Cannon.class);
		
		when(cannon.getAngle()).thenReturn(ModelConfig.CANNON_DEFAULT_ANGLE);
		when(cannon.getForce()).thenReturn(ModelConfig.CANNON_DEFAULT_FORCE);
		when(cannon.getX()).thenReturn(ModelConfig.CANNON_X);
		when(cannon.getY()).thenReturn(ModelConfig.CANNON_DEFAULT_Y);
		
		missile.move(cannon);
		System.out.println(missile.getY());
		System.out.println(missile.getX());
		assertNotEquals(20, missile.getX());
		assertNotEquals(250, missile.getY());
	}
	
	@Test
	public void testIfMissileMovedWithRealisticStrategy() {
		Missile missile = new Missile(20, 250, new RealisticStrategy(), 0);
		Cannon cannon = mock(Cannon.class);
		
		when(cannon.getAngle()).thenReturn(ModelConfig.CANNON_DEFAULT_ANGLE);
		when(cannon.getForce()).thenReturn(ModelConfig.CANNON_DEFAULT_FORCE);
		when(cannon.getX()).thenReturn(ModelConfig.CANNON_X);
		when(cannon.getY()).thenReturn(ModelConfig.CANNON_DEFAULT_Y);
		
		missile.move(cannon);
		System.out.println(missile.getY());
		System.out.println(missile.getX());
		assertNotEquals(20, missile.getX());
		//assertNotEquals(250, missile.getY());
	}

	@Test
	public void testMoveDelegationToSimpleStrategy() {
		SimpleStrategy simpleStrategy = mock(SimpleStrategy.class);
		Missile missile = new Missile(20, 250, simpleStrategy, 0);
		Cannon cannon = mock(Cannon.class);
		missile.move(cannon);
		verify(simpleStrategy).moveMissile(missile, cannon);
	}
	
	@Test
	public void testMoveDelegationToRealisticStrategy() {
		RealisticStrategy realisticStrategy = mock(RealisticStrategy.class);
		Missile missile = new Missile(20, 250, realisticStrategy, 0);
		Cannon cannon = mock(Cannon.class);
		missile.move(cannon);
		verify(realisticStrategy).moveMissile(missile, cannon);
	}

}
