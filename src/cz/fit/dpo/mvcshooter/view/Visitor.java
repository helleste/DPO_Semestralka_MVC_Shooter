package cz.fit.dpo.mvcshooter.view;

import java.awt.Graphics;

import cz.fit.dpo.mvcshooter.model.entities.Cannon;
import cz.fit.dpo.mvcshooter.model.entities.Collision;
import cz.fit.dpo.mvcshooter.model.entities.Enemy;
import cz.fit.dpo.mvcshooter.model.entities.Missile;
import cz.fit.dpo.mvcshooter.model.entities.MoustacheEnemy;
import cz.fit.dpo.mvcshooter.model.entities.StaticEnemy;

public interface Visitor {
	public void visit(Graphics g, Cannon cannon);
	public void visit(Graphics g, StaticEnemy enemy);
	public void visit(Graphics g, MoustacheEnemy enemy);
	public void visit(Graphics g, Missile missile);
	public void visit(Graphics g, Collision collision);
	
}
