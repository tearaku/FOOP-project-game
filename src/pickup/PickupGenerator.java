package pickup;

import model.Sprite;
import views.GameView;

import java.awt.*;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class PickupGenerator extends Sprite {
    private final List<? extends PickupItem> listOfPickupType;
    private Iterator<? extends PickupItem> listIter;
    private final Random PRNG;
    private int counter;
    private final int ticksPerGeneration;

    public PickupGenerator(int ticksPerItem, List<? extends PickupItem> itemTypes) {
        this.listOfPickupType = itemTypes;
        this.listIter = this.listOfPickupType.iterator();
        this.ticksPerGeneration = ticksPerItem;
        this.counter = 0;
        this.PRNG = new Random();
    }

    private PickupItem getNextItem() {
        if (!listIter.hasNext()) {
            Collections.shuffle(listOfPickupType);
            listIter = listOfPickupType.iterator();
        }
        PickupItem itemToSpawn = listIter.next().getNewInstance();
        itemToSpawn.setLocation(new Point(PRNG.nextInt(GameView.WIDTH), PRNG.nextInt(GameView.HEIGHT)));
        return itemToSpawn;
    }

    @Override
    public void update() {
        counter++;
        if (counter == ticksPerGeneration) {
            this.world.addSprite(getNextItem());
            counter = 0;
        }
    }

    @Override
    public void render(Graphics g) {
    }

    @Override
    public void onDamaged(Rectangle damageArea, int damage) {

    }

    @Override
    public Rectangle getRange() {
        return new Rectangle();
    }

    @Override
    public Dimension getBodyOffset() {
        return new Dimension(0, 0);
    }

    @Override
    public Dimension getBodySize() {
        return new Dimension(0, 0);
    }
}
