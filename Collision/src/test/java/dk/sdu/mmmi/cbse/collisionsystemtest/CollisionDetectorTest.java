package dk.sdu.mmmi.cbse.collisionsystemtest;

import dk.sdu.mmmi.cbse.asteroid.Asteroid;
import dk.sdu.mmmi.cbse.collisionsystem.CollisionDetector;
import dk.sdu.mmmi.cbse.common.bullet.Bullet;
import dk.sdu.mmmi.cbse.enemysystem.EnemyShip;
import dk.sdu.mmmi.cbse.playersystem.Player;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CollisionDetectorTest {

    private CollisionDetector collisionDetector;
    private GameData gameData;
    private World world;

    @BeforeEach
    public void setUp() {
        collisionDetector = new CollisionDetector();
        gameData = new GameData();
        world = new World();
    }

    // Test that asteroids don't collide with each other
    @Test
    public void testAsteroidAsteroidCollision() {
        // Given
        Asteroid asteroid1 = new Asteroid();
        Asteroid asteroid2 = new Asteroid();
        world.addEntity(asteroid1);
        world.addEntity(asteroid2);

        // When
        collisionDetector.process(gameData, world);

        // Then
        Assertions.assertFalse(world.getEntities().isEmpty());
    }

    // Test that player doesn't collide with its own bullets
    @Test
    public void testPlayerBulletCollision() {
        // Given
        Player player = new Player();
        Bullet bullet = new Bullet();
        player.getBullets().add(bullet);
        world.addEntity(player);
        world.addEntity(bullet);

        // When
        collisionDetector.process(gameData, world);

        // Then
        Assertions.assertFalse(world.getEntities().isEmpty());
    }

    // Test that enemy doesn't collide with its own bullets
    @Test
    public void testEnemyBulletCollision() {
        // Given
        EnemyShip enemyShip = new EnemyShip();
        Bullet bullet = new Bullet();
        enemyShip.getBullets().add(bullet);
        world.addEntity(enemyShip);
        world.addEntity(bullet);

        // When
        collisionDetector.process(gameData, world);

        // Then
        Assertions.assertFalse(world.getEntities().isEmpty());
    }

    // Test that player collides with enemy ship
    @Test
    public void testPlayerEnemyCollision() {
        // Given
        Player player = new Player();
        EnemyShip enemyShip = new EnemyShip();
        world.addEntity(player);
        world.addEntity(enemyShip);

        // When
        collisionDetector.process(gameData, world);

        // Then
        Assertions.assertTrue(world.getEntities().isEmpty());
    }

    // Test that bullets collide with entities they should
    @Test
    public void testBulletCollision() {
        // Given
        Bullet bullet = new Bullet();
        Entity entity = new Entity() {
            @Override
            public void handleCollision(GameData gameData, World world, Entity otherEntity) {
                // Custom collision handling for testing
            }
        };
        world.addEntity(bullet);
        world.addEntity(entity);

        // When
        collisionDetector.process(gameData, world);

        // Then
        Assertions.assertTrue(world.getEntities().isEmpty());
    }
}
