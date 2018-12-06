package com.hofer.tij.common;

/**
 * @author hofer.bhf
 * created on 2018/05/19 5:28 PM
 */
public class PlayGround {
    public static void main(String[] args) {
        Boolean x = null;
        System.out.println(toHexString("\001"));
    }

    public static String toHexString(String s)
    {
        String str="";
        for (int i=0;i<s.length();i++)
        {
            int ch = (int)s.charAt(i);
            String s4 = Integer.toHexString(ch);
            str = str + s4;
        }
        return str;
    }
}
