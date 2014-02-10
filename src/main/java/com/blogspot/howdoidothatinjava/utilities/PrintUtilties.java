package com.blogspot.howdoidothatinjava.utilities;

import java.util.function.Function;

public class PrintUtilties {
    
    public final static String under = "*";
    
    public static void println(){
        println(null);
    }
    
    public static void println(String out){
        println(out, null);
    }
    
    public static void println(String out, String under){
        if (null == out) {
            System.out.println();
        } else {
            System.out.println(out);
            if(null != under && !under.isEmpty()){
                StringBuffer sb = new StringBuffer();
                while (out.length() > 0){
                    sb.append(under);
                    out = out.substring(1);
                }
                System.out.println(sb.toString());
            }
        }
    }
}
