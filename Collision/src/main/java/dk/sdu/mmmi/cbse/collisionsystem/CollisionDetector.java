package dk.sdu.mmmi.cbse.collisionsystem;

import dk.sdu.mmmi.cbse.asteroid.Asteroid;
import dk.sdu.mmmi.cbse.common.bullet.Bullet;
import dk.sdu.mmmi.cbse.enemysystem.EnemyShip;
import dk.sdu.mmmi.cbse.playersystem.Player;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

public class CollisionDetector implements IPostEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {

        // Iterate through all entities in the world
        for (Entity entity : world.getEntities()) {
            for (Entity collideEntity : world.getEntities()) {

                // Skip if entity is colliding with itself
                if (entity == collideEntity) {
                    continue;
                }

                // Check for collision based on entity types
                if (shouldCollide(entity, collideEntity)) {
                    handleCollision(entity, collideEntity, gameData, world);
                }
            }
        }
    }

    private boolean shouldCollide(Entity entity, Entity collideEntity) {
        // Skip collision if both entities are asteroids
        if (entity.getClass() == Asteroid.class && collideEntity.getClass() == Asteroid.class) {
            return false;
        }

        // Check if entity is a player and collideEntity is a bullet made by someone else
        if (entity.getClass() == Player.class && collideEntity.getClass() == Bullet.class) {
            return !((Player) entity).getBullets().contains(collideEntity);
        }

        // Check if entity is an enemy spaceship and collideEntity is a bullet made by someone else
        if (entity.getClass() == EnemyShip.class && collideEntity.getClass() == Bullet.class) {
            return !((EnemyShip) entity).getBullets().contains(collideEntity);
        }

        // Default case: allow collision
        return true;
    }

    private void handleCollision(Entity entity, Entity collideEntity, GameData gameData, World world) {
        // If there is a collision, handle it for different entity types
        if (isColliding(entity, collideEntity)) {
            entity.handleCollision(gameData, world, collideEntity);
        }
    }

    private boolean isColliding(Entity entity, Entity collideEntity) {
        // Calculate center positions and radii
        double entityCenterX = entity.getX() + entity.getWidth() / 2;
        double entityCenterY = entity.getY() + entity.getHeight() / 2;
        double collideEntityCenterX = collideEntity.getX() + collideEntity.getWidth() / 2;
        double collideEntityCenterY = collideEntity.getY() + collideEntity.getHeight() / 2;
        double entityRadius = Math.max(entity.getWidth() / 2, entity.getHeight() / 2);
        double collideEntityRadius = Math.max(collideEntity.getWidth() / 2, collideEntity.getHeight() / 2);

        // Check for overlap using distance between centers
        double distanceX = Math.abs(entityCenterX - collideEntityCenterX);
        double distanceY = Math.abs(entityCenterY - collideEntityCenterY);
        return distanceX < entityRadius + collideEntityRadius && distanceY < entityRadius + collideEntityRadius;
    }

    @Override
    public void postProcess(GameData gameData, World world) {

    }
}