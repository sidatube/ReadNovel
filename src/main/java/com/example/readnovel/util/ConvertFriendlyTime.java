package com.example.readnovel.util;

import org.ocpsoft.prettytime.PrettyTime;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Locale;

public class ConvertFriendlyTime {
    public static String getFriendlyTime(Timestamp date){
        PrettyTime p = new PrettyTime(new Locale("vi"));
        return p.format(date).replace("cách đây ","")+" trước";
    }


}
