package lotto.util;

import lotto.ErrorMessage;

public class StringParser {

    public static int toInteger(String input) {
        if (input == null) {
            throw new IllegalArgumentException(ErrorMessage.UNKNOWN_ERROR.getMessage());
        }
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_INTEGER.getMessage());
        }
    }

    public static String numberFormat(int number) {
        return String.format("%,d", number);
    }

    public static String numberFormat(double number) {
        return String.format("%,.1f", number);
    }
}
