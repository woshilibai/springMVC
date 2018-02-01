
package com.ifunyoung.converter;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * 系统自定义日期类型转换器
 */

public class DateConverter implements Converter<String,Date>{

    public static final String DATE_YYYYMMDD = "yyyyMMdd";
    public static final String DATE_YYYY_MM_DD = "yyyy/MM/dd";
    public static final String DATE_YYYY_MM_DD_HH_MM_SS = "yyyy/MM/dd HH:mm:ss";
    public static final String DATE_YYYY_MM_DD_HH_MM_SS_SSS = "yyyy/MM/dd HH:mm:ss sss";

    public Date convert(String dateStr) {
        Date d = null;
        try {
            d = new SimpleDateFormat(DATE_YYYY_MM_DD).parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return d;
    }
}

