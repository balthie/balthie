package org.balthie.demo.jdk.text;

import java.text.MessageFormat;
import java.util.Date;

public class MessageFormatTest
{
    public static void main(String[] args)
    {
        System.out.println(MessageFormat.format("{0}   \n  {1, number, ##}\n  {2,date} \n {3,time}"  , 12345612, 132133, new Date(), new Date()));
        
        System.out.println(MessageFormat.format("{0, time, HH:mm:ss:ms}", new Date()));
    }
}
