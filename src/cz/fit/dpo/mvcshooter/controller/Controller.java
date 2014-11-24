package cz.fit.dpo.mvcshooter.controller;

import cz.fit.dpo.mvcshooter.model.Model;
import cz.fit.dpo.mvcshooter.view.MainWindow;
import java.awt.event.KeyEvent;

/**
 *
 * @author Ondrej Stuchlik
 */
public class Controller {

    private Model model;
    private MainWindow view;

    public Controller(Model model) {
        this.model = model;
    }

    public void keyPressed(KeyEvent evt) {
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_DOWN:
                model.moveCannonDown();
                break;
            case KeyEvent.VK_UP:
                model.moveCannonUp();
                break; 
                
            case KeyEvent.VK_F1:
                view.showHelp();
                break;
                
            case KeyEvent.VK_SPACE:
            	model.handleShooting();
            	System.out.println("shoot!");
            	break;
        }	
    }

    public void setView(MainWindow view) {
        this.view = view;
    }  

    
}
