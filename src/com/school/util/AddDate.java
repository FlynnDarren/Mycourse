package com.school.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddDate {
    public static String addDateMinut(String day, int hour){
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        Date date = null;
        try {
            date = format.parse(day);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (date == null)
            return "";
        System.out.println("front:" + format.format(date)); //��ʾ���������
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR, hour);// 24Сʱ��
        date = cal.getTime();
        System.out.println("after:" + format.format(date));  //��ʾ���º������
        cal = null;
        return format.format(date);
    }

}
