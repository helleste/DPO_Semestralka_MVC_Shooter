package test.mvcshooter.model.entities;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import cz.fit.dpo.mvcshooter.model.entities.Cannon;
import cz.fit.dpo.mvcshooter.model.entities.Missile;
import cz.fit.dpo.mvcshooter.model.factories.EntitiesFactory;
import cz.fit.dpo.mvcshooter.model.factories.RealisticEntitiesFactory;
import cz.fit.dpo.mvcshooter.model.factories.SimpleEntitiesFactory;
import cz.fit.dpo.mvcshooter.model.states.DoubleShootingState;
import cz.fit.dpo.mvcshooter.model.states.SimpleShootingState;
import cz.fit.dpo.mvcshooter.model.strategies.RealisticStrategy;
import cz.fit.dpo.mvcshooter.model.strategies.SimpleStrategy;

public class CannonTest {

	@Test
	public void testSwitchShootingState() {
		Cannon cannon = new Cannon();
		cannon.switchShootingState();
		assertTrue("State should be double.", cannon.getShootingState() instanceof DoubleShootingState);
		cannon.switchShootingState();
		assertTrue("State should be simple.", cannon.getShootingState() instanceof SimpleShootingState);
	}

	@Test
	public void testShootDelegationToSimpleEntitiesFactory() {
		SimpleEntitiesFactory entitiesFactory = mock(SimpleEntitiesFactory.class);
		Missile missile = new Missile(20, 250, new SimpleStrategy(), 0);
		Cannon cannon = new Cannon();
		when(entitiesFactory.createMissile(20,250,0)).thenReturn(missile);
		
		List<Missile> shootedMissiles = cannon.shoot(entitiesFactory);
		assertTrue(shootedMissiles.get(0).getStrategy() instanceof SimpleStrategy);
		verify(entitiesFactory).createMissile(20, 250, 0);
	}
	
	@Test
	public void testShootDelegationToRealisticEntitiesFactory() {
		RealisticEntitiesFactory realisticEntitiesFactory = mock(RealisticEntitiesFactory.class);
		Cannon cannon = new Cannon();
		Missile missile = new Missile(20, 250, new RealisticStrategy(), 0);
		when(realisticEntitiesFactory.createMissile(20,250,0)).thenReturn(missile);
		
		List<Missile> shootedMissiles = cannon.shoot(realisticEntitiesFactory);
		assertTrue(shootedMissiles.get(0).getStrategy() instanceof RealisticStrategy);
		verify(realisticEntitiesFactory).createMissile(20, 250, 0);
	}

}
