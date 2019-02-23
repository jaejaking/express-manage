package com.eightbyte.util;

import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class DateUtil {

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

    private DateUtil() {

    }

    public static String formatDate2Str(Date date) {

        return sdf.format(date);
    }

    public static Date parseStr2Date(String dateStr) {
        Date parse = null;
        try {
            parse = sdf.parse(dateStr);
        } catch (ParseException e) {
            log.error("parseException", e);
        }

        return parse;
    }

    public static void main(String[] args) {
        System.out.println(formatDate2Str(new Date()).length());
    }

}
