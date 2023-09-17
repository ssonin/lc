package interview.uber.dynamicprogramming;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Walls and Gates
 */
public class WallsAndGates {

    public static void main(String[] args) {
        new WallsAndGates().run();
    }

    private void run() {
        final var rooms = new int[][] {
            {2147483647,-1,0,2147483647},
            {2147483647,2147483647,2147483647,-1},
            {2147483647,-1,2147483647,-1},
            {0,-1,2147483647,2147483647}
        };
        wallsAndGates(rooms);
        for (final var row : rooms) {
            System.out.println(Arrays.toString(row));
        }
    }

    public void wallsAndGates(int[][] rooms) {
        for (var i = 0; i < rooms.length; i++) {
            for (var j = 0; j < rooms[0].length; j++) {
                if (rooms[i][j] == 0) {
                    bfs(i, j, rooms);
                }
            }
        }
    }

    private void bfs(int i, int j, int[][] grid) {
        final var queue = new ArrayDeque<Cell>();
        queue.offer(new Cell(i, j));
        while (!queue.isEmpty()) {
            final var current = queue.poll();
            final var adjacentCells = getAdjacentCells(current, grid);
            for (final var cell : adjacentCells) {
                if (cell.depth > current.depth + 1) {
                    cell.depth = current.depth + 1;
                    grid[cell.row][cell.col] = current.depth + 1;
                    queue.offer(cell);
                }
            }
        }
    }



    private List<Cell> getAdjacentCells(Cell cell, int[][] grid) {
        final var top = cell.row - 1 >= 0
            ? new Cell(cell.row - 1, cell.col, grid[cell.row - 1][cell.col])
            : null;
        final var bot = cell.row + 1 < grid.length
            ? new Cell(cell.row + 1, cell.col, grid[cell.row + 1][cell.col])
            : null;
        final var left = cell.col - 1 >= 0
            ? new Cell(cell.row, cell.col - 1, grid[cell.row][cell.col - 1])
            : null;
        final var right = cell.col + 1 < grid[0].length
            ? new Cell(cell.row, cell.col + 1, grid[cell.row][cell.col + 1])
            : null;
        return Stream.of(top, bot, left, right)
            .filter(nonNull().and(isEmpty(grid)))
            .collect(Collectors.toList());
    }

    private Predicate<Cell> nonNull() {
        return Objects::nonNull;
    }

    private Predicate<Cell> isEmpty(int[][] grid) {
        return cell -> grid[cell.row][cell.col] > 0;
    }

    private static class Cell {

        public final int row;
        public final int col;
        public int depth;

        public Cell(int row, int col) {
            this(row, col, 0);
        }

        public Cell(int row, int col, int depth) {
            this.row = row;
            this.col = col;
            this.depth = depth;
        }
    }
}
