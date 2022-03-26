package jet;

import fsm.FiniteStateMachine;
import fsm.Sequence;
import fsm.State;
import fsm.StateMachine;
import media.AudioPlayer;

import java.awt.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public abstract class Attacking extends Sequence {
    public static final String SHOOT = "shoot";
    public static final String AUDIO_SWORD_CLASH_1 = "sword-clash1";
    protected static final int DIR_UP = 90;
    protected final Jet jet;
    protected final StateMachine stateMachine;
    protected final Set<Integer> damagingStateNumbers = new HashSet<>(List.of(2));

    public Attacking(Jet jet, StateMachine stateMachine, List<? extends State> states) {
        super(states);
        this.jet = jet;
        this.stateMachine = stateMachine;
    }

    @Override
    public void update() {
        if (jet.isAlive()) {
            super.update();
            if (damagingStateNumbers.contains(currentPosition) &&
                    !jet.getAmmoBar().outOfAmmo()) {
                enactAttack();
            }
            if (jet.getAmmoBar().outOfAmmo()) {
                jet.getAmmoBar().reset();
                ((FiniteStateMachine)stateMachine).resetAttackingState();
            }
        }
    }

    @Override
    public void render(Graphics g) {
        super.render(g);
    }

    protected abstract void enactAttack();

    @Override
    protected void onSequenceEnd() {
        currentPosition = 0;
        stateMachine.reset();
    }
}
