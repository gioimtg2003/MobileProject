package com.example.myapplication;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Utility {
    public static String formatMoney(int price){
        DecimalFormat formatter = new DecimalFormat("#,###");
        return formatter.format(price);
    }
    public static String formatDate(String date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        try {
            Date date1 = simpleDateFormat.parse(date);
            SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
            return simpleDateFormat1.format(date1);
        }catch (Exception e){
            return "Error";
        }

    }
}
