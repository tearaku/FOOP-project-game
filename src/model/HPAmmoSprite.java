package model;

import jet.AmmoBar;
import media.AudioPlayer;

import java.awt.*;

public abstract class HPAmmoSprite extends HealthPointSprite {
    protected AmmoBar ammoBar;

    public HPAmmoSprite(int hp, int ammo) {
        super(hp);
        this.ammoBar = new AmmoBar(ammo);
        ammoBar.setOwner(this);
    }

    public AmmoBar getAmmoBar() {
        return this.ammoBar;
    }

    @Override
    public void render(Graphics g) {
        super.render(g);
        ammoBar.render(g);
    }
}
