/**
 * @author��balthie + 10050
 * @createtime �� 2014-5-22 ����2:56:03
 * @description TODO һ�仰����
 */
package org.balthie.demo.jdk.security.digest;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author��balthie + 10050
 * @createtime �� 2014-5-22 ����2:56:03
 * @description TODO һ�仰����
 */
public class MessageDigestTest
{
    public static void main(String[] args) throws NoSuchAlgorithmException
    {
        byte[] originalMessage = "some thing important!".getBytes();
        
        System.out.println(" originalMessage  = " + originalMessage);
        
        java.security.MessageDigest digester = MessageDigest.getInstance("SHA");
        
        digester.update(originalMessage);
        
        byte[] output = digester.digest();
        System.out.println(" digestMessage  = " + output);
        
    }
}
