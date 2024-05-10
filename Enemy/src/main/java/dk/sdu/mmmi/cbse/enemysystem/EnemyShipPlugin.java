package dk.sdu.mmmi.cbse.enemysystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

import java.util.Random;

public class EnemyShipPlugin implements IGamePluginService {

    private Entity enemyShip;

    public EnemyShipPlugin() {
    }

    @Override
    public void start(GameData gameData, World world) {
        enemyShip = createEnemyShip(gameData);
        world.addEntity(enemyShip);
    }

    private Entity createEnemyShip(GameData gameData) {
        Entity enemyShip = new EnemyShip();
        enemyShip.setPolygonCoordinates(-10, 0, -5, 3, -3, 6, 3, 6, 5, 3, 10, 0, 3, -3, -3, -3);
        enemyShip.setWidth(20);
        enemyShip.setHeight(9);
        placeEnemyShip(gameData, enemyShip);
        return enemyShip;
    }

    private void placeEnemyShip(GameData gameData, Entity enemyShip) {
        Random random = new Random();
        int randomizer = random.nextInt(4) + 1;
        double displayWidth = gameData.getDisplayWidth();
        double displayHeight = gameData.getDisplayHeight();
        switch (randomizer) {
            case 1:
                enemyShip.setX(random.nextDouble() * displayWidth);
                enemyShip.setY(0);
                enemyShip.setRotation(random.nextDouble() * 180 + 0.1);
                break;
            case 2:
                enemyShip.setX(random.nextDouble() * displayWidth);
                enemyShip.setY(displayHeight);
                enemyShip.setRotation(random.nextDouble() * -180 - 0.1);
                break;
            case 3:
                enemyShip.setX(0);
                enemyShip.setY(random.nextDouble() * displayHeight);
                enemyShip.setRotation(random.nextDouble() * 180 - 90);
                break;
            case 4:
                enemyShip.setX(displayWidth);
                enemyShip.setY(random.nextDouble() * displayHeight);
                enemyShip.setRotation(random.nextDouble() * 180 + 90);
                break;
        }
    }

    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        world.removeEntity(enemyShip);
    }
}
