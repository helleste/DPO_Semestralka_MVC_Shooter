package test.mvcshooter.model;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;

import cz.fit.dpo.mvcshooter.model.Model;
import cz.fit.dpo.mvcshooter.model.entities.Cannon;
import cz.fit.dpo.mvcshooter.model.states.DoubleShootingState;
import cz.fit.dpo.mvcshooter.model.states.SimpleShootingState;

public class ModelTest {

	@Test
	public void testModel() {
		Model testModel = new Model("-n");
		assertTrue("Mode should be set to NORMAL.", testModel.getMode().equals("NORMAL"));
		testModel = new Model("-r");
		assertTrue("Mode should be set to REALISTIC.", testModel.getMode().equals("REALISTIC"));
	}

	@Test
	public void testHandleShooting() {
		Model testModel = new Model("-n");
		testModel.handleShooting();
		assertEquals("Missiles size should be 1.", 1, testModel.getMissiles().size());
	}
	
	@Test
	public void testEnoughEnemies() throws InterruptedException {
		Model testModel = new Model("-n");
		Thread.sleep(20);
		assertEquals("Enemies size should be 3.", 3, testModel.getEnemies().size());
	}

}
