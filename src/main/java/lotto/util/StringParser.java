package lotto.util;

public class StringParser {

    public static int toInteger(String input, String domain) {
        if (input == null) {
            throw new IllegalArgumentException("[ERROR] 알 수 없는 오류가 발생했습니다");
        }
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] " + domain + "은/는 정수로 입력해주세요");
        }
    }

    public static String numberFormat(int number) {
        return String.format("%,d", number);
    }

    public static String numberFormat(double number) { // todo 둘째자리 반올림 안될걸
        return String.format("%,.1f", number);
    }
}
