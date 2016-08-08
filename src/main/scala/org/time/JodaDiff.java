package org.time;

import org.joda.time.*;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import javax.print.attribute.standard.DateTimeAtCompleted;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by rahul on 20/7/16.
 */
public class JodaDiff {

    public static void main(String[] args) {
        String dateStart = "01/14/2012 09:29:58";
        String dateEnd  =  "01/15/2012 10:31:48";

        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

        Date d1;
        Date d2;

        try {
            d1 = format.parse(dateStart);
            d2 = format.parse(dateEnd);

            DateTime dt1 = new DateTime(d1);
            DateTime dt2 = new DateTime(d2);

            System.out.println(Days.daysBetween(dt1, dt2).getDays() + " days");
            System.out.println(Hours.hoursBetween(dt1, dt2).getHours() + " hours");
            System.out.println(Minutes.minutesBetween(dt1, dt2).getMinutes() + " minutes");
            System.out.println(Seconds.secondsBetween(dt1, dt2).getSeconds() + " seconds");
            DateTimeFormatter formatJ = ISODateTimeFormat.dateTimeNoMillis();

            System.out.println(formatJ.parseDateTime("2013-04-26T12:00:00Z"));
            DateTime dateTime =  new DateTime(2000, 1 ,1, 1, 1, 0);
            System.out.println( dateTime.plusDays(90).toString("E MM/dd/yyyy HH:mm:ss.SSS"));

            DateTime dT = new DateTime(2000, 1, 1, 0, 0, 0, 0);
            dT = dT.plusDays(45).plusMonths(1).dayOfWeek().withMaximumValue();

            System.out.println(dT.plusDays(45).plusMonths(1).dayOfWeek().withMaximumValue().toDate());
            System.out.println(new DateTime());

            LocalDate now =  new LocalDate();
            System.out.println(now);
            System.out.println(now.monthOfYear().setCopy(11));
            System.out.println(now.monthOfYear().setCopy(11).dayOfMonth());
            System.out.println(now.monthOfYear().setCopy(11).dayOfMonth().withMinimumValue());
            System.out.println(now.monthOfYear().setCopy(11).dayOfMonth().withMinimumValue().plusDays(6));
            System.out.println(now.monthOfYear().setCopy(11).dayOfMonth().withMinimumValue().plusDays(6).dayOfWeek());
            System.out.println(now.monthOfYear().setCopy(11).dayOfMonth().withMinimumValue().plusDays(6).dayOfWeek().setCopy("Monday"));
            System.out.println(now.monthOfYear().setCopy(11).dayOfMonth().withMinimumValue().plusDays(6).dayOfWeek().setCopy("Monday").plusDays(1));
            DateTime ab = new DateTime();

            System.out.println(ab);
            System.out.println(ab.toString(ISODateTimeFormat.basicDateTime()));
            System.out.println(ab.toString(ISODateTimeFormat.basicDateTimeNoMillis()));
            System.out.println(ab.toString(ISODateTimeFormat.basicOrdinalDateTime()));
            System.out.println(ab.toString(ISODateTimeFormat.basicWeekDateTime()));
            System.out.println();
            System.out.println(ab.toString("MM/dd/yyyy hh:mm:ss.SSSa"));
            System.out.println(ab.toString("dd-MM-yyyy HH:mm:ss"));
            System.out.println(ab.toString("EEEE dd MMMM, yyyy HH:mm:ssa"));
            System.out.println(ab.toString("MM/dd/yyyy HH:mm ZZZZ"));
            System.out.println(ab.toString("MM/dd/yyyy HH:mm Z"));



            DateTimeFormatter formater =  ISODateTimeFormat.dateTimeNoMillis();

            DateTime time = formater.parseDateTime("2013-04-26T12:00:00Z");

            System.out.println(time);









        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}
