package org.example.SnakeAndLadders.factory;

import org.example.SnakeAndLadders.enums.ObstacleType;
import org.example.SnakeAndLadders.model.Ladder;
import org.example.SnakeAndLadders.model.Obstacle;
import org.example.SnakeAndLadders.model.Snake;

public class ObstacleFactory {

    public static Obstacle createObstacle(ObstacleType type, int up, int down) {
        return switch (type) {
            case SNAKE -> new Snake(up, down);
            case LADDER -> new Ladder(up, down);
            default -> throw new IllegalArgumentException("Invalid obstacle type: " + type);
        };
    }
}
