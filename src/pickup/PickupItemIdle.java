package pickup;

import fsm.CyclicSequence;
import fsm.ImageState;

import java.util.List;

public class PickupItemIdle extends CyclicSequence {
    private final PickupItem powerUpItems;

    public PickupItemIdle(PickupItem powerUpItems, List<ImageState> states) {
        super(states);
        this.powerUpItems = powerUpItems;
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public String toString() {
        return "PowerUpItemsInTransit";
    }
}
