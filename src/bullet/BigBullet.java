package bullet;

import jet.Jet;
import model.Sprite;
import model.SpriteShape;

import java.awt.*;

public class BigBullet extends Bullet{
    // Bullet default size: same as source png file (after cropping)
    public static final String AUDIO_BULLET_EXPLODE = "Bullet_Explode";

    @Override
    public void effectOnSprite(Sprite target) {
        target.onDamaged(new Rectangle(), this.getDamage());
        explodeOnCollide();
    }

    public BigBullet(Sprite owner, Point location, int damage, int speed, double angle) {
        super(owner, location, damage, speed, angle, new SpriteShape(new Dimension(25, 65),
                new Dimension(0, 0), new Dimension(25, 32)), "bigbullet");
        ((Jet)owner).getAmmoBar().onDamaged(null, 1);
    }
}

