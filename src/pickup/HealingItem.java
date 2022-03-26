package pickup;

import media.AudioPlayer;
import model.Sprite;
import model.SpriteShape;

import java.awt.*;

public class HealingItem extends PickupItem {
    public static final String HEALING = "healing";
    public HealingItem(Point location) {
        super("healingitem", location, new SpriteShape(new Dimension(30, 30),
                new Dimension(0, 0), new Dimension(30, 30)));
    }

    public PickupItem getNewInstance() {
        return new HealingItem(this.location);
    }

    public void enactOnTarget(Sprite target){
        AudioPlayer.playSounds(HEALING);
        target.onDamaged(new Rectangle(),-100);
        this.world.removeSprite(this);
    }
}
