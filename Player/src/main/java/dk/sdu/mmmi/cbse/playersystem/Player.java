package dk.sdu.mmmi.cbse.playersystem;

import dk.sdu.mmmi.cbse.common.bullet.Bullet;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Emil
 */
public class Player extends Entity {

    private long bulletFiredTime;
    private int timesHit;
    private List<Bullet> bullets = new ArrayList<>();

    public long getBulletFiredTime() {
        return bulletFiredTime;
    }

    public void setBulletFiredTime(long bulletFiredTime) {
        this.bulletFiredTime = bulletFiredTime;
    }

    @Override
    public void handleCollision(GameData gameData, World world, Entity collidingEntity) {
        if (collidingEntity instanceof Bullet) {
            if (timesHit < 3) {
                timesHit++;
            } else {
                world.removeEntity(this);
            }
            world.removeEntity(collidingEntity);
        }
    }

    public List<Bullet> getBullets() {
        return bullets;
    }

    public void addBullet(Bullet bullet) {
        bullets.add(bullet);
    }
}
