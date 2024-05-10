package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

/**
 * This interface defines the contract for game plugins in the game.
 */
public interface IGamePluginService {

    /**
     * Initializes and starts the game plugin.
     *
     * @param gameData The game data containing information about the game state.
     * @param world    The game world containing entities and other game elements.
     * @precondition The plugin is not initialized.
     * @postcondition The plugin is initialized and ready for use.

     */
    void start(GameData gameData, World world);

    /**
     * Stops and cleans up resources used by the game plugin.
     *
     * @param gameData The game data containing information about the game state.
     * @param world    The game world containing entities and other game elements.
     * @precondition The plugin is currently running.
     * @postcondition The plugin is stopped and resources are cleaned up.
     */
    void stop(GameData gameData, World world);
}