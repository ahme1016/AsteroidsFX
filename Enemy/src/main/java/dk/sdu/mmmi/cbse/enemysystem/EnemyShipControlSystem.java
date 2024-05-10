package dk.sdu.mmmi.cbse.enemysystem;

import dk.sdu.mmmi.cbse.common.bullet.Bullet;
import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

import java.util.Collection;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

public class EnemyShipControlSystem implements IEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {
        for (Entity enemyShip : world.getEntities(EnemyShip.class)) {
            moveEnemyShip(enemyShip);
            if (fireBullet(System.currentTimeMillis(), (EnemyShip) enemyShip)) {
                spawnBullets((EnemyShip) enemyShip, gameData, world);
            }
            wrapAroundScreen(enemyShip, gameData);
        }
    }

    private void moveEnemyShip(Entity enemyShip) {
        double changeX = Math.cos(Math.toRadians(enemyShip.getRotation()));
        double changeY = Math.sin(Math.toRadians(enemyShip.getRotation()));
        enemyShip.setX(enemyShip.getX() + changeX);
        enemyShip.setY(enemyShip.getY() + changeY);
    }

    private void spawnBullets(EnemyShip enemyShip, GameData gameData, World world) {
        for (BulletSPI bullet : getBulletSPIs()) {
            Entity bulletEntity = bullet.createBullet(enemyShip, gameData);
            bulletEntity.setRotation(0.1 + Math.random() * 359.9);
            enemyShip.addBullet((Bullet) bulletEntity);
            world.addEntity(bulletEntity);
        }
    }

    private void wrapAroundScreen(Entity enemyShip, GameData gameData) {
        if (enemyShip.getX() < 0) {
            enemyShip.setX(gameData.getDisplayWidth());
        } else if (enemyShip.getX() > gameData.getDisplayWidth()) {
            enemyShip.setX(0);
        }

        if (enemyShip.getY() < 0) {
            enemyShip.setY(gameData.getDisplayHeight());
        } else if (enemyShip.getY() > gameData.getDisplayHeight()) {
            enemyShip.setY(0);
        }
    }

    public boolean fireBullet(long currentTime, EnemyShip enemySpaceship) {
        long fireNewBulletTime = enemySpaceship.getBulletFiredTime() + 1000;
        if (enemySpaceship.getBulletFiredTime() == 0 || currentTime >= fireNewBulletTime) {
            enemySpaceship.setBulletFiredTime(currentTime);
            return true;
        } else {
            return false;
        }
    }

    private Collection<? extends BulletSPI> getBulletSPIs() {
        return ServiceLoader.load(BulletSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}