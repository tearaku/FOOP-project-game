package collisionhandlers;

import jet.Jet;
import model.CollisionHandler;
import model.Sprite;
import pickup.PickupItem;

import java.awt.*;

public class PickupCollisionHandler implements CollisionHandler {
    @Override
    public void handle(Point originalLocation, Sprite from, Sprite to) {
        if ((from instanceof PickupItem && to instanceof Jet) ||
                (from instanceof Jet && to instanceof PickupItem)) {
            Sprite target = (from instanceof Jet) ? from: to;
            PickupItem item = (PickupItem) ((from instanceof PickupItem) ? from: to);
            item.enactOnTarget(target);
        }
    }
}
