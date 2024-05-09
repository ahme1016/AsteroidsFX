package dk.sdu.mmmi.cbse.asteroid;

import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

public class AsteroidControlSystem implements IEntityProcessingService, IPostEntityProcessingService {

    private static final int MIN_ASTEROIDS = 3; // Adjust as needed

    private AsteroidPlugin asteroidPlugin = new AsteroidPlugin();

    @Override
    public void process(GameData gameData, World world) {
        spawnAsteroids(gameData, world);

        for (Entity asteroid : world.getEntities(Asteroid.class)) {
            double changeX = Math.cos(Math.toRadians(asteroid.getRotation()));
            double changeY = Math.sin(Math.toRadians(asteroid.getRotation()));
            asteroid.setX(asteroid.getX() + changeX);
            asteroid.setY(asteroid.getY() + changeY);

            if (asteroid.getX() < 0) {
                asteroid.setX(gameData.getDisplayWidth());
            }

            if (asteroid.getX() > gameData.getDisplayWidth()) {
                asteroid.setX(0);
            }

            if (asteroid.getY() < 0) {
                asteroid.setY(gameData.getDisplayHeight());
            }

            if (asteroid.getY() > gameData.getDisplayHeight()) {
                asteroid.setY(0);
            }
        }
    }

    @Override
    public void postProcess(GameData gameData, World world) {
        // Remove any asteroids that have gone out of bounds
        for (Entity asteroid : new ArrayList<>(world.getEntities(Asteroid.class))) {
            if (asteroid.getX() < 0 || asteroid.getX() > gameData.getDisplayWidth() ||
                    asteroid.getY() < 0 || asteroid.getY() > gameData.getDisplayHeight()) {
                world.removeEntity(asteroid);
            }
        }
    }

    private void spawnAsteroids(GameData gameData, World world) {
        int asteroidCount = world.getEntities(Asteroid.class).size();
        if (asteroidCount < MIN_ASTEROIDS) {
            for (int i = 0; i < MIN_ASTEROIDS - asteroidCount; i++) {
                Entity asteroid = asteroidPlugin.createAsteroid(gameData);
                world.addEntity(asteroid);
            }
        }
    }

    private Collection<? extends BulletSPI> getBulletSPIs() {
        return ServiceLoader.load(BulletSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}
