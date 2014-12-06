package cz.fit.dpo.mvcshooter.model;

import cz.fit.dpo.mvcshooter.model.entities.Cannon;
import cz.fit.dpo.mvcshooter.model.entities.Collision;
import cz.fit.dpo.mvcshooter.model.entities.Enemy;
import cz.fit.dpo.mvcshooter.model.entities.GameObject;
import cz.fit.dpo.mvcshooter.model.entities.Missile;
import cz.fit.dpo.mvcshooter.model.entities.StaticEnemy;
import cz.fit.dpo.mvcshooter.model.factories.EntitiesFactory;
import cz.fit.dpo.mvcshooter.model.factories.RealisticEntitiesFactory;
import cz.fit.dpo.mvcshooter.model.factories.SimpleEntitiesFactory;
import cz.fit.dpo.mvcshooter.model.strategies.MovementStrategy;
import cz.fit.dpo.mvcshooter.model.strategies.RealisticStrategy;
import cz.fit.dpo.mvcshooter.model.strategies.SimpleStrategy;

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
    private List<GameObject> gameObjects = new ArrayList();
    private List<Missile> missiles = new ArrayList();
    private List<Enemy> enemies = new ArrayList();
    private List<Collision> collisions = new ArrayList();
    private List<ModelObserver> observers = new ArrayList();
    private Timer timer;
    private int score = 0;
    private final String mode;

    public Model(String mode) {
    	initDefaultObjects();
        initTimer();
        
		if (mode.equals("-n")) {
			this.mode = "NORMAL";
			System.out.println("Mode set to NORMAL.");
		}
		else {
			this.mode = "REALISTIC";
			System.out.println("Mode set to REALISTIC.");
		}
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
    
    public void handleShooting() {
    	EntitiesFactory entitiesFactory;
    	
    	if (mode.equals("NORMAL"))
    		entitiesFactory = new SimpleEntitiesFactory();
    	else
    		entitiesFactory = new RealisticEntitiesFactory();
    	
    	List<Missile> missiles = cannon.shoot(entitiesFactory);
    	this.missiles.addAll(missiles);
    	notifyObservers();
    }
    
    public void switchStates() {
		cannon.switchShootingState();
	}

    // ####################### getting data and registering #########################
    public void registerObserver(ModelObserver observer) {
        observers.add(observer);
    }

    public Cannon getCannon() {
        return cannon;
    }
    
    public List<GameObject> getGameObjects() {
    	return gameObjects;
    }

    public int getPlaygroundWidth() {
        return ModelConfig.PLAYGROUND_WIDTH;
    }

    public int getPlaygroundHeight() {
        return ModelConfig.PLAYGROUND_HEIGHT;
    }
    
    public String getMode() {
    	return mode;
    }
    
    public List<Missile> getMissiles() {
    	return missiles;
    }
    
    public List<Enemy> getEnemies() {
    	return enemies;
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
    	generateEnemies();
    	initGameObjects();
    	moveMissiles();
    	printMissiles();
    	discardMissiles();
    	handleCollisions();
    	decreaseCollisionTicks();
    	clearOldCollisions();
    	notifyObservers();
    }
    
    private void initGameObjects() {
    	gameObjects.clear();
    	gameObjects.add(cannon);
    	gameObjects.addAll(enemies);
    	gameObjects.addAll(missiles);
    	gameObjects.addAll(collisions);
    }
    
    // Checks if we have enough enemies on the playground
    private boolean enoughEnemies() {
    	if (enemies.size() < ModelConfig.ENEMIES_COUNT)
			return false;
    	
    	return true;
    }
    
    // Generates new enemies
    private void generateEnemies() {
    	EntitiesFactory entitiesFactory;
    	
    	if (mode.equals("NORMAL"))
    		entitiesFactory = new SimpleEntitiesFactory();
    	else
    		entitiesFactory = new RealisticEntitiesFactory();
    	
    	while (enemies.size() < ModelConfig.ENEMIES_COUNT) {
    		Random rand = new Random();
    		Enemy staticEnemy = entitiesFactory.createEnemy(
    				rand.nextInt(ModelConfig.PLAYGROUND_WIDTH), 
    				rand.nextInt(ModelConfig.PLAYGROUND_HEIGHT));
    		enemies.add(staticEnemy);
		}
    }
    
    // Moves missiles
    private void moveMissiles() {
    	for (int i = 0; i < missiles.size(); i++) {
    		Missile missile = missiles.get(i);
			missile.move(cannon);
		}
    }
    
    // Discard missiles out of range
    private void discardMissiles() {
    	for (int i = 0; i < missiles.size(); i++) {
			if (missiles.get(i).getX() >= ModelConfig.PLAYGROUND_WIDTH || 
					missiles.get(i).getY()  >= ModelConfig.PLAYGROUND_HEIGHT) {
				missiles.remove(i);
				System.out.println("Deleting missile...");
			}
		}
    }
    
    // Remove enemy, missile and create collision instead
    private void handleCollisions() {
    	for (int i = 0; i < missiles.size(); i++) {
    		Missile missile = missiles.get(i);
    		for (int j = 0; j < enemies.size(); j++) {
    			Enemy enemy = enemies.get(j);
    			if (missile.collidesWith(enemy)) {
					Collision collision = new Collision(enemy.getX(), enemy.getY()); // Create collision on the coordinates of the hit enemy
					collisions.add(collision);
					enemies.remove(enemy);
					missiles.remove(missile);
					score++;
					System.out.println("score: " + score);
				}
    		}
    	}
    }
    
    // Decrease collision ticks
    private void decreaseCollisionTicks() {
    	for (int i = 0; i < collisions.size(); i++) {
    		collisions.get(i).decreaseTicksLeft();
    	}
    }
    
    // Clear old collisions
    private void clearOldCollisions() {
    	for (int i = 0; i < collisions.size(); i++) {
    		if (collisions.get(i).getTicksLeft() == 0)
    			collisions.remove(i);
    	}
    }
    
    // Remove collisions
    private void clearCollisions() {
    	collisions.clear();
    }

    
    private void printEnemies() {
    	for (Enemy enemy : enemies) {
			System.out.println(enemy.toString());
		}
    }
    
    // Deep copy of collisions
    private List<Collision> copyCollisions() {
    	List<Collision> copiedCollisions = new ArrayList<Collision>();
    	
    	for (int i = 0; i < collisions.size(); i++)
    		copiedCollisions.add(collisions.get(i).clone());
    	
    	return copiedCollisions;
    }
    
 // Deep copy of enemies
    private List<Enemy> copyEnemies() {
    	List<Enemy> copiedEnemies = new ArrayList<Enemy>();
    	
    	for (int i = 0; i < enemies.size(); i++) {
    		copiedEnemies.add(enemies.get(i).clone());
    	}
    	
    	return copiedEnemies;
    }
    
    private void printMissiles() {
    	for (int i = 0; i < missiles.size(); i++) {
    		Missile missile = missiles.get(i);
			System.out.println(missile.toString());
		}
    }
    
    private void notifyObservers() {
        for (ModelObserver obs : observers) {
            obs.modelUpdated();
        }
    }
    
    public Memento save() {
    	return new Memento(this.cannon, this.gameObjects, 
    			this.missiles, this.enemies, this.collisions, this.observers, this.score);
    }
    
    public void undoToLastSave(Object obj) {
    	Memento memento = (Memento) obj;
    	this.cannon = memento.cannon;
    	this.gameObjects = memento.gameObjects;
    	this.missiles = memento.missiles;
    	this.enemies = memento.enemies;
    	this.collisions = memento.collisions;
    	this.observers = memento.observers;
    	this.score = memento.score;
    }
    
    private class Memento {
    	private int score;
    	private Cannon cannon;
        private List<GameObject> gameObjects;
        private List<Missile> missiles;
        private List<Enemy> enemies;
        private List<Collision> collisions;
        private List<ModelObserver> observers;
        
        public Memento(Cannon cannon, List<GameObject> gameObjects, List<Missile> missiles, 
        		List<Enemy> enemies, List<Collision> collisions, List<ModelObserver> observers, 
        		int score) {
        	this.cannon = new Cannon(cannon);
        	this.gameObjects = new ArrayList<GameObject>(); // empty list ok
        	this.missiles = new ArrayList<Missile>(); // no need to copy missiles
        	this.enemies = copyEnemies();
        	this.collisions = copyCollisions();
        	this.observers = new ArrayList<ModelObserver>(observers);
        	this.score = score;
        }
    	
    }

}
