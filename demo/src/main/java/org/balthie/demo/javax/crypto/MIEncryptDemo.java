package org.balthie.demo.javax.crypto;

import java.security.Key;
import java.util.Base64;
import org.apache.commons.lang.RandomStringUtils;

public class MIEncryptDemo
{
    static String appId = "10002";
    
    static String appKey = "C5AmeIlu4JWiJOfO";
    
    public static void main(String[] args)
    {
        String nonce = RandomStringUtils.random(16);
        String base64Nonce = Base64.getEncoder().encodeToString(nonce.getBytes());
        String security = SHA.SHA256(appKey + nonce);
        String base64security = Base64.getEncoder().encodeToString(security.getBytes());
        
        AESCoder coder = new AESCoder();
        byte[] key = coder.initSecretKey();
        System.out.println("key：" + coder.showByteArray(key));
        Key k = coder.toKey(key);
        String data = "AES数据";
        System.out.println("加密前数据: string:" + data);
        System.out.println("加密前数据: byte[]:" + AESCoder.showByteArray(data.getBytes()));
        String base64Data = Base64.getEncoder().encodeToString(AESCoder.showByteArray(data.getBytes()).getBytes());
    }
}
