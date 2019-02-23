package com.eightbyte.util;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtil {

    private RegexUtil() {

    }

    public static boolean isIdNum(String idNum) {
        if (StringUtils.isBlank(idNum)) {
            return false;
        }
        //粗略的校验
        Pattern pattern = Pattern.compile("^(\\d{6})(19|20)(\\d{2})(1[0-2]|0[1-9])(0[1-9]|[1-2][0-9]|3[0-1])(\\d{3})(\\d|X|x)?$");
        Matcher matcher = pattern.matcher(idNum);
        return matcher.matches();

    }

    public static boolean isMobile(String mobileNo) {
        if (StringUtils.isBlank(mobileNo)) {
            return false;
        }
        Pattern regex = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(17[0-9])|(18[0,5-9]))\\d{8}$");
        Matcher m = regex.matcher(mobileNo);
        return m.matches();
    }
}
