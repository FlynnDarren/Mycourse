package com.school.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public  class CompareDate{

    public static String CompareCoursrDate(String date1, String date2){
        Date mydate1 = null;
        Date mydate2 =null;
        String type = null;

        String[] split = date1.split(" ");

        if(split.length==2){

            try {
                mydate1 = new SimpleDateFormat("HH:mm:ss").parse(split[1]);//arrdate
                mydate2 = new SimpleDateFormat("HH:mm:ss").parse(date2);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }else {
            try {
                mydate1 = new SimpleDateFormat("HH:mm:ss").parse(date1);//arrdate
                mydate2 = new SimpleDateFormat("HH:mm:ss").parse(date2);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }


        if (mydate1.compareTo(mydate2) < 0) {
            type = "已签到";
        } else {
            type = "迟到";
        }
        return type;
    }

}
