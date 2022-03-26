package bullet;

import fsm.CyclicSequence;
import fsm.ImageState;
import views.GameView;

import java.awt.*;
import java.util.List;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class BulletInTransit extends CyclicSequence {
    private final Bullet bullet;

    public BulletInTransit(Bullet bullet, List<ImageState> states) {
        super(states);
        this.bullet = bullet;
    }

    // Destroy out of view bullets
    private void onOutOfView() {
        Point bulletLoc = this.bullet.getLocation();
        if ((bulletLoc.getX() < 0) ||
                (bulletLoc.getX() > GameView.WIDTH) ||
                (bulletLoc.getY() < 0) ||
                (bulletLoc.getY() > GameView.HEIGHT)) {
            this.bullet.explodeOnCollide();
        }
    }

    @Override
    public void update() {
        super.update();
        this.bullet.getWorld().move(this.bullet, this.bullet.unitDirection);
        onOutOfView();
    }

    @Override
    public String toString() {
        return "BulletInTransit";
    }
}
