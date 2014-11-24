package cz.fit.dpo.mvcshooter.model.entities;

import java.awt.Graphics;

import cz.fit.dpo.mvcshooter.view.Visitor;

public interface Visitable {
 public void accept(Graphics g, Visitor visitor);
}
