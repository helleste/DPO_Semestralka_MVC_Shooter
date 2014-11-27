package cz.fit.dpo.mvcshooter.model.saver;

import cz.fit.dpo.mvcshooter.model.Model;

public class ModelSaver {
	
	private Object obj;
	
	public void save(Model model) {
		this.obj = model.save();
	}
	
	public void undo(Model model) {
		model.undoToLastSave(obj);
	}
}
