package dk.sdu.mmmi.cbse.common.bullet;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;

public interface BulletSPI {

    /**
     * Creates a new bullet entity based on the given source entity and game data.
     * The specific behavior of the bullet creation (e.g., its speed, direction,
     * and appearance) should be defined by the implementing class.
     *
     * @param e the source entity from which the bullet is created
     * @param gameData the current game data used to configure the bullet
     * @return a new bullet entity
     */
    Entity createBullet(Entity e, GameData gameData);
}
