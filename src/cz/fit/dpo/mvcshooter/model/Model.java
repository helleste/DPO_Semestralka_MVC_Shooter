package cz.fit.dpo.mvcshooter.model;

import cz.fit.dpo.mvcshooter.model.entities.Cannon;
import cz.fit.dpo.mvcshooter.model.entities.Collision;
import cz.fit.dpo.mvcshooter.model.entities.Enemy;
import cz.fit.dpo.mvcshooter.model.entities.Missile;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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
    	initDefaultObjects();
        initTimer();
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
    	generateEnemies();
    	shoot();
    	shoot();
    	printMissiles();
    	printEnemies();
    	moveMissiles();
    	printMissiles();
    	discardMissiles();
    	handleCollisions();
    	notifyObservers();
    }
    
    // Checks if we have enough enemies on the playground
    private boolean enoughEnemies() {
    	if (enemies.size() < ModelConfig.ENEMIES_COUNT)
			return false;
    	
    	return true;
    }
    
    // Generates new enemies
    private void generateEnemies() {
    	while (enemies.size() < ModelConfig.ENEMIES_COUNT) {
    		Random rand = new Random();
    		enemies.add(new Enemy(
    				rand.nextInt(ModelConfig.PLAYGROUND_WIDTH),
    				rand.nextInt(ModelConfig.PLAYGROUND_HEIGHT)
    				));
		}
    }
    
    // Moves missiles
    private void moveMissiles() {
    	for (Missile missile : missiles) {
			missile.move(gravity, cannon.getForce(), cannon.getAngle());
		}
    }
    
    // Discard missiles out of range
    private void discardMissiles() {
    	for (int i=0; i < missiles.size(); i++) {
			if (missiles.get(i).getX() >= ModelConfig.PLAYGROUND_WIDTH || 
					missiles.get(i).getY()  >= ModelConfig.PLAYGROUND_HEIGHT) {
				missiles.remove(i);
				System.out.println("Deleting missile...");
			}
		}
    }
    
    private void handleCollisions() {
    	for (Missile missile : missiles) {
			for (Enemy enemy : enemies) {
				if (missile.collidesWith(enemy)) {
					enemies.remove(enemy);
					missiles.remove(missile);
					// TODO create Collision
				}
					
			}
		}
    }
    
    private void shoot() {
    	Missile missile = new Missile(cannon.getX(), cannon.getY());
    	missiles.add(missile);
    }
    
    private void printEnemies() {
    	for (Enemy enemy : enemies) {
			System.out.println(enemy.toString());
		}
    }
    
    private void printMissiles() {
    	for (Missile missile : missiles) {
			System.out.println(missile.toString());
		}
    }
    
    private void notifyObservers() {
        for (ModelObserver obs : observers) {
            obs.modelUpdated();
        }
    }

}
