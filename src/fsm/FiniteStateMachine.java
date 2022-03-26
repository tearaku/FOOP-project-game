package fsm;

import java.util.HashMap;
import java.util.Map;

import static fsm.FiniteStateMachine.Transition.from;
import static jet.Jet.Event.ATTACK;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class FiniteStateMachine implements StateMachine {
    private final Map<State, Map<Object, State>> transitionTable = new HashMap<>();
    private State initialState;
    private State current;
    private State idle;
    private State attacking;
    private State walking;

    public FiniteStateMachine() {
    }

    public void setInitialState(State initialState) {
        this.initialState = initialState;
        reset();
    }

    public void addTransition(Transition transition) {
        if (!transitionTable.containsKey(transition.from)) {
            transitionTable.put(transition.from, new HashMap<>());
        }
        transitionTable.get(transition.from).put(transition.event, transition.to);
    }

    public void set(State idle, State attacking, State walking) {
        this.idle = idle;
        this.attacking = attacking;
        this.walking = walking;
    }

    public void setAttackingState(State Attacking) {
        this.addTransition(from(idle).when(ATTACK).to(Attacking));
        this.addTransition(from(walking).when(ATTACK).to(Attacking));
    }

    public void resetAttackingState() {
        this.addTransition(from(idle).when(ATTACK).to(attacking));
        this.addTransition(from(walking).when(ATTACK).to(attacking));
    }

    @Override
    public void trigger(Object event) {
        var transition = transitionTable.get(current);
        if (transition != null) {
            State to = transition.get(event);
            if (to != null) {
                current = to;
            }
        }
    }

    @Override
    public void update() {
        currentState().update();
    }

    @Override
    public void reset() {
        current = initialState;
    }

    @Override
    public State currentState() {
        return current;
    }


    public static class Transition {
        State from;
        Object event;
        State to;

        public Transition(State from) {
            this.from = from;
        }

        public static Transition from(State from) {
            return new Transition(from);
        }

        public Transition when(Object event) {
            this.event = event;
            return this;
        }

        public Transition to(State to) {
            this.to = to;
            return this;
        }

    }
}
