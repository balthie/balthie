/**
 * @author��balthie + 10050
 * @createtime �� 2014-5-23 ����10:29:24
 * @description TODO һ�仰����
 */
package org.balthie.demo.jdk.security.sign;

import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.security.SignatureException;

/**
 * @author��balthie + 10050
 * @createtime �� 2014-5-23 ����10:29:24
 * @description     
 */
public class SignedObjectTest
{
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException
    {
        byte[] origanlData = " some important thing need signature".getBytes();
        
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("DSA");
        
        kpg.initialize(512);
        
        KeyPair kp = kpg.genKeyPair();
        
        Signature signature = Signature.getInstance(kpg.getAlgorithm());
        
        //ʹ��˽Կ��������ǩ��
        signature.initSign(kp.getPrivate());
        signature.update(origanlData);
        byte[] sign = signature.sign();
        for(byte b : sign)
        {
            System.out.print(b);
        }
        System.out.println();
        
        // ˽Կ���ǩ������Կ������֤
        signature.initVerify(kp.getPublic());
        signature.update(" some important thing need signature but modified ".getBytes());
        boolean isValid = signature.verify(sign);
        System.out.println(isValid);
    }
}
