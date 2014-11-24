/**
 * @author��balthie + 10050
 * @createtime �� 2014-5-22 ����3:42:33
 * @description TODO һ�仰����
 */
package org.balthie.demo.jdk.security.digest;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author��balthie + 10050
 * @createtime �� 2014-5-22 ����3:42:33
 * @description TODO һ�仰����
 */
public class InputStreamMessageDigestTest
{
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException
    {
        byte[] originalMessage = " like some important message in stream ".getBytes(); 
                
        java.security.MessageDigest digester = MessageDigest.getInstance("MD5");
                
        DigestInputStream dis = new DigestInputStream(new ByteArrayInputStream(originalMessage), digester);
        
        dis.read(originalMessage);
        
        // �ڶ�ȡ����ͬʱ��ȡ��ժҪ
        byte[] output = dis.getMessageDigest().digest();
        
        System.out.println(" digestMessage  = " + output);
        
        dis.close();
    }
}
