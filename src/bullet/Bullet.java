package bullet;

import fsm.FiniteStateMachine;
import fsm.ImageRenderer;
import fsm.State;
import fsm.WaitingPerFrame;
import model.Sprite;
import model.SpriteShape;

import java.awt.*;

import static utils.ImageStateUtils.imageStatesFromFolder;
/**
 * @author - johnny850807@gmail.com (Waterball)
 */

public abstract class Bullet extends Sprite {
    private final Sprite owner;
    protected final Dimension unitDirection;
    protected final double angle;
    protected final SpriteShape shape;
    protected final int damage;
    // Bullet only has 1 state it's always in, moving w/ specified velocity until it collision occurs
    private final FiniteStateMachine fsm;
    // Whether or not bullet is currently in-sync with the world's POV of sprites array
    protected boolean worldSync;

    public Bullet(Sprite owner, Point location, int damage, int speed, double angle, SpriteShape shape, String type) {
        this.worldSync = true;
        this.owner = owner;
        this.location = location;
        this.shape = shape;
        this.damage = damage;
        // Use angles to differentiate between movement of players' bullets (top- / down-seeking)
        this.angle = angle;
        double angleRadian = Math.toRadians(angle);

        //Dimension angledHitboxOffset = calculate_offset();
        this.shape.bodyOffset.setSize(calculate_offset());

        this.unitDirection = new Dimension(Math.toIntExact(Math.round(Math.cos(angleRadian) * speed)),
                Math.toIntExact(Math.round(Math.sin(angleRadian) * speed)));
        this.fsm = new FiniteStateMachine();
        ImageRenderer imageRenderer = new BulletImageRenderer(this);
        State inTransit = new WaitingPerFrame(4,
                new BulletInTransit(this, imageStatesFromFolder("assets/bullet/" + type, imageRenderer)));
        fsm.setInitialState(inTransit);
    }

    public abstract void effectOnSprite(Sprite target);

    protected void explodeOnCollide() {
        this.worldSync = false;
        this.getWorld().removeSprite(this);
    }

    public int getDamage() {
        return this.damage;
    }
    public Sprite getOwner() {  return this.owner; }

    public Dimension calculate_offset(){
        int offset_x = 0;
        int offset_y = 0;
        if (Math.toIntExact(Math.round(Math.cos((Math.toRadians(-angle-180))) * this.shape.bodySize.height)) <=0&&
                Math.toIntExact(Math.round(Math.sin((Math.toRadians(-angle-180))) * this.shape.bodySize.height)) >0) {
            offset_x -= this.shape.bodySize.width;
            offset_y -=Math.toIntExact(Math.round(Math.sin((Math.toRadians(-angle-180))) * this.shape.bodySize.height));
        }
        else if (Math.toIntExact(Math.round(Math.cos((Math.toRadians(-angle-180))) * this.shape.bodySize.height)) >0&&
                Math.toIntExact(Math.round(Math.sin((Math.toRadians(-angle-180))) * this.shape.bodySize.height)) >=0){
            offset_x -= this.shape.bodySize.width;
            offset_y -= this.shape.bodySize.height;
            offset_x += Math.toIntExact(Math.round(Math.cos((Math.toRadians(-angle-180))) * this.shape.bodySize.height));
        }
        else if (Math.toIntExact(Math.round(Math.cos((Math.toRadians(-angle-180))) * this.shape.bodySize.height)) <0&&
                Math.toIntExact(Math.round(Math.sin((Math.toRadians(-angle-180))) * this.shape.bodySize.height)) <=0) {
            offset_x += Math.toIntExact(Math.round(Math.cos((Math.toRadians(-angle-180))) * this.shape.bodySize.height));
        }
        else {
            offset_y -= this.shape.bodySize.height;
            offset_y -=Math.toIntExact(Math.round(Math.sin((Math.toRadians(-angle-180))) * this.shape.bodySize.height));
        }
        return new Dimension(offset_x,offset_y);
    }

    @Override
    public void update() {
        if (worldSync) {
            this.fsm.update();
        }
    }

    @Override
    public void render(Graphics g) {
        this.fsm.render(g);
    }

    @Override
    // Bullet cannot be damaged, its destroyed on contact
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