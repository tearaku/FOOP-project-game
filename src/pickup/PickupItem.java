package pickup;

import fsm.FiniteStateMachine;
import fsm.ImageRenderer;
import fsm.State;
import fsm.WaitingPerFrame;
import model.Sprite;
import model.SpriteShape;

import java.awt.*;

import static utils.ImageStateUtils.imageStatesFromFolder;

public abstract class PickupItem extends Sprite {
    protected final String itemName;
    protected final SpriteShape shape;
    private final FiniteStateMachine fsm;

    public abstract void enactOnTarget(Sprite target);

    public PickupItem(String name, Point location, SpriteShape shape) {
        this.itemName = name;
        this.location = location;
        this.shape = shape;
        this.fsm = new FiniteStateMachine();
        ImageRenderer imageRenderer = new PickupItemImageRenderer(this);
        State inTransit = new WaitingPerFrame(4,
                new PickupItemIdle(this, imageStatesFromFolder("assets/pickupitem/" + itemName, imageRenderer)));
        fsm.setInitialState(inTransit);
    }

    public abstract PickupItem getNewInstance();

    @Override
    public void update() {
        this.fsm.update();
    }

    @Override
    public void render(Graphics g) {
        this.fsm.render(g);
    }

    @Override
    public void onDamaged(Rectangle damageArea, int damage) {
    }

    @Override
    public Rectangle getRange() {
        return new Rectangle(location, shape.size);
    }

    @Override
    public Dimension getBodyOffset() {
        return shape.bodyOffset;
    }

    @Override
    public Dimension getBodySize() {
        return shape.bodySize;
    }
}
