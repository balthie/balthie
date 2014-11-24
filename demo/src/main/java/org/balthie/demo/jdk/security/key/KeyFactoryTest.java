/**
 * @author��balthie + 10050
 * @createtime �� 2014-5-22 ����5:57:50
 * @description TODO һ�仰����
 */
package org.balthie.demo.jdk.security.key;

import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;

/**
 * @author��balthie + 10050
 * @createtime �� 2014-5-22 ����5:57:50
 * @description     KeyFactory ��˽Կ�͹�Կ���ֽ����飬��ԭ����Կ����
 */
public class KeyFactoryTest
{
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException
    {
        KeyPair kp = KeyPairGeneratorTest.getAKeyPair("DSA");
        
        byte[] privateKeyBytes = kp.getPrivate().getEncoded();
        
        //����Կ�ֽ����飬��ȡ��Կ�淶
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
        
        java.security.KeyFactory kf = KeyFactory.getInstance("DSA");
        
        Key privateKey = kf.generatePrivate(pkcs8KeySpec);
        
        System.out.println(privateKey);
    }
}
