package org.time;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by rahul on 20/7/16.
 */
public class DateDiff {

    public static void main(String[] args) {
        String dateStart = "01/14/2012 09:29:58";
        String dateEnd = "01/14/2013   10:29:58";

        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

        Date d1;
        Date d2;

        try {
            d1 = format.parse(dateStart);
            d2 = format.parse(dateEnd);
            System.out.println(d1);
            System.out.println(d2);
           /* System.out.println(d1.getTime());
            System.out.println(d2.getTime());*/


            long diff = d2.getTime() - d1.getTime();

            System.out.println(diff);


            long diffSeconds = diff / 1000 % 60;
            long diffMinutes = diff / (60 * 1000) % 60;
            long diffHours = diff / (60 * 60 * 1000) % 24;
            long diffDays = diff / (24 * 60 * 60 * 1000);

            System.out.print(diffDays + " days, ");
            System.out.print(diffHours + " hours, ");
            System.out.print(diffMinutes + " minutes, ");
            System.out.print(diffSeconds + " seconds.");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

