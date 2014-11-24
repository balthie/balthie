/**
 * @author��balthie + 10050
 * @createtime �� 2014-5-22 ����5:46:51
 * @description TODO һ�仰����
 */
package org.balthie.demo.jdk.security.key;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

/**
 * @author��balthie + 10050
 * @createtime �� 2014-5-22 ����5:46:51
 * @description TODO һ�仰����
 */
public class KeyPairGeneratorTest
{
    public static void main(String[] args) throws NoSuchAlgorithmException
    {
        KeyPair kp = getAKeyPair("DSA");
        System.out.println(kp.getPublic().toString());
        System.out.println(kp.getPrivate().toString());
    }
    
    public static KeyPair getAKeyPair(String algorithm) throws NoSuchAlgorithmException
    {
        KeyPairGenerator kpg = KeyPairGenerator.getInstance(algorithm);
        
        kpg.initialize(512);
        
        KeyPair kp = kpg.genKeyPair();
        return kp;
    }
}
