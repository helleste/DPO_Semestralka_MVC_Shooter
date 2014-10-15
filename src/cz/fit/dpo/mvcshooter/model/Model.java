package cz.fit.dpo.mvcshooter.model;

import cz.fit.dpo.mvcshooter.model.entities.Cannon;
import cz.fit.dpo.mvcshooter.model.entities.Collision;
import cz.fit.dpo.mvcshooter.model.entities.Enemy;
import cz.fit.dpo.mvcshooter.model.entities.Missile;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Ondrej Stuchlik
 */
public class Model {

    private Cannon cannon;
    private List<Missile> missiles = new ArrayList();
    private List<Enemy> enemies = new ArrayList();
    private List<Collision> collisions = new ArrayList();
    private List<ModelObserver> observers = new ArrayList();
    private Timer timer;
    private int gravity = ModelConfig.DEFAULT_GRAVITY;
    private int score = 0;
  
    public Model() {
        initTimer();
        initDefaultObjects();
    }

    // ####################### model controlling #########################
    public void moveCannonUp() {
        cannon.moveUp();
        notifyObservers();
    }

    public void moveCannonDown() {
        cannon.moveDown();
        notifyObservers();
    }


    // ####################### getting data and registering #########################
    public void registerObserver(ModelObserver observer) {
        observers.add(observer);
    }

    public Cannon getCannon() {
        return cannon;
    }    

    public int getPlaygroundWidth() {
        return ModelConfig.PLAYGROUND_WIDTH;
    }

    public int getPlaygroundHeight() {
        return ModelConfig.PLAYGROUND_HEIGHT;
    }

    // ############################### private initialization ###############################
    private void initTimer() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                moveObjects();
            }
        }, 0, ModelConfig.TICK_TIME);
    }

    private void initDefaultObjects() {
        cannon = new Cannon();
    }

    // ################################## private logic ##################################
    private void moveObjects() {
        // todo implement
    }
    
    private void notifyObservers() {
        for (ModelObserver obs : observers) {
            obs.modelUpdated();
        }
    }

}
