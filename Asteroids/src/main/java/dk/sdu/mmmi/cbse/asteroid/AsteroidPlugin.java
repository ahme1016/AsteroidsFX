package dk.sdu.mmmi.cbse.asteroid;  // Make sure the package matches

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

import java.util.Random;

public class AsteroidPlugin implements IGamePluginService {

    private Entity asteroid;

    public AsteroidPlugin() { }

    @Override
    public void start(GameData gameData, World world) {
        asteroid = createAsteroid(gameData);
        world.addEntity(asteroid);
    }

    public Entity createAsteroid(GameData gameData) {
        Entity asteroid = new Asteroid(Asteroid.AsteroidSize.LARGE); // Default to LARGE

        setAsteroidCoordinates(asteroid);
        setAsteroidPosition(gameData, asteroid);

        return asteroid;
    }

    public void setAsteroidCoordinates(Entity asteroid) { // No need for size parameter
        Asteroid.AsteroidSize size = ((Asteroid) asteroid).getAsteroidSize();

        switch (size) {
            case SMALL:
                asteroid.setPolygonCoordinates(-9, 3, -3, 9, 3, 9, 9, 3, 9, -3, 3, -9, -3, -9, -9, -3, -9, 3);
                asteroid.setWidth(18);
                asteroid.setHeight(18);
                break;
            case MEDIUM:
                asteroid.setPolygonCoordinates(-18, 6, -6, 18, 6, 18, 18, 6, 18, -6, 6, -18, -6, -18, -18, -6, -18, 6);
                asteroid.setWidth(36);
                asteroid.setHeight(36);
                break;
            case LARGE:
                asteroid.setPolygonCoordinates(-36, 12, -12, 36, 12, 36, 36, 12, 36, -12, 12, -36, -12, -36, -36, -12, -36, 12);
                asteroid.setWidth(72);
                asteroid.setHeight(72);
                break;
            // No default needed if all enum values are covered
        }
    }

    private void setAsteroidPosition(GameData gamedata, Entity asteroid) {
        Random random = new Random();

        int randomX;
        int randomY;
        double randomRotation;

        switch ((int) (Math.random() * 4) + 1) {
            case 1:
                randomX = (int) (Math.random() * gamedata.getDisplayWidth());
                randomY = 0;
                randomRotation = Math.random() * 180;
                break;
            case 2:
                randomX = (int) (Math.random() * gamedata.getDisplayWidth());
                randomY = gamedata.getDisplayHeight();
                randomRotation = -Math.random() * 180;
                break;
            case 3:
                randomX = 0;
                randomY = (int) (Math.random() * gamedata.getDisplayHeight());
                randomRotation = random.nextDouble() * 180 - 90;
                break;
            case 4:
                randomX = gamedata.getDisplayWidth();
                randomY = (int) (Math.random() * gamedata.getDisplayHeight());
                randomRotation = random.nextDouble() * 180 + 90;
                break;
            default:
                // Handle unexpected case (should never happen with proper random number generation)
                throw new IllegalStateException("Unexpected randomizer value");
        }

        asteroid.setX(randomX);
        asteroid.setY(randomY);
        asteroid.setRotation(randomRotation);

    }


    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        world.removeEntity(asteroid);
    }

}