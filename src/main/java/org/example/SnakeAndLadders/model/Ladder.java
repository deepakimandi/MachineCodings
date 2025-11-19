package org.example.SnakeAndLadders.model;

import org.example.SnakeAndLadders.enums.ObstacleType;

public class Ladder extends Obstacle {

    public Ladder(int bottom, int top) {
        super(bottom, top);
    }

    @Override
    public ObstacleType getObstacleType() {
        return ObstacleType.LADDER;
    }
}
