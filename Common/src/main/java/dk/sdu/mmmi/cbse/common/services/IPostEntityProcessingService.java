package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

/**
 * This interface defines the contract for post-entity processors in the game.
 */
public interface IPostEntityProcessingService {

    /**
     * Used when the entity processing is completed.
     *
     * @param gameData The game data containing information about the game state.
     * @param world    The game world containing entities and other game elements.
     * @precondition The entity processing is completed.
     * @postcondition The post entity processing is completed.
     */
    void process(GameData gameData, World world);

    void postProcess(GameData gameData, World world);
}
