/**
 * @author��balthie + 10050
 * @createtime �� 2014-5-22 ����10:14:12
 * @description TODO һ�仰����
 */
package org.balthie.demo.jdk.security;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * @author��balthie + 10050
 * @createtime �� 2014-5-22 ����10:14:12
 * @description TODO һ�仰����
 */
public class SecureRandomTest
{
    public static void main(String[] args) throws NoSuchAlgorithmException
    {
        SecureRandom sr = SecureRandom.getInstance("DSA");
        
        for(int i=0;i<10;i++)
        {
            System.out.println(sr.nextDouble());
        }
    }
}
