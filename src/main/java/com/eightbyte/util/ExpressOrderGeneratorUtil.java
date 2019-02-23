package com.eightbyte.util;

import java.util.Date;
import java.util.Random;

/**
 * @author yanghaoran@ehomepay.com.cn
 * @createDate 2019/2/23
 * @description 快递订单生产器类
 */
public class ExpressOrderGeneratorUtil {

    private static final String ORDER_PREFIX = "ebyte";

    private static final Integer DEFAULT_LENGTH = 32;

    private static Random random = new Random();

    private static String[] alphabets = {"Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P", "A", "S", "D",
            "F", "G", "H", "J", "K", "L", "Z", "X", "C", "V", "B", "N", "M",
            "q", "w", "e", "r", "t", "y", "u", "i", "o", "p", "a", "s", "d", "f",
            "g", "h", "j", "k", "l", "z", "x", "c", "v", "b", "n", "m"};

    private static String[] number = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};

    private ExpressOrderGeneratorUtil() {

    }

    public static String generateOrderNo() {
        return generateFixLengthOrderNo(DEFAULT_LENGTH);
    }

    public static String generateFixLengthOrderNo(int fixLength) {
        if (fixLength <= 14&&fixLength>50) {
            throw new RuntimeException();
        }
        StringBuilder sb = new StringBuilder();
        sb.append(ORDER_PREFIX);
        String dateStr = DateUtil.formatDate2Str(new Date());
        sb.append(dateStr);
        int leftLength = fixLength - dateStr.length();
        if (leftLength > 0) {
            int alphaLength = random.nextInt(leftLength);
            for (int i = 0; i < alphaLength; i++) {
                sb.append(alphabets[random.nextInt(alphabets.length)]);
            }
            int numLength = leftLength - alphaLength;
            if (numLength > 0) {
                for (int j = 0; j < numLength; j++) {
                    sb.append(number[random.nextInt(number.length)]);
                }
            }

        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String s = generateFixLengthOrderNo(32);
        System.out.println(s + "\t\t\t" + s.length());
    }
}
