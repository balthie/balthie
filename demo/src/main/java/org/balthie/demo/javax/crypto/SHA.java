
package org.balthie.demo.javax.crypto;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 
 * @author：balthie@126.com
 * @createtime ： 2016年11月21日 上午11:44:38
 *             安全哈希算法（Secure Hash Algorithm）主要适用于数字签名标准（Digital Signature Standard
 *             DSS）里面定义的数字签名算法（Digital Signature Algorithm
 *             DSA）。对于长度小于2^64位的消息，SHA1会产生一个160位的消息摘要。该算法经过加密专家多年来的发展和改进已日益完善，并被广泛使用。该算法的思想是接收一段明文，
 *             然后以一种不可逆的方式将它转换成一段（通常更小）密文，也可以简单的理解为取一串输入码（称为预映射或信息），并把它们转化为长度较短、位数固定的输出序列即散列值（
 *             也称为信息摘要或信息认证代码）的过程。
 */
public class SHA
{
    /**
     * 传入文本内容，返回 SHA-256 串
     * 
     * @param strText
     * @return
     */
    public static String SHA256(final String strText)
    {
        return encrypt(strText, "SHA-256");
    }
    
    /**
     * 传入文本内容，返回 SHA-512 串
     * 
     * @param strText
     * @return
     */
    public static String SHA512(final String strText)
    {
        return encrypt(strText, "SHA-512");
    }
    
    /**
     * 字符串 SHA 加密
     * 
     * @param strSourceText
     * @return
     */
    private static String encrypt(final String strText, final String strType)
    {
        // 返回值
        String strResult = null;
        
        // 是否是有效字符串
        if(strText != null && strText.length() > 0)
        {
            try
            {
                // SHA 加密开始
                // 创建加密对象 并傳入加密類型
                MessageDigest messageDigest = MessageDigest.getInstance(strType);
                // 传入要加密的字符串
                messageDigest.update(strText.getBytes());
                // 得到 byte 類型结果
                byte byteBuffer[] = messageDigest.digest();
                
                // 將 byte 轉換爲 string
                StringBuffer strHexString = new StringBuffer();
                // 遍歷 byte buffer
                for(int i = 0; i < byteBuffer.length; i++)
                {
                    String hex = Integer.toHexString(0xff & byteBuffer[i]);
                    if(hex.length() == 1)
                    {
                        strHexString.append('0');
                    }
                    strHexString.append(hex);
                }
                // 得到返回結果
                strResult = strHexString.toString();
            }
            catch (NoSuchAlgorithmException e)
            {
                e.printStackTrace();
            }
        }
        
        return strResult;
    }
}
