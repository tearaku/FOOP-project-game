package collisionhandlers;

import bullet.Bullet;
import jet.Jet;
import model.CollisionHandler;
import model.Sprite;

import java.awt.*;

public class JetBulletCollisionHandler implements CollisionHandler{
    @Override
    public void handle(Point originalLocation, Sprite from, Sprite to) {
        if ((from instanceof Bullet && to instanceof Jet) ||
                (from instanceof Jet && to instanceof Bullet)) {
            Sprite target = (from instanceof Jet) ? from: to;
            Bullet bullet = (Bullet) ((from instanceof Bullet) ? from: to);
            if (bullet.getOwner() != target) {
                bullet.effectOnSprite(target);
            }
        }
    }
}