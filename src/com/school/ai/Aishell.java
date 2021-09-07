package com.school.ai;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.*;

public class Aishell {

    public static Map<Integer,String> stu_map=new TreeMap<>();
    //Ñ§ºÅ
    public static String num=null;
    private static String back_date=null;
    private static String date=null;
    private static String over_date=null;

    public static String getDate() {
        return date;
    }

    public static void setDate(String date) {
        Aishell.date = date;
    }

    public static String getOver_date() {
        return over_date;
    }

    public static void setOver_date(String over_date) {
        Aishell.over_date = over_date;
    }

    public static String getBack_date() {
        return back_date;
    }


    public void FaceRec() throws IOException, InterruptedException {
        ArrayList<String> reset = new ArrayList<>();
        String bashCommand = "/home/huoz/ai_face_recognition/aishell.sh";
        Process process = Runtime.getRuntime().exec(bashCommand);
        int status = process.waitFor();  //¢Û
        String line = null;
        int j=0;
        InputStreamReader stdISR = new InputStreamReader(process.getInputStream());
        BufferedReader stdBR = new BufferedReader(stdISR);
        StringBuilder sb = new StringBuilder();
        String tmp=null;
        while ((line = stdBR.readLine()) != null) {
            System.out.println("STD line:" + line);
            char[] chars = line.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                if(chars[i]>='0'&&chars[i]<='9'){
                    sb.append(chars[i]);
                }
            }
            String s = sb.toString();
            System.out.println("@@@@"+s);
            j++;
            reset.add(s);
            if(j>60){
                tmp=sb.toString();
                break;
            }
            sb.delete(0,sb.length());
        }

        InputStreamReader errISR = new InputStreamReader(process.getErrorStream());
        BufferedReader errBR = new BufferedReader(errISR);
        while ((line = errBR.readLine()) != null) {
            System.out.println("ERR line:" + line);
        }

        stdISR.close();
        stdBR.close();


        int t1=0;
        int t2=0;
        int t3=0;
        List<String> list1 = reset.subList(0, 20);
        List<String> list2 = reset.subList(20, 40);
        List<String> list3 = reset.subList(40, 60);

        for (String s : list1) {
            if(s==list1.get(5)){
                t1++;
                stu_map.put(t1,s);
            }
        }

        for (String s : list2) {
            if(s==list2.get(5)){
                t2++;
                stu_map.put(t2,s);
            }
        }

        for (String s : list3) {
            if(s==list3.get(5)){
                t3++;
                stu_map.put(t3,s);
            }
        }


        int a=0;
        for (Map.Entry<Integer, String> entry : stu_map.entrySet()) {
            a++;
            if(a==stu_map.entrySet().size()){
                num=entry.getValue();
            }

        }

        System.out.println("####"+num);
        Aishell.num=num;

        Date date = new Date();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String format = dateFormat.format(date);
        System.out.println(format);

        back_date=format;

        if (process != null) {
            process.destroy();
        }

    }
}
