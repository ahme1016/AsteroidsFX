package dk.sdu.mmmi.cbse.enemysystem;

import dk.sdu.mmmi.cbse.common.bullet.Bullet;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

import java.util.ArrayList;

public class EnemyShip extends Entity {

    private long bulletFiredTime;
    private ArrayList<Bullet> bullets = new ArrayList<>();

    public long getBulletFiredTime() {
        return bulletFiredTime;
    }

    public void setBulletFiredTime(long bulletFiredTime) {
        this.bulletFiredTime = bulletFiredTime;
    }

    @Override
    public void handleCollision(GameData gameData, World world, Entity collidingEntity) {
        if (collidingEntity instanceof Bullet) {
            handleBulletCollision((Bullet) collidingEntity, world);
        }
    }

    private void handleBulletCollision(Bullet bullet, World world) {
        world.removeEntity(bullet); // Remove the bullet upon collision
        destroyEnemy(world); // Destroy the enemy ship on the first hit itself
    }

    private void destroyEnemy(World world) {
        world.removeEntity(this);
    }

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }

    public void addBullet(Bullet bullet) {
        this.bullets.add(bullet);
    }
}
