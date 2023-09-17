package interview.uber.design;

import interview.uber.trees.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Codec {

    private static final String DELIMITER = ",";
    private static final String NULL = "null";

    public static void main(String[] args) {
        final var result = "null,1".split(DELIMITER);
        System.out.println(result.length);
        System.out.println(Arrays.toString(result));
        new Codec().run();
    }

    private void run() {
        System.out.println(Math.log10(3)/Math.log10(2));
        final var root = new TreeNode(1,
            new TreeNode(2, null, null),
            new TreeNode(3,
                new TreeNode(4,
                    null,
                    new TreeNode(6,
                        new TreeNode(8, null, null),
                        null)),
                new TreeNode(5,
                    null,
                    new TreeNode(7,
                        null,
                        null))));
        final var serialised = serialize(root);
        System.out.println(serialised);
        final var deserialised = deserialize(serialised);
        System.out.println(serialize(deserialised));
    }

    public String serialize(TreeNode root) {
        if (root == null) {
            return "";
        }
        final var bfsOrder = bfs(root, height(root));
        return bfsOrder
            .stream()
            .limit(rightmostNull(bfsOrder))
            .map(node -> node == null ? NULL : Integer.toString(node.val))
            .collect(Collectors.joining(DELIMITER));
    }

    private int rightmostNull(List<TreeNode> nodes) {
        var i = nodes.size() - 1;
        while (nodes.get(i) == null) {
            i--;
        }
        return i + 1;
    }

    private List<TreeNode> bfs(TreeNode root, int height) {
        final var result = new ArrayList<TreeNode>();
        final var queue = new ArrayDeque<LeveledNode>();
        queue.offer(new LeveledNode(root, 0));
        while (!queue.isEmpty()) {
            final var current = queue.poll();
            if (current.depth <= height) {
                result.add(current.node);
                if (current.node != null) {
                    queue.offer(new LeveledNode(current.node.left, current.depth + 1));
                    queue.offer(new LeveledNode(current.node.right, current.depth + 1));
                }
            }
        }
        return result;
    }

    private int height(TreeNode root) {
        return children(root)
            .mapToInt(ch -> height(ch) + 1)
            .max()
            .orElse(0);
    }

    private Stream<TreeNode> children(TreeNode root) {
        return Optional.ofNullable(root)
            .stream()
            .flatMap(r -> Stream.of(r.left, r.right))
            .filter(Objects::nonNull);
    }

    public TreeNode deserialize(String data) {
        if (data.isEmpty()) return null;
        final var nodes = Arrays.stream(data.split(DELIMITER))
            .map(parse())
            .collect(Collectors.toCollection(LinkedList::new));
        final var root = nodes.poll();
        var previousLevel = Arrays.asList(root);
        while (!nodes.isEmpty()) {
            final var currentLevel = new ArrayList<TreeNode>();
            for (final var node : previousLevel) {
                if (node == null) continue;
                node.left = nodes.poll();
                node.right = nodes.poll();
                currentLevel.add(node.left);
                currentLevel.add(node.right);
            }
            previousLevel = currentLevel;
        }
        return root;
    }

    private Function<String, TreeNode> parse() {
        return s -> (NULL.equals(s)) ? null : new TreeNode(Integer.parseInt(s));
    }

    private static class LeveledNode {

        public final TreeNode node;
        public final int depth;

        public LeveledNode(TreeNode node, int depth) {
            this.node = node;
            this.depth = depth;
        }
    }
}
