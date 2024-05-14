package dk.sdu.mmmi.cbse.asteroid;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;


public class Asteroid extends Entity {

    private int asteroidSize;
    private AsteroidPlugin asteroidPlugin = new AsteroidPlugin();
    private int score;
    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public void handleCollision(GameData gameData, World world, Entity collidingEntity) {
        switch (getAsteroidSize()) {
            case 1:
                handleAsteroidSizeOne(world, collidingEntity);
                break;
            case 2:
                handleAsteroidSizeTwo(gameData, world, collidingEntity);
                break;
            default:
                handleDefaultAsteroidSize(gameData, world, collidingEntity);
                break;
        }
    }

    private void handleAsteroidSizeOne(World world, Entity collidingEntity) {
        updateScore(200);
        world.removeEntity(collidingEntity);
        world.removeEntity(this);
    }

    private void handleAsteroidSizeTwo(GameData gameData, World world, Entity collidingEntity) {
        updateScore(100); // Update score for medium asteroid
        for (int i = 0; i < 2; i++) {
            Entity asteroidChild = asteroidPlugin.createAsteroid(gameData);
            asteroidPlugin.setAsteroidCoordinates(asteroidChild, 1);
            asteroidChild.setX(this.getX());
            asteroidChild.setY(this.getY());
            asteroidChild.setRotation(Math.random() * 360);
            world.addEntity(asteroidChild);
        }
        world.removeEntity(collidingEntity);
        world.removeEntity(this);
    }

    private void handleDefaultAsteroidSize(GameData gameData, World world, Entity collidingEntity) {
        updateScore(50);
        for (int i = 0; i < 2; i++) {
            Entity asteroidChild = asteroidPlugin.createAsteroid(gameData);
            asteroidPlugin.setAsteroidCoordinates(asteroidChild, 2);
            asteroidChild.setX(this.getX());
            asteroidChild.setY(this.getY());
            asteroidChild.setRotation(Math.random() * 360);
            world.addEntity(asteroidChild);
        }
        world.removeEntity(collidingEntity);
        world.removeEntity(this);
    }

    public int getAsteroidSize() {
        return asteroidSize;
    }

    public void setAsteroidSize(int asteroidSize) {
        this.asteroidSize = asteroidSize;
    }

    private void updateScore(int points) {
        try {
            // Construct URL with appropriate points
            URL updateUrl = new URL("http://localhost:8080/update-score?point=" + points);
            HttpURLConnection connection = (HttpURLConnection) updateUrl.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println("Score updated");
            } else {
                System.out.println("Failed to update score: " + responseCode);
            }

            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}