package jet;

import fsm.CyclicSequence;
import fsm.ImageState;
import model.Direction;
import views.GameView;

import java.awt.*;
import java.util.List;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class Walking extends CyclicSequence {
    public static final String AUDIO_STEP1 = "step1";
    public static final String AUDIO_STEP2 = "step2";
    private final Jet jet;

    public Walking(Jet jet, List<ImageState> states) {
        super(states);
        this.jet = jet;
    }

    // Restricting: 1) moving out of canvas, and 2) players crossing the middle line (area segregation)
    private boolean validMovement(Direction direction, Jet jet) {
        Point newLocation = new Point(jet.getLocation());
        newLocation.translate(direction.translate().width, direction.translate().height);
        switch(direction) {
            case RIGHT:
                if (newLocation.getX() > GameView.WIDTH - jet.getBody().width) {
                    return false;
                }
                break;
            case LEFT:
                if (newLocation.getX() < 0) {
                    return false;
                }
                break;
            case UP:
                if ((newLocation.getY() < 0) ||
                        ((jet.getFace() == Direction.UP) && (newLocation.getY() < (double)GameView.HEIGHT/2))) {
                    return false;
                }
                break;
            case DOWN:
                if ((newLocation.getY() + jet.getBody().height > GameView.HEIGHT) ||
                        ((jet.getFace() == Direction.DOWN) && (newLocation.getY() + jet.getBody().height > (double)GameView.HEIGHT/2))) {
                    return false;
                }
        }
        return true;
    }

    @Override
    public void update() {
        if (jet.isAlive()) {
            super.update();
            for (Direction direction : jet.getDirections()) {
                if (validMovement(direction, jet)) {
                    jet.getWorld().move(jet, direction.translate());
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Walking";
    }
}
