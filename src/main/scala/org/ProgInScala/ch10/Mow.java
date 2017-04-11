package org.ProgInScala.ch10;

/**
 * Created by rahul on 14/3/17.
 */
public class Mow {

    int i[] = {0};
    public static void main(String[] args) {
        int i[] = {1};
        change(i);
        System.out.println(i[0]);

        String s = "dolly";
        String t = s.concat("Hello");

        System.out.println(s + ":" + t);

        check();
    }

    public static  void change(int i[]){
        int j[] = {2};
        i=j;
    }


    public static void check() {
        StringBuffer sb1 = new StringBuffer("amit");
        StringBuffer sb2 = new StringBuffer("amit");
        String ss1 = "amit";

        System.out.println(sb1 == sb2);
        System.out.println(sb1.equals(sb2));
        System.out.println(sb1.equals(ss1));
        System.out.println("Toddar".substring(3));

    }
}
