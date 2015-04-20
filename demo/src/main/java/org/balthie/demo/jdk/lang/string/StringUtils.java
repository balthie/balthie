package org.balthie.demo.jdk.lang.string;

import java.util.regex.Pattern;

public class StringUtils
{
    public static final Pattern FLOATING_PATTERN = Pattern.compile("[+-]?\\d*(.\\d+)?");
    
    public static boolean isFloating(String value)
    {
        return FLOATING_PATTERN.matcher(value).matches();
    }
    
    public static void main(String[] args)
    {
        System.out.println(StringUtils.isFloating(" 12.00 "));
        System.out.println(StringUtils.isFloating("-1a2 "));
        System.out.println(StringUtils.isFloating("-0.0.12"));
        System.out.println("---------");
        System.out.println(StringUtils.isFloating("+000.12"));
        System.out.println(StringUtils.isFloating("+1000.00"));
    }
}
