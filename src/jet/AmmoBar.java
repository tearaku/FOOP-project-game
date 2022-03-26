package jet;

import model.Sprite;

import java.awt.*;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class AmmoBar extends Sprite {
    private final int maxAmmo;
    private Sprite owner;
    private int currentAmmo;

    public AmmoBar(int ammo) {
        this.maxAmmo = this.currentAmmo = ammo;
    }

    public void setOwner(Sprite owner) {
        this.owner = owner;
    }

    public void setAmmo(int ammo) {
        this.currentAmmo = ammo;
    }

    public void reset() {
        this.currentAmmo = this.maxAmmo;
    }

    @Override
    public void update() {
    }

    @Override
    public void render(Graphics g) {
        Rectangle range = getRange();
        int width = (int) (currentAmmo * owner.getRange().getWidth() / maxAmmo);
        g.setColor(Color.GRAY);
        g.fillRect(range.x, range.y, (int) owner.getRange().getWidth(), range.height);
        g.setColor(Color.CYAN);
        g.fillRect(range.x, range.y, width, range.height);
    }

    // Throwing an amount just because? XD
    public void onResourceUse(int amount) {
        // Technically, its onResourceDeplete
        onDamaged(null, amount);
    }

    @Override
    // Technically, it's more like onResourceDeplete(), it always depletes by 1
    public void onDamaged(Rectangle damageArea, int damage) {
        this.currentAmmo = Math.max(currentAmmo - damage, 0);
    }

    @Override
    public Rectangle getRange() {
        return new Rectangle(owner.getX(), owner.getY() - 30, (int) owner.getRange().getWidth(), 10);
    }

    @Override
    public Dimension getBodyOffset() {
        return new Dimension();
    }

    @Override
    public Dimension getBodySize() {
        return new Dimension();
    }

    public boolean outOfAmmo() {
        return currentAmmo <= 0;
    }
}
