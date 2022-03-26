package jet;

import fsm.ImageRenderer;
import model.Direction;

import java.awt.*;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class JetImageRenderer implements ImageRenderer {
    protected Jet jet;

    public JetImageRenderer(Jet jet) {
        this.jet = jet;
    }

    @Override
    public void render(Image image, Graphics g) {
        // TODO: change direction to UP / DOWN
        Direction face = jet.getFace();
        Rectangle range = jet.getRange();
        Rectangle body = jet.getBody();
        if (face == Direction.LEFT) {
            g.drawImage(image, range.x + range.width, range.y, -range.width, range.height, null);
        } else {
            g.drawImage(image, range.x, range.y, range.width, range.height, null);
        }
        g.setColor(Color.RED);
        g.drawRect(body.x, body.y, body.width, body.height);
    }
}
