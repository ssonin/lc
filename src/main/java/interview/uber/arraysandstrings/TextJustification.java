package interview.uber.arraysandstrings;

import java.util.ArrayList;
import java.util.List;

public class TextJustification {

    public static void main(String[] args) {
        final var app = new TextJustification();
        app.fullJustify(new String[]{"Science","is","what","we","understand","well","enough","to","explain",
                                     "to","a","computer.","Art","is","everything","else","we","do"}, 20)
            .forEach(System.out::println);
    }

    /**
     * Text Justification
     */
    public List<String> fullJustify(String[] words, int maxWidth) {
        final var lines = new ArrayList<Line>();
        lines.add(new Line());
        var lastLine = lines.get(0);
        for (final var word : words) {
            if (lastLine.tryAdd(word) <= maxWidth) {
                lastLine.add(word);
            } else {
                lastLine = new Line(word);
                lines.add(lastLine);
            }
        }

        var j = 0;
        final var acc = new ArrayList<String>(lines.size());
        while (j < lines.size()) {
            final var line = lines.get(j);
            acc.add(line.pad(maxWidth, j == lines.size() - 1));
            j++;
        }
        return acc;
    }

    private static class Line {

        private int width;
        private int numSpaces;
        private final List<String> words = new ArrayList<>();

        public Line() {}

        public Line(String word) {
            this.words.add(word);
            this.width = word.length();
        }

        public void add(String word) {
            width += word.length();
            numSpaces += (words.isEmpty()) ? 0 : 1;
            words.add(word);
        }

        public int tryAdd(String word) {
            return width + numSpaces + (words.isEmpty() ? word.length() : word.length() + 1);
        }

        public String pad(int maxWidth, boolean isLastLine) {
            final var paddings = paddings(maxWidth, isLastLine);
            final String result;
            if (paddings.length == 0) {
                result = words.get(0);
            } else {
                final var acc = new StringBuilder();
                var i = 0;
                while(i < paddings.length) {
                    acc.append(words.get(i));
                    for (var j = 0; j < paddings[i]; j++){
                        acc.append(" ");
                    }
                    i++;
                }
                if (i < words.size()) {
                    acc.append(words.get(i));
                }
                result = acc.toString();
            }
            return result;
        }

        private boolean extraPaddingNeeded(int maxWidth, boolean isLastLine) {
            return (words.size() == 1 && width < maxWidth)
                || (isLastLine && width + numSpaces < maxWidth);
        }

        private int[] paddings(int maxWidth, boolean isLastLine) {
            final var numPaddings = extraPaddingNeeded(maxWidth, isLastLine)
                                    ? numSpaces + 1
                                    : numSpaces;
            final var spaceLength = maxWidth - width;
            final var paddings = new int[numPaddings];
            if (numPaddings == 0) return paddings;
            var i = 0;
            if (isLastLine) {
                while (i < numPaddings - 1) {
                    paddings[i++] = 1;
                }
                paddings[numPaddings - 1] = spaceLength - (numPaddings - 1);
            } else {
                final var evenlyDistributed = spaceLength / numPaddings;
                final var leftover = spaceLength % numPaddings;
                while (i < numPaddings) {
                    paddings[i] = evenlyDistributed;
                    if (i < leftover) {
                        paddings[i]++;
                    }
                    i++;
                }
            }
            return paddings;
        }
    }
}
