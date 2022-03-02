package com.Kosmos.Model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Deklinacja
{
    private final static String DeklinacjaModel = "^(-?(([0-8]?[0-9])|(90))):(([0-5]?[0-9])([,.]\\d*)?):(([0-5]?[0-9])([,.]\\d*)?)$";

    public int stopnie;
    public float minuty;
    public float sekundy;

    public Deklinacja(){}

    public Deklinacja(String deklinacja)
    {
        Pattern pat = Pattern.compile(DeklinacjaModel);
        Matcher m = pat.matcher(deklinacja);

        if(m.matches()){

            stopnie=Integer.parseInt(m.group(1));
            minuty=Float.parseFloat(m.group(5));
            sekundy=Float.parseFloat(m.group(8));
        }
        else{
            stopnie=0;
            minuty=0;
            sekundy=0;
        }
    }

    @Override
    public String toString()
    {
        return stopnie+"º"+minuty+"'"+sekundy+"\" δ";
    }
}