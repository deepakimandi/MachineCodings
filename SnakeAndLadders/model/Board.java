package org.example.SnakeAndLadders.model;

import lombok.Getter;
import org.example.SnakeAndLadders.enums.ObstacleType;

import java.util.*;

@Getter
public class Board {

    private final int size;
    private final int sideLength;
    private final Cell[][] grid;

    public Board(int size) {
        this.size = size;
        this.sideLength = (int) Math.sqrt(size);
        this.grid = new Cell[sideLength][sideLength];

        int position = 1;
        boolean leftToRight = true;

        for (int i = sideLength - 1; i >= 0; i--) {
            if (leftToRight) {
                for (int j = 0; j < sideLength; j++) {
                    grid[i][j] = new Cell(position++);
                }
            } else {
                for (int j = sideLength - 1; j >= 0; j--) {
                    grid[i][j] = new Cell(position++);
                }
            }
            leftToRight = !leftToRight;
        }
    }

    private int getRow(int position) {
        int row = (position - 1) / sideLength;
        return sideLength - 1 - row;
    }

    private int getCol(int position) {
        int row = getRow(position);
        int col = (position - 1) % sideLength;
        return (row % 2 == 0) ? sideLength - 1 - col : col;
    }

    private Cell getCell(int position) {
        return grid[getRow(position)][getCol(position)];
    }

    public boolean addObstacle(Obstacle obstacle) {
        Cell srcCell = getCell(obstacle.getSrc());
        Cell destCell = getCell(obstacle.getDest());

        // Prevent overlapping obstacles
        if (srcCell.hasObstacle() || destCell.hasObstacle()) {
            return false;
        }

        srcCell.setObstacle(obstacle);
        return true;
    }

    public int getNewPosition(Player player, int offset) {
        int newPosition = player.getPosition() + offset;

        if (newPosition > size) {
            System.out.println("You are going out of the board! Better luck next time!");
            return player.getPosition();
        }

        Cell cell = getCell(newPosition);
        int finalPosition = cell.getFinalPosition();

        if (finalPosition < newPosition) {
            System.out.println("Oops! Snake has bitten " + player.getName() + "!");
        } else if (finalPosition > newPosition) {
            System.out.println("Congratulations! " + player.getName() + " climbed a ladder!");
        } else {
            System.out.println(player.getName() + " moved from " + player.getPosition() + " to " + finalPosition);
        }

        return finalPosition;
    }

    public void printBoard(Queue<Player> players) {
        System.out.println("\nCurrent Board State:");

        // Map player positions for quick lookup
        Map<Integer, List<String>> playerPositions = new HashMap<>();
        for (Player player : players) {
            playerPositions.computeIfAbsent(player.getPosition(), k -> new ArrayList<>())
                    .add(player.getName());
        }

        for (int i = 0; i < sideLength; i++) {
            for (int j = 0; j < sideLength; j++) {
                Cell cell = grid[i][j];
                int position = cell.getPosition();
                String cellContent = String.valueOf(position);

                if (cell.hasObstacle()) {
                    Obstacle obs = cell.getObstacle();
                    if (obs.getObstacleType() == ObstacleType.SNAKE) {
                        cellContent = "S→" + obs.getDest();
                    } else if (obs.getObstacleType() == ObstacleType.LADDER) {
                        cellContent = "L→" + obs.getDest();
                    }
                }

                // Add player names if any in this cell
                if (playerPositions.containsKey(position)) {
                    List<String> playersHere = playerPositions.get(position);
                    cellContent += "[" + String.join(",", playersHere) + "]";
                }

                System.out.print(String.format("%-6s", cellContent));
            }
            System.out.println();
        }
    }

}
