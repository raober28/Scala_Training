package org.time;


import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by rahul on 13/11/16.
 */
public class TimePrac {

    public static void main(String[] args) {
        Date date = new Date();
        GregorianCalendar cal = new GregorianCalendar();

        System.out.println("args = [" + date + "]");
        System.out.println("args = [" + cal + "]");
        System.out.println("moth = [" + cal.get(Calendar.MONTH) + "]");
        System.out.println("day_Of_Month = [" + cal.get(Calendar.DAY_OF_MONTH) + "]");
        System.out.println("day_Of_Week = [" + cal.get(Calendar.DAY_OF_WEEK) + "]");
        System.out.println("args = [" + cal.getTimeZone() + "]");
        System.out.println("args = [" + TimeZone.getDefault() + "]");
        System.out.println("zone = [" + TimeZone.getTimeZone("Europe/Copenhagen") + "]");
        System.out.println("args = [" + cal + "]");
        System.out.println("args = [" + cal + "]");
        System.out.println("args = [" + cal + "]");
        System.out.println("args = [" + cal + "]");

        String pattern = "EEEEE-MMMMM-yyyy hh:mm:ss.SSSZ";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, new Locale("hi", "IND"));
        String date2 = simpleDateFormat.format(new Date());
        System.out.println("hindi = [" + date2 + "]");



    }
}
