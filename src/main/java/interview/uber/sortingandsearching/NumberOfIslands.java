package interview.uber.sortingandsearching;

import common.GridCell;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.function.Predicate.not;

/**
 * Number of Islands
 */
public class NumberOfIslands {

    public static void main(String[] args) {
        final var app = new NumberOfIslands();
        System.out.println(app.numIslands(new char[][]{{'1','1','1'},{'0','1','0'},{'1','1','1'}}));
        System.out.println(app.numIslands2(new char[][]{
            {'1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '0', '1', '1'},
            {'0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '0'},
            {'1', '0', '1', '1', '1', '0', '0', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1'},
            {'1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1'},
            {'1', '0', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1'},
            {'1', '0', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '0', '1', '1', '1', '0', '1', '1', '1'},
            {'0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '0', '1', '1', '1', '1'},
            {'1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '0', '1', '1'},
            {'1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1'},
            {'1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1'},
            {'0', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1'},
            {'1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1'},
            {'1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1'},
            {'1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1'},
            {'1', '0', '1', '1', '1', '1', '1', '0', '1', '1', '1', '0', '1', '1', '1', '1', '0', '1', '1', '1'},
            {'1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1', '0'},
            {'1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '0', '0'},
            {'1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1'},
            {'1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1'},
            {'1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1'}
        }));
    }

    public int numIslands(char[][] grid) {
        final var m = grid.length;
        final var n = grid[0].length;
        final var uf = new UF(m * n);
        for (var i = 0; i < m; i++) {
            for (var j = 0; j < n; j++) {
                final var idx = n * i + j;
                if (grid[i][j] == '1') {
                    if (i > 0 && j > 0) {
                        if (grid[i - 1][j] == '1') {
                            uf.union(idx, n * (i - 1) + j);
                        }
                        if (grid[i][j - 1] == '1') {
                            uf.union(idx, n * i + (j - 1));
                        }
                    } else if (i > 0) {
                        if (grid[i - 1][j] == '1') {
                            uf.union(idx, n * (i - 1) + j);
                        }
                    } else if (j > 0) {
                        if (grid[i][j - 1] == '1') {
                            uf.union(j, j - 1);
                        }
                    }
                } else {
                    uf.nullify(idx);
                }
            }
        }
        System.out.println(uf);
        return uf.count();
    }

    public int numIslands2(char[][] grid) {
        final var m = grid.length;
        final var n = grid[0].length;
        var result = 0;
        for (var i = 0; i < m; i++) {
            for (var j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    bfs(grid, new GridCell(i, j));
                    result++;
                }
            }
        }
        return result;
    }

    private int bfs(char[][] grid, GridCell root) {
        var result = 0;
        final var queue = new ArrayDeque<GridCell>();
        queue.add(root);
        while (!queue.isEmpty()) {
            final var p = queue.removeFirst();
            if (grid[p.row()][p.col()] == '1') {
                result++;
                grid[p.row()][p.col()] = '0';
                final var neighbors = neighbors(grid, p);
                queue.addAll(neighbors);
            }
        }
        return result;
    }

    private List<GridCell> neighbors(char[][] grid, GridCell root) {
        final var top = new GridCell(root.row() - 1, root.col());
        final var bot = new GridCell(root.row() + 1, root.col());
        final var left = new GridCell(root.row(), root.col() - 1);
        final var right = new GridCell(root.row(), root.col() + 1);
        return Stream.of(top, bot, left, right)
            .filter(isLand(grid))
            .collect(Collectors.toList());
    }

    private static Predicate<GridCell> isLand(char[][] grid) {
        return not(p -> p.row() < 0 || p.col() < 0 || p.row() >= grid.length
            || p.col() >= grid[0].length || grid[p.row()][p.col()] == '0');
    }

    private static class UF {

        private int count;
        private final int[] id;

        public UF(int size) {
            this.count = size;
            this.id = new int[size];
            for (var i = 0; i < size; i++) {
                id[i] = i;
            }
        }

        public void nullify(int p) {
            id[p] = -1;
            count--;
        }

        public void union(int p, int q) {
            final var pRoot = find(p);
            final var qRoot = find(q);
            if (pRoot != qRoot) {
                if (pRoot < qRoot) {
                    id[qRoot] = pRoot;
                } else {
                    id[pRoot] = qRoot;
                }
                count--;
            }
        }

        public int count() {
            return count;
        }

        private int find(int p) {
            while (p != id[p]) {
                p = id[p];
            }
            return p;
        }

        @Override
        public String toString() {
            return Arrays.toString(id) + " " + count;
        }
    }
}
