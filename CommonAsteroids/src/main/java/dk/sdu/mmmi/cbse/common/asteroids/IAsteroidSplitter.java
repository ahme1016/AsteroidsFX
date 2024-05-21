package dk.sdu.mmmi.cbse.common.asteroids;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.World;

public interface IAsteroidSplitter {

    /**
     * Splits the given asteroid entity into smaller entities and adds them
     * to the provided game world. The specific behavior of the splitting
     * (e.g., number of smaller asteroids, their sizes, positions, and rotations)
     * should be defined by the implementing class.
     *
     * @param e the asteroid entity to be split
     * @param w the game world where the new smaller asteroids will be added
     */
    void createSplitAsteroid(Entity e, World w);
}
