package interview.uber.sortingandsearching;

import java.util.ArrayDeque;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.function.Predicate.not;

/**
 * Max Area of Island
 */
public class MaxAreaOfIsland {

    public static void main(String[] args) {

    }

    public int maxAreaOfIsland(int[][] grid) {
        final var m = grid.length;
        final var n = grid[0].length;
        var result = 0;
        for (var i = 0; i < m; i++) {
            for (var j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    result += bfs(grid, new Pair(i, j));
                }
            }
        }
        return result;
        
    }

    private int bfs(int[][] grid, Pair root) {
        var result = 0;
        final var queue = new ArrayDeque<Pair>();
        queue.add(root);
        while (!queue.isEmpty()) {
            final var p = queue.removeFirst();
            if (grid[p.row][p.col] == 1) {
                result++;
                grid[p.row][p.col] = 1;
                final var neighbors = neighbors(grid, p);
                queue.addAll(neighbors);
            }
        }
        return result;
    }

    private List<Pair> neighbors(int[][] grid, Pair root) {
        final var top = new Pair(root.row - 1, root.col);
        final var bot = new Pair(root.row + 1, root.col);
        final var left = new Pair(root.row, root.col - 1);
        final var right = new Pair(root.row, root.col + 1);
        return Stream.of(top, bot, left, right)
            .filter(isLand(grid))
            .collect(Collectors.toList());
    }

    private static Predicate<Pair> isLand(int[][] grid) {
        return not(p -> p.row < 0 || p.col < 0 || p.row >= grid.length
            || p.col >= grid[0].length || grid[p.row][p.col] == 0);
    }
}
