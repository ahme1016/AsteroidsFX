package dk.sdu.mmmi.cbse.asteroid;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;


public class Asteroid extends Entity {

    public enum AsteroidSize {SMALL, MEDIUM, LARGE} // Use an enum for clarity

    private AsteroidSize asteroidSize;
    private AsteroidPlugin asteroidPlugin = new AsteroidPlugin();

    public Asteroid(AsteroidSize size) { // Constructor to set size
        this.asteroidSize = size;
    }

    @Override
    public void handleCollision(GameData gameData, World world, Entity collidingEntity) {
        switch (asteroidSize) { // Switch on enum values
            case SMALL:
                handleAsteroidSizeOne(world, collidingEntity);
                break;
            case MEDIUM:
                handleAsteroidSizeTwo(gameData, world, collidingEntity);
                break;
            case LARGE: // Use the original default behavior
                handleDefaultAsteroidSize(gameData, world, collidingEntity);
                break;
        }
    }

    private void handleAsteroidSizeOne(World world, Entity collidingEntity) {
        world.removeEntity(collidingEntity);
        world.removeEntity(this);
    }

    private void handleAsteroidSizeTwo(GameData gameData, World world, Entity collidingEntity) {
        for (int i = 0; i < 2; i++) {
            Asteroid asteroidChild = (Asteroid) asteroidPlugin.createAsteroid(gameData);
            asteroidChild.setAsteroidSize(AsteroidSize.SMALL); // Set child size to SMALL
            asteroidPlugin.setAsteroidCoordinates(asteroidChild);
            asteroidChild.setX(this.getX());
            asteroidChild.setY(this.getY());
            asteroidChild.setRotation(Math.random() * 360);
            world.addEntity(asteroidChild);
        }
        world.removeEntity(collidingEntity);
        world.removeEntity(this);
    }

    private void handleDefaultAsteroidSize(GameData gameData, World world, Entity collidingEntity) {
        for (int i = 0; i < 2; i++) {
            Asteroid asteroidChild = (Asteroid) asteroidPlugin.createAsteroid(gameData);
            asteroidChild.setAsteroidSize(AsteroidSize.MEDIUM); // Set child size to MEDIUM
            asteroidPlugin.setAsteroidCoordinates(asteroidChild);
            asteroidChild.setX(this.getX());
            asteroidChild.setY(this.getY());
            asteroidChild.setRotation(Math.random() * 360);
            world.addEntity(asteroidChild);
        }
        world.removeEntity(collidingEntity);
        world.removeEntity(this);
    }

    public AsteroidSize getAsteroidSize() {
        return asteroidSize;
    }

    public void setAsteroidSize(AsteroidSize asteroidSize) {
        this.asteroidSize = asteroidSize;
    }
}