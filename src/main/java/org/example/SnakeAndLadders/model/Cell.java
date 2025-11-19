package org.example.SnakeAndLadders.model;

import lombok.Getter;
import lombok.Setter;
import lombok.RequiredArgsConstructor;

@Getter
@Setter
@RequiredArgsConstructor
public class Cell {
    private final int position;
    private Obstacle obstacle;

    public boolean hasObstacle() {
        return obstacle != null;
    }

    public int getFinalPosition() {
        return hasObstacle() ? obstacle.movePlayer() : position;
    }
}
