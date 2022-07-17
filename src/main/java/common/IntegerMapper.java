package common;

public class IntegerMapper {

    private static final String NULL_TEXT = "null";

    public static Integer[] mapToInteger(String[] texts) {
        Integer[] parsed = new Integer[texts.length];
        try {
            for (int i = 0; i < texts.length; i++) {
                parsed[i] = Integer.parseInt(texts[i]);
            }
            return parsed;
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Podane argumenty nie są liczbami całkowitoliczbowymi!");
        }
    }

    public static Integer[] mapToNullableInteger(String[] texts) {
        Integer[] parsed = new Integer[texts.length];
        try {
            for (int i = 0; i < texts.length; i++) {
                if (NULL_TEXT.equals(texts[i])) {
                    parsed[i] = null;
                } else {
                    parsed[i] = Integer.parseInt(texts[i]);
                }
            }
            return parsed;
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Podane argumenty nie są liczbami całkowitoliczbowymi!");
        }
    }
}
