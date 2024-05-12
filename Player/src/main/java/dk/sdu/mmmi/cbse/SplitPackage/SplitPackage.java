package dk.sdu.mmmi.cbse.SplitPackage;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;


public class SplitPackage implements IEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {
        System.out.println("Hello Asteroids!");

    }
}
