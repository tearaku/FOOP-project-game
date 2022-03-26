package pickup;

import fsm.ImageRenderer;

import java.awt.*;

public class PickupItemImageRenderer implements ImageRenderer {
    protected PickupItem powerUpItems;

    public  PickupItemImageRenderer(PickupItem powerUpItems) {
        this.powerUpItems = powerUpItems;
    }

    @Override
    public void render(Image image, Graphics g) {
        Rectangle range =powerUpItems.getRange();
        Rectangle body = powerUpItems.getBody();
        g.drawImage(image, range.x, range.y, range.width, range.height, null);
        g.setColor(Color.RED);
        g.drawRect(body.x, body.y, body.width, body.height);
    }
}
