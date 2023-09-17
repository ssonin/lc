package interview.uber.trees;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Print Binary Tree
 */
public class PrintBinaryTree {

    public static void main(String[] args) {
        final var app = new PrintBinaryTree();
        final var root = new TreeNode(1, new TreeNode(2, null, new TreeNode(4, null, null)), new TreeNode(3, null, null));
        for (final var row : app.printTree(root)) {
            System.out.println(row);
        }
    }

    public List<List<String>> printTree(TreeNode root) {
        final var nRows = height(root) + 1;
        final var nColumns = (int) Math.pow(2, nRows) - 1;
        final var result = init(nRows, nColumns);
        final var bfsOrder = bfs(root, nRows - 1, nColumns);

        for (final var node : bfsOrder) {
            result.get(node.depth).set(node.column, Integer.toString(node.node.val));
        }
        return result;
    }

    private List<List<String>> init(int nRows, int nColumns) {
        final var result = new ArrayList<List<String>>(nRows);
        for (var i = 0; i < nRows; i++) {
            result.add(new ArrayList<>(Collections.nCopies(nColumns, "")));
        }
        return result;
    }

    private int height(TreeNode root) {
        var result = 0;
        for (final var child : children(root)) {
            result = Math.max(result, height(child) + 1);
        }
        return result;
    }

    private List<TreeNode> children(TreeNode node) {
        return Stream.of(node.left, node.right)
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
    }

    private List<LeveledNode> bfs(TreeNode root, int height, int nColumns) {
        final var result = new ArrayList<LeveledNode>();
        final var queue = new ArrayDeque<LeveledNode>();
        queue.offer(new LeveledNode(root, 0, 0, nColumns / 2));
        while (!queue.isEmpty()) {
            final var p = queue.poll();
            result.add(p);
            if (p.node.left != null  || p.node.right != null) {
                final var shift = (int) Math.pow(2, height - p.depth - 1);
                if (p.node.left != null) {
                    queue.add(new LeveledNode(p.node.left, 0, p.depth + 1, p.column - shift));
                }
                if (p.node.right != null) {
                    queue.add(new LeveledNode(p.node.right, 0, p.depth + 1, p.column + shift));
                }
            }
        }
        return result;
    }

    private static class LeveledNode {

        public final TreeNode node;
        public final int side;
        public final int depth;
        public final int column;

        public LeveledNode(TreeNode node, int side, int depth, int column) {
            this.node = node;
            this.side = side;
            this.depth = depth;
            this.column = column;
        }
    }
}
