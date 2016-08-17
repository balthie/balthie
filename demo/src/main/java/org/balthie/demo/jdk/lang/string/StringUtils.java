package org.balthie.demo.jdk.lang.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Pattern;
import org.apache.commons.collections4.CollectionUtils;

public class StringUtils
{
    /**
     * @IS@列分隔符
     */
    public static final String COLUMN_SEPERATE_STR = "@IS@";
    
    /**
     * @RS@行分隔符
     */
    public final static String ROW_SEPERATE_STR = "@RS@";
    
    public static final Pattern FLOATING_PATTERN = Pattern.compile("[+-]?\\d*(.\\d+)?");
    
    public static final Pattern MOBILE_PHONE_PATTERN = Pattern.compile("^(1)\\d{10}$");
    
    public static final Pattern IP4_PATTERN = Pattern.compile(
            "^(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])$");
            
    /**
     * 使用指定分隔符连接字符串数组
     * 
     * @author : balthie@126.com
     * @createtime ： 2013-12-30 下午2:37:16
     * @description 使用指定分隔符连接字符串数组
     * @param originalStrArr
     * @param delimiter
     * @return
     */
    public static StringBuilder concat(String[] originalStrArr, String delimiter)
    {
        StringBuilder sb = null;
        if(originalStrArr != null && originalStrArr.length > 0)
        {
            sb = new StringBuilder();
            
            for(int i = 0; i < originalStrArr.length; i++)
            {
                sb.append(originalStrArr[i]);
                if(i < (originalStrArr.length - 1))
                {
                    sb.append(delimiter);
                }
            }
        }
        return sb;
    }
    
    public static StringBuilder concat(List<String> list, String delimiter)
    {
        StringBuilder sb = null;
        if(!CollectionUtils.isEmpty(list))
        {
            sb = new StringBuilder();
            for(int i = 0; i < list.size(); i++)
            {
                sb.append(list.get(i));
                if(i < (list.size() - 1))
                {
                    sb.append(delimiter);
                }
            }
        }
        return sb;
    }
    
    /**
     * 根据指定的分隔符将字符串拆分成字符串类型集合
     * 
     * @author lisheng
     * @date 2013-7-12 下午5:37:27
     * @update by lemon + 10050 2014-7-21 修改实现
     * @return List<String>
     * @param src
     *            要操作的字符串
     * @param delimiter
     *            分隔符
     */
    public static List<String> getStringList(String src, String delimiter)
    {
        List<String> list = new ArrayList<String>();
        String[] arr = null;
        if(StringUtils.isNotBlank(src) && StringUtils.isNotBlank(delimiter))
        {
            arr = src.split(delimiter);
            list = Arrays.asList(arr);
        }
        return list;
    }
    
    /**
     * @author : balthie@126.com
     * @createtime ： 2014-7-22 下午2:23:31
     * @description 根据指定的分隔符将字符串拆分成字符串类型集合，过滤重复
     * @since version 初始于版本 V2.5.2
     * @param src
     * @param delimiter
     * @return
     */
    public static Set<String> getStringSet(String src, String delimiter)
    {
        Set<String> set = new TreeSet<String>();
        List<String> list = null;
        String[] arr = null;
        if(StringUtils.isNotBlank(src) && StringUtils.isNotBlank(delimiter))
        {
            arr = src.split(delimiter);
            list = Arrays.asList(arr);
            
            set.addAll(list);
        }
        return set;
    }
    
    private static boolean isNotBlank(String src)
    {
        return org.apache.commons.lang.StringUtils.isNotBlank(src);
    }

    /**
     * 根据指定的分隔符将字符串拆分成字符串类型数组
     * 
     * @author lisheng
     * @date 2013-7-12 下午5:38:36
     * @update by lemon + 10050 2014-7-21 修改实现
     * @return String[]
     * @param src
     *            要操作的字符串
     * @param delimiter
     *            分隔符
     */
    public static String[] getStringArray(String src, String delimiter)
    {
        String[] arr = null;
        if(StringUtils.isNotBlank(src) && StringUtils.isNotBlank(delimiter))
        {
            arr = src.split(delimiter);
        }
        return arr;
    }
    
    public static boolean isFloating(String value)
    {
        if(StringUtils.isEmpty(value)) return false;
        return FLOATING_PATTERN.matcher(value).matches();
    }
    
    public static boolean isPhoneNum(String value)
    {
        if(StringUtils.isEmpty(value)) return false;
        return MOBILE_PHONE_PATTERN.matcher(value).matches();
    }
    
    private static boolean isEmpty(String src)
    {
        return org.apache.commons.lang.StringUtils.isEmpty(src);
    }

    public final static boolean isNumeric(String s)
    {
        if(s != null && !"".equals(s.trim()))
            return s.matches("^[0-9]*$");
        else return false;
    }
    
    public final static boolean isIP(String s)
    {
        if(StringUtils.isEmpty(s)) return false;
        return IP4_PATTERN.matcher(s).matches();
    }
    
    public static int parseInt(String input, int defVal)
    {
        if(isNumeric(input))
            return Integer.parseInt(input);
        else return defVal;
    }
    
    /**
     * @author <a href="mailto:zhangxp@viomi.net">zhangxp@viomi.net</a>
     * @date 2016/7/6 9:34
     * @desc 验证sn格式，但是不校验skuid是否存在
     *      
     */
    public static boolean isSn(String sn)
    {
        boolean flag = false;
        if(StringUtils.isNotBlank(sn) && sn.split("/")[0].matches("^[0-9]*$"))
        {
            flag = true;
        }
        return flag;
    }
    /**
     * @author <a href="mailto:zhangxp@viomi.net">zhangxp@viomi.net</a>
     * @date 2016/9/20 19:40
     * @desc 判定是否为emoji表情
     *
     */
    public static boolean isEmojiCharacter(int codePoint) {
        return (codePoint >= 0x2600 && codePoint <= 0x27BF) // 杂项符号与符号字体
                || codePoint == 0x303D
                || codePoint == 0x2049
                || codePoint == 0x203C
                || (codePoint >= 0x2000 && codePoint <= 0x200F)//
                || (codePoint >= 0x2028 && codePoint <= 0x202F)//
                || codePoint == 0x205F //
                || (codePoint >= 0x2065 && codePoint <= 0x206F)//
                /* 标点符号占用区域 */
                || (codePoint >= 0x2100 && codePoint <= 0x214F)// 字母符号
                || (codePoint >= 0x2300 && codePoint <= 0x23FF)// 各种技术符号
                || (codePoint >= 0x2B00 && codePoint <= 0x2BFF)// 箭头A
                || (codePoint >= 0x2900 && codePoint <= 0x297F)// 箭头B
                || (codePoint >= 0x3200 && codePoint <= 0x32FF)// 中文符号
                || (codePoint >= 0xD800 && codePoint <= 0xDFFF)// 高低位替代符保留区域
                || (codePoint >= 0xE000 && codePoint <= 0xF8FF)// 私有保留区域
                || (codePoint >= 0xFE00 && codePoint <= 0xFE0F)// 变异选择器
                || codePoint >= 0x10000; // Plane在第二平面以上的，char都不可以存，全部都转
    }
    /**
     * @author <a href="mailto:zhangxp@viomi.net">zhangxp@viomi.net</a>
     * @date 2016/9/8 11:43
     * @desc 过滤emoji表情
     *
     */
    public static String replaceEmoji(String src){
        if (src == null) {
            return null;
        }
        int cpCount = src.codePointCount(0, src.length());
        int firCodeIndex = src.offsetByCodePoints(0, 0);
        int lstCodeIndex = src.offsetByCodePoints(0, cpCount - 1);
        StringBuilder sb = new StringBuilder(src.length());
        for (int index = firCodeIndex; index <= lstCodeIndex;) {
            int codepoint = src.codePointAt(index);
            if (!isEmojiCharacter(codepoint)) {
                //System.err.println("codepoint:" + Integer.toHexString(codepoint));
                sb.append((char) codepoint);
            }
            index += ((Character.isSupplementaryCodePoint(codepoint)) ? 2 : 1);

        }
        return sb.toString();
    }
    
    public static String getStringValue(Object o)
    {
        return o != null ? String.valueOf(o) : "";
    }
    
    public static void main(String[] args)
    {
        String text = "This is a smiley \uD83C\uDFA6 face\uD83C\uDFA6 bb\uD860\uDE07 \uD860\uDEE2 \uD863\uDCCA \uD863\uDCCD \uD863\uDCD2 \uD867\uDD98 ";
        //System.out.println(isPhoneNum("13800138000"));
        String text2="112abc111abd";
        System.out.println(replaceEmoji(text));
    }
}
