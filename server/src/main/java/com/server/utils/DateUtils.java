package com.server.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");

    public static String format(Date date){
        return DATE_FORMAT.format(date);
    }

}
