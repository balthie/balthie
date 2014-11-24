/**
 * @author��balthie + 10050
 * @createtime �� 2014-5-23 ����10:29:24
 * @description TODO һ�仰����
 */
package org.balthie.demo.jdk.security.sign;

import java.io.IOException;
import java.security.CodeSigner;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.security.SignatureException;
import java.security.SignedObject;

/**
 * @author��balthie + 10050
 * @createtime �� 2014-5-23 ����10:29:24
 * @description     
 */
public class SignatureTest
{
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException, IOException
    {
        byte[] origanlData = " some important thing need signature".getBytes();
        
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("DSA");
        
        kpg.initialize(512);
        
        KeyPair kp = kpg.genKeyPair();
        
        Signature signature = Signature.getInstance(kpg.getAlgorithm());
        
        //ʹ��˽Կ��������ǩ��
        SignedObject so = new SignedObject(origanlData, kp.getPrivate(), signature);
        byte[] sign = so.getSignature();
        for(byte b : sign)
        {
            System.out.print(b);
        }
        System.out.println();
        
        // ˽Կ���ǩ������Կ������֤
        boolean isValid = so.verify(kp.getPublic(), signature);
        System.out.println(isValid);
        
    }
}
