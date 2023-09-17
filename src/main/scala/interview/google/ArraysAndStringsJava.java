package interview.google;

public class ArraysAndStringsJava {

    public static void main(String[] args) {

    }

    public boolean backspaceCompare(String s, String t) {
        return removeBackspace(s).equals(removeBackspace(t));
    }

    private String removeBackspace(String s) {
        final var acc = new StringBuilder();
        for (var i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '#' && acc.length() > 0) {
                acc.deleteCharAt(acc.length() - 1);
            } else if (s.charAt(i) != '#') {
                acc.append(s.charAt(i));
            }
        }
        return acc.toString();
    }


}
