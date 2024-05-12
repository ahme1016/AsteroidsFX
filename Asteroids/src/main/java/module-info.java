import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

module Asteroid {
    uses dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
    exports dk.sdu.mmmi.cbse.asteroid;
    requires Common;
    requires CommonBullet;
    provides IGamePluginService with dk.sdu.mmmi.cbse.asteroid.AsteroidPlugin;
    provides IEntityProcessingService with dk.sdu.mmmi.cbse.asteroid.AsteroidControlSystem, dk.sdu.mmmi.cbse.SplitPackage.SplitPackage;
}