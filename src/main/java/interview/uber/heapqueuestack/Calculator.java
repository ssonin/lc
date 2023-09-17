package interview.uber.heapqueuestack;

/**
 * Basic Calculator
 */
public class Calculator {

    public static void main(String[] args) {
        final var app = new Calculator();
        System.out.println(app.calculate("(1+(4+5+2)-3)+(6+8)"));
        System.out.println(app.calculate("(1+1)"));
        System.out.println(app.calculate("  2-1 + 2 "));
        System.out.println(app.calculate("1-(     -2)"));
    }

    public int calculate(String s) {
        final var tokens = s.replaceAll("\\s+", "")
            .split("(?<=\\d)(?!\\d)|(?<!\\d)(?=\\d)|(?<=\\()|(?=\\()|(?<=\\))|(?=\\))");
        return eval(tokens, 0).acc;
    }

    private Interim eval(String[] tokens, int idx) {
        var result = 0;
        var i = idx;
        var sign = 1;
        OUTER:
        while (i < tokens.length) {
            switch (tokens[i]) {
                case "+":
                    sign = 1;
                    i++;
                    break;
                case "-":
                    sign = -1;
                    i++;
                    break;
                case "(":
                    final var interim = eval(tokens, i + 1);
                    result += sign * interim.acc;
                    i = interim.idx + 1;
                    break;
                case ")":
                    break OUTER;
                default:
                    result += sign * Integer.parseInt(tokens[i++]);
            }
        }
        return new Interim(i, result);
    }

    private static class Interim {

        public final int idx;
        public final int acc;

        public Interim(int idx, int acc) {
            this.idx = idx;
            this.acc = acc;
        }
    }
}
