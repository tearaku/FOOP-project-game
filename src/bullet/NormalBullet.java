package bullet;

import jet.Jet;
import media.AudioPlayer;
import model.Sprite;
import model.SpriteShape;

import java.awt.*;
/**
 * @author - johnny850807@gmail.com (Waterball)
 */

public class NormalBullet extends Bullet {
    // Bullet default size: same as source png file (after cropping)
    public static final String AUDIO_BULLET_EXPLODE = "Bullet_Explode";

    @Override
    public void effectOnSprite(Sprite target) {
        target.onDamaged(new Rectangle(), this.getDamage());
        explodeOnCollide();
    }

    public NormalBullet(Sprite owner, Point location, int damage, int speed, double angle) {
        super(owner, location, damage, speed, angle, new SpriteShape(new Dimension(15, 39),
                new Dimension(0, 0), new Dimension(15, 18)), "normalbullet");
        ((Jet)owner).getAmmoBar().onResourceUse(1);
    }
}
