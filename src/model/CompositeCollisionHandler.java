package model;

import java.awt.*;
import java.util.List;

public class CompositeCollisionHandler implements CollisionHandler {
    private final List<? extends CollisionHandler> collisionHandlers;

    public CompositeCollisionHandler(List<? extends CollisionHandler> handlerList) {
        this.collisionHandlers = handlerList;
    }

    @Override
    public void handle(Point originalLocation, Sprite from, Sprite to) {
        for (CollisionHandler collisionHandler : collisionHandlers) {
            collisionHandler.handle(originalLocation, from, to);
        }
    }
}
